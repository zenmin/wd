<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isErrorPage="true" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<jsp:include page="head.jsp"></jsp:include>
${requestScope.deleteSpecialError }
<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
	<div class="row">
		<ol class="breadcrumb">
			<li><a href="#"><span class="glyphicon glyphicon-home"></span></a></li>
			<li class="active">出错啦</li>
		</ol>
	</div>
	<!--/.row-->

	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header"></h1>
		</div>
	</div>
	<!--/.row-->
	<br>
	<div align="center">亲，你访问的页面没有找到哦！<br>
	<br>
	   <button onclick="javascript:window.history.back();" class="btn btn-primary btn-md" id="btn-todo" type="button">返&nbsp;回</button>
	</div>
</div>
<!--/.main-->

<jsp:include page="foot.jsp"></jsp:include>