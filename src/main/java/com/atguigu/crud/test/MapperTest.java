package com.atguigu.crud.test;

import java.util.List;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.atguigu.crud.bean.Department;
import com.atguigu.crud.bean.DepartmentExample;
import com.atguigu.crud.bean.Employee;
import com.atguigu.crud.bean.EmployeeExample;
import com.atguigu.crud.dao.DepartmentMapper;
import com.atguigu.crud.dao.EmployeeMapper;

/**
 * Spring单元测试:
 * 测试DepartmentMapper
 * 推荐Spring的项目可以使用Spring的单元测试，可以自动注入我们需要的组件
 * 1.导入SpringTest模块
 * 2.@ContextConfiguration指定Spring配置文件的位置
 * 3.@RunWith(SpringJUnit4ClassRunner.class)指定使用具体哪个单元测试
 * 4.直接@Autowired指定要使用的组件
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class MapperTest {
	@Autowired
	DepartmentMapper departmentMapper;
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	//在Spring xml文件里配置了批量执行的<constructor-arg name="executorType" value="BATCH">
	@Autowired
	SqlSession sqlSession;
	
	@Test
	public void testCRUD(){
	/*	
		//1.创建Spring IOC容器
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		//2.从容器中获取mapper
		DepartmentMapper bean=context.getBean(DepartmentMapper.class);
	*/	
		System.out.println(departmentMapper);
		
		//新增
//		departmentMapper.insertSelective(new Department(null, "开发部"));
//		departmentMapper.insertSelective(new Department(null, "测试部"));
		
		//修改
//		departmentMapper.updateByPrimaryKeySelective(new Department(1, ""));
//		departmentMapper.updateByPrimaryKey(new Department(1, "开发部"));
		
		//查找
//		List<Department> list=departmentMapper.selectByExample(new DepartmentExample());
//		for(Department dt:list){
//			System.out.println(dt);
//		}
//		Department dt=departmentMapper.selectByPrimaryKey(2);
//		System.out.println(dt);
		
		//删除
//		departmentMapper.deleteByPrimaryKey(1);
//		departmentMapper.deleteByExample(new DepartmentExample());
		
		
//测试员工表：
		//插入
//		employeeMapper.insertSelective(new Employee(null, "Tom", "M", "Tom@atguigu.com", 1));
		//批量插入多个员工信息：批量，使用可以执行批量的sqlSession
		/*
		EmployeeMapper mapper=sqlSession.getMapper(EmployeeMapper.class);
		for(int i=0;i<=1000;i++){
			String uid=UUID.randomUUID().toString().substring(0, 5)+i;
			mapper.insertSelective(new Employee(null, uid, "M", uid+"@atguigu.com", 1));
		}
		System.out.println("批量完成!");
		*/
		
//		List<Employee> list=employeeMapper.selectByExampleWithDept(new EmployeeExample());
//		for(Employee employee:list){
//			System.out.println(employee);
//		}
//		
//		Employee emp=employeeMapper.selectByPrimaryKeyWithDept(1);
//		System.out.println(emp);
	}
}



