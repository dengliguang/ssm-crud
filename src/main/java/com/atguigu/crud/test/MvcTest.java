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
 * 使用Spring测试模块提供的测试请求功能，测试CRUD请求的正确性
 * Spring4测试的时候需要Servlet 3.0的支持
 * @author Administrator
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:applicationContext.xml","file:src/main/webapp/WEB-INF/springDispatcherServlet-servlet.xml"})
public class MvcTest {
	
	//传入SpringMVC的IOC容器
	@Autowired
	WebApplicationContext context;
	
	//虚拟MVC请求，获取到处理结果
	MockMvc mockMvc;
	
	//初始化MockMvc，也就是引入SpringMVC的IOC容器
	@Before
	public void initMockMvc(){
		mockMvc=MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	//测试
	@Test
	public void test() throws Exception{
		//模拟URL请求拿到请求值
		MvcResult result=mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("pageNum", "4"))
		.andReturn();
		
		//请求成功以后，请求域中会有PageInfo,可以pageInfo进行验证
		MockHttpServletRequest request = result.getRequest();
		PageInfo pageInfo=(PageInfo)request.getAttribute("pageInfo");
		System.out.println("当前页："+pageInfo.getPageNum());
		System.out.println("总页数"+pageInfo.getPages());
		System.out.println("总共多少条数据："+pageInfo.getTotal());
		System.out.println("当前连续显示的页码：");
		int[] pages=pageInfo.getNavigatepageNums();
		for(int page:pages){
			System.out.println(page);
		}
		System.out.println("当前显示页码的第一页码："+pageInfo.getNavigateFirstPage());
		System.out.println("当前显示页码的第一页码："+pageInfo.getNavigateLastPage());
		
		//获取员工数据
		List<Employee> emps = pageInfo.getList();
		for(Employee emp:emps){
			System.out.println("ID:"+emp.getdId()+"NAME:"+emp.getEmpName());
		}
	}
}




