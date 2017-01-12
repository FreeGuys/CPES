package com.atguigu.crowdfunding.cpes.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.crowdfunding.controller.BaseController;
import com.atguigu.crowdfunding.cpes.bean.Permission;
import com.atguigu.crowdfunding.cpes.service.PermissionService;

@Controller
@RequestMapping("/system/permission")
public class PermissionController extends BaseController {

	@Autowired
	private PermissionService permissionService;
	
	/**
	 * 跳转到用户主页面
	 * @return
	 */
	@RequestMapping("/index")
	public String index() {
		return "permission/index";
	}
	
	/**
	 * 跳转到新增许可页面
	 * @param pid
	 * @param model
	 * @return
	 */
	@RequestMapping("/add")
	public String add( Integer pid, Model model ) {
		model.addAttribute("pid", pid);
		return "permission/add";
	}
	
	/**
	 * 跳转到修改许可页面
	 * @param pid
	 * @param model
	 * @return
	 */
	@RequestMapping("/edit")
	public String edit( Integer id, Model model ) {
		
		// 主键查询许可信息
		Permission p = permissionService.queryPermissionById(id);
		model.addAttribute("p", p);
		return "permission/edit";
	}
	
	/**
	 * 新增许可信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/insert")
	public Object insert( Permission p ) {
		
		start();
		try {
			permissionService.insertPermission(p);
			success(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			success(false);
			error("许可信息保存失败");
		}
		return end();
		
//		Map<String, Object> resultMap = new HashMap<String, Object>();
//		try {
//			permissionService.insertPermission(p);
//			resultMap.put("success", true);
//		} catch ( Exception e ) {
//			e.printStackTrace();
//			resultMap.put("success", false);
//			resultMap.put("error", "许可信息保存失败");
//		}
//		
//		return resultMap;
	}
	
	/**
	 * 修改许可信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/update")
	public Object update( Permission p ) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			permissionService.updatePermission(p);
			resultMap.put("success", true);
		} catch ( Exception e ) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("error", "许可信息修改失败");
		}
		
		return resultMap;
	}
	
	/**
	 * 删除许可信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public Object delete( Permission p ) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			permissionService.deletePermission(p);
			resultMap.put("success", true);
		} catch ( Exception e ) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("error", "许可信息删除失败");
		}
		
		return resultMap;
	}
	
	/**
	 * 构建许可树
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/tree")
	public Object tree() {
		
		List<Permission> permissions = new ArrayList<Permission>(); 
		/*
		Permission p = new Permission();
		p.setName("权限管理");
		
		permissions.add(p);
		
		Permission p1 = new Permission();
		p1.setName("用户维护");
		
		Permission p2 = new Permission();
		p2.setName("角色维护");
		
		Permission p3 = new Permission();
		p3.setName("权限维护");
		
		p.getChildren().add(p1);
		p.getChildren().add(p2);
		p.getChildren().add(p3);
		*/
		// 读取系统菜单（许可）数据
		/*
		List<Permission> ps = permissionService.queryChildPermission(0);

		for ( Permission p : ps ) {
			int pid = p.getId();
			List<Permission> cps = permissionService.queryChildPermission(pid);
			for ( Permission cp : cps ) {
				int cpid = cp.getId();
				List<Permission> ccps = permissionService.queryChildPermission(cpid);
			}
		}
		*/
		// 5! = 5 * 4 * 3 * 2 *1 = ?
		/*
		Permission root = new Permission();
		root.setId(0);
		buildChildNode(root);
		
		permissions.addAll(root.getChildren());
		*/
		// 递归
		// 不断调用相同逻辑的处理
		// 一定要有跳出逻辑的处理
		// 在调用逻辑时传递的参数要有规律
		
		// 为了提高效率，只查询一次数据库就能够形成树形结构数据
		List<Permission> ps = permissionService.queryAll();
		
		/*
		 // 通过双重for循环实现许可菜单节点的关联
		for ( Permission p : ps ) {
			// 子菜单
			Permission childPermission = p;
			int pid = childPermission.getPid();
			if ( pid == 0 ) {
				permissions.add(p);
			} else {
				for ( Permission cp : ps ) {
					if ( cp.getId() == pid ) {
						// 父菜单
						Permission parentPermission = cp;
						// 组合父子菜单的关系
						parentPermission.getChildren().add(childPermission);
					}
				}
			}
		}
		*/
		Map<Integer, Permission> pmap = new HashMap<Integer, Permission>();
		for ( Permission p : ps ) {
			pmap.put(p.getId(), p);
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
	
	public void buildChildNode( Permission parentPermission ) {
		List<Permission> ps = permissionService.queryChildPermission(parentPermission.getId());
		for ( Permission p : ps ) {
			buildChildNode(p);
			// 组合许可菜单之间的父子关系
			parentPermission.getChildren().add(p);
		}
	}
}
