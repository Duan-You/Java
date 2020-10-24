<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<!--[if IE 9]><html class="no-js lt-ie10" lang="en"> <![endif]-->
<!--[if gt IE 9]><!-->
<html class="no-js" lang="en">
<!--<![endif]-->

<head>
    <meta charset="utf-8">
    <title>学生成绩管理系统</title>
    <meta name="description" content="学生成绩管理系统">
    <meta name="robots" content="noindex, nofollow">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/materialize.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-table.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
</head>

<body>
<div id="page-wrapper" class="page-loading">
    <div class="preloader">
        <div class="inner">
            <div class="preloader-spinner themed-background hidden-lt-ie10"></div>
            <h3 class="text-primary visible-lt-ie10"><strong>Loading..</strong></h3>
        </div>
    </div>
    <div id="page-container" class="header-fixed-top sidebar-visible-lg-full">
        <div id="sidebar">
            <div id="sidebar-brand" class="themed-background">
                <a href="#" class="sidebar-title">
                    <i class="fa fa-cog"></i>
                    <span class="sidebar-nav-mini-hide">学生成绩管理系统<small>Student_Grade_System</small></span>
                </a>
            </div>
            <div id="sidebar-scroll">
                <div class="sidebar-content">
                    <ul class="sidebar-nav">
                        <li>
                            <a href="#"><i class="glyphicon glyphicon-home sidebar-nav-icon"></i><span
                                    class="sidebar-nav-mini-hide">首页</span></a>
                        </li>

                        <li>
                            <a href="#" class="sidebar-nav-menu"><i
                                    class="icon-chevron-right sidebar-nav-indicator sidebar-nav-mini-hide"></i>
                                <i class="glyphicon glyphicon-send sidebar-nav-icon" aria-hidden="true"></i><span
                                        class="sidebar-nav-mini-hide">成绩管理</span></a>
                            <ul>
                                <li>
                                    <a href="#">查看</a>
                                </li>
                                <li>
                                    <a href="#">录入</a>
                                </li>
                                <li>
                                    <a href="#">修改</a>
                                </li>

                            </ul>
                        </li>

                    </ul>
                    <!-- END Sidebar Navigation -->
                </div>
            </div>
        </div>
        <div id="main-container">
            <header class="navbar navbar-inverse navbar-fixed-top">
                <ul class="nav navbar-nav-custom">
                    <li>
                        <a href="javascript:void(0)" id="treeNavControl">
                            <i class="glyphicon glyphicon-th-list animation-fadeInRight" id="sidebar-toggle-mini"></i>
                            <i class="glyphicon glyphicon-th-list animation-fadeInRight" id="sidebar-toggle-full"></i>
                        </a>
                    </li>
                </ul>
                <ul class="nav navbar-nav-custom pull-right">
                    <li class="dropdown">
                        <a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">
                            <i class="glyphicon glyphicon-user sidebar-nav-icon fa-3x"></i>
                            <span>${loginUser.name}</span>
                            <span id="loginUserId" style="display: none">${loginUser.id}</span>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-right">

                            <li>
                                <a id="logout" href="javascript:void(0)">
                                    <i class="glyphicon glyphicon-off pull-right"></i> 退出
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </header>
            <div id="page-content">
                <div class="row">
                    <div class="col-md-12">
                        <!-- Advanced Tables -->
                        <div class="card">
                            <div class="panel panel-primary">
                                <div class="panel-heading">查询条件</div>
                                <div class="panel-body">
                                    <form id="formSearch" class="form-horizontal">
                                        <div class="form-group" style="margin-top:15px">
                                            <label class="control-label col-sm-1"
                                                   for="txt_search_coursename">课程名称</label>
                                            <div class="col-sm-3">
                                                <select id="txt_search_coursename" class="input-xlarge">

                                                </select>
                                            </div>
                                            <label class="control-label col-sm-1" for="txt_search_term">学年</label>
                                            <div class="col-sm-2">
                                                <select id="txt_search_term_year" class="input-xlarge">
                                                    <option>大一</option>
                                                    <option>大二</option>
                                                    <option>大三</option>
                                                    <option>大四</option>
                                                </select>
                                            </div>
                                            <label class="control-label col-sm-1" for="txt_search_term">学期</label>
                                            <div class="col-sm-2">
                                                <select id="txt_search_term" class="input-xlarge">
                                                    <option>第一学期</option>
                                                    <option>第二学期</option>
                                                </select>
                                            </div>
                                            <div class="col-sm-2" style="text-align:left;">
                                                <button type="button" style="margin-left:50px" id="btn_query"
                                                        class="btn btn-primary">查询
                                                </button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <!-- 查询-->
                            <div id="toolbar" class="btn-group">
                                <button id="btn_edit" type="button" class="btn btn-primary">
                                    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>提交
                                </button>
                            </div>
                            <!--操作-->
                        </div>

                        <div class="card-content">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="tb_grade"></table>
                            </div>
                        </div>
                    </div>
                    <!--End Advanced Tables -->
                </div>
            </div>
            <!-- /. ROW  -->

            <!--主信息-->
        </div>
    </div>
</div>
</div>
<script src="${pageContext.request.contextPath}/js/vendor/jquery-2.2.4.min.js"></script>
<script src="${pageContext.request.contextPath}/js/vendor/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/vendor/bootstrap-table.min.js"></script>
<script src="${pageContext.request.contextPath}/js/vendor/bootstrap-table-zh-CN.min.js"></script>
<script src="${pageContext.request.contextPath}/js/plugins.js"></script>
<script src="${pageContext.request.contextPath}/js/tool.js"></script>
<script src="${pageContext.request.contextPath}/js/admin_teacher.js"></script>
</body>

</html>