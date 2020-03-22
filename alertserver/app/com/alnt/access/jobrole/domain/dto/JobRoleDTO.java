package com.alnt.access.jobrole.domain.dto;

import java.util.ArrayList;
import java.util.List;

import com.alnt.platform.base.domain.dto.BaseMasterDTO;
import com.alnt.platform.core.navigation.domain.dto.MenuItemDTO;

public class JobRoleDTO extends BaseMasterDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    protected String startActivity;
    
    private List<MenuItemDTO> menuItems = new ArrayList<MenuItemDTO>();


	public String getStartActivity() {
		return startActivity;
	}

	public void setStartActivity(String startActivity) {
		this.startActivity = startActivity;
	}

	public List<MenuItemDTO> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(List<MenuItemDTO> menuItems) {
		this.menuItems = menuItems;
	}

}
