package com.alnt.platform.core.binaryresource.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.alnt.platform.base.domain.BaseEntity;

/**
 * Entity implementation class for Entity: WorkflowStep
 *
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="BINARY_RESOURCE")
public class BinaryResource extends BaseEntity {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="TYPE")
	private String type;

	@Column(name="SIZE")
	private Long size;
    
	@Column(name="TITLE")
	protected String title;
  
	@Column(name="MIME_TYPE")
	private String mimeType;
	
	@Column(name="THUMBNAIL_PATH")
	private String thumbnailPath;
	
	@Column(name="PATH")
	private String path;
	
	@Lob
	@Type(type = "org.hibernate.type.MaterializedBlobType")
	@Column(name="FILE_DATA")
	private byte[] fileData;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getThumbnailPath() {
		return thumbnailPath;
	}

	public void setThumbnailPath(String thumbnailPath) {
		this.thumbnailPath = thumbnailPath;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}	
	
	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}

}
