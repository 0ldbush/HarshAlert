package com.alnt.policyengine.domain;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;

import com.alnt.platform.base.domain.BaseSettingEntity;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "POLICY_GROUP")
@SQLDelete(sql = "UPDATE POLICY_GROUP SET INT_STATUS = 3 WHERE ID = ?")
public class PolicyGroup extends BaseSettingEntity {

	private static final long serialVersionUID = 1L;

}
