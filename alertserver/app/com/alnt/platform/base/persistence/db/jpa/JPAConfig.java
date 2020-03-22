package com.alnt.platform.base.persistence.db.jpa;

import java.util.Set;

/** JPA configuration. */
public interface JPAConfig {

  Set<PersistenceUnit> persistenceUnits();

  class PersistenceUnit {
    public String name;
    public String schema;
    public String unitName;

    public PersistenceUnit(String name, String schema, String unitName) {
      this.name = name;
      this.schema = schema;
      this.unitName = unitName;
    }
  }
}
