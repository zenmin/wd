<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<jsp:include page="head.jsp"></jsp:include>

<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
	<div class="row">
		<ol class="breadcrumb">
			<li><a href="#"><span class="glyphicon glyphicon-home"></span></a></li>
			<li class="active">编辑档期资料</li>
		</ol>
	</div>
	<!--/.row-->

	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">编辑档期资料</h1>
		</div>
	</div>
	<!--/.row-->
	<!-- 行列 -->

	<div class="form-group" align="center" style="width:600px;height: 400px;margin: auto;">

		<form role="search" method="get" action="updatespecial">
		 <s:iterator value="#request.editspecial" id="especial">
		  <br>
			 档期ID：<input type="text" class="form-control" 
				name="id" value="<s:property value="#especial.specialId" />" readonly="readonly" 
				 onclick="javascript:alert('档期ID不可修改！');"> 
			   <br>
<!-- 				档期名称：<input type="text" class="form-control" -->
<!-- 				placeholder="请输入档期名称" value="<s:property value="#especial.special"/>" -->
<!-- 				 name="special" >  -->
<!-- 			   <br> -->
				专场名称：<input
				type="text" class="form-control" placeholder="请输入专场名称" required="required"
				name="specialName" value="<s:property value="#especial.specialName"/>"> 
			 </s:iterator>
			       <br>
			<button class="btn btn-primary btn-md" id="btn-todo" type="submit">提&nbsp;交</button>
			&nbsp; &nbsp;
			<button class="btn btn-default" id="btn-todo" type="reset">重&nbsp;置</button>
		</form>
	</div>

	<!-- 结束 -->


</div>
<!--/.main-->

<jsp:include page="foot.jsp"></jsp:include>