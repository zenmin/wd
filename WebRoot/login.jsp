<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>后台登陆</title>
<link rel = "Shortcut Icon" href=/favicon.ico> 
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<meta name="keywords"
	content="Flat Dark Web Login Form Responsive Templates, Iphone Widget Template, Smartphone login forms,Login form, Widget Template, Responsive Templates, a Ipad 404 Templates, Flat Responsive Templates" />
<link href="css/loginstyle.css" rel='stylesheet' type='text/css' />
<!--webfonts
<link href='http://fonts.useso.com/css?family=PT+Sans:400,700,400italic,700italic|Oswald:400,300,700' rel='stylesheet' type='text/css'>
<link href='http://fonts.useso.com/css?family=Exo+2' rel='stylesheet' type='text/css'>
<script src="http://ajax.useso.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
-->
<script src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
$(function(){
	$("#username").focus();
});
</script>
</head>
<body>
	<h1 style="color:#EE1289;">后台系统登陆</h1>
	<div class="login-form">
		<div class="head-info">
			<label class="lbl-1"> </label> <label class="lbl-2"> </label> <label
				class="lbl-3"> </label>
		</div>
		<div class="clear"></div>
		<div class="avtar">
			<img src="images/icon.png" width="60px" height="60px" />
		</div>
		<form action="login" method="post">
			<input type="text" class="text" id="username" value="${requestScope.username }" name="username" required="required" placeholder="用户名"
				>
			<div class="key">
				<input type="password" value="${requestScope.password }" name="password" required="required" placeholder="密  码"
				>
			</div>
			<div  style="margin-top:-30px;margin-bottom:24px;color:red;"><span>${requestScope.errorMsg }</span></div>
			
		<div class="signin">
			<input type="submit" id="sub" value="登  陆">
		</div>
		  </form>
		  
	</div>
</body>
</html>