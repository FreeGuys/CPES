package com.atguigu.crowdfunding.cpes.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crowdfunding.bean.Datas;
import com.atguigu.crowdfunding.bean.Page;
import com.atguigu.crowdfunding.cpes.bean.Role;
import com.atguigu.crowdfunding.cpes.bean.RolePermissionCount;
import com.atguigu.crowdfunding.cpes.dao.RoleDao;
import com.atguigu.crowdfunding.cpes.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	public Page<Role> queryRoleDatas(Map<String, Object> paramMap) {
	
		// 分页对象
		Page<Role> rolePage = new Page<Role>();
		
		// 查询数据
		List<Role> roles = roleDao.queryRoleDatas(paramMap);
		int index = 1;
		for ( Role r : roles ) {
			r.setIndex(index++);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roles", roles);
		List<RolePermissionCount> cnts = roleDao.queryPermissionCount(map);
		for ( RolePermissionCount cnt : cnts ) {
			for ( Role r : roles ) {
				if ( cnt.getRoleid().equals(r.getId()) ) {
					r.setCount(cnt.getRpcount());
				}
			}
		}
		
		// 查询数量
		int count = roleDao.queryRoleCount(paramMap);
		
		rolePage.setData(roles);
		rolePage.setRecordsTotal(count);
		rolePage.setRecordsFiltered(count);
		
		return rolePage;
	}

	public void insertRole(Role role) {
		roleDao.insertRole(role);
	}

	public Role queryById(Integer id) {
		return roleDao.queryById(id);
	}

	public void updateRole(Role role) {
		roleDao.updateRole(role);
	}

	public void deleteRoles(Datas ds) {
		roleDao.deleteRoles(ds);
	}

	public void insertRolePermissions(Datas ds, Integer roleid) {
		
		// 删除当前角色已经分配的许可信息
		roleDao.deleteAllPermission(roleid);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roleid", roleid);
		for ( Integer id : ds.getIds() ) {
			paramMap.put("permissionid", id);
			roleDao.insertRolePermission(paramMap);
		}
	}
}
