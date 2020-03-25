package com.alnt.platform.core.configsetting.repository;

import com.alnt.platform.base.persistence.db.jpa.JPAApi;
import com.alnt.platform.base.repository.BaseRepository;
import com.alnt.platform.base.repository.BaseRepositoryImpl;
import com.alnt.platform.base.repository.DatabaseExecutionContext;
import com.alnt.platform.core.configsetting.domain.ConfigSetting;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ConfigSettingRepository extends BaseRepositoryImpl<ConfigSetting> implements BaseRepository<ConfigSetting> {
    @Inject
    public ConfigSettingRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        super(jpaApi, executionContext, ConfigSetting.class);
    }

}
