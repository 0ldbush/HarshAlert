package com.alnt.platform.base.persistence.db.jpa;

import play.components.ConfigurationComponents;
import play.db.DBComponents;
import play.inject.ApplicationLifecycle;

/** Java JPA Components. */
public interface JPAComponents extends DBComponents, ConfigurationComponents {

  ApplicationLifecycle applicationLifecycle();

  default JPAConfig jpaConfig() {
    return new DefaultJPAConfig.JPAConfigProvider(config()).get();
  }

  default JPAApi jpaApi() {
    return new DefaultJPAApi.JPAApiProvider(jpaConfig(), applicationLifecycle(), dbApi(), null).get();
  }
}
