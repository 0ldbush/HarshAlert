package com.alnt.workflow.repository;

import static java.util.concurrent.CompletableFuture.supplyAsync;

import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;

import com.alnt.platform.base.repository.BaseRepository;
import com.alnt.platform.base.repository.BaseRepositoryImpl;
import com.alnt.platform.base.repository.DatabaseExecutionContext;
import com.alnt.platform.base.request.RequestDetails;
import com.alnt.workflow.domain.Workflow;

import com.alnt.platform.base.persistence.db.jpa.JPAApi;

@Singleton
public class WorkflowRepository extends BaseRepositoryImpl<Workflow> implements BaseRepository<Workflow> {

	@Inject
	public WorkflowRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
		super(jpaApi, executionContext, Workflow.class);
	}
	

	public CompletionStage<List<Workflow>> findByBusObjCatAndTemplate(final String busObjCat, final boolean template) {
		return supplyAsync(() -> wrap(new RequestDetails(), em -> this.findByBusObjCatAndTemplate((EntityManager)em, busObjCat, template)), executionContext);
	}
	
	private List<Workflow> findByBusObjCatAndTemplate(EntityManager em, final String busObjCat, final boolean template) {
        List<Workflow> workflows = em.createQuery("select p from "+this.dataModelClass.getSimpleName()+" p", this.dataModelClass).getResultList();
        return workflows;
    }
	
	public CompletionStage<List<Workflow>> findByBusObjCatAndTemplateAndIntStatus(final String busObjCat, final boolean template, final Integer intStatus) {
		return supplyAsync(() -> wrap(new RequestDetails(), em -> this.findByBusObjCatAndTemplateAndIntStatus((EntityManager)em, busObjCat, template, intStatus)), executionContext);
	}
	
	private List<Workflow> findByBusObjCatAndTemplateAndIntStatus(EntityManager em, final String busObjCat, final boolean template, final Integer intStatus) {
        List<Workflow> workflows = em.createQuery("select p from "+this.dataModelClass.getSimpleName()+" p", this.dataModelClass).getResultList();
        return workflows;
    }
	
	public CompletionStage<List<Workflow>> findByWorkflowCodeAndTemplate(final String workflowCode, final boolean template) {
		return supplyAsync(() -> wrap(new RequestDetails(), em -> this.findByWorkflowCodeAndTemplate((EntityManager)em, workflowCode, template)), executionContext);
	}
	
	private List<Workflow> findByWorkflowCodeAndTemplate(EntityManager em, final String workflowCode, final boolean template) {
        List<Workflow> workflows = em.createQuery("select p from "+this.dataModelClass.getSimpleName()+" p", this.dataModelClass).getResultList();
        return workflows;
    }
	
	public CompletionStage<List<Workflow>> findByBusObjCatAndTemplateAndDisplayTemplate(final String busObjCat, final boolean template, final boolean displayTemplate) {
		return supplyAsync(() -> wrap(new RequestDetails(),em -> this.findByBusObjCatAndTemplateAndDisplayTemplate((EntityManager)em, busObjCat, template, displayTemplate)), executionContext);
	}
	
	private List<Workflow> findByBusObjCatAndTemplateAndDisplayTemplate(EntityManager em, final String busObjCat, final boolean template, final boolean displayTemplate) {
        List<Workflow> workflows = em.createQuery("select p from "+this.dataModelClass.getSimpleName()+" p", this.dataModelClass).getResultList();
        return workflows;
    }
	
	//public List<Workflow> findByBusObjCatAndTemplate(final String busObjCat, final boolean template);
	
	//public List<Workflow> findByBusObjCatAndTemplateAndIntStatus(final String busObjCat, final boolean template, final Integer intStatus);
	
	//public List<Workflow> findByWorkflowCodeAndTemplate(final String workflowCode, final boolean template);
	
	//public List<Workflow> findByBusObjCatAndTemplateAndDisplayTemplate(final String busObjCat, final boolean template, final boolean displayTemplate);

}
