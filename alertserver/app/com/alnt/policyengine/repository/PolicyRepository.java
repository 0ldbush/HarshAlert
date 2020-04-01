package com.alnt.policyengine.repository;

import static java.util.concurrent.CompletableFuture.supplyAsync;

import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.alnt.platform.base.persistence.db.jpa.JPAApi;
import com.alnt.platform.base.repository.BaseRepository;
import com.alnt.platform.base.repository.BaseRepositoryImpl;
import com.alnt.platform.base.repository.DatabaseExecutionContext;
import com.alnt.platform.base.request.RequestDetails;
import com.alnt.policyengine.domain.Policy;

@Singleton
public class PolicyRepository extends BaseRepositoryImpl<Policy> implements BaseRepository<Policy> {

	@Inject
	public PolicyRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
		super(jpaApi, executionContext, Policy.class);
	}
	
	public CompletionStage<Stream<Policy>> getAllPolicyForGroups(RequestDetails requestDetails,
			List<String> groups) {
		return supplyAsync(() -> wrap(requestDetails, em -> getAllPolicyForGroups( em, groups)), executionContext);
	}
	
	
	private Stream<Policy> getAllPolicyForGroups(EntityManager em, List<String> groups) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Policy> q = cb.createQuery(getDomainClass());
		Root<Policy> c = q.from(getDomainClass());
		
		Expression<String> inExpression = c.get("policyGroup");
		Predicate inPredicate = inExpression.in(groups);
		
		q.select(c).where(inPredicate);
		
		TypedQuery<Policy> query = em.createQuery(q);
		
		return query.getResultList().stream();
    }
	

}
