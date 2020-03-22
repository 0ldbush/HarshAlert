package com.alnt.platform.base.persistence.db.jpa;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.hibernate.jpa.boot.spi.IntegratorProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alnt.platform.base.interceptor.EventListenerIntegrator;
import com.alnt.platform.base.persistence.db.jpa.JPAConfig.PersistenceUnit;
import com.alnt.platform.core.tenant.domain.Tenant;
import com.google.common.collect.ImmutableSet;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import play.db.DBApi;
import play.inject.ApplicationLifecycle;
import play.inject.Injector;

/** Default implementation of the JPA API. */
public class DefaultJPAApi implements JPAApi {

  private static final Logger logger = LoggerFactory.getLogger(DefaultJPAApi.class);

  private final JPAConfig jpaConfig;

  private final Map<String, EntityManagerFactory> emfs = new HashMap<>();

  private final Injector injector;
  
  private final Config config;
  
  public DefaultJPAApi(JPAConfig jpaConfig, Injector injector) {
    this.jpaConfig = jpaConfig;
    this.injector = injector;
    this.config = ConfigFactory.load();
  }

  @Singleton
  public static class JPAApiProvider implements Provider<JPAApi> {
    private final JPAApi jpaApi;

    /**
     * @deprecated Deprecated as of 2.8.0. Use {@link #JPAApiProvider(JPAConfig,
     *     ApplicationLifecycle, DBApi)} instead.
     */
    @Deprecated
    public JPAApiProvider(
        JPAConfig jpaConfig, ApplicationLifecycle lifecycle, DBApi dbApi, Config config, Injector injector) {
      this(jpaConfig, lifecycle, dbApi, injector);
      
    }

    @Inject
    public JPAApiProvider(JPAConfig jpaConfig, ApplicationLifecycle lifecycle, DBApi dbApi, Injector injector) {
      // dependency on db api ensures that the databases are initialised
      jpaApi = new DefaultJPAApi(jpaConfig, injector);
      lifecycle.addStopHook(
          () -> {
            jpaApi.shutdown();
            return CompletableFuture.completedFuture(null);
          });
      jpaApi.start();
    }

    @Override
    public JPAApi get() {
      return jpaApi;
    }
  }

  /** Initialise JPA entity manager factories. */
  public JPAApi start() {
	  
	  String hibernateDialect = this.config.getString("hibernateDialect");

	for(PersistenceUnit pu : jpaConfig.persistenceUnits()) {
		if("master".equals(pu.name)) {
			Properties properties = new Properties();
        	properties.put(
        	    "hibernate.default_schema",
        	    pu.schema
        	);
        	properties.put(
            	    "hibernate.dialect",
            	    hibernateDialect
            	);
			emfs.put(
					pu.name,
                    Persistence.createEntityManagerFactory(pu.unitName, properties));
		}
	}
	List<Tenant> tenantList = this.em("master").createQuery("select t from Tenant t", Tenant.class).getResultList();
	ImmutableSet.Builder<JPAConfig.PersistenceUnit> persistenceUnits =
	          new ImmutableSet.Builder<JPAConfig.PersistenceUnit>();
	tenantList.forEach(tenant -> {
		persistenceUnits.add(new JPAConfig.PersistenceUnit(tenant.getTenantName(), tenant.getDbSchema(), tenant.getPuName()));
	});
	persistenceUnits.build()
        .forEach(
            persistenceUnit -> {
            	if(!"master".equalsIgnoreCase(persistenceUnit.name)) {
	            	Properties properties = new Properties();
	            	properties.put(
		            	    "alert.tenantName",
		            	    persistenceUnit.name
		            	);
	            	properties.put(
	            	    "hibernate.integrator_provider",
	            	    (IntegratorProvider) () -> Collections.singletonList(
	            	    		injector.instanceOf(EventListenerIntegrator.class)
	            	    )
	            	);
	            	properties.put(
	                	    "hibernate.default_schema",
	                	    persistenceUnit.schema
	                	);
	            	properties.put(
	                	    "hibernate.dialect",
	                	    hibernateDialect
	                	);
	            	if("aeguardian".equalsIgnoreCase(persistenceUnit.name)) {
	            		properties.put(
		                	    "hibernate.hbm2ddl.auto",
		                	    "none"
		                	);
	            	}
	                emfs.put(
	                    persistenceUnit.name,
	                    Persistence.createEntityManagerFactory(persistenceUnit.unitName, properties));
            	}
            });
    return this;
  }

  /**
   * Get a newly created EntityManager for the specified persistence unit name.
   *
   * @param name The persistence unit name
   */
  public EntityManager em(String name) {
    EntityManagerFactory emf = emfs.get(name);
    if (emf == null) {
      return null;
    }
    return emf.createEntityManager();
  }

  /**
   * Run a block of code with a newly created EntityManager for the default Persistence Unit.
   *
   * @param block Block of code to execute
   * @param <T> type of result
   * @return code execution result
   */
  public <T> T withTransaction(Function<EntityManager, T> block) {
    return withTransaction("default", block);
  }

  /**
   * Run a block of code with a newly created EntityManager for the default Persistence Unit.
   *
   * @param block Block of code to execute
   */
  public void withTransaction(Consumer<EntityManager> block) {
    withTransaction(
        em -> {
          block.accept(em);
          return null;
        });
  }

  /**
   * Run a block of code with a newly created EntityManager for the named Persistence Unit.
   *
   * @param name The persistence unit name
   * @param block Block of code to execute
   * @param <T> type of result
   * @return code execution result
   */
  public <T> T withTransaction(String name, Function<EntityManager, T> block) {
    return withTransaction(name, false, block);
  }

  /**
   * Run a block of code with a newly created EntityManager for the named Persistence Unit.
   *
   * @param name The persistence unit name
   * @param block Block of code to execute
   */
  public void withTransaction(String name, Consumer<EntityManager> block) {
    withTransaction(
        name,
        em -> {
          block.accept(em);
          return null;
        });
  }

  /**
   * Run a block of code with a newly created EntityManager for the named Persistence Unit.
   *
   * @param name The persistence unit name
   * @param readOnly Is the transaction read-only?
   * @param block Block of code to execute
   * @param <T> type of result
   * @return code execution result
   */
  public <T> T withTransaction(String name, boolean readOnly, Function<EntityManager, T> block) {
    EntityManager entityManager = null;
    EntityTransaction tx = null;

    try {
      entityManager = em(name);

      if (entityManager == null) {
        throw new RuntimeException("Could not create JPA entity manager for '" + name + "'");
      }

      if (!readOnly) {
        tx = entityManager.getTransaction();
        tx.begin();
      }

      T result = block.apply(entityManager);

      if (tx != null) {
        if (tx.getRollbackOnly()) {
          tx.rollback();
        } else {
          tx.commit();
        }
      }

      return result;

    } catch (Throwable t) {
      if (tx != null) {
        try {
          if (tx.isActive()) {
            tx.rollback();
          }
        } catch (Exception e) {
          logger.error("Could not rollback transaction", e);
        }
      }
      throw t;
    } finally {
      if (entityManager != null) {
        entityManager.close();
      }
    }
  }

  /**
   * Run a block of code with a newly created EntityManager for the named Persistence Unit.
   *
   * @param name The persistence unit name
   * @param readOnly Is the transaction read-only?
   * @param block Block of code to execute
   */
  public void withTransaction(String name, boolean readOnly, Consumer<EntityManager> block) {
    withTransaction(
        name,
        readOnly,
        em -> {
          block.accept(em);
          return null;
        });
  }

  /** Close all entity manager factories. */
  public void shutdown() {
    emfs.values().forEach(EntityManagerFactory::close);
  }
}