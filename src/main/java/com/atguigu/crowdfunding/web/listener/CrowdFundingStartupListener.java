package com.atguigu.crowdfunding.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

/**
 * 
 * @author evaima
 *
 */
public class CrowdFundingStartupListener extends ContextLoaderListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		
		// 将WEB应用路径保存成常量方便使用
		ServletContext application = event.getServletContext();
		String appPath = application.getContextPath();
		
		application.setAttribute("APP_PATH", appPath);
	}

}
