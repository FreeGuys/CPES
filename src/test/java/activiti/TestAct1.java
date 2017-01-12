package activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAct1 {

	public static void main(String args[]) {
		
		//加载Spring环境
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/*.xml");
		//获取流程框架的核心对象
		ProcessEngine pe = context.getBean("processEngine",ProcessEngine.class);
		
		//流程定义
		//将流程定义工具画的流程图加载到数据库当中，这一操作过程叫做部署
		
		//获取持久化服务的对象
		RepositoryService repositoryService = pe.getRepositoryService();
		//从类路径中加载流对象定义文件，部署到数据库中
		Deployment d = repositoryService.createDeployment().addClasspathResource("Myprocess.bpmn").deploy();
		
		//部署对象 （）
		System.out.println(d);
	}
}
	
