package com.alnt.platform.core.tenant.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Type;

import com.alnt.platform.base.domain.BaseEntity;


// Message or comment

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "TENANT")
@SQLDelete(sql = "UPDATE TENANT SET INT_STATUS = 3 WHERE ID = ?")

public class Tenant extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="TENANT_ID")
	private Long tenantId;

	@Column(name="TENANT_NAME")
	private String tenantName;
	
	@Column(name="DB_SCHEMA")
	private String dbSchema;
	
	@Column(name="PERSISTENCE_UNIT_NAME")
	private String puName;

	@Column(name="DOMAIN")
	private String domain;

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	public String getDbSchema() {
		return dbSchema;
	}

	public void setDbSchema(String dbSchema) {
		this.dbSchema = dbSchema;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getPuName() {
		return puName;
	}

	public void setPuName(String puName) {
		this.puName = puName;
	}
	
	
	
}