package com.atguigu.crowdfunding.cpes.service;

import java.util.List;

import com.atguigu.crowdfunding.cpes.bean.Permission;
import com.atguigu.crowdfunding.cpes.bean.RolePermission;

public interface PermissionService {

	List<Permission> queryChildPermission(int i);

	List<Permission> queryAll();

	void insertPermission(Permission p);

	Permission queryPermissionById(Integer id);

	void updatePermission(Permission p);

	void deletePermission(Permission p);

	List<RolePermission> queryAssignedPermission(Integer roleid);

}
