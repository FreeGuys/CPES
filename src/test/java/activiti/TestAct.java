package activiti;

import org.activiti.engine.ProcessEngine;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.vaadin.Application;


public class TestAct {

	public static void main(String args[]) {
		
		//加载Spring环境
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/*.xml");
		//获取流程框架的核心对象
		ProcessEngine pe = context.getBean("processEngine",ProcessEngine.class);
		
		System.out.println(pe);
	}
}
