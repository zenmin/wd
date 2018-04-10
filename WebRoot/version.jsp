<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<jsp:include page="head.jsp"></jsp:include>

<link rel="stylesheet" type="text/css" media="screen"
	href="css-table.css" />
<link rel="stylesheet" type="test/css" media="screen"
	href="bookstrap.css" />
<script type="text/javascript" src="js/jquery-1.2.6.min.js"></script>
<script type="text/javascript" src="js/style-table.js"></script>

<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
	<div class="row">
		<ol class="breadcrumb">
			<li><a href="#"><span class="glyphicon glyphicon-home"></span></a></li>
			<li class="active">关于程序</li>
		</ol>
	</div>
	<!--/.row-->
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">关于程序</h1>
		</div>
	</div>
	<!--/.row-->
	<div class="form-group" style="width:600px;height: 400px;margin: auto;"
		align="center">
		<table class="zebra" style="border:1;border-color: gray;"
			align="center">
			<!-- 表头 -->
			<thead>
				<tr>
					<th colspan=8>服务器信息</th>
				</tr>
			</thead>
			<!-- 账号信息 -->
			<tbody>
                
				<tr>
					<td>服务器地址：${header.host }</td>
				</tr>
				<tr>
					<td>服务器版本：<%= application.getServerInfo() %></td>
				</tr>

				<tr>
					<td>服务器端口：<%=request.getServerPort() %></td>
				</tr>
				<tr>
					<td>Session标识符：<%= session.getId() %></td>
				</tr>
				<tr>
					<td>Session创建时间：<%= new Date(session.getCreationTime()).toString() %></td>
				</tr>
				<tr>
					<td>Session超时时间：<%= session.getMaxInactiveInterval()%></td>
				</tr>
                 <% String  IP=request.getRemoteAddr(); %> 
                 <tr>
                    <td>客户端IP：<%=IP %></td>
                </tr>
				<tr>
					<td>请求信息：${header["user-agent"] }</td>
				</tr>
				<tr>
					<td>请求语言：${headerValues["Accept-Language"][0] }</td>
				</tr>

				<tr>
					<td>当前项目：<%= application.getServletContextName() %></td>
				</tr>
			</tbody>
			<tr>
				<td>程序版本：V1.0</td>
			</tr>
			<tr>
			<td>开发人员：曾敏、魏孟欢</td>
			</tr>
			<tr>
			<td>BUG反馈：<a
				href="http://wpa.qq.com/msgrd?v=3&uin=741703967&site=qq&menu=yes" target="_blank">请点击此处</a></td>
			</tr>
		</table>
	</div>

</div>
<!--/.main-->

<jsp:include page="foot.jsp"></jsp:include>