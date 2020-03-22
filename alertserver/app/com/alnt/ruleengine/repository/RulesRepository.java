package com.alnt.ruleengine.repository;

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
import com.alnt.policyengine.domain.Rule;

import com.alnt.platform.base.persistence.db.jpa.JPAApi;

@Singleton
public class RulesRepository extends BaseRepositoryImpl<Rule> implements BaseRepository<Rule>  {
	
	@Inject
	public RulesRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
		super(jpaApi, executionContext, Rule.class);
	}
    
    public CompletionStage<Stream<Rule>> findByRuleNamespace(RequestDetails requestDetails, Long policyGroup) {
		return supplyAsync(() -> wrap(requestDetails, em -> findByRuleNamespace((EntityManager)em, policyGroup)), executionContext);
	}
	
	private Stream<Rule> findByRuleNamespace(EntityManager em, Long policyGroup) {
		List<Rule> rules = em.createQuery("select p from "+this.dataModelClass.getSimpleName()+" p "
         		+ "where p.ruleNameSpace = :rns", this.dataModelClass)
         		.setParameter("rns", policyGroup)
         		.getResultList();
         return rules.stream();
    }
}
