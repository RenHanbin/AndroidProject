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
<title>用户简介页面</title>
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
							aria-expanded="false"><img src="${managerImg}"
								alt="user" class="profile-pic m-r-10" />${manager.managerName }</a></li>
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
						<li><a class="waves-effect waves-dark" href="${path }/index.jsp"
							aria-expanded="false"><i class="mdi mdi-gauge"></i><span
								class="hide-menu">管理员信息</span></a></li>
						<li><a class="waves-effect waves-dark" href="${path }/user/getUserList"
							aria-expanded="false"><i class="mdi mdi-account-check"></i><span
								class="hide-menu">用户管理</span></a></li>
						<li><a class="waves-effect waves-dark" href="${path }/shop/getShopList"
							aria-expanded="false"><i class="mdi mdi-emoticon"></i><span
								class="hide-menu">商家管理</span></a></li>
						<li><a class="waves-effect waves-dark" href="${path}/question/getQuestionList"
							aria-expanded="false"><i class="mdi mdi-table"></i><span
								class="hide-menu">问题管理</span></a></li>
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
						<h3 class="text-themecolor m-b-0 m-t-0">用户信息</h3>
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="javascript:void(0)">主页</a></li>
							<li class="breadcrumb-item active">用户信息</li>
						</ol>
					</div>
				
				</div>
				<!-- ============================================================== -->
				<!-- End Bread crumb and right sidebar toggle -->
				<!-- ============================================================== -->
				<!-- ============================================================== -->
				<!-- Start Page Content -->
				<!-- ============================================================== -->
				<!-- Row -->
				<div class="row">
                    <!-- Column -->
                    <div class="col-lg-4 col-xlg-3 col-md-5">
                        <!-- Column -->
                        <div class="card">
                            <img class="card-img-top" src="${path }/assets/images/background/profile-bg.jpg" alt="Card image cap">
                            <div class="card-block little-profile text-center">
                                <div class="pro-img"><img src="${path }/assets/images/users/4.jpg" alt="user" /></div>
                                <h3 class="m-b-0">${user.userName }</h3>
                              <div class="row text-center m-t-20">
                                   	<!-- 关注的用户的人数 -->
                                    <div class="col-lg-4 col-md-4 m-t-20">
                                        <h3 class="m-b-0 font-light">${userAtten }</h3><small>关注人</small></div>
                                   <!-- 粉丝人数 --> 
                                    <div class="col-lg-4 col-md-4 m-t-20">
                                        <h3 class="m-b-0 font-light">${userFan.userFans }</h3><small>粉丝数</small></div>
                                </div>

                            </div>
                        </div>
                        <!-- 用户左侧底部关于用户粉丝或者关注人的信息的显示 -->
                        <c:if test="${userAtten ne 0}">
                        <div class="card">
                            <div class="card-block bg-info">
                                <h4 class="text-white card-title">关注人</h4>
                                <h6 class="card-subtitle text-white m-b-0 op-5">该用户所关注的用户</h6>
                            </div>
                          
                            <div class="card-block">
                                <div class="message-box contact-box">
                                    <h2 class="add-ct-btn"><button type="button" class="btn btn-circle btn-lg btn-success waves-effect waves-dark">+</button></h2>
                                    <div class="message-widget contact-widget">
                                        <!-- Message -->
                                        
                                        <c:forEach items="${follows}" var="follow">
                                        <a href="${path }/user/findUserById?userId=${follow.userId}">
                                            <div class="user-img"> <img src="http://img3.duitang.com/uploads/item/201605/07/20160507191419_J2m8R.thumb.700_0.jpeg" alt="user" class="img-circle"> <span class="profile-status online pull-right"></span> </div>
                                            <div class="mail-contnet">
                                                <h5>${follow.userName}</h5> <span class="mail-desc">${follow.userEmail }</span></div>
                                        </a>
                                        </c:forEach>
                                    

                                        
                                        
                                    </div>
                                </div>
                            </div>
                           
                        </div>
                        </c:if>
                    </div>
                    <div class="col-lg-8 col-xlg-9 col-md-7">
                    <!-- 右侧用户问题，评论和用户信息修改处 -->
                        <div class="card">
                            <!-- Nav tabs -->
                            <ul class="nav nav-tabs profile-tab" role="tablist">
                               
                                <li class="nav-item"> <a class="nav-link" data-toggle="tab" href="#settings" role="tab">个人设置</a> </li>
                            </ul>
                            <!-- Tab panes -->
                            <div class="tab-content">
                    
                            <!-- 用户个人信息设值 -->
                                <div class="tab-pane" id="settings" role="tabpanel">
                                    <div class="card-block">
                                        <form class="form-horizontal form-material" action="${path }/user/updateUser">
                                        <div class="row text-center justify-content-md-center">
	                                        <div class="col-4">
	                                        <a href="javascript:void(0)" class="link">
	                                        <i class="icon-people"></i> 
	                                        <%-- <input type="text" placeholder="${user.userName }" class="form-control form-control-line" value="${user.userName }" name="userName"> --%>
	                                   
	                                        <input type="hidden" class="form-control form-control-line"  name="userId" value="${user.userId }" >
	               
	                                        </a>
	                                     	</div> 
                                       		
                                    	</div>
                                            <div class="form-group">
                                                <label class="col-md-12">用户名</label>
                                                <div class="col-md-12">
                                                    <input type="text" placeholder="${user.userName }" class="form-control form-control-line" value="${user.userName }" name="userName">
                                                </div>
                                            </div>
                                            <div class="form-group">
										<label for="example-email" class="col-md-12">邮箱</label>
										<div class="col-md-12">
											<input type="email" value="${user.userEmail }" name="userEmail"
												class="form-control form-control-line" 
												id="example-email">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-12">密码</label>
										<div class="col-md-12">
											<input type="password" value="${user.userPassword }" name="userPassword"
												class="form-control form-control-line">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-12">Tel</label>
										<div class="col-md-12">
											<input type="text" value="${user.userTel }" name="userTel"
												class="form-control form-control-line">
										</div>
									</div>
                                            
                                            
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <button class="btn btn-success">更新</button>
                                                    <c:if test="${userLog ne null }">
														<font size="2" color="#BCEE68">${userLog }</font>
													</c:if>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                </div>
				<!-- Row -->
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
</body>
</html>