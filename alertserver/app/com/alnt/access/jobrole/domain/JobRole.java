package com.alnt.access.jobrole.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;

import com.alnt.platform.base.domain.BaseMasterEntity;
import com.alnt.platform.core.navigation.domain.MenuItem;

import be.objectify.deadbolt.java.models.Role;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "JOB_ROLE")
@SQLDelete(sql = "UPDATE JOB_ROLE SET INT_STATUS = 3 WHERE ID = ?")
public class JobRole extends BaseMasterEntity {
	
	private static final long serialVersionUID = 1L;

    @Column(name="START_ACTIVITY")
    private String startActivity;
    	
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="JOB_ROLE_ID", referencedColumnName="ID", foreignKey = @ForeignKey)
    private List<MenuItem> menuItems = new ArrayList<MenuItem>();


	public String getStartActivity() {
		return startActivity;
	}

	public void setStartActivity(String startActivity) {
		this.startActivity = startActivity;
	}

	public List<MenuItem> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(List<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}

}
