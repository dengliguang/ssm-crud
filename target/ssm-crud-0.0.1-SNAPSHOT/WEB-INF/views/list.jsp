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
	<!-- 搭建显示页面 -->
	<div class="container" >
		<!-- 标题 -->
		<div class="row">
			<div class="col-md-12">
				<h1>SSM-CRUD</h1>
			</div>
		</div>
		<!-- 增删按钮 -->
		<div class="col-md-4 col-md-offset-8">
			<button class="btn btn-success">新增</button>
			<button class="btn btn-danger">删除</button>
		</div>

		<!-- 显示表格数据 -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover"> 
					<tr>
						<th>ID</th>
						<th>empName</th>
						<th>gender</th>
						<th>email</th>
						<th>deptName</th>
						<th>操作</th>
					</tr>
					<c:forEach items="${requestScope.pageInfo.list }" var="emp">					
					<tr>
						<td>${emp.empId }</td>
						<td>${emp.empName }</td>
						<td>${emp.gender=="M" ? "男" : "女" }</td>
						<td>${emp.email }</td>
						<td>${emp.department.deptName }</td>
						<td>
						<button class="btn btn-success btn-sm">
						<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
							编辑
						</button>
						
						<button class="btn btn-danger btn-sm">
						<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>	
							删除
						</button>	
						</td>
					</tr>
				</c:forEach>
				</table>
			</div>
		</div>
	
		<!-- 显示分页信息 -->
		<div class="row">
			<!-- 分页文字信息 -->
			<div class="col-md-6">
				当前第${requestScope.pageInfo.pageNum }页
				总共有${requestScope.pageInfo.pages }页
				总共有${requestScope.pageInfo.total }条数据
			</div>
			<!-- 分页条信息 -->
			<div class="col-md-6">
				<nav aria-label="Page navigation">
				  <ul class="pagination">
				  
				  	<li>
				  		<a href="${APP_PATH }/emps?pageNum=1">首页</a>
				  	</li>
				  	
				  	<c:if test="${requestScope.pageInfo.hasPreviousPage }">
				    <li>
				      <a href="${APP_PATH }/emps?pageNum=${requestScope.pageInfo.pageNum-1 }" aria-label="Previous">
				        <span aria-hidden="true">&laquo;</span>
				      </a>
				    </li>
				    </c:if>
				    <c:forEach items="${requestScope.pageInfo.navigatepageNums }" var="page">
				   	<c:if test="${requestScope.pageInfo.pageNum == page }">
				    	<li class="active"><a href="#">${page }</a></li>
				    </c:if>
				    <c:if test="${requestScope.pageInfo.pageNum != page }">
				    	<li><a href="${APP_PATH }/emps?pageNum=${page }">${page }</a></li>
				    </c:if>
				    </c:forEach>
				    
				    <c:if test="${requestScope.pageInfo.hasNextPage }">
				    <li>
				      <a href="${APP_PATH }/emps?pageNum=${requestScope.pageInfo.pageNum+1 }" aria-label="Next">
				        <span aria-hidden="true">&raquo;</span>
				      </a>
				    </li>
				    </c:if>
				    
				    <li>
				    	<a href="${APP_PATH }/emps?pageNum=${requestScope.pageInfo.pages }">尾页</a>
				    </li>
				  </ul>
				</nav>
			</div>
		</div>
	</div>
	
</body>
</html>




