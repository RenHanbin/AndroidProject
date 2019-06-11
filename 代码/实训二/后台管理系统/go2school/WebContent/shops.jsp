<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">


<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Tell the browser to be responsive to screen width -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<!-- Favicon icon -->
<!-- 设置路径 -->
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<link rel="icon" type="image/png" sizes="16x16"
	href="${path}/assets/images/favicon.png">
<title>商家管理</title>
<!-- Bootstrap Core CSS -->
<link href="${path}/assets/plugins/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom CSS -->
<link href="${path}/css/style.css" rel="stylesheet">
<!-- You can change the theme colors from here -->
<link href="${path}/css/colors/blue.css" id="theme" rel="stylesheet">
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
</head>


<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<body class="fix-header fix-sidebar card-no-border">
	<!-- ============================================================== -->
	<!-- Preloader - style you can find in spinners.css -->
	<!-- ============================================================== -->
	<div class="preloader">
		<svg class="circular" viewBox="25 25 50 50">
            <circle class="path" cx="50" cy="50" r="20" fill="none"
				stroke-width="2" stroke-miterlimit="10" /> </svg>
	</div>
	<!-- ============================================================== -->
	<!-- Main wrapper - style you can find in pages.scss -->
	<!-- ============================================================== -->
	<div id="main-wrapper">
		<!-- ============================================================== -->
		<!-- Topbar header - style you can find in pages.scss -->
		<!-- ============================================================== -->
		<header class="topbar">
			<nav class="navbar top-navbar navbar-toggleable-sm navbar-light">
				<!-- ============================================================== -->
				<!-- Logo -->
				<!-- ============================================================== -->
				<div class="navbar-header">
					<a class="navbar-brand" href="${path }/index.jsp"> <!-- Logo icon --> <b>
							<!--You can put here icon as well // <i class="wi wi-sunset"></i> //-->

							<!-- Light Logo icon --> <img
							src="${path }/assets/images/logo.png" width="50" height="50" alt="homepage"
							class="light-logo" />
					</b> <!--End Logo icon --> <!-- Logo text --> <span> <!-- Light Logo text -->
							<img src="${path }/assets/images/logo-light-text.png"
							class="light-logo" alt="homepage" /></span>
					</a>
				</div>
				<!-- ============================================================== -->
				<!-- End Logo -->
				<!-- ============================================================== -->
				<div class="navbar-collapse">
					<!-- ============================================================== -->
					<!-- toggle and nav items -->
					<!-- ============================================================== -->
					<ul class="navbar-nav mr-auto mt-md-0">
						<!-- This is  -->
						<li class="nav-item"><a
							class="nav-link nav-toggler hidden-md-up text-muted waves-effect waves-dark"
							href="javascript:void(0)"><i class="mdi mdi-menu"></i></a></li>
						<!-- ============================================================== -->
						<!-- Search -->
						<!-- ============================================================== -->
						<li class="nav-item hidden-sm-down search-box"><a
							class="nav-link hidden-sm-down text-muted waves-effect waves-dark"
							href="javascript:void(0)"><i class="ti-search"></i></a>
							<form class="app-search">
								<input type="text" class="form-control"
									placeholder="Search & enter"> <a class="srh-btn"><i
									class="ti-close"></i></a>
							</form></li>
					</ul>
					<!-- ============================================================== -->
					<!-- User profile and search -->
					<!-- ============================================================== -->
					<ul class="navbar-nav my-lg-0">
						<!-- ============================================================== -->
						<!-- Profile -->
						<!-- ============================================================== -->
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle text-muted waves-effect waves-dark"
							href="" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"><img src="${managerImg }" alt="user"
								class="profile-pic m-r-10" />${manager.managerName }</a></li>
					</ul>
				</div>
			</nav>
		</header>
		<!-- ============================================================== -->
		<!-- End Topbar header -->
		<!-- ============================================================== -->
		<!-- ============================================================== -->
		<!-- Left Sidebar - style you can find in sidebar.scss  -->
		<!-- ============================================================== -->
		<aside class="left-sidebar">
			<!-- Sidebar scroll-->
			<div class="scroll-sidebar">
				<!-- Sidebar navigation-->
				<nav class="sidebar-nav">
					<ul id="sidebarnav">
						<li><a class="waves-effect waves-dark"
							href="${path }/index.jsp" aria-expanded="false"><i
								class="mdi mdi-gauge"></i><span class="hide-menu">管理员信息</span></a></li>
						<li><a class="waves-effect waves-dark"
							href="${path }/user/getUserList" aria-expanded="false"><i
								class="mdi mdi-account-check"></i><span class="hide-menu">用户管理</span></a>
						</li>
						<li><a class="waves-effect waves-dark"
							href="${path }/shop/getShopList" aria-expanded="false"><i
								class="mdi mdi-emoticon"></i><span class="hide-menu">商家管理</span></a>
						</li>
						<li><a class="waves-effect waves-dark"
							href="${path}/question/getQuestionList" aria-expanded="false"><i
								class="mdi mdi-table"></i><span class="hide-menu">问题管理</span></a></li>
						<li>
                        <a class="waves-effect waves-dark" href="${path}/article/getArticleList" aria-expanded="false">
                        <i class="mdi mdi-book-open-variant"></i><span class="hide-menu">文章管理</span>
                        </a>
                        </li>
					</ul>

				</nav>
				<!-- End Sidebar navigation -->
			</div>
			<!-- End Sidebar scroll-->
			<!-- Bottom points-->
			<div class="sidebar-footer">
				<!-- item-->
				<a href="" class="link" data-toggle="tooltip" title="Settings"><i
					class="ti-settings"></i></a>
				<!-- item-->
				<a href="" class="link" data-toggle="tooltip" title="Email"><i
					class="mdi mdi-gmail"></i></a>
				<!-- item-->
				<a href="${path }/manager/unLogin" class="link" data-toggle="tooltip" title="Logout"><i
					class="mdi mdi-power"></i></a>
			</div>
			<!-- End Bottom points-->
		</aside>
		<!-- ============================================================== -->
		<!-- End Left Sidebar - style you can find in sidebar.scss  -->
		<!-- ============================================================== -->
		<!-- ============================================================== -->
		<!-- Page wrapper  -->
		<!-- ============================================================== -->
		<div class="page-wrapper">
			<!-- ============================================================== -->
			<!-- Container fluid  -->
			<!-- ============================================================== -->
			<div class="container-fluid">
				<!-- ============================================================== -->
				<!-- Bread crumb and right sidebar toggle -->
				<!-- ============================================================== -->
				<div class="row page-titles">
					<div class="col-md-5 col-8 align-self-center">
						<h3 class="text-themecolor m-b-0 m-t-0">商家管理</h3>
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="javascript:void(0)">主页</a></li>
							<li class="breadcrumb-item active">商家管理</li>
						</ol>
					</div>
					<div class="col-md-7 col-4 align-self-center">
						<form action="${path}/shop/getLikeShopList" method="post"
							align="right">
							<input id="search" type="text" name="searchstr" class="username"
								placeholder="搜索" size="20">
							<button type="submit"
								class="btn waves-effect waves-light btn-warning hidden-md-down">搜索</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="${path}/shop/addShop"
								class="btn waves-effect waves-light btn-danger pull-right hidden-sm-down">添加</a>
						</form>
					</div>
				</div>
				<!-- ============================================================== -->
				<!-- End Bread crumb and right sidebar toggle -->
				<!-- ============================================================== -->
				<!-- ============================================================== -->
				<!-- Start Page Content -->
				<!-- ============================================================== -->
				<div class="row">
					<!-- column -->
					<div class="col-lg-12">
						<div class="card">
							<div class="card-block">
								<h4 class="card-title">商铺信息</h4>
								<div class="table-responsive">
									<table class="table">
										<thead>
											<tr>
												<th>商铺id</th>
												<th>商铺名</th>
												<th>商铺连接</th>
												<th></th>
											</tr>
										</thead>
										<tbody>
											<c:if test="${remark=='getShopList' }">
												<c:forEach items="${shopList}" var="shop">
													<tr>
														<td>${shop.shopId}</td>
														<td>${shop.shopName}</td>
														<td><a href="${shop.shopLink}">${shop.shopLink}</a></td>
														<td><a
															href="${path }/shop/findShopById?shopId=${shop.shopId}"
															class="link"><font class="font-medium">详 情</font> </a></td>
														<td><a
															href="${path}/shop/deleteShop?shopId=${shop.shopId}&&remark1=all"
															class="btn waves-effect waves-light btn-warning hidden-md-down">删除</a></td>
													</tr>
												</c:forEach>
											</c:if>
											<c:if test="${remark=='getLikeShopList' }">
												<c:forEach items="${shopListLike}" var="shop">
													<tr>
														<td>${shop.shopId}</td>
														<td>${shop.shopName}</td>
														<td>${shop.shopLink}</td>
														<td><a
															href="${path }/shop/findShopById?shopId=${shop.shopId}"
															class="link"><font class="font-medium">详 情</font> </a></td>
														<td><a
															href="${path}/shop/deleteShop?shopId=${shop.shopId}&&remark1=like"
															class="btn waves-effect waves-light btn-warning hidden-md-down">删除</a></td>
													</tr>
												</c:forEach>
											</c:if>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- ============================================================== -->
				<!-- End PAge Content -->
				<!-- ============================================================== -->
			</div>
			<!-- ============================================================== -->
			<!-- End Container fluid  -->
			<!-- ============================================================== -->
			<!-- ============================================================== -->
			<!-- footer -->
			<!-- ============================================================== -->
			<footer class="footer">
				Copyright©2019-2020,All Right Reserved
			</footer>
			<!-- ============================================================== -->
			<!-- End footer -->
			<!-- ============================================================== -->
		</div>
		<!-- ============================================================== -->
		<!-- End Page wrapper  -->
		<!-- ============================================================== -->
	</div>
	<!-- ============================================================== -->
	<!-- End Wrapper -->
	<!-- ============================================================== -->
	<!-- ============================================================== -->
	<!-- All Jquery -->
	<!-- ============================================================== -->
	<script src="${path}/assets/plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap tether Core JavaScript -->
	<script src="${path}/assets/plugins/bootstrap/js/tether.min.js"></script>
	<script src="${path}/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
	<!-- slimscrollbar scrollbar JavaScript -->
	<script src="${path }/js/jquery.slimscroll.js"></script>
	<!--Wave Effects -->
	<script src="${path }/js/waves.js"></script>
	<!--Menu sidebar -->
	<script src="${path }/js/sidebarmenu.js"></script>
	<!--stickey kit -->
	<script
		src="${path }/assets/plugins/sticky-kit-master/dist/sticky-kit.min.js"></script>
	<!--Custom JavaScript -->
	<script src="${path }/js/custom.min.js"></script>
</body>

</html>
