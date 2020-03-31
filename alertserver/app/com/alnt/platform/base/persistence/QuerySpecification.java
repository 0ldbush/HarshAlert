package com.alnt.platform.base.persistence;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.alnt.platform.base.request.Criteria;
import com.alnt.platform.base.util.DateUtil;

public class QuerySpecification<T> implements Specification<T> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4655468686674499091L;
	static final String[] DATE_FORMAT = {"yyyy-MM-dd'T'HH:mm:ssZ","yyyy-MM-dd HH:mm:ss","yyyy-MM-dd"};
	
	private Criteria criteria;
	private String asociationNames;	
	private List<String> selectionList;
	
	public QuerySpecification(Criteria criteria) {
		super();
		this.criteria = criteria;
	}

	public String getAsociationNames() {
		return asociationNames;
	}

	public QuerySpecification<T> setAsociationNames(String asociationNames) {
		this.asociationNames = asociationNames;
		return this;
	}
	
	public Criteria getCriteria() {
		return criteria;
	}

	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}
		
	public List<String> getSelectionList() {
		return selectionList;
	}

	public void setSelectionList(List<String> selectionList) {
		this.selectionList = selectionList;
	}
	
	public Specification<T> and(QuerySpecification<T> other) {
		this.criteria.addNestedCriteria(other.getCriteria());
		return this;
	}
	
	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		if(criteria != null) {
		        return this.buildPredicate(root, query, criteriaBuilder, criteria);
		}
		return null;
	}


	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Predicate buildPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder, Criteria criteriaTmp) {
		Predicate result = null;
		Path path = null;
		if (criteriaTmp.getFieldName().contains(".")) {
			String[] tokens = criteriaTmp.getFieldName().split("\\.");

			int len = tokens.length;
			Join<Object, Object> join = null;
			for (String token : tokens) {
				if (join == null) {
					join = root.join(token);
					len--;
				}else if(len>=2)
				{
					join = join.join(token);
					len--;
				}else {
					path = join.get(token);
				}
				
			}

		} else {
			path = root.get(criteriaTmp.getFieldName());
			
		}
		if(criteriaTmp.isDistinct())
		query.distinct(criteriaTmp.isDistinct());	
		
		if (criteriaTmp.getOperator().equalsIgnoreCase("in")) {
			if(criteriaTmp.getValueList() instanceof List) {
				if(path.getJavaType() == Integer.class || path.getJavaType() == int.class) {
					List<Integer> notNullList=(List)((List)criteriaTmp.getValueList()).stream().filter(value-> value!=null).collect(Collectors.toList());
					Predicate tmp = criteriaBuilder.isTrue(path.in(notNullList.toArray()));
					if(criteriaTmp.getValueList().contains(null)){
						result=criteriaBuilder.or(tmp,criteriaBuilder.isNull(path));
					}else {
						result=tmp;
					}
				}else if( path.getJavaType() == Long.class || path.getJavaType() == long.class) {
					List<Long> notNullList=(List)((List)criteriaTmp.getValueList()).stream().filter(value-> value!=null).collect(Collectors.toList());
					Predicate tmp = criteriaBuilder.isTrue(path.in(notNullList.toArray()));
					if(criteriaTmp.getValueList().contains(null)){
						result=criteriaBuilder.or(tmp,criteriaBuilder.isNull(path));
					}else {
						result=tmp;
					}					    
				}else if(path.getJavaType() == String.class) {
					List<String> notNullList=(List)((List)criteriaTmp.getValueList()).stream().filter(value-> value!=null).collect(Collectors.toList());
					Predicate tmp = criteriaBuilder.isTrue(path.in(notNullList.toArray()));
					if(criteriaTmp.getValueList().contains(null)){
						result=criteriaBuilder.or(tmp,criteriaBuilder.isNull(path));
					}else {
						result=tmp;
					}
				}
			}
		} else if (criteriaTmp.getOperator().equalsIgnoreCase("nin")) {
			if(criteriaTmp.getValueList() instanceof List) {
				if(path.getJavaType() == Integer.class || path.getJavaType() == int.class) {
					result = criteriaBuilder.not(path.in(((List)criteriaTmp.getValueList()).toArray(new Integer[((List)criteriaTmp.getValueList()).size()])));
				}else if(path.getJavaType() == String.class) {
					result = criteriaBuilder.not(path.in(((List)criteriaTmp.getValueList()).toArray(new String[((List)criteriaTmp.getValueList()).size()])));
				}
			}
		}else if (criteriaTmp.getOperator().equalsIgnoreCase(">=")) {
			if (path.getJavaType() == Date.class) { 
				result = criteriaBuilder.greaterThanOrEqualTo(path, getDate(criteriaTmp.getValue().toString()));
			}else {
				result = criteriaBuilder.greaterThanOrEqualTo(path, criteriaTmp.getValue().toString());
			}
		} else if (criteriaTmp.getOperator().equalsIgnoreCase("<=")) {
			if (path.getJavaType() == Date.class) { 
				result = criteriaBuilder.lessThanOrEqualTo(path, getDate(criteriaTmp.getValue().toString(),true ));
			}else {
				result = criteriaBuilder.lessThanOrEqualTo(path, criteriaTmp.getValue().toString());
			}
		}else if (criteriaTmp.getOperator().equalsIgnoreCase(">")) {
			if (path.getJavaType() == Date.class) { 
				result = criteriaBuilder.greaterThan(path, getDate(criteriaTmp.getValue().toString()));
			}else {
				result = criteriaBuilder.greaterThan(path, criteriaTmp.getValue().toString());
			}
		} else if (criteriaTmp.getOperator().equalsIgnoreCase("<")) {
			if (path.getJavaType() == Date.class) { 
				result = criteriaBuilder.lessThan(path, getDate(criteriaTmp.getValue().toString(),true ));
			}else {
				result = criteriaBuilder.lessThan(path, criteriaTmp.getValue().toString());
			}
		} else if (criteriaTmp.getOperator().equalsIgnoreCase(":")) {
			if (path.getJavaType() == String.class) {
				result = criteriaBuilder.like((Expression<String>)path, "%" + criteriaTmp.getValue() + "%");
			} else {
				result = criteriaBuilder.equal(path, criteriaTmp.getValue());
			}
		}else if(criteriaTmp.getOperator().equalsIgnoreCase("=")) {
			if (path.getJavaType() == Date.class) { 
				result = criteriaBuilder.equal(path, getDate(criteriaTmp.getValue().toString()));
			}else {
				if(BooleanUtils.toBooleanObject(criteriaTmp.getValue())==null)
				result = criteriaBuilder.equal(path, criteriaTmp.getValue().toString());
				else result = criteriaBuilder.equal(path, Boolean.parseBoolean(criteriaTmp.getValue()));
			}
		}else if(criteriaTmp.getOperator().equalsIgnoreCase("or")) {
			if(criteriaTmp.getValueList() instanceof List) {
				List<Predicate> orPredicates=new ArrayList<>();
					for(Object value:criteriaTmp.getValueList())
					{
						if(value==null)
						 orPredicates.add(criteriaBuilder.isNull(path));
						else
						orPredicates.add(criteriaBuilder.equal(path, value));
					}
					result = criteriaBuilder.isTrue(criteriaBuilder.or(orPredicates.toArray(new Predicate[orPredicates.size()])));
			}
		}
		
		else if(criteriaTmp.getOperator().equalsIgnoreCase("isnull")) {
				result = criteriaBuilder.isNull(path);
		}else if(criteriaTmp.getOperator().equalsIgnoreCase("isnotnull")) {
			result = criteriaBuilder.isNotNull(path);
		}
		if(!criteriaTmp.getNestedCriteria().isEmpty()) {
			for(Criteria crit : criteriaTmp.getNestedCriteria()) {
				Predicate resultTmp = this.buildPredicate(root, query, criteriaBuilder, crit);
				if(resultTmp != null ) {
					if(crit.isAnd())
					result = criteriaBuilder.and(result, resultTmp);
					else result = criteriaBuilder.or(result, resultTmp);
				}
			}
		}
		return result;
	}
	
	private Date getDate(String strDate) {
		return getDate(strDate, false);
	}
	
	private Date getDate(String strDate, boolean endOfdate) {
		Date date = null;
		try {
			date = DateUtils.parseDate(strDate, DATE_FORMAT);
			
			if(strDate.length() <=10) {
				if(endOfdate) {
					date = DateUtil.atEndOfDay(date);
				}else {
					date = DateUtil.atStartOfDay(date);
				}
				
			}
			
		} catch (ParseException e) {
			throw new IllegalArgumentException("INVALID_DATE_FORMAT");
		}
		return date;
	}
	


	public static void main(String[] args) {
		List<Long> lstLong = new ArrayList<>();
		lstLong.add(1l);
		lstLong.add(2l);
		lstLong.add(3l);
		
		System.out.println(lstLong.toArray(new Long[lstLong.size()]));
	}
}
