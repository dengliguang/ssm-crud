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
 * ����Ա����CRUD����
 * url=/emps/{id}    method=GET      ��ѯ
 * url=/emps			method=POST	         ����
 * url=/emps/{id}	method=PUT		�޸�
 * url=/emps/{id} 	method=DELETE   ɾ��
 * @author Administrator
 *
 */
@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	/**
	 * ͨ��AJAX���󣬲�����json���ݸ��ͻ���
	 * ��Ҫ����jackson jar�ܰ� 
	 */
	@RequestMapping(value="/emps",method=RequestMethod.GET)
	@ResponseBody
	public Msg getEmpInfoWithJson(@RequestParam(value="pageNum",defaultValue="1") int pageNum){
		//1.���õ�ǰ�ڼ�ҳ����ÿҳ��ʾ����������
		PageHelper.startPage(pageNum,10);
		//2.��ȡ��������  ---�������⣬����һ����ȡ�����е����ݣ�������,��δ����ݿ��з�ҳȡ��
		List<Employee> emps=employeeService.getEmpAll();
		for(Employee empl:emps){
			System.out.println(empl);
		}
		//3.��װpageInfo     7��ÿҳ��ʾ����ҳ��
		PageInfo page=new PageInfo(emps,7);
	
		return Msg.success().add("PageInfo",page);
	}
	
	/**
	 * ��ѯԱ������(��ҳ��ѯ) 
	 * @return
	 */
	//@RequestMapping("/emps")
	//�ڼ�ҳ��pageNum
	public String getEmps(@RequestParam(value="pageNum",defaultValue="1") Integer pageNum,
				Model model){
		//�ⲻ��һ����ҳ��ѯ
		//����PageHelper��ҳ���
		//1.�ڲ�ѯ֮ǰ����PageHelper����ҳ���ÿҳ���ݵ�����
		PageHelper.startPage(pageNum, 10);
		List<Employee> emps=employeeService.getEmpAll();
		//2.startPage��������������ѯ����һ����ҳ��ѯ
		//ʹ��pageInfo��װ��ѯ��Ľ��:5 ÿ�ζ���ʾ��ҳ��,ֻ��Ҫ��pageInfo����ҳ�������
		//��װ����ϸ�ķ�ҳ��Ϣ�����������ǲ�ѯ�������ݣ�����������ʾ��ҳ��
		PageInfo page=new PageInfo(emps,7);
		model.addAttribute("pageInfo",page);
		
		return "list";
	}	
	
	/**
	 * Ա������   
	 * ���ݶ���ҳ��ֱ�Ӱѱ����ݵ����ַ������ݹ��������ֱ���ö�����վ���
	 * url=/emps    method=post
	 * @param emp
	 * @return
	 */
	@RequestMapping(value="/emps",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveEmployee(@Valid Employee emp, BindingResult result){
		System.out.println(emp+"--------");
		if(result.hasErrors()){
			//У��ʧ�ܣ�Ӧ�÷���ʧ����Ϣ����ģ̬������ʾУ��ʧ�ܵĴ�����Ϣ
			Map<String,Object> map=new HashMap();
			List<FieldError> errors=result.getFieldErrors();
			for(FieldError error:errors){
				System.out.println("������ֶΣ�"+error.getField());
				System.out.println("�������Ϣ��"+error.getDefaultMessage());
				map.put(error.getField(), error.getDefaultMessage());
			}
			return Msg.failure().add("errorFields", map);
		}else{
			employeeService.saveEmp(emp);
			return Msg.success();
		}
		
	}
	
	//У�飺Ա�������Ƿ��ظ�
	@RequestMapping("/checkEmpName")
	@ResponseBody
	public Msg checkEmpName(@RequestParam("empName") String empName){
		//�������ݿ�ƥ���Ƿ��ظ�֮ǰ���Ƚ�����֤�����Ƿ�Ϸ�(ǰ����֤),�Ա�ҳ����֤��ʾʱ��ͬ��
		//��Ϊ��̨��֤����Ժ��ֱ�Ӹ�ǰ̨ҳ�淵���û������û��߲����ã������Ҫȫ����֤
		String regx="(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})";
		boolean b=empName.matches(regx);
		if(!b){
			return Msg.failure().add("va_msg", "�����룺2-5λ���ģ�����6-16ΪӢ�ĺ����ֵ����");
		}
		//�������ݿ��ظ�У��
		long count=employeeService.checkEmpName(empName);
		if(count>0){
			return Msg.failure().add("va_msg", "�û����Ѵ���");
		}
		return Msg.success();
	}
	
	//��1:   ��ѯ��Ա������Ϣ�����أ���ʾ���޸�ģ̬����
	@RequestMapping(value="/getEmp/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Msg getEmp(@PathVariable("id") Integer id){
		Employee employee=employeeService.getEmployee(id);
		return Msg.success().add("Employee", employee);
	}
	
	//�޸�2����ȡ�޸��Ժ�����ݲ��������ݿ�
	/**
	 * ���ֱ�ӷ���ajax PUT�������ݿ��Է��͹������ǲ����װ��Employee����  
	 * ԭ��
	 * Tomact��
	 * 		1.�Ὣ�������е����ݣ���װһ��map
	 * 		2.SpringMVC��װPOJO����ʱ
	 * 		3.ͨ��request.getParameter("empName")�ͻ�����mapȡֵ
	 * 				���POJO�е�ÿ�����Ե�ֵ��ͨ��request.getParameter("")
	 * ����ͨ��AJAXֱ�ӷ�PUT����
	 * 				ͨ��ԭ����HttpServletRequest��request.getParameter("")
	 * 		ԭ��TOMACT �����װPUT�������е����ݵ�map����SpringMVC�޷���ȡ��POJO����������,
	 * 			ֻ��POST��ʽ������Ż��װ��map����
	 * 
	 * Ҫֱ�ӷ���PUT������Ҫ����һ��������filter��HttpPutFormContentFilter
	 * @param employee
	 * @return
	 */
	@RequestMapping(value="/emps/{empId}",method=RequestMethod.PUT)
	@ResponseBody
	public Msg update(Employee employee){
		System.out.println("��Ҫ���µ����ݣ�"+employee);
		employeeService.updateEmp(employee);
		return Msg.success();
	}
	/**
	 * �࣬����ɾ����
	 * ����ids  ����� 1-2-3
	 * 		       ������1
	 * 	
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/emps/{ids}",method=RequestMethod.DELETE)
	@ResponseBody
	public Msg deleteEmp(@PathVariable("ids") String ids){
		//���ɾ��
		if(ids.contains("-")){
			List<Integer> list=new ArrayList();
			String[] str_id=ids.split("-");
			for(String str:str_id){
				Integer id=Integer.parseInt(str);
				list.add(id);
			}
			employeeService.deleteAllEmp(list);
		}else{
			//����ɾ��
			int id=Integer.parseInt(ids);
			employeeService.deleteEmp(id);
		}
		
		return Msg.success();
	}
	
	
	
	
}		


















