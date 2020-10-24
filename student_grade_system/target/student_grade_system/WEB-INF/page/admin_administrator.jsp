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
                            <a id="grade_manage" href="#" class="sidebar-nav-menu"><i
                                    class="icon-chevron-right sidebar-nav-indicator sidebar-nav-mini-hide"></i>
                                <i class="glyphicon glyphicon-send sidebar-nav-icon" aria-hidden="true"></i><span
                                        class="sidebar-nav-mini-hide">成绩管理</span></a>

                        </li>

                        <li>
                            <a id="user_manage" href="#" class="sidebar-nav-menu"><i
                                    class="icon-chevron-right sidebar-nav-indicator sidebar-nav-mini-hide"></i>
                                <i class="glyphicon glyphicon-tree-deciduous sidebar-nav-icon"
                                   aria-hidden="true"></i><span
                                        class="sidebar-nav-mini-hide">人员管理</span></a>

                        </li>
                        <li>
                            <a id="course_manage" href="#" class="sidebar-nav-menu"><i
                                    class="icon-chevron-right sidebar-nav-indicator sidebar-nav-mini-hide"></i>
                                <i class="glyphicon glyphicon-grain sidebar-nav-icon" aria-hidden="true"></i><span
                                        class="sidebar-nav-mini-hide">课程管理</span></a>

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
                                                <input type="text" class="form-control" id="txt_search_coursename"
                                                       placeholder="课程名称">
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
                                <button id="btn_submit" type="button" class="btn btn-primary">
                                    <span class="glyphicon glyphicon-saved" aria-hidden="true"></span>提交
                                </button>
                                <button id="btn_add" type="button" class="btn btn-primary" data-toggle="modal" data-target="#addGradeModal">
                                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
                                </button>
                                <button id="btn_delete" type="button" class="btn btn-primary">
                                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
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
                    <!--覆盖层-->
                    <div class="modal fade" id="addGradeModal" tabindex="-1" role="dialog"
                         aria-labelledby="addGradeModal" aria-hidden="true">
                        <div class="modal-dialog" style="width: 100%;margin-top: 0;">
                            <div class="modal-content">

                                <div class="modal-header bg-primary">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                        ×
                                    </button>

                                </div>

                                <div class="modal-body">
                                    <form id="add_grade_form" class="form-horizontal">

                                        <div class="control-group">
                                            <div class="controls">
                                                <input type="text" name="pgrade.course_id" placeholder="课程编号"
                                                       class="input-xlarge">
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <div class="controls">
                                                <input type="text" name="pgrade.major" placeholder="开课专业"
                                                       class="input-xlarge">
                                                <p class="help-block"></p>
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <div class="controls">
                                                <input type="text" name="pgrade.class_" placeholder="开课班级" class="input-xlarge">
                                                <p class="help-block"></p>
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label">考试性质</label>
                                            <div class="controls">
                                                <label class="radio radio-inline">
                                                    <input type="radio" value="初修" name="grade.property"
                                                           checked="checked">
                                                    初修
                                                </label>
                                                <label class="radio radio-inline">
                                                    <input type="radio" value="重修" name="grade.property">
                                                    重修
                                                </label>
                                            </div>
                                        </div>

                                        <div class="control-group">
                                            <label class="control-label">考试方式</label>
                                            <div class="controls">
                                                <label class="radio radio-inline">
                                                    <input type="radio" value="考试" name="grade.way" checked="checked">
                                                    考试
                                                </label>
                                                <label class="radio radio-inline">
                                                    <input type="radio" value="考查" name="grade.way">
                                                    考查
                                                </label>
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label">学年</label>
                                            <div class="controls">
                                                <label class="radio radio-inline">
                                                    <input type="radio" value="大一" checked="checked"
                                                           name="pgrade.term_year">
                                                    大一
                                                </label>
                                                <label class="radio radio-inline">
                                                    <input type="radio" value="大二" name="pgrade.term_year">
                                                    大二
                                                </label>
                                                <label class="radio radio-inline">
                                                    <input type="radio" value="大三" name="pgrade.term_year">
                                                    大三
                                                </label>
                                                <label class="radio radio-inline">
                                                    <input type="radio" value="大四" name="pgrade.term_year">
                                                    大四
                                                </label>
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label">学期</label>
                                            <div class="controls">
                                                <label class="radio radio-inline">
                                                    <input type="radio" value="第一学期" checked="checked"
                                                           name="pgrade.term_season">
                                                    第一学期
                                                </label>
                                                <label class="radio radio-inline">
                                                    <input type="radio" value="第二学期" name="pgrade.term_season">
                                                    第二学期
                                                </label>
                                            </div>
                                        </div>

                                        <div class="control-group">
                                            <label class="control-label">备注</label>
                                            <div class="controls">
                                                <div class="textarea">
                                                    <textarea name="grade.comment" class=""> </textarea>
                                                </div>
                                            </div>
                                        </div>

                                    </form>
                                    <!--表单-->
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                    </button>
                                    <button type="submit" class="btn btn-primary">
                                        提交
                                    </button>
                                </div>
                            </div>
                            <!-- /.modal-content -->
                        </div>
                    </div>
                    <!-- 覆盖层-->
                </div>
                <!-- /. ROW  -->
                <!-- /. 人员管理  -->
                <div class="row">
                    <div class="col-md-12">
                        <!-- Advanced Tables -->
                        <div class="card">
                            <div class="panel panel-primary">
                                <div class="panel-heading">查询条件</div>
                                <div class="panel-body">
                                    <form id="formSearch_user" class="form-horizontal">
                                        <div class="form-group" style="margin-top:15px">
                                            <label class="control-label col-sm-1" for="txt_search_username">姓名</label>
                                            <div class="col-sm-3">
                                                <input type="text" class="form-control" id="txt_search_username"
                                                       placeholder="姓名">
                                            </div>
                                            <label class="control-label col-sm-1"
                                                   for="txt_search_user_class">用户类型</label>
                                            <div class="col-sm-3">
                                                <select id="txt_search_user_class" class="input-xlarge">
                                                    <option>学生</option>
                                                    <option>教师</option>
                                                    <option>管理员</option>
                                                </select>
                                            </div>
                                            <div class="col-sm-2" style="text-align:left;">
                                                <button type="button" style="margin-left:50px" id="btn_query_user"
                                                        class="btn btn-primary">查询
                                                </button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <!-- 查询-->
                            <div id="user_toolbar" class="btn-group">
                                <button id="btn_add_user" type="button" class="btn btn-primary" data-toggle="modal" data-target="#UserModal">
                                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
                                </button>
                                <button id="btn_edit_user" type="button" class="btn btn-primary" data-toggle="modal" data-target="#UserModal">
                                    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
                                </button>
                                <button id="btn_delete_user" type="button" class="btn btn-primary">
                                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
                                </button>
                            </div>
                            <!--操作-->

                            <div class="card-content">
                                <div class="table-responsive">
                                    <table class="table table-striped table-bordered table-hover" id="tb_user"></table>
                                </div>
                            </div>
                        </div>
                        <!--End Advanced Tables -->
                    </div>
                    <!-- /. ROW  -->
                    <!--覆盖层-->
                    <div class="modal fade" id="UserModal" tabindex="-1" role="dialog"
                         aria-labelledby="UserModal" aria-hidden="true">
                        <div class="modal-dialog" style="width: 100%;margin-top: 0;">
                            <div class="modal-content">
                                <div class="modal-header bg-primary">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                        ×
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <form id="user_form" class="form-horizontal">
                                        <div class="control-group" style="display: none">
                                            <div class="controls">
                                                <input type="text" name="user.id" placeholder=""
                                                       class="input-xlarge">
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <div class="controls">
                                                <input type="text" name="user.userId" placeholder="学号/工号"
                                                       class="input-xlarge">
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <div class="controls">
                                                <input type="text" name="user.name" placeholder="姓名"
                                                       class="input-xlarge">
                                                <p class="help-block"></p>
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label">用户类型</label>
                                            <div class="controls">
                                                <label class="radio radio-inline">
                                                    <input type="radio" value="学生" name="user.userClass" checked="checked">
                                                    学生
                                                </label>
                                                <label class="radio radio-inline">
                                                    <input type="radio" value="教师" name="user.userClass">
                                                    教师
                                                </label>
                                                <label class="radio radio-inline">
                                                    <input type="radio" value="管理员" name="user.userClass">
                                                    管理员
                                                </label>
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <div class="controls">
                                                <input type="text" name="user.class_" class="input-xlarge" placeholder="班级(学生属性)">
                                                <p class="help-block"></p>
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label">性别</label>
                                            <div class="controls">
                                                <label class="radio radio-inline">
                                                    <input type="radio" value="男" name="user.gender"
                                                           checked="checked">
                                                    男
                                                </label>
                                                <label class="radio radio-inline">
                                                    <input type="radio" value="女" name="user.gender">
                                                    女
                                                </label>
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <div class="controls">
                                                <input type="text" name="user.nation" class="input-xlarge" placeholder="名族">
                                                <p class="help-block"></p>
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <div class="controls">
                                                <input type="text" name="user.institute" class="input-xlarge" placeholder="学院">
                                                <p class="help-block"></p>
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <div class="controls">
                                                <input type="text" name="user.major" class="input-xlarge" placeholder="专业">
                                                <p class="help-block"></p>
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <div class="controls">
                                                <input type="text" name="user.password" class="input-xlarge" placeholder="密码">
                                                <p class="help-block"></p>
                                            </div>
                                        </div>
                                    </form>
                                    <!--表单-->
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                    </button>
                                    <button type="submit" class="btn btn-primary">
                                        提交
                                    </button>
                                </div>
                            </div>
                            <!-- /.modal-content -->
                        </div>
                    </div>
                    <!-- 覆盖层-->
                </div>
                <!-- /. 课程管理  -->
                <div class="row">
                    <div class="col-md-12">
                        <!-- Advanced Tables -->
                        <div class="card">
                            <div class="panel panel-primary">
                                <div class="panel-heading">查询条件</div>
                                <div class="panel-body">
                                    <form id="formSearch_course" class="form-horizontal">
                                        <div class="form-group" style="margin-top:15px">
                                            <label class="control-label col-sm-1"
                                                   for="txt_search_course_name">课程名称</label>
                                            <div class="col-sm-3">
                                                <input type="text" class="form-control" id="txt_search_course_name"
                                                       placeholder="课程名称">
                                            </div>
                                            <label class="control-label col-sm-1"
                                                   for="txt_search_course_class">课程类型</label>
                                            <div class="col-sm-3">
                                                <input type="text" class="form-control" id="txt_search_course_class"
                                                       placeholder="课程类型">
                                            </div>
                                            <div class="col-sm-2" style="text-align:left;">
                                                <button type="button" style="margin-left:50px" id="btn_query_course"
                                                        class="btn btn-primary">查询
                                                </button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <!-- 查询-->
                            <div id="course_toolbar" class="btn-group">
                                <button id="btn_arrange_course" type="button" class="btn btn-primary">
                                    <span class="glyphicon glyphicon-saved" aria-hidden="true"></span>提交
                                </button>
                                <button id="btn_add_course" type="button" class="btn btn-primary" data-toggle="modal" data-target="#CourseModal">
                                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
                                </button>
                                <button id="btn_edit_course" type="button" class="btn btn-primary" data-toggle="modal" data-target="#CourseModal">
                                    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
                                </button>
                                <button id="btn_delete_course" type="button" class="btn btn-primary">
                                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
                                </button>
                            </div>
                            <!--操作-->

                            <div class="card-content">
                                <div class="table-responsive">
                                    <table class="table table-striped table-bordered table-hover"
                                           id="tb_course"></table>
                                </div>
                            </div>
                        </div>
                        <!--End Advanced Tables -->
                    </div>
                    <!-- /. ROW  -->
                    <!--覆盖层-->
                    <div class="modal fade" id="CourseModal" tabindex="-1" role="dialog"
                         aria-labelledby="CourseModal" aria-hidden="true">
                        <div class="modal-dialog" style="width: 100%;margin-top: 0;">
                            <div class="modal-content">
                                <div class="modal-header bg-primary">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                        ×
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <form id="course_form" class="form-horizontal">
                                        <div class="control-group" style="display: none;">
                                            <div class="controls">
                                                <input type="text" name="course.id" placeholder=""
                                                       class="input-xlarge">
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <div class="controls">
                                                <input type="text" name="course.name" placeholder="课程名称"
                                                       class="input-xlarge">
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <div class="controls">
                                                <input type="text" name="course.class_" placeholder="课程类型"
                                                       class="input-xlarge">
                                                <p class="help-block"></p>
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <div class="controls">
                                                <input type="text" name="course.credit" placeholder="学分"
                                                       class="input-xlarge">
                                                <p class="help-block"></p>
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <div class="controls">
                                                <input type="text" name="course.gpa" placeholder="绩点"
                                                       class="input-xlarge">
                                                <p class="help-block"></p>
                                            </div>
                                        </div>
                                    </form>
                                    <!--表单-->
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                    </button>
                                    <button type="submit" class="btn btn-primary">
                                        提交
                                    </button>
                                </div>
                            </div>
                            <!-- /.modal-content -->
                        </div>
                    </div>
                    <!-- 覆盖层-->
                </div>

            </div>
            <!--主信息-->
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/vendor/jquery-2.2.4.min.js"></script>
<script src="${pageContext.request.contextPath}/js/vendor/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/vendor/bootstrap-table.min.js"></script>
<script src="${pageContext.request.contextPath}/js/vendor/bootstrap-table-zh-CN.min.js"></script>
<script src="${pageContext.request.contextPath}/js/plugins.js"></script>
<script src="${pageContext.request.contextPath}/js/tool.js"></script>
<script src="${pageContext.request.contextPath}/js/admin_administrator.js"></script>
</body>

</html>