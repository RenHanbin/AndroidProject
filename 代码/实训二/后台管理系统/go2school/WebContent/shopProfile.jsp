<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge"></meta>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- Tell the browser to be responsive to screen width -->
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta name="description" content="" />
<meta name="author" content="" />
<!-- 设置路径 -->
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!-- Favicon icon -->
<link rel="icon" type="image/png" sizes="16x16"
	href="${path }/assets/images/favicon.png">
<title>升学问后台管理系统</title>
<!-- Bootstrap Core CSS -->
<link href="${path }/assets/plugins/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<!-- chartist CSS -->
<link href="${path }/assets/plugins/chartist-js/dist/chartist.min.css"
	rel="stylesheet">
<link href="${path }/assets/plugins/chartist-js/dist/chartist-init.css"
	rel="stylesheet">
<link
	href="${path }/assets/plugins/chartist-plugin-tooltip-master/dist/chartist-plugin-tooltip.css"
	rel="stylesheet">
<!--This page css - Morris CSS -->
<link href="${path }/assets/plugins/c3-master/c3.min.css"
	rel="stylesheet">
<!-- Custom CSS -->
<link href="${path }/css/style.css" rel="stylesheet">
<!-- You can change the theme colors from here -->
<link href="${path }/css/colors/blue.css" id="theme" rel="stylesheet">
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
</head>

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
		<!-- 标题头样式- style you can find in pages.scss -->
		<!-- ============================================================== -->
		<header class="topbar">
			<nav class="navbar top-navbar navbar-toggleable-sm navbar-light">
				<!-- ============================================================== -->
				<!-- 网站后台logo -->
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
					<!-- 左侧列表压缩页图标 -->
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
					<!-- 商铺的信息展示 -->
					<!-- ============================================================== -->
					<ul class="navbar-nav my-lg-0">
						<!-- ============================================================== -->
						<!-- Profile -->
						<!-- ============================================================== -->
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle text-muted waves-effect waves-dark"
							href="" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <img
								src="${managerImg }" alt="user"
								class="profile-pic m-r-10" />${manager.managerName }</a></li>
					</ul>
				</div>
			</nav>
		</header>
		<!-- ============================================================== -->
		<!-- ============================================================== -->
		<!-- Left Sidebar - style you can find in sidebar.scss  -->
		<!-- ============================================================== -->
		<aside class="left-sidebar">
			<!-- Sidebar scroll-->
			<div class="scroll-sidebar">
				<!-- 左侧页面导航栏-->
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

			<div class="container-fluid">

				<div class="row page-titles">
					<div class="col-md-5 col-8 align-self-center">
						<h3 class="text-themecolor">商家信息</h3>
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="javascript:void(0)">主页</a></li>
							<li class="breadcrumb-item active">商家信息</li>
						</ol>
					</div>
				
				</div>
				<!-- 商家信息的修改页面 -->
					<!-- remark为null的情况，就是修改商铺信息页面 -->
					<c:if test="${remark eq null }">
					<div class="row">
						<div class="col-lg-8 col-xlg-9 col-md-7">
							<div class="card">
								<div class="card-block">
										<form class="form-horizontal form-material"
											action="${path}/shop/updateShopById" method="post">
											<input type="hidden" class="form-control form-control-line"
												name="shopId" value="${shop.shopId }" />
											<div class="row text-center justify-content-md-center">
												<div class="col-4">
													<a href="javascript:void(0)" class="link"> <i
														class="icon-people"></i> <font class="font-medium"
														name="shopId" value="${shop.shopId }">${shop.shopId }</font>
													</a>
												</div>
	
											</div>
	
											<div class="form-group">
												<label class="col-md-12">商家姓名</label>
												<div class="col-md-12">
													<input type="text" value="${shop.shopName}" name="shopName"
														class="form-control form-control-line">
												</div>
											</div>
											<div class="form-group">
												<label for="example-email" class="col-md-12">商家链接</label>
												<div class="col-md-12">
													<input type="text" value="${shop.shopLink }" name="shopLink"
														class="form-control form-control-line" id="example-email">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-12">入驻时间</label>
												<div class="col-md-12">
													<input type="date" value="${shop.shopSettlingTime }"
														name="shopSettlingTime"
														class="form-control form-control-line">${shop.shopSettlingTime }
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-12">商家备注</label>
												<div class="col-md-12">
													<textarea rows="5" class="form-control form-control-line" name="shopNotes" value="${shop.shopNotes }">${shop.shopNotes }</textarea>
												</div>
											</div>
											<div class="form-group">
												<div class="col-sm-12">
													<button class="btn btn-success">保存更改</button>
													<c:if test="${shopMessage ne null }">
														<font size="2" color="#BCEE68">${shopMessage }</font>
												</c:if>
												</div>
											</div>
										</form>
								</div>
							</div>
						</div>
					</div>				
					</c:if>
					<!-- remark不是null的情况，就是商铺入驻页面 -->
					<c:if test="${remark ne null }">
						<div class="row">
						<div class="col-lg-8 col-xlg-9 col-md-7">
							<div class="card">
								<div class="card-block">
										<form class="form-horizontal form-material"
											action="${path}/shop/insertShop" method="post">
											<%-- <input type="hidden" class="form-control form-control-line"
												name="shopId" value="${shop.shopId }" /> --%>
											<div class="row text-center justify-content-md-center">
												<%-- <div class="col-4">
													<a href="javascript:void(0)" class="link"> <i
														class="icon-people"></i> <font class="font-medium"
														name="shopId" value="${shop.shopId }">${shop.shopId }</font>
													</a>
												</div> --%>
	
											</div>
	
											<div class="form-group">
												<label class="col-md-12">商家姓名</label>
												<div class="col-md-12">
													<input type="text" name="shopName" value="${shop.shopName }"
														class="form-control form-control-line">
												</div>
											</div>
											<div class="form-group">
												<label for="example-email" class="col-md-12">商家链接</label>
												<div class="col-md-12">
													<input type="text"  name="shopLink" value="${shop.shopLink }"
														class="form-control form-control-line" id="example-email">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-12">入驻时间</label>
												<div class="col-md-12">
													<input type="date" 
														name="shopSettlingTime" value="${shop.shopSettlingTime }"
														class="form-control form-control-line">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-12">商家备注</label>
												<div class="col-md-12">
													<textarea rows="5" class="form-control form-control-line" name="shopNotes" value="${shop.shopNotes }">${shop.shopNotes }</textarea>
												</div>
											</div>
											<div class="form-group">
												<div class="col-sm-12">
													<button class="btn btn-success" type="submit">入驻</button>
													<c:if test="${shopMessage ne null }">
														<font size="2" color="#BCEE68">${shopMessage }</font>
													</c:if>
												</div>
											</div>
										</form>
								</div>
							</div>
						</div>
					</div>	
					</c:if>			
							
			</div>
			
			<!-- ============================================================== -->
			<!-- End Container fluid  -->
			<!-- ============================================================== -->
			<!-- ============================================================== -->
			<!-- 底部footer -->
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

	<script src="${path }/assets/plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap tether Core JavaScript -->
	<script src="${path }/assets/plugins/bootstrap/js/tether.min.js"></script>
	<script src="${path }/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
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
	<!-- ============================================================== -->
	<!-- This page plugins -->
	<!-- ============================================================== -->
	<!-- chartist chart -->
	<script src="${path }/assets/plugins/chartist-js/dist/chartist.min.js"></script>
	<script
		src="${path }/assets/plugins/chartist-plugin-tooltip-master/dist/chartist-plugin-tooltip.min.js"></script>
	<!--c3 JavaScript -->
	<script src="${path }/assets/plugins/d3/d3.min.js"></script>
	<script src="${path }/assets/plugins/c3-master/c3.min.js"></script>
	<!-- Chart JS -->
	<script src="${path }/js/dashboard1.js"></script>
</body>

</html>