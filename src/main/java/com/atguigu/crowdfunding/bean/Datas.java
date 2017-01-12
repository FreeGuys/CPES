package com.atguigu.crowdfunding.bean;

import java.util.List;

import com.atguigu.crowdfunding.cpes.bean.Role;

public class Datas {

	
	private List<Integer> ids;
	private List<Role> roles;

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
}
