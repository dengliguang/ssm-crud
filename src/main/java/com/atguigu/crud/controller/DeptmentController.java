package com.atguigu.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.crud.bean.Department;
import com.atguigu.crud.bean.Msg;
import com.atguigu.crud.service.DeptmentService;
import com.atguigu.crud.service.EmployeeService;

/**
 * 新增--部门查询
 * @author Administrator
 *
 */
@Controller
public class DeptmentController {
	
	@Autowired
	DeptmentService deptmentService;
	//新增
	//新增--部门查询
	@RequestMapping("depts")
	@ResponseBody
	public Msg getDeptInfo(){
		List<Department> depts=deptmentService.getDeptAll();
		return Msg.success().add("depts", depts);
	}
}
