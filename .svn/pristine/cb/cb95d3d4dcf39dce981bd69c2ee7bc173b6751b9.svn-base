package activiti;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAct3 {
public static void main(String args[]) {
		
	// 加载Spring环境
			ApplicationContext context = new ClassPathXmlApplicationContext("spring/*.xml");
			
			// 获取流程框架的核心对象
			ProcessEngine pe = context.getBean("processEngine", ProcessEngine.class);
			
			// 启动（创建）流程实例
			// new User();
			
			// 加载最新的流程定义
			Deployment d = pe.getRepositoryService().createDeployment().addClasspathResource("FristEmail.bpmn").deploy();
			
			// 获取运行时服务对象
			RuntimeService runtimeService = pe.getRuntimeService();
			
			// 获取流程定义对象
			ProcessDefinitionQuery query =
				pe.getRepositoryService().createProcessDefinitionQuery();
			ProcessDefinition pd = query.latestVersion().singleResult();
			
			// 启动（创建）流程实例
			ProcessInstance pi = runtimeService.startProcessInstanceById(pd.getId());
			/*
				act_hi_actinst : 历史节点表
				act_hi_identitylink : 历史流程人员表
				act_hi_procinst : 历史流程实例表
				act_hi_taskinst : 历史任务实例表
				act_ru_execution : 运行时流程执行实例表
				act_ru_identitylink : 运行时流程人员表
				act_ru_task : 运行时任务节点表
			 * 
			 */
			System.out.println( pi );
	}
}
