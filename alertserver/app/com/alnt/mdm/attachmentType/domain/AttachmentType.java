package com.alnt.mdm.attachmentType.domain;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;

import com.alnt.platform.base.domain.BaseSettingEntity;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "ATTACHMENT_TYPE")
@SQLDelete(sql = "UPDATE ATTACHMENT_TYPE SET INT_STATUS = 3 WHERE ID = ?")
public class AttachmentType extends BaseSettingEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
