package com.alnt.platform.core.binaryresource.domain.dto;

import com.alnt.mdm.attachmentType.domain.dto.AttachmentTypeDTO;
import com.alnt.platform.base.domain.dto.BaseSocialDTO;



public class AttachmentDTO extends BaseSocialDTO {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long binaryResourceId;

	private Long thumbnailId;
    
	private String sourceFile;
	
	private boolean shared;
	
	private AttachmentTypeDTO attachmentType;

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

	public AttachmentTypeDTO getAttachmentType() {
		return attachmentType;
	}

	public void setAttachmentType(AttachmentTypeDTO attachmentType) {
		this.attachmentType = attachmentType;
	}	

}