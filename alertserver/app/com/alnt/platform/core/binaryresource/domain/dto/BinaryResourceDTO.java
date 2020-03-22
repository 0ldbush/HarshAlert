package com.alnt.platform.core.binaryresource.domain.dto;


import com.alnt.platform.base.domain.dto.BaseDTO;

/**
 * Entity implementation class for Entity: WorkflowStep
 *
 */


public class BinaryResourceDTO extends BaseDTO {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String type;

	private Long size;
    
	protected String title;
  
	private String mimeType;

	private String thumbnailPath;
	
	private String path;

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
}
