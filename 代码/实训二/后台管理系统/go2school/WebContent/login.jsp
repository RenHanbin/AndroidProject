<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>
<!DOCTYPE html>
<html>
<head>
<head>
<!-- 设置路径 -->
    <c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<meta charset="utf-8">
<title>登录页面</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- CSS -->
<link rel='stylesheet'
	href='http://fonts.googleapis.com/css?family=PT+Sans:400,700'>
<link rel="stylesheet" href="<%=basePath %>/go2school/assets/css/reset.css">
<link rel="stylesheet" href="<%=basePath %>/assets/css/supersized.css">
<link rel="stylesheet" href="<%=basePath %>/assets/css/style.css">
	
</head>
</head>
<body>
	<div class="page-container">
		<h1>登录</h1>
		<!-- ${path}/admin/checkAdmin -->
		<form action="${path}/manager/logina" method="post">
			<input type="text" name="managerName" value="${manager.managerName}" class="username" placeholder="用户名"> 
			<input type="password" name="managerPassword" value="${manager.managerPassword}" class="password" placeholder="密码">
			<button type="submit">登录</button>
			
				<span><c:if test="${loginMsg ne null }">
					<font size="2" color="#BCEE68">${loginMsg }</font>
					</c:if>
				</span>	
			
		</form>
	</div>
	<!-- Javascript -->
	<script src="<%=basePath %>/assets/js/jquery-1.8.2.min.js"></script>
	<script src="<%=basePath %>/assets/js/supersized.3.2.7.min.js"></script>
	<script src="<%=basePath %>/assets/js/supersized-init.js"></script>
	<script src="<%=basePath %>/assets/js/scripts.js"></script>
</body>
</html>