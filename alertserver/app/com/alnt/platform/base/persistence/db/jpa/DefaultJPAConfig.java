package com.alnt.platform.base.persistence.db.jpa;

import com.google.common.collect.ImmutableSet;
import com.typesafe.config.Config;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import java.util.Map;
import java.util.Set;

/** Default JPA configuration. */
public class DefaultJPAConfig implements JPAConfig {

  private Set<JPAConfig.PersistenceUnit> persistenceUnits;

  public DefaultJPAConfig(Set<JPAConfig.PersistenceUnit> persistenceUnits) {
    this.persistenceUnits = persistenceUnits;
  }

  public DefaultJPAConfig(JPAConfig.PersistenceUnit... persistenceUnits) {
    this(ImmutableSet.copyOf(persistenceUnits));
  }

  @Override
  public Set<JPAConfig.PersistenceUnit> persistenceUnits() {
    return persistenceUnits;
  }

  @Singleton
  public static class JPAConfigProvider implements Provider<JPAConfig> {
    private final JPAConfig jpaConfig;

    @Inject
    public JPAConfigProvider(Config configuration) {
      String jpaKey = "play.jpa.master";

      ImmutableSet.Builder<JPAConfig.PersistenceUnit> persistenceUnits =
          new ImmutableSet.Builder<JPAConfig.PersistenceUnit>();

      if (configuration.hasPath(jpaKey)) {
        Config jpa = configuration.getConfig(jpaKey);
        String name = jpa.getString("name");
        String schema = jpa.getString("schema");
        String puName =  jpa.getString("puName");
        persistenceUnits.add(new JPAConfig.PersistenceUnit(name, schema, puName));
      }

      jpaConfig = new DefaultJPAConfig(persistenceUnits.build());
    }

    @Override
    public JPAConfig get() {
      return jpaConfig;
    }
  }

  /**
   * Create a default JPA configuration with the given name and unit name.
   *
   * @param name the name for the entity manager factory
   * @param unitName the persistence unit name as used in `persistence.xml`
   * @return a default JPA configuration
   */
  public static JPAConfig of(String name, String schema, String unitName) {
    return new DefaultJPAConfig(new JPAConfig.PersistenceUnit(name, schema, unitName));
  }

  /**
   * Create a default JPA configuration with the given names and unit names.
   *
   * @param n1 Name of the first entity manager factory
   * @param u1 Name of the first unit
   * @param n2 Name of the second entity manager factory
   * @param u2 Name of the second unit
   * @return a default JPA configuration with the provided persistence units.
   */
  public static JPAConfig of(String n1, String s1, String u1, String n2, String s2, String u2) {
    return new DefaultJPAConfig(
        new JPAConfig.PersistenceUnit(n1, s1, u1), new JPAConfig.PersistenceUnit(n2, s2, u2));
  }

  /**
   * Create a default JPA configuration with the given names and unit names.
   *
   * @param n1 Name of the first entity manager factory
   * @param u1 Name of the first unit
   * @param n2 Name of the second entity manager factory
   * @param u2 Name of the second unit
   * @param n3 Name of the third entity manager factory
   * @param u3 Name of the third unit
   * @return a default JPA configuration with the provided persistence units.
   */
  public static JPAConfig of(String n1, String s1, String u1, String n2,String s2,  String u2, String n3, String s3, String u3) {
    return new DefaultJPAConfig(
        new JPAConfig.PersistenceUnit(n1, s1, u1),
        new JPAConfig.PersistenceUnit(n2, s2, u2),
        new JPAConfig.PersistenceUnit(n3, s3, u3));
  }

  /**
   * Create a default JPA configuration from a map of names to unit names.
   *
   * @param map Map of entity manager factory names to unit names
   * @return a JPAConfig configured with the provided mapping
   */
  public static JPAConfig from(Map<String, String> map) {
    ImmutableSet.Builder<JPAConfig.PersistenceUnit> persistenceUnits =
        new ImmutableSet.Builder<JPAConfig.PersistenceUnit>();
    for (Map.Entry<String, String> entry : map.entrySet()) {
      persistenceUnits.add(new JPAConfig.PersistenceUnit(entry.getKey(), entry.getKey(), entry.getValue()));
    }
    return new DefaultJPAConfig(persistenceUnits.build());
  }
}
