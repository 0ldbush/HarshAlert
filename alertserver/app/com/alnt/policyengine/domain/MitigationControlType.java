package com.alnt.policyengine.domain;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;

import com.alnt.platform.base.domain.BaseSettingEntity;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "MITIGATION_CONTROL_TYPE")
@SQLDelete(sql = "UPDATE MITIGATION_CONTROL_TYPE SET INT_STATUS = 3 WHERE ID = ?")
public class MitigationControlType extends BaseSettingEntity {

	private static final long serialVersionUID = 1L;

}
