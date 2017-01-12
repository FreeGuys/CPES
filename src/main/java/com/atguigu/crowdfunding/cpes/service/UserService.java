package com.atguigu.crowdfunding.cpes.service;

import java.util.Map;

import com.atguigu.crowdfunding.bean.Page;
import com.atguigu.crowdfunding.cpes.bean.User;

public interface UserService {

	User queryUserByLoginacct(String loginacct);

	Page<User> queryUserDatas(Map<String, Object> paramMap);

	

	void insertUser(User user);

}
