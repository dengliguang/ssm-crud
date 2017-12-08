package com.atguigu.crud.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Binding;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.crud.bean.Employee;
import com.atguigu.crud.bean.Msg;
import com.atguigu.crud.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 处理员工的CRUD请求
 * url=/emps/{id}    method=GET      查询
 * url=/emps			method=POST	         保存
 * url=/emps/{id}	method=PUT		修改
 * url=/emps/{id} 	method=DELETE   删除
 * @author Administrator
 *
 */
@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	/**
	 * 通过AJAX请求，并返回json数据给客户端
	 * 需要导入jackson jar架包 
	 */
	@RequestMapping(value="/emps",method=RequestMethod.GET)
	@ResponseBody
	public Msg getEmpInfoWithJson(@RequestParam(value="pageNum",defaultValue="1") int pageNum){
		//1.设置当前第几页，和每页显示多少条数据
		PageHelper.startPage(pageNum,10);
		//2.获取所有数据  ---存在问题，这是一次性取出所有的数据，不合适,如何从数据库中分页取出
		List<Employee> emps=employeeService.getEmpAll();
		for(Employee empl:emps){
			System.out.println(empl);
		}
		//3.封装pageInfo     7：每页显示多少页码
		PageInfo page=new PageInfo(emps,7);
	
		return Msg.success().add("PageInfo",page);
	}
	
	/**
	 * 查询员工数据(分页查询) 
	 * @return
	 */
	//@RequestMapping("/emps")
	//第几页：pageNum
	public String getEmps(@RequestParam(value="pageNum",defaultValue="1") Integer pageNum,
				Model model){
		//这不是一个分页查询
		//引入PageHelper分页插件
		//1.在查询之前调用PageHelper传入页码和每页数据的条数
		PageHelper.startPage(pageNum, 10);
		List<Employee> emps=employeeService.getEmpAll();
		//2.startPage后面紧跟的这个查询就是一个分页查询
		//使用pageInfo包装查询后的结果:5 每次都显示的页数,只需要将pageInfo交给页面就行了
		//封装了详细的分页信息，包括有我们查询出的数据，传入连续显示的页数
		PageInfo page=new PageInfo(emps,7);
		model.addAttribute("pageInfo",page);
		
		return "list";
	}	
	
	/**
	 * 员工保存   
	 * 传递对象：页面直接把表单数据当成字符串传递过来，这边直接用对象接收就行
	 * url=/emps    method=post
	 * @param emp
	 * @return
	 */
	@RequestMapping(value="/emps",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveEmployee(@Valid Employee emp, BindingResult result){
		System.out.println(emp+"--------");
		if(result.hasErrors()){
			//校验失败：应该返回失败信息，在模态框中显示校验失败的错误信息
			Map<String,Object> map=new HashMap();
			List<FieldError> errors=result.getFieldErrors();
			for(FieldError error:errors){
				System.out.println("错误的字段："+error.getField());
				System.out.println("错误的信息："+error.getDefaultMessage());
				map.put(error.getField(), error.getDefaultMessage());
			}
			return Msg.failure().add("errorFields", map);
		}else{
			employeeService.saveEmp(emp);
			return Msg.success();
		}
		
	}
	
	//校验：员工名字是否重复
	@RequestMapping("/checkEmpName")
	@ResponseBody
	public Msg checkEmpName(@RequestParam("empName") String empName){
		//进行数据库匹配是否重复之前，先进行验证输入是否合法(前端验证),以便页面验证显示时能同步
		//因为后台验证完毕以后会直接给前台页面返回用户名可用或者不可用，因此需要全面验证
		String regx="(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})";
		boolean b=empName.matches(regx);
		if(!b){
			return Msg.failure().add("va_msg", "请输入：2-5位中文，或者6-16为英文和数字的组合");
		}
		//进行数据库重复校验
		long count=employeeService.checkEmpName(empName);
		if(count>0){
			return Msg.failure().add("va_msg", "用户名已存在");
		}
		return Msg.success();
	}
	
	//修1:   查询该员工的信息并返回，显示在修改模态框中
	@RequestMapping(value="/getEmp/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Msg getEmp(@PathVariable("id") Integer id){
		Employee employee=employeeService.getEmployee(id);
		return Msg.success().add("Employee", employee);
	}
	
	//修改2：获取修改以后的数据并存入数据库
	/**
	 * 如果直接发送ajax PUT请求，数据可以发送过来但是不会封装到Employee对象  
	 * 原因：
	 * Tomact：
	 * 		1.会将请求体中的数据，封装一个map
	 * 		2.SpringMVC封装POJO对象时
	 * 		3.通过request.getParameter("empName")就会从这个map取值
	 * 				会把POJO中的每个属性的值，通过request.getParameter("")
	 * 但是通过AJAX直接发PUT请求：
	 * 				通过原生的HttpServletRequest：request.getParameter("")
	 * 		原因：TOMACT 不会封装PUT请求体中的数据到map里，因此SpringMVC无法获取到POJO的属性数据,
	 * 			只有POST形式的请求才会封装到map里面
	 * 
	 * 要直接发送PUT请求需要配置一个过滤器filter：HttpPutFormContentFilter
	 * @param employee
	 * @return
	 */
	@RequestMapping(value="/emps/{empId}",method=RequestMethod.PUT)
	@ResponseBody
	public Msg update(Employee employee){
		System.out.println("将要更新的数据："+employee);
		employeeService.updateEmp(employee);
		return Msg.success();
	}
	/**
	 * 多，单个删除：
	 * 传入ids  多个： 1-2-3
	 * 		       单个：1
	 * 	
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/emps/{ids}",method=RequestMethod.DELETE)
	@ResponseBody
	public Msg deleteEmp(@PathVariable("ids") String ids){
		//多个删除
		if(ids.contains("-")){
			List<Integer> list=new ArrayList();
			String[] str_id=ids.split("-");
			for(String str:str_id){
				Integer id=Integer.parseInt(str);
				list.add(id);
			}
			employeeService.deleteAllEmp(list);
		}else{
			//单个删除
			int id=Integer.parseInt(ids);
			employeeService.deleteEmp(id);
		}
		
		return Msg.success();
	}
	
	
	
	
}		


















