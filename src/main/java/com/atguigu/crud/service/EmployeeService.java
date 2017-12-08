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
	 * 查询所有员工
	 * @return
	 */
	public List<Employee> getEmpAll() {
		return employeeMapper.selectByExampleWithDept(null);
	}
	
	//保存员工信息(新增)
	public void saveEmp(Employee emp) {
		employeeMapper.insertSelective(emp);
	}
	
	//检验用户名是否重用了
	public long checkEmpName(String empName) {
		//EmployeeExample  这个类没看懂是什么意思
		EmployeeExample example=new EmployeeExample();
		Criteria criteria=example.createCriteria();
		criteria.andEmpNameEqualTo(empName);
		long count=employeeMapper.countByExample(example);
		return count;
	}
	
	//根据员工ID查询该员工的信息
	public Employee getEmployee(Integer id) {
		Employee emp=employeeMapper.selectByPrimaryKey(id);
		return emp;	
	}
	
	//修改：修改以后把数据插入数据库
	public void updateEmp(Employee employee) {
		employeeMapper.updateByPrimaryKeySelective(employee);	
	}
	
	//删除单个员工
	public void deleteEmp(Integer id) {
		employeeMapper.deleteByPrimaryKey(id);	
	}
	
	//多个删除
	public void deleteAllEmp(List<Integer> ids) {
		EmployeeExample example=new EmployeeExample();
		Criteria criteria=example.createCriteria();
		//delete from XXX where emp_id in(1,2,3)
		criteria.andEmpIdIn(ids);
		employeeMapper.deleteByExample(example);
	}
}















