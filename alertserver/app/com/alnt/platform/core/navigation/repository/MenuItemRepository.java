package com.alnt.platform.core.navigation.repository;

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
import com.alnt.platform.core.navigation.domain.MenuItem;


import static java.util.concurrent.CompletableFuture.supplyAsync;

import com.alnt.platform.base.persistence.db.jpa.JPAApi;

@Singleton
public class MenuItemRepository extends BaseRepositoryImpl<MenuItem> implements BaseRepository<MenuItem> {

	@Inject
	public MenuItemRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
		super(jpaApi, executionContext, MenuItem.class);
	}

	
	public CompletionStage<Stream<MenuItem>> getMenuForUser(RequestDetails requestDetails, Long userId) {
		return supplyAsync(() -> wrap(requestDetails, em -> getMenuForUser((EntityManager)em, userId)), executionContext);
	}
	
	private Stream<MenuItem> getMenuForUser(EntityManager em, Long userId) {
        List<MenuItem> menus = em.createQuery("select p from "+this.dataModelClass.getSimpleName()+" p "
        		+ "where p.parentId is null ", this.dataModelClass)
        		.getResultList();
        return menus.stream();
    }
}
