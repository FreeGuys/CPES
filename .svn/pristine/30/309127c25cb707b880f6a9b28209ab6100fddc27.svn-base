package com.atguigu.crowdfunding.cpes.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crowdfunding.bean.Page;


import com.atguigu.crowdfunding.cpes.bean.User;
import com.atguigu.crowdfunding.cpes.bean.UserRoleCount;
import com.atguigu.crowdfunding.cpes.dao.UserDao;
import com.atguigu.crowdfunding.cpes.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	public User queryUserByLoginacct(String loginacct) {
		return userDao.queryUserByLoginacct(loginacct);
	}

	@Override
	public Page<User> queryUserDatas(Map<String, Object> paramMap) {
		// 分页对象
				Page<User> userPage = new Page<User>();
				
				// 查询数据
				List<User> users = userDao.queryUserDatas(paramMap);
				int index = 1;
				for ( User u : users ) {
					u.setIndex(index++);
				}
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("users", users);
				List<UserRoleCount> cnts = userDao.queryUserRoleCount(map);
				for ( UserRoleCount cnt : cnts ) {
					for ( User u : users ) {
						if ( cnt.getUserid().equals(u.getId()) ) {
							u.setCount(cnt.getUpcount());
						}
					}
				}
				
				// 查询数量
				int count = userDao.queryUserCount(paramMap);
				
				userPage.setData(users);
				userPage.setRecordsTotal(count);
				userPage.setRecordsFiltered(count);
				
				return userPage;
	}

	@Override
	public void insertUser(User user) {
		userDao.insertUser(user);
		
	}
}
