<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isErrorPage="true"  %>
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
	<div align="center">发生错误了，可能是你提交的参数不正确！<br>
	<br>如果是资料更新请一定上传Excel文件哦<br><br>
	<br>如果是修改类目出错可能是已经有同名类目了哦<br><br>
	   <button onclick="javascript:window.history.back();" class="btn btn-primary btn-md" id="btn-todo" type="button">返&nbsp;回</button>
	</div>
</div>
<!--/.main-->

<jsp:include page="foot.jsp"></jsp:include>