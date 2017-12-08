<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib	uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工列表</title>
<%
	pageContext.setAttribute("APP_PATH",request.getContextPath());
%>
<!--	web路径问题： 
	不以/开始的相对路径，找资源，是以当前资源的路径为基准，容易出问题
	以/开始的相对路径，找资源，以服务器的路径为标准(http://localhost:8080);需要加上项目名
				http://localhost:8080/ssm-crud
	src="${pageContext.request.getContextPath() }/static/js/jquery.min.js"			
 -->

<script type="text/javascript" src="${APP_PATH }/static/js/jquery.min.js"></script>
<!-- 引入bootStrap样式和js -->
<link href="${APP_PATH }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<script src="${APP_PATH }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body>

	<!-- 员工修改模态框 -->
	<div class="modal fade" id="empUpdateModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">修改员工信息</h4>
	      </div>
	      <div class="modal-body">
	  <!-- 表单数据 -->    
	  <form class="form-horizontal">
		  <div class="form-group">
		    <label for="inputEmail3" class="col-sm-2 control-label">empName</label>
		    <div class="col-sm-10">
		      <input type="text" name="empName" class="form-control" id="empName_update_input" placeholder="empName" readonly>
		      <span class="help-block"></span>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputPassword3" class="col-sm-2 control-label">email</label>
		    <div class="col-sm-10">
		      <input type="text" name="email" class="form-control" id="email_update_input" placeholder="email@atguigu.com">
		      <span class="help-block"></span>
		    </div>
		  </div> 
		  <!-- 性别选择 -->
		   <div class="form-group">
		    <label for="inputPassword3" class="col-sm-2 control-label">gender</label>
		    <div class="col-sm-10">
		      <label class="radio-inline">
				<input type="radio" name="gender" id="gender1_update_input" value="M"> 男
				</label>
				<label class="radio-inline">
				  <input type="radio" name="gender" id="gender2_update_input" value="F"> 女
				</label>
		    </div>
		  </div>
		  <!-- 部门名选择 -->
		  <div class="form-group" id="dept_update_info">
		    <label for="inputPassword3" class="col-sm-2 control-label">deptName</label>
		    <div class="col-sm-3">
		      <!-- 提交部门Id -->
		      <select class="form-control" name="dId">
		     	  <option>---请选择---</option>
			  </select>
		    </div>
		  </div>
	</form>
	      </div>
	      <div class="modal-footer" id="dept_update_btn">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <button type="button" class="btn btn-primary">更新</button>
	      </div>
	    </div>
	  </div>
	</div>


	<!-- 员工新增模态框 -->
	<div class="modal fade" id="empAddModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">新增员工</h4>
	      </div>
	      <div class="modal-body">
	  <!-- 表单数据 -->    
	  <form class="form-horizontal">
		  <div class="form-group">
		    <label for="inputEmail3" class="col-sm-2 control-label">empName</label>
		    <div class="col-sm-10">
		      <input type="text" name="empName" class="form-control" id="empName_add_input" placeholder="empName">
		      <span class="help-block"></span>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputPassword3" class="col-sm-2 control-label">email</label>
		    <div class="col-sm-10">
		      <input type="text" name="email" class="form-control" id="email_add_input" placeholder="email@atguigu.com">
		      <span class="help-block"></span>
		    </div>
		  </div>
		  <!-- 性别选择 -->
		   <div class="form-group">
		    <label for="inputPassword3" class="col-sm-2 control-label">gender</label>
		    <div class="col-sm-10">
		      <label class="radio-inline">
				<input type="radio" name="gender" id="gender1_add_input" value="M" checked="checked"> 男
				</label>
				<label class="radio-inline">
				  <input type="radio" name="gender" id="gender2_add_input" value="F"> 女
				</label>
		    </div>
		  </div>
		  <!-- 部门名选择 -->
		  <div class="form-group" id="dept_add_info">
		    <label for="inputPassword3" class="col-sm-2 control-label">deptName</label>
		    <div class="col-sm-3">
		      <!-- 提交部门Id -->
		      <select class="form-control" name="dId">
		     	  <option>---请选择---</option>
			  </select>
		    </div>
		  </div>  
	</form>
	      </div>
	      <div class="modal-footer" id="dept_add_btn">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <button type="button" class="btn btn-primary">保存</button>
	      </div>
	    </div>
	  </div>
	</div>


	<!-- 搭建显示页面 -->
	<div class="container" >
		<!-- 标题 -->
		<div class="row">
			<div class="col-md-12">
				<h1>SSM-CRUD</h1>
			</div>
		</div>
		<!-- 增删按钮 -->
		<div class="col-md-4 col-md-offset-8" id="op_di">
			<button class="btn btn-success" id="emp_add_model">新增</button>
			<button class="btn btn-danger" id="delete_all">删除</button>
		</div>

		<!-- 显示表格数据 -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover" id="tab"> 
					<thead>
						<tr>
							<th><input type="checkbox" name="emp_check" id="check_all"/></th>
							<th>ID</th>
							<th>empName</th>
							<th>gender</th>
							<th>email</th>
							<th>deptName</th>
							<th>操作</th>
						</tr>
					</thead>	
					<tbody></tbody>				
			
				
				</table>
			</div>
		</div>
	
		<!-- 显示分页信息 -->
		<div class="row">
			<!-- 分页文字信息 -->
			<div class="col-md-6">
				当前第<span id="sp1"></span> 页
				总共有<span id="sp2"></span> 页
				总共有<span id="sp3"></span> 条数据
			</div>
			<!-- 分页条信息 -->
			<div class="col-md-6" id="di">
				<nav aria-label="Page navigation">
			    	<ul class="pagination">
			    		
			    		
				    </ul>
				</nav>	
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		$(function(){
			to_page(1);
			
		});
		var totalPages,currentPage;
			//查询员工信息 
			function to_page(pn){
				$.ajax({
					url:"${APP_PATH }/emps",
					data:"pageNum="+pn,
					type:"GET",
					success:function(result){
						//console.log(result.extend.PageInfo)
						//var emps=result.extend.PageInfo.list;
						//解析并显示
						build_emps_table(result);
						build_page_info(result);
						build_page_nav(result);
						
					}
				});
			}
			
			//解析员工信息  
			function build_emps_table(result){
				//清空数据
				$("#tab tbody").empty();
				
				var emps=result.extend.PageInfo.list;
				$.each(emps,function(index,item){
					var check=$("<td></td>").append("<input type='checkbox' name='emp_check' class='check_item'/>");
					var empId=$("<td></td>").append(item.empId);
					var empName=$("<td></td>").append(item.empName);
					var gender=$("<td></td>").append(item.gender=="M"?"男":"女");
					var email=$("<td></td>").append(item.email);
					var deptName=$("<td></td>").append(item.department.deptName);
					var operate=$("<td></td>").append("<button edit_val='"+item.empId+"' id='update_btn' class='btn btn-success btn-sm'><span class='glyphicon glyphicon-pencil' aria-hidden='true'></span>编辑</button><button delete_val="+item.empId+" id='delete_btn' class='btn btn-danger btn-sm'><span class='glyphicon glyphicon-trash' aria-hidden='true'></span>删除</button>");
					$("<tr></tr>").append(check)
								  .append(empId)
								  .append(empName)
								  .append(gender)
								  .append(email)
								  .append(deptName)
								  .append(operate)
								  .appendTo($("#tab tbody"));		  
				});	
			}
			
			//解析分页信息
			function build_page_info(result){
				var emps=result.extend.PageInfo;
				$("#sp1").text(emps.pageNum);
				$("#sp2").text(emps.pages);
				$("#sp3").text(emps.total);
				totalPages=emps.pages;
				currentPage=emps.pageNum;
			}
			
			//解析分页条数据 
			function build_page_nav(result){
				//清空数据
				$("#di ul").empty();
				
				var pageInfo=result.extend.PageInfo;
				var firstPage=$("<li></li>").append("<a href='#' aria-label='Previous'><span aria-hidden='true'>首页</span>");
				var lastPage=$("<li></li>").append("<a href='#' aria-label='Previous'><span aria-hidden='true'>尾页</span>");
				
				var previousPage=$("<li></li>").append("<a href='#' aria-label='Previous'><span aria-hidden='true'>&laquo;</span>");
				var nextPage=$("<li></li>").append("<a href='#' aria-label='Previous'><span aria-hidden='true'>&raquo;</span>");
				
				//设置如果是第一页：那么首页和上一页不可点击，最后一页：那么尾页和下一页不可点击 
				$("#di ul").append(firstPage)
		  		   		   .append(previousPage)
		  		if(pageInfo.pageNum==1){
		  			firstPage.addClass("disabled");
		  			previousPage.addClass("disabled");
		  		}   
				
				if(pageInfo.pageNum==pageInfo.pages){
					nextPage.addClass("disabled");
					lastPage.addClass("disabled");
				}
				
				//为首页，前一页，后一页，尾页 添加点击事件 
				firstPage.click(function(){
					if(pageInfo.pageNum!=1){
						to_page(1);
					}
				});
				previousPage.click(function(){
					if(pageInfo.pageNum-1>0){
						to_page(pageInfo.pageNum-1);
					}
				});
				nextPage.click(function(){
					if(pageInfo.pageNum+1<=pageInfo.pages){
						to_page(pageInfo.pageNum+1);
					}
				});
				lastPage.click(function(){
					if(pageInfo.pageNum!=pageInfo.pages){
						to_page(pageInfo.pages);
					}
				});
				
				//获取每页显示的页码 
				$.each(pageInfo.navigatepageNums ,function(index,item){
					//console.log(item);
					var pages=$("<li></li>").append("<a href='#'>"+item+"</a>");		
					$("#di ul").append(pages)
					if(pageInfo.pageNum==item){
						pages.addClass("active");
					}
					pages.click(function(){
						to_page(item);
					}); 	 	   
				});
				$("#di ul").append(nextPage)
		 		  		   .append(lastPage); 	
		}
		
			//添加员工信息:查询部门
			function to_dept(elem){
				$.ajax({
					url:"${APP_PATH }/depts",
					type:"GET",
					success:function(result){
						build_dept_select(result,elem);
					}
				});
			}
		
			//新增按钮点击事件 
			$("#emp_add_model").click(function(){
				//重置表单 
				reset_form("#empAddModel form")
				
				to_dept("#dept_add_info select");
				$("#empAddModel").modal({
					backdrop:"static"
				})
			});	
			
			//表单重置：包括数据和样式 
			function reset_form(elem){
				$(elem)[0].reset();
				//清空样式：文本框显示样式    span提示信息显示样式
				$(elem).find("*").removeClass("has-error has-success");
				$(elem).find("span").text("");
			}
			
			//渲染部门数据到页面下拉选择框 
			function build_dept_select(result,elem){
				var depts=result.extend.depts;
				//fangfa1
				var options="";
				$.each(depts,function(index,item){
					console.log(item.deptName);
					options+="<option value='"+item.deptId+"'>"+item.deptName+"</option>";
					});
				
				//fangfa2
				//var options="";
				//for(var i=0;i<depts.length;i++){
				//options+="<option value='"+depts[i].deptId+"'>"+depts[i].deptName+"</option>";		
				//}
				$(elem).html(options);
			}
			
			//员工数据前端校验  
			function validate_add_form(){
				//对员工名字进行验证：允许a到z A到Z 0到9 6到16位     允许中文  2到5位 
				var empName=$("#empName_add_input").val();
				var regName=/(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})/;
				if(regName.test(empName)==false){
					//alert("请输入：2-5位中文，或者6-16为英文和数字的组合");
					show_validate_info("#empName_add_input","error","请输入：2-5位中文，或者6-16为英文和数字的组合");
					return false;
				}else{
					show_validate_info("#empName_add_input","success","");
				};
				//2.对邮箱进行验证
				var email=$("#email_add_input").val();
				var regEmail=/^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
				if(regEmail.test(email)==false){
					//alert("邮箱格式错误！");
					show_validate_info("#email_add_input","error","邮箱格式错误！");
					return false;
				}else{
					show_validate_info("#email_add_input","success","");
				}
				return true;
			}
			//封装显示方式 
			function show_validate_info(eles,status,msg){
				//清除指定样式，和span标签的文本
				$(eles).parent().removeClass("has-error has-success");
				$(eles).next("span").text("");
				if("error"==status){
					$(eles).parent().addClass("has-error");
					$(eles).next("span").text(msg);
				}else if("success"==status){
					$(eles).parent().addClass("has-success");
					$(eles).next("span").text(msg);
				}
			}
			
			//后台验证：对员工姓名进行后台数据库验证 
			var emp_code;
			$("#empName_add_input").change(function(){
				var empName=$("#empName_add_input").val();
				$.ajax({
					url:"${APP_PATH }/checkEmpName",
					data:"empName="+empName,
					type:"GET",
					success:function(result){
						console.log(result.code)
						emp_code=result.code;
						if(result.code==200){
							show_validate_info("#empName_add_input","error",result.extend.va_msg);
							$("#dept_add_btn .btn-primary").attr("ajax_va","error");
						}else if(result.code==100){
							show_validate_info("#empName_add_input","success","用户名可用！");
							$("#dept_add_btn .btn-primary").attr("ajax_va","success");
						}
					}		
				});
			});
			
			
			//保存员工信息 
			$("#dept_add_btn .btn-primary").click(function(){
				//console.log($("#empAddModel form").serialize());、
				//先查看后台验证状态  如果是200 那就不能进行前端验证 直接结束 
				if(emp_code==200){
					return false;
				}
				//前端校验员工数据  	
				if(validate_add_form()==false){
					return false;
				}
				
				if($("#dept_add_btn .btn-primary").attr("ajax_va")=="success"){
					//如果校验成功，传递所有数据给后台，保存到数据库  
					$.ajax({
						url:"${APP_PATH }/emps",
						type:"POST",
						data:$("#empAddModel form").serialize(),
						success:function(result){
							//console.log(result.msg);
							console.log(result);
							if(result.code==100){
								//关闭添加框 
								$('#empAddModel').modal('hide');
								//直接跳转到最后一页 
								to_page(totalPages);
							}else if(result.code==200){
								//显示失败信息：哪个字段失败显示哪个字段 
								if(undefined != result.extend.errorFields.email){
									//显示邮箱错误信息
									show_validate_info("#email_add_input","error",result.extend.errorFields.email);
								}
								if(undefined != result.extend.errorFields.empName){
									//显示员工名字错误信息 
									show_validate_info("#empName_add_input","success",result.extend.errorFields.empName);
								}
							}
						}
					});
				}	
			});
			
			//修改 ：  为编辑按钮绑定点击事件
			//对于动态添加按钮 直接添加click点击事件是添加不上的  
			//可以通过on() 来绑定 
			$(document).on("click","#update_btn",function(){
				var empId=$(this).attr("edit_val");
				//1.查询部门信息，并显示在修改框中
				to_dept("#dept_update_info select");
				//2.查询员工信息，并显示在修改框中 
				getEmp(empId);
				//3.把员工ID传递给更新按钮
				$("#dept_update_btn .btn-primary").attr("edit_val",empId);
				//显示修改模态框 
				$("#empUpdateModel").modal({
					backdrop:"static"
				})
			});
			//发送AJAX请求查询该员工的信息，并显示到修改模态框 
			function getEmp(id){
				$.ajax({
					url:"${APP_PATH }/getEmp/"+id,
					type:"GET",
					success:function(result){
						show_update_emp(result);
					}	
				});
			}	
			//渲染修改员工框员工的数据 
			function show_update_emp(result){
				var empVal=result.extend.Employee;
				console.log(empVal);
				$("#empName_update_input").val(empVal.empName);
				$("#email_update_input").val(empVal.email);
				if(empVal.gender=="M"){
					$("#gender1_update_input").attr("checked","checked");
				}else{
					$("#gender2_update_input").attr("checked","checked");
				}
				$("#dept_update_info select").val(empVal.dId);    
			} 
			//点击更新按钮：进行更新 
			$("#dept_update_btn .btn-primary").click(function(){
				//1.进行验证： 进行邮箱验证
				var email=$("#email_update_input").val();
				var regEmail=/^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
				if(regEmail.test(email)==false){
					//alert("邮箱格式错误！");
					show_validate_info("#email_update_input","error","邮箱格式错误！");
					return false;
				}else{
					show_validate_info("#email_update_input","success","");
				}
				console.log($("#empUpdateModel form").serialize());
				// 发送ajax请求 给后台进行更新
				//如果直接发送ajax请求，数据可以发送过去但是不会封装到Employee对象  
				$.ajax({
					url:"${APP_PATH }/emps/"+$(this).attr("edit_val"),
					//data:$("#empUpdateModel form").serialize()+"&_method=PUT",
					data:$("#empUpdateModel form").serialize(),
					type:"PUT",
					success:function(result){
						//关闭模态框
						$('#empUpdateModel').modal('hide');
						//并回到本页面(修改的页面)
						to_page(currentPage);
					}	
				});
			});
			//为删除按钮绑定点击事件 
			$(document).on("click","#delete_btn",function(){
				//1.弹出确认框
				var empName=$(this).parents("tr").find("td:eq(1)").text();
				if(confirm("确认删除:"+empName+"吗！！！")){
					//点击确定：确认--发送ajax请求，进行删除
					$.ajax({
						url:"${APP_PATH }/emps/"+$(this).attr("delete_val"),
						type:"DELETE",
						success:function(result){
							//回到本页
							to_page(currentPage);
						}
					});
				}
			});
			//为全选按钮添加点击事件
			$("#check_all").click(function(){
				console.log($(this).prop("checked"));
				//$(".check_item").attr("checked","checked");
				/*
				var b=$(this).prop("checked");
				if(b){
					$(".check_item").prop("checked",true);
				}else{
					
					$(".check_item").prop("checked",false);
				}
				*/
				$(".check_item").prop("checked",$(this).prop("checked"));
			});
			
			//为每一个按钮，添加点击事件 
			$(document).on("click",".check_item",function(){
				var length=$(".check_item:checked").length;
				//console.log($(".check_item").prop("checked"));
				if(length==10){
					$("#check_all").prop("checked",true);
				}else{
					$("#check_all").prop("checked",false);
				}
				
			});
			
			//为一键删除按钮添加点击事件
			$("#delete_all").click(function(){
				//console.log($(".check_item:checked").parents("tr").find("td:eq(2)").text());
				var empNames="",empId="";
				$.each($(".check_item:checked"),function(){
					empNames+=$(this).parents("tr").find("td:eq(2)").text()+","
					empId+=$(this).parents("tr").find("td:eq(1)").text()+"-"
					//console.log($(this).parents("tr").find("td:eq(1)").text());
				});
				empNames=empNames.substr(0,empNames.length-1);
				empId=empId.substr(0,empId.length-1);
				console.log(empId);
				if(confirm("确认 删除"+empNames+"吗？？")){
					$.ajax({
						url:"${APP_PATH }/emps/"+empId,
						type:"DELETE",
						success:function(result){
							alert(result.msg);
							to_page(currentPage);
						}	
					});
				}
				
			});
			
			
	</script>
	
</body>
</html>













