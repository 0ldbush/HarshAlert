package com.alnt.platform.core.configsetting.domain;

import com.alnt.platform.base.domain.BaseMasterEntity;
import com.alnt.platform.base.persistence.CacheConstants;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="CONFIG_SETTING", indexes = {
})
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region= CacheConstants.REGION_CONFIG_MODULE_ELEMENTS)
@SQLDelete(sql="UPDATE CONFIG_SETTING SET INTSTATUS = 3 WHERE CONFIG_SETTING_ID = ?")
public class ConfigSetting extends BaseMasterEntity{

    private static final long serialVersionUID = 1L;


    @Column(name = "NAME", nullable=false)
    protected String name;

    @Column(name = "VALUE", nullable=false)
    protected String value;

    @Column(name = "GROUP_NAME")
    protected String groupName;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }


}
