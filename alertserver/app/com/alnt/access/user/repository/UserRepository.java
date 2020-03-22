package com.alnt.access.user.repository;

import static java.util.concurrent.CompletableFuture.supplyAsync;

import java.util.Optional;
import java.util.concurrent.CompletionStage;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;

import com.alnt.access.user.domain.User;
import com.alnt.platform.base.persistence.db.jpa.JPAApi;
import com.alnt.platform.base.repository.BaseRepository;
import com.alnt.platform.base.repository.BaseRepositoryImpl;
import com.alnt.platform.base.repository.DatabaseExecutionContext;
import com.alnt.platform.base.request.RequestDetails;

@Singleton
public class UserRepository extends BaseRepositoryImpl<User> implements BaseRepository<User> {

	@Inject
	public UserRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
		super(jpaApi, executionContext, User.class);
	}
	
	public CompletionStage<Optional<User>> getUserByName(RequestDetails requestDetails, String username) {
		return supplyAsync(() -> wrap(requestDetails, em -> getUserByName(getEmfForTenant(requestDetails), username)), executionContext);
	}
	
	private Optional<User> getUserByName(EntityManager em, String username) {
		User user = (User)em.createQuery("select p from "+this.dataModelClass.getSimpleName()+" p "
        		+ "where p.userName = :username ", this.dataModelClass)
        		.setParameter("username", username)
        		.getSingleResult();
        return Optional.ofNullable(user);
    }

}
