package com.alnt.platform.base.request;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import com.alnt.platform.base.domain.BaseEntity.INT_STATUS;
import com.alnt.platform.base.persistence.QuerySpecification;

public class SearchCriteria {

	private List<Criteria> filterCriteria = new ArrayList<>();		
	private String sortByString ;//sent by UI we parse this in baseCOntroller
	private List<SortBy> sortBy = new ArrayList<>();
	private int page = 0;
	private int size = 50;
	private long userId;
	private List<String>associationsToLoad;
	private List<Integer> intStatus;
	private List<String>extraFilters=new ArrayList<>();	
	private List<SelectionField> selectionList;
	private List<JoinEntity> joinList;
	private String langCode;	
	private String aliasName;	
	private boolean applyIntStatusClause = true;
	private boolean applyChangedOnSort = true;
	private List<String> attributes=new ArrayList<String>();	
	
	public SearchCriteria() {
		super();
	}
	public SearchCriteria(List<Criteria> filterCriteria, List<SortBy> sortBy, int page, int size) {
		super();
		this.filterCriteria = filterCriteria;
		this.sortBy = sortBy;
		this.page = page;
		this.size = size;
	}
	
	public static <T> QuerySpecification<T> buildQuerySpecification(SearchCriteria searchCriteria){
		List intStatusList = null;
		if(searchCriteria != null && searchCriteria.getIntStatus() != null && !searchCriteria.getIntStatus().isEmpty()) {
			intStatusList = searchCriteria.getIntStatus();
		}
		if(searchCriteria.applyIntStatusClause && (intStatusList == null || intStatusList.isEmpty())) {
			intStatusList= new ArrayList<Integer>();
			intStatusList.add(INT_STATUS.ACTIVE.getValue());
		}
		List<SortBy> sortByList = null; 
		if(searchCriteria != null && searchCriteria.getIntStatus() != null && !searchCriteria.getIntStatus().isEmpty()) {
			sortByList = searchCriteria.getSortBy();
		}
		
		if(searchCriteria.applyChangedOnSort && (sortByList == null || sortByList.isEmpty())) {
			sortByList = new ArrayList<SortBy>();
			SortBy sortBy = new SortBy();
			sortBy.setDirection("DESC");
			sortBy.setProperty("changedOn");
			sortByList.add(sortBy);
			searchCriteria.setSortBy(sortByList);
		}
		//add condition for intStatus
		List<QuerySpecification<T>> specs = new ArrayList<QuerySpecification<T>>();
		if(searchCriteria.getFilterCriteria()!=null) {
			Optional<Criteria> intStatus=	searchCriteria.getFilterCriteria().stream().filter(filter->filter.getFieldName().equals("intStatus")).findAny();
			if(!intStatus.isPresent() && searchCriteria.getApplyIntStatusClause()) {
				Criteria intStatusCriteria = new Criteria("intStatus", "in", intStatusList);
				specs.add(new  QuerySpecification(intStatusCriteria));
			}
		}
				
        if(searchCriteria != null && searchCriteria.getFilterCriteria() != null && !searchCriteria.getFilterCriteria().isEmpty()) {
	        for (Criteria criteria : searchCriteria.getFilterCriteria()) {
	            specs.add(new  QuerySpecification(criteria));
	        }
        }
        if(searchCriteria != null && searchCriteria.getAssociationsToLoad() != null && !searchCriteria.getAssociationsToLoad().isEmpty()) {
	        if(!specs.isEmpty()) {
	        		specs.get(0).setAsociationNames(String.join(",", searchCriteria.getAssociationsToLoad()));
	        } else {
	        		specs.add(new QuerySpecification(null).setAsociationNames(String.join(",", searchCriteria.getAssociationsToLoad())));
	        }
        }
        QuerySpecification<T> result = null;
        if(specs.size() > 0) {
	        result = specs.get(0);
	        for (int i = 1; i < specs.size(); i++) {
	        		result.and(specs.get(i));
	        }
        }
        if(result!=null && searchCriteria.getSelectionList()!=null && !searchCriteria.getSelectionList().isEmpty())
        {
        	result.setSelectionList(searchCriteria.getSelectionList().stream().map(select->select.getFieldName()).collect(Collectors.toList()));
        }
        return result;
	}
	public Pageable getPageable() {
		Pageable pageable = null;
		if(page > 0 || size > 0) {
			Sort sort = getSort();
			if(sort != null) {
				pageable = PageRequest.of(page, size, sort);
			} else {
				pageable = PageRequest.of(page, size);
			}
		} 
		return pageable;
	}
	public Sort getSort() {
		Sort sort = null;
		if(sortBy != null && !sortBy.isEmpty()) {
			List<Order> orders = new ArrayList<Order>();
			for(SortBy sortB : sortBy) {
				Order ord = new Order(Direction.fromString(sortB.getDirection()), sortB.getProperty());
				if(sortB.isIgnoreCase()) {
					ord.ignoreCase();
				}
				orders.add(ord);
			}
			sort = Sort.by(orders);
		}
		return sort;
	}
	public List<Criteria> getFilterCriteria() {
		return filterCriteria;
	}
	public void setFilterCriteria(List<Criteria> filterCriteria) {
		this.filterCriteria = filterCriteria;
	}
	public List<SortBy> getSortBy() {
		return sortBy;
	}
	public void setSortBy(List<SortBy> sortBy) {
		this.sortBy = sortBy;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public List<String> getAssociationsToLoad() {
		return associationsToLoad;
	}
	public void setAssociationsToLoad(List<String> associationsToLoad) {
		this.associationsToLoad = associationsToLoad;
	}
	public List<Integer> getIntStatus() {
		return intStatus;
	}
	public void setIntStatus(List<Integer> intStatus) {
		this.intStatus = intStatus;
	}
	public List<String> getExtraFilters() {
		return extraFilters;
	}
	public void setExtraFilters(List<String> extraFilters) {
		this.extraFilters = extraFilters;
	}
	public List<SelectionField> getSelectionList() {
		return selectionList;
	}
	public void setSelectionList(List<SelectionField> selectionList) {
		this.selectionList = selectionList;
	}
	public List<JoinEntity> getJoinList() {
		return joinList;
	}
	public void setJoinList(List<JoinEntity> joinList) {
		this.joinList = joinList;
	}
	public String getLangCode() {
		return langCode;
	}
	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}
	public String getAliasName() {
		return aliasName;
	}
	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}
	public boolean getApplyIntStatusClause() {
		return applyIntStatusClause;
	}
	public void setApplyIntStatusClause(boolean applyIntStatusClause) {
		this.applyIntStatusClause = applyIntStatusClause;
	}
	public List<String> getAttributes() {
		return attributes;
	}
	public void setAttributes(List<String> attributes) {
		this.attributes = attributes;
	}
	public String getSortByString() {
		return sortByString;
	}
	public void setSortByString(String sortByString) {
		this.sortByString = sortByString;
	}
	public boolean isApplyChangedOnSort() {
		return applyChangedOnSort;
	}
	public void setApplyChangedOnSort(boolean applyChangedOnSort) {
		this.applyChangedOnSort = applyChangedOnSort;
	}
	
	
}
