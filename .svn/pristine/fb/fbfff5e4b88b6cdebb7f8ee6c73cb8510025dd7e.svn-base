package com.atguigu.crowdfunding.cpes.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.crowdfunding.bean.Datas;
import com.atguigu.crowdfunding.bean.Page;
import com.atguigu.crowdfunding.controller.BaseController;
import com.atguigu.crowdfunding.cpes.bean.Permission;
import com.atguigu.crowdfunding.cpes.bean.Role;
import com.atguigu.crowdfunding.cpes.bean.RolePermission;
import com.atguigu.crowdfunding.cpes.service.PermissionService;
import com.atguigu.crowdfunding.cpes.service.RoleService;

@Controller
@RequestMapping("/system/role")
public class RoleController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private PermissionService permissionService;
	
	/**
	 * 跳转到用户主页面
	 * @return
	 */
	@RequestMapping("/index")
	public String index() {
		return "role/index";
	}
	
	/**
	 * 跳转到新增角色页面
	 * @param pid
	 * @param model
	 * @return
	 */
	@RequestMapping("/add")
	public String add() {
		return "role/add";
	}
	
	/**
	 * 跳转到修改角色页面
	 * @param pid
	 * @param model
	 * @return
	 */
	@RequestMapping("/edit/{id}")
	public String edit( @PathVariable("id")Integer id, Model model ) {
		
		Role role = roleService.queryById(id);
		model.addAttribute("role", role);
		
		return "role/edit";
	}
	
	/**
	 * 跳转到分配许可页面
	 * @param pid
	 * @param model
	 * @return
	 */
	@RequestMapping("/permission/{id}")
	public String permission( @PathVariable("id")Integer id, Model model ) {
		
		Role role = roleService.queryById(id);
		model.addAttribute("role", role);
		
		return "role/permission";
	}
	
	/**
	 * 新增角色数据
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/insert")
	public Object insert( Role role ) {
		start();
		
		try {
			roleService.insertRole(role);
			success(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			success(false);
		}
		
		return end();
	}
	
	/**
	 * 修改角色数据
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/update")
	public Object update( Role role ) {
		start();
		
		try {
			roleService.updateRole(role);
			success(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			success(false);
		}
		
		return end();
	}
	
	/**
	 * 删除角色数据
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deletes")
	public Object deletes( Datas ds ) {
		start();
		
		try {
			//roleService.updateRole(role);
			roleService.deleteRoles(ds);
			success(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			success(false);
		}
		
		return end();
	}
	
	/**
	 * 角色分配权限
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/assign")
	public Object assign( Datas ds, Integer roleid ) {
		start();
		
		try {
			roleService.insertRolePermissions(ds, roleid);
			success(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			success(false);
		}
		
		return end();
	}
	
	/**
	 * 分页查询数据
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/datas")
	public Object datas( Integer draw, Integer start, @RequestParam(value="length")Integer pagesize ) {
		// 分页对象
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("start", start);
		paramMap.put("pagesize", pagesize);
		
		Page<Role> rolePage = roleService.queryRoleDatas(paramMap);
		rolePage.setDraw(draw);
		return rolePage;
	}
	
	/**
	 * 构建许可树
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/permissiontree")
	public Object tree( Integer roleid ) {
		
		List<Permission> permissions = new ArrayList<Permission>(); 
		List<Permission> ps = permissionService.queryAll();
		Map<Integer, Permission> pmap = new HashMap<Integer, Permission>();
		
		// 查询角色已经分配的许可信息
		List<RolePermission> assignps = permissionService.queryAssignedPermission(roleid);
		
		for ( Permission p : ps ) {
			pmap.put(p.getId(), p);
		}
		
		for ( RolePermission rp : assignps ) {
			Permission node = pmap.get(rp.getPermissionid());
			node.setChecked(true);
		}
		
		for ( Permission p : ps ) {
			Permission childPermission = p;
			int pid = p.getPid();
			Permission parentPermission = pmap.get(pid);
			if ( parentPermission == null ) {
				permissions.add(p);
			} else {
				parentPermission.getChildren().add(childPermission);
			}
		}
		
		return permissions;
	}
}
