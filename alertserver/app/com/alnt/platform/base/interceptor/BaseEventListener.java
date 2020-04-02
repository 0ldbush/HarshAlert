package com.alnt.platform.base.interceptor;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.FlushModeType;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hibernate.event.spi.AbstractEvent;
import org.hibernate.event.spi.EventSource;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostUpdateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alnt.platform.base.domain.BaseSocialEntity;
import com.alnt.platform.base.domain.dto.BaseSocialDTO;
import com.alnt.platform.base.request.RequestDetails;
import com.alnt.platform.base.service.BaseLocalCachedServiceImpl;
import com.alnt.platform.core.classdef.domain.ClassDef;
import com.alnt.platform.core.classdef.domain.dto.ClassDefDTO;
import com.alnt.platform.core.classdef.service.ClassDefService;
import com.alnt.platform.core.timeline.domain.Timeline;
import com.alnt.platform.core.timeline.domain.TimelineChgs;
import com.alnt.platform.core.timeline.repository.TimelineRepository;

import jodd.bean.BeanUtil;
import play.inject.Injector;

public abstract class BaseEventListener {

	protected ClassDefService classDefService;
	protected TimelineRepository timelineRepository;

	protected Injector injector;

	final Logger Logger = LoggerFactory.getLogger(this.getClass());

//	Map<String, Timeline> timelineByClientSession = new HashMap<>();

	protected static final String OPERATION_CREATE = "CREATE";
	protected static final String OPERATION_UPDATE = "UPDATE";
	protected static final String OPERATION_DELETE = "DELETE";

	protected static final String FIELDNAME_BUSOBJCAT = "busObjCat";
	protected static final String FIELDNAME_CHANGEDBY = "changedBy";
	protected static final String FIELDNAME_BODY = "body";

	protected static final String CLASS_MESSAGELOG = "MessageLog";

	private static final String CLASSDEF_QUERY = "Select * from Class_Def where busObjCat = ?1";
	private static final String INSERT_TIMELINE_QUERY = "Insert into Timeline";

	protected boolean isValidForTimeline(String entiityName) {
		List<String> includes = Arrays.asList(new String[] { "policy", "rule", "ruleset", "mitigationrule",
				"responsecode", "riskrule", "messagelog" });
		if (includes.contains(entiityName.toLowerCase())) {
			return true;
		}
		return false;
	}

	protected void insertTimeline(EventSource session, Timeline timeline) {

	}

	protected List<String> skippable_fields = Arrays
			.asList(new String[] { "createdOn", "createdBy", "changedOn", "changedBy" });

	protected boolean shouldSkipField(String fieldName, String operation, Object object, Object oldObject) {
		if (skippable_fields.contains(fieldName)) {
			return true;
		}

		if (OPERATION_UPDATE.equals(operation)) {
			if (object == null && oldObject == null) {
				return true;
			} else if (object == null || oldObject == null) {
				return false;
			} else if (object.toString().equals(oldObject.toString())) {
				return true;
			}
		}

		return false;
	}

	protected void insertTimeline(AbstractEvent event, String operation, com.alnt.platform.base.domain.Entity entity,
			Serializable id, Object[] currentState, Object[] previousState, int[] propertyNames) {
		try {
			Object sessionID = entity.getClientSequenceId();

			String busobjcat = entity.getClass().getSimpleName();
			Long busobjid = entity.getId();

			Timeline tl = new Timeline();
			if (sessionID == null) {
				sessionID = operation + busobjcat + busobjid;
			}

			Query query = event.getSession().createNativeQuery(CLASSDEF_QUERY, ClassDef.class);
			query.setParameter(1, busobjcat);
			query.setFlushMode(FlushModeType.COMMIT);

			RequestDetails requestDetails = new RequestDetails();
			requestDetails
					.setTenantName((String) event.getSession().getFactory().getProperties().get("alert.tenantName"));
			
			
			List<ClassDefDTO> classDefs =  this.classDefService.getBy(requestDetails, "busObjCat", busobjcat).toCompletableFuture().get().collect(Collectors.toList());

			ClassDefDTO parent = classDefs != null && !classDefs.isEmpty() ? classDefs.get(0) : null;

			if (entity instanceof BaseSocialEntity) {
				tl.setBusObjCat(((BaseSocialEntity) entity).getBusObjCat());
				tl.setBusObjID(((BaseSocialEntity) entity).getBusObjId());
			} else {
				if (parent == null || StringUtils.isBlank(parent.getParentBusObjCat())) {
					tl.setBusObjCat(busobjcat);
					tl.setBusObjID(busobjid);
				} else {
					tl.setBusObjCat(parent.getParentBusObjCat());
					tl.setBusObjID((Long) BeanUtil.declaredForcedSilent.getProperty(entity, parent.getRefField()));
				}
			}
			if (sessionID != null) {
				tl.setSessionID(sessionID.toString());
			}
			tl.setTimelineType(operation);
			Long changedBy = BeanUtil.declaredForcedSilent.getProperty(entity, FIELDNAME_CHANGEDBY);
			tl.setCreatedBy(changedBy);

			if (event instanceof PostInsertEvent) {
				TimelineChgs chg = new TimelineChgs();
				chg.setField(busobjcat);
				chg.setNewValue(BeanUtil.declaredForcedSilent.getProperty(entity, FIELDNAME_BODY));
				chg.setSubType(operation);
				chg.setOldValue(null);
				tl.getChgs().add(chg);
			} else if (event instanceof PostUpdateEvent) {
				PostUpdateEvent postUpdateEvent = (PostUpdateEvent) event;
				for (int i = 0; i < propertyNames.length; i++) {
					String propertyName = postUpdateEvent.getPersister().getEntityMetamodel()
							.getProperties()[propertyNames[i]].getName();

					Object object = currentState[propertyNames[i]];
					Object oldObject = previousState != null ? previousState[propertyNames[i]] : null;
					if (!shouldSkipField(propertyName, operation, object, oldObject)) {

						TimelineChgs chg = new TimelineChgs();
						try {

						} catch (Exception e) {
							System.err.println(" Error while setting subdocket in timeline -----"); //$NON-NLS-1$
						}
						chg.setField(propertyName);

						chg.setOldValue(oldObject != null ? oldObject.toString() : null);
						chg.setNewValue(object != null ? object.toString() : null);
						chg.setSubType(operation);

						boolean exits = false;
						for (TimelineChgs c : tl.getChgs()) {
							if (c.getField().equals(chg.getField())) {
								exits = true;
							}
						}
						if (!exits) {
							tl.getChgs().add(chg);
						}

					}
				}
			}
			timelineRepository.save(requestDetails, tl);

		} catch (Exception ex) {
			Logger.error(ExceptionUtils.getStackTrace(ex));
		}
	}

}
