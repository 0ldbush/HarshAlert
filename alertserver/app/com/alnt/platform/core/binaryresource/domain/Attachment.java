package com.alnt.platform.core.binaryresource.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.alnt.mdm.attachmentType.domain.AttachmentType;
import com.alnt.platform.base.domain.BaseSocialEntity;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "ATTACHMENT")
public class Attachment extends BaseSocialEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "BINARY_RESOURCE_ID")
	private Long binaryResourceId;

	@Column(name = "THUMBNAIL_ID")
	private Long thumbnailId;

	@Column(name = "SOURCE_FILE")
	private String sourceFile;

	@Column(name = "SHARED")
	private boolean shared;

	@ManyToOne(targetEntity = AttachmentType.class, optional = false, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "TYPE", referencedColumnName = "EXT_ID", foreignKey = @ForeignKey, insertable = false, updatable = false)
	private AttachmentType attachmentType;

	public Long getBinaryResourceId() {
		return binaryResourceId;
	}

	public void setBinaryResourceId(Long binaryResourceId) {
		this.binaryResourceId = binaryResourceId;
	}

	public Long getThumbnailId() {
		return thumbnailId;
	}

	public void setThumbnailId(Long thumbnailId) {
		this.thumbnailId = thumbnailId;
	}

	public String getSourceFile() {
		return sourceFile;
	}

	public void setSourceFile(String sourceFile) {
		this.sourceFile = sourceFile;
	}

	public boolean isShared() {
		return shared;
	}

	public void setShared(boolean shared) {
		this.shared = shared;
	}

	public AttachmentType getAttachmentType() {
		return attachmentType;
	}

	public void setAttachmentType(AttachmentType attachmentType) {
		this.attachmentType = attachmentType;
	}

}