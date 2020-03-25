package com.alnt.platform.core.configsetting.domain.dto;

import com.alnt.platform.base.domain.dto.BaseMasterDTO;

import javax.persistence.Column;

public class ConfigSettingDTO extends BaseMasterDTO {
    private static final long serialVersionUID = 1L;
    protected String name;
    protected String value;
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
