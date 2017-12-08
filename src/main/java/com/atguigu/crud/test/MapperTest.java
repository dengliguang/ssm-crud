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
 * Spring��Ԫ����:
 * ����DepartmentMapper
 * �Ƽ�Spring����Ŀ����ʹ��Spring�ĵ�Ԫ���ԣ������Զ�ע��������Ҫ�����
 * 1.����SpringTestģ��
 * 2.@ContextConfigurationָ��Spring�����ļ���λ��
 * 3.@RunWith(SpringJUnit4ClassRunner.class)ָ��ʹ�þ����ĸ���Ԫ����
 * 4.ֱ��@Autowiredָ��Ҫʹ�õ����
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class MapperTest {
	@Autowired
	DepartmentMapper departmentMapper;
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	//��Spring xml�ļ�������������ִ�е�<constructor-arg name="executorType" value="BATCH">
	@Autowired
	SqlSession sqlSession;
	
	@Test
	public void testCRUD(){
	/*	
		//1.����Spring IOC����
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		//2.�������л�ȡmapper
		DepartmentMapper bean=context.getBean(DepartmentMapper.class);
	*/	
		System.out.println(departmentMapper);
		
		//����
//		departmentMapper.insertSelective(new Department(null, "������"));
//		departmentMapper.insertSelective(new Department(null, "���Բ�"));
		
		//�޸�
//		departmentMapper.updateByPrimaryKeySelective(new Department(1, ""));
//		departmentMapper.updateByPrimaryKey(new Department(1, "������"));
		
		//����
//		List<Department> list=departmentMapper.selectByExample(new DepartmentExample());
//		for(Department dt:list){
//			System.out.println(dt);
//		}
//		Department dt=departmentMapper.selectByPrimaryKey(2);
//		System.out.println(dt);
		
		//ɾ��
//		departmentMapper.deleteByPrimaryKey(1);
//		departmentMapper.deleteByExample(new DepartmentExample());
		
		
//����Ա����
		//����
//		employeeMapper.insertSelective(new Employee(null, "Tom", "M", "Tom@atguigu.com", 1));
		//����������Ա����Ϣ��������ʹ�ÿ���ִ��������sqlSession
		/*
		EmployeeMapper mapper=sqlSession.getMapper(EmployeeMapper.class);
		for(int i=0;i<=1000;i++){
			String uid=UUID.randomUUID().toString().substring(0, 5)+i;
			mapper.insertSelective(new Employee(null, uid, "M", uid+"@atguigu.com", 1));
		}
		System.out.println("�������!");
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



