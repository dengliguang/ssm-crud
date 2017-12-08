package com.atguigu.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atguigu.crud.bean.Department;
import com.atguigu.crud.bean.Employee;
import com.atguigu.crud.bean.EmployeeExample;
import com.atguigu.crud.bean.EmployeeExample.Criteria;
import com.atguigu.crud.dao.DepartmentMapper;
import com.atguigu.crud.dao.EmployeeMapper;

@Repository
public class EmployeeService {
	@Autowired
	EmployeeMapper employeeMapper;
	/**
	 * ��ѯ����Ա��
	 * @return
	 */
	public List<Employee> getEmpAll() {
		return employeeMapper.selectByExampleWithDept(null);
	}
	
	//����Ա����Ϣ(����)
	public void saveEmp(Employee emp) {
		employeeMapper.insertSelective(emp);
	}
	
	//�����û����Ƿ�������
	public long checkEmpName(String empName) {
		//EmployeeExample  �����û������ʲô��˼
		EmployeeExample example=new EmployeeExample();
		Criteria criteria=example.createCriteria();
		criteria.andEmpNameEqualTo(empName);
		long count=employeeMapper.countByExample(example);
		return count;
	}
	
	//����Ա��ID��ѯ��Ա������Ϣ
	public Employee getEmployee(Integer id) {
		Employee emp=employeeMapper.selectByPrimaryKey(id);
		return emp;	
	}
	
	//�޸ģ��޸��Ժ�����ݲ������ݿ�
	public void updateEmp(Employee employee) {
		employeeMapper.updateByPrimaryKeySelective(employee);	
	}
	
	//ɾ������Ա��
	public void deleteEmp(Integer id) {
		employeeMapper.deleteByPrimaryKey(id);	
	}
	
	//���ɾ��
	public void deleteAllEmp(List<Integer> ids) {
		EmployeeExample example=new EmployeeExample();
		Criteria criteria=example.createCriteria();
		//delete from XXX where emp_id in(1,2,3)
		criteria.andEmpIdIn(ids);
		employeeMapper.deleteByExample(example);
	}
}















