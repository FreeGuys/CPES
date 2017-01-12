package activiti;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAct2 {
public static void main(String args[]) {
		
		//加载Spring环境
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/*.xml");
		//获取流程框架的核心对象
		ProcessEngine pe = context.getBean("processEngine",ProcessEngine.class);
		
		//流程定义
		//将流程定义工具画的流程图加载到数据库当中，这一操作过程叫做部署
		
		//获取持久化服务的对象
		RepositoryService repositoryService = pe.getRepositoryService();
		//创建流程定义查询对象
		ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
		//查询流程定义对象
//		List<ProcessDefinition> pds = query.list();
//		
//		for (ProcessDefinition pd : pds) {
//			System.out.println("______________________________");
//			System.out.println(pd.getKey());
//			System.out.println(pd.getName());
//			System.out.println(pd.getVersion());
//			System.out.println("______________________________");
//		}
		
		ProcessDefinition pd = 
				query.processDefinitionKey("myProcess").processDefinitionVersion(1).singleResult();
		System.out.println("______________________________");
		System.out.println(pd.getKey());
		System.out.println(pd.getName());
		System.out.println(pd.getVersion());
		System.out.println("______________________________");
	}
}
