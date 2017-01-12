package com.atguigu.crowdfunding.cpes.dao;

import java.util.List;
import java.util.Map;

import com.atguigu.crowdfunding.bean.Datas;
import com.atguigu.crowdfunding.cpes.bean.Role;
import com.atguigu.crowdfunding.cpes.bean.RolePermissionCount;

public interface RoleDao {

	List<Role> queryRoleDatas(Map<String, Object> paramMap);

	int queryRoleCount(Map<String, Object> paramMap);

	void insertRole(Role role);

	Role queryById(Integer id);

	void updateRole(Role role);

	void deleteRoles(Datas ds);

	void insertRolePermission(Map<String, Object> paramMap);

	void deleteAllPermission(Integer roleid);

	List<RolePermissionCount> queryPermissionCount(Map<String, Object> map);

}
