package com.alnt.mdm.messageType.domain;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;

import com.alnt.platform.base.domain.BaseSettingEntity;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "MESSAGE_TYPE")
@SQLDelete(sql = "UPDATE MESSAGE_TYPE SET INT_STATUS = 3 WHERE ID = ?")
public class MessageType extends BaseSettingEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
