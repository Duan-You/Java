<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>学生成绩管理系统</title>
		<link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" rev="stylesheet" type="text/css" media="all" />
		<link href="${pageContext.request.contextPath}/css/user-login.css" rel="stylesheet" rev="stylesheet" type="text/css" media="all" />
		<script src="${pageContext.request.contextPath}/js/vendor/jquery-2.2.4.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/vendor/Validform_v5.3.2_min.js"  type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/login.js"  type="text/javascript"></script>

	</head>
	<body>
		<div style="height: 70px;"></div>
		<div class="banner">
			<div class="login-aside">
				<div id="o-box-up"></div>
				<div id="o-box-down" style="table-layout:fixed;">
					<div class="error-box"></div>
					<form class="registerform" action="/student_grade_system/user/login" method="post">
						<div class="fm-item">
							<label class="form-label">系统登陆：</label>
							<input name="user.userId" type="text" value="" maxlength="100" id="username" class="i-text"  datatype="s6-18" errormsg="用户名至少6个字符,最多18个字符！">
							<div class="ui-form-explain"></div>
						</div>
						<div class="fm-item">
							<label class="form-label">登陆密码：</label>
							<input name="user.password" type="password" value="" maxlength="100" id="password" class="i-text" datatype="*6-16" nullmsg="请设置密码！" errormsg="密码范围在6~16位之间！">
							<div class="ui-form-explain"></div>
						</div>

						<div class="fm-item">
							<label class="form-label"></label>
							<input type="submit" value="" tabindex="4" id="send-btn" class="btn-login">
							<div class="ui-form-explain"></div>
						</div>
					</form>
				</div>
			</div>
			<div class="bd">
				<ul>
					<li style="background:url(${pageContext.request.contextPath}/images/bg-login.jpg) #CCE1F3 center 0 no-repeat;"></li>
				</ul>
			</div>
			<div class="hd">
				<ul></ul>
			</div>
		</div>
		<div class="banner-shadow"></div>
	</body>
</html>