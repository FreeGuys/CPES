package com.atguigu.crowdfunding.cpes.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.crowdfunding.bean.Page;
import com.atguigu.crowdfunding.cpes.bean.Role;
import com.atguigu.crowdfunding.cpes.bean.User;
import com.atguigu.crowdfunding.cpes.service.UserService;
import com.atguigu.crowdfunding.util.Const;
import com.atguigu.crowdfunding.util.MD5Util;
import com.atguigu.crowdfunding.util.StringUtil;

@Controller
@RequestMapping("/system/user")
public class UserController {

	private Font mFont = new Font("Times New Roman", Font.BOLD, 24);
	
	@Autowired
	private UserService userService;
	
	/**
	 * 新增角色数据
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/insert")
	public Object insert( User user ) {
		start();
		
		try {
			userService.insertUser(user);
			success(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			success(false);
		}
		
		return end();
	}
	private Object end() {
		// TODO Auto-generated method stub
		return null;
	}
	private void success(boolean b) {
		// TODO Auto-generated method stub
		
	}
	private void start() {
		// TODO Auto-generated method stub
		
	}
	/**
	 * 跳转到新增角色页面
	 * @param pid
	 * @param model
	 * @return
	 */
	@RequestMapping("/add")
	public String add() {
		return "user/add";
	}
	
	
	/**
	 * 跳转到用户主页面
	 * @return
	 */
	@RequestMapping("/index")
	public String index() {
		return "user/index";
	}
	
	/**
	 * 用户登录
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/login")
	public Object login( User user, String authcode, HttpSession session ) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String sesscode = (String)session.getAttribute(Const.AUTHCODE);
		if (authcode == null || !authcode.equals(sesscode)) {
			resultMap.put("success", false);
			resultMap.put("error", "验证码不正确。请重新输入");
		} else {
			// 根据登录账号查询用户信息
			User dbUser = userService.queryUserByLoginacct(user.getLoginacct());
			if ( dbUser == null ) {
				resultMap.put("success", false);
				resultMap.put("error", "用户账号不存在。请重新输入");
			} else {
			    if ( dbUser.getUserpswd().equals(MD5Util.digest(user.getUserpswd())) ) {
			    	resultMap.put("success", true);
			    	session.setAttribute(Const.SESS_D_USER, dbUser);
			    } else {
			    	resultMap.put("success", false);
			    	resultMap.put("error", "用户账号/密码不正确。请重新输入");
			    }
			}
		}
		return resultMap;
	}
	
	/**
	 * 图形验证码
	 * @param response
	 * @param r1
	 * @param session
	 * @throws Exception
	 */
	@RequestMapping("/verify")
	public void webverify( HttpServletResponse response, Integer r1, HttpSession session ) throws Exception {
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
		
		response.setContentType("image/png");
		
		int width  = 100;
		int height = 40;
		
		// 画布
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		// 画笔
		Graphics g = image.getGraphics();
		
		String color = "#59B0FF";
		if ( StringUtil.isNotEmpty(color) ) {
			g.setColor(new Color(Integer.parseInt(color.substring(1, 3), 16),Integer.parseInt(color.substring(3, 5), 16),Integer.parseInt(color.substring(5, 7), 16)));	
		} else {
			g.setColor(new Color(50,118,177));
		}
		
		g.fillRect(1, 1, width-1, height-1);
		//66D178
		//g.setColor(new Color(204,204,204));
		g.setColor(new Color(77,170,255));
		g.drawRect(0, 0, width-1, height-1);
		g.setFont(mFont);
		g.setColor(new Color(255,255,255));
		
		String content = "";
		int i = r1;
		
		int i1 = new Random().nextInt(10);
		
		content = i + " + " + i1 + " = ? ";
		
		g.drawString(content, 10, 25);
		session.setAttribute(Const.AUTHCODE, ""+(i+i1));
		g.dispose();
		ImageIO.write(image, "PNG", response.getOutputStream());
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
		
		Page<User> userPage = userService.queryUserDatas(paramMap);
		userPage.setDraw(draw);
		return userPage;
	}
}
