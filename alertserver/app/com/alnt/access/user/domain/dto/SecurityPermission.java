package com.alnt.access.user.domain.dto;

import be.objectify.deadbolt.java.models.Permission;

public class SecurityPermission implements Permission
{
    public final String value;

    public SecurityPermission(final String value)
    {
        this.value = value;
    }

    @Override
    public String getValue()
    {
        return value;
    }
}
