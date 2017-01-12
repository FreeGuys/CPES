package com.atguigu.crowdfunding.cpes.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crowdfunding.cpes.bean.Permission;
import com.atguigu.crowdfunding.cpes.bean.RolePermission;
import com.atguigu.crowdfunding.cpes.dao.PermissionDao;
import com.atguigu.crowdfunding.cpes.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionDao permissionDao;

	public List<Permission> queryChildPermission(int i) {
		return permissionDao.queryChildPermission(i);
	}

	public List<Permission> queryAll() {
		return permissionDao.queryAll();
	}

	public void insertPermission(Permission p) {
		permissionDao.insertPermission(p);
	}

	public Permission queryPermissionById(Integer id) {
		return permissionDao.queryPermissionById(id);
	}

	public void updatePermission(Permission p) {
		permissionDao.updatePermission(p);
	}

	public void deletePermission(Permission p) {
		permissionDao.deletePermission(p);
	}

	public List<RolePermission> queryAssignedPermission(Integer roleid) {
		return permissionDao.queryAssignedPermission(roleid);
	}
}
