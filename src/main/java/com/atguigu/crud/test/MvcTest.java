package com.atguigu.crud.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.atguigu.crud.bean.Employee;
import com.github.pagehelper.PageInfo;

/**
 * ʹ��Spring����ģ���ṩ�Ĳ��������ܣ�����CRUD�������ȷ��
 * Spring4���Ե�ʱ����ҪServlet 3.0��֧��
 * @author Administrator
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:applicationContext.xml","file:src/main/webapp/WEB-INF/springDispatcherServlet-servlet.xml"})
public class MvcTest {
	
	//����SpringMVC��IOC����
	@Autowired
	WebApplicationContext context;
	
	//����MVC���󣬻�ȡ��������
	MockMvc mockMvc;
	
	//��ʼ��MockMvc��Ҳ��������SpringMVC��IOC����
	@Before
	public void initMockMvc(){
		mockMvc=MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	//����
	@Test
	public void test() throws Exception{
		//ģ��URL�����õ�����ֵ
		MvcResult result=mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("pageNum", "4"))
		.andReturn();
		
		//����ɹ��Ժ��������л���PageInfo,����pageInfo������֤
		MockHttpServletRequest request = result.getRequest();
		PageInfo pageInfo=(PageInfo)request.getAttribute("pageInfo");
		System.out.println("��ǰҳ��"+pageInfo.getPageNum());
		System.out.println("��ҳ��"+pageInfo.getPages());
		System.out.println("�ܹ����������ݣ�"+pageInfo.getTotal());
		System.out.println("��ǰ������ʾ��ҳ�룺");
		int[] pages=pageInfo.getNavigatepageNums();
		for(int page:pages){
			System.out.println(page);
		}
		System.out.println("��ǰ��ʾҳ��ĵ�һҳ�룺"+pageInfo.getNavigateFirstPage());
		System.out.println("��ǰ��ʾҳ��ĵ�һҳ�룺"+pageInfo.getNavigateLastPage());
		
		//��ȡԱ������
		List<Employee> emps = pageInfo.getList();
		for(Employee emp:emps){
			System.out.println("ID:"+emp.getdId()+"NAME:"+emp.getEmpName());
		}
	}
}




