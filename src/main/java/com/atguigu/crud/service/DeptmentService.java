package com.atguigu.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atguigu.crud.bean.Department;
import com.atguigu.crud.dao.DepartmentMapper;

@Repository
public class DeptmentService {
	@Autowired
	DepartmentMapper departmentMapper;
	
	public List<Department> getDeptAll() {
		//�������в�����Ϣ
		return departmentMapper.selectByExample(null);
	}
}
