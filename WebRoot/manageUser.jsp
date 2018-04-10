<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<jsp:include page="${pageConten.request.contenPath }/head.jsp"></jsp:include>
<script type="text/javascript">
	var nowPage = 1;
	$(document).ready(function() {
		showUser();
	});
	function delUser(id) {
		if (confirm("你确定删除此用户吗？")) {
			$.ajax({
				cache : false,
				url : "delUser",
				method : "post",
				dataType : "json",
				data : {
					"id" : id
				},
				success : function(result) {
					if (result == '1') {
						alert("删除用户成功!");
						showUser();
					}
					if (result == '0') {
						alert("删除用户失败!");
					}
				},
				error : function() {
					alert('未连接到服务器,请检查服务器或数据库配置！');
				}
			});
		}
	}
	function showUser() {
		$
				.ajax({
					cache : false,
					url : "getUser",
					method : "get",
					dataType : "json",
					data : $('#userfrom').serialize(),
					success : function(result) {
						var dataObj = result, //返回json
						con = "";
						$("#salesTab").empty();
						if (dataObj == "" || dataObj == null) {
							nowPage = 1;
							$("#nowPage").val(nowPage);
							$("#pageSpan").text(nowPage);
							$("#salesTab")
									.append(
											"<td id='msg' style='line-height: 5" +
                          ";color:blue;' align='center' " +
                          "colspan='9'><span class='msg'>没有查询结果！</span></td>");
							alert('没有下一页啦！');
						}
						$
								.each(
										dataObj,
										function(index, item) {
											var power;
											if (item.power == 1) {
												power = "超级管理员";
											}
											if (item.power == 0) {
												power = "普通管理员";
											}
											con = "<tr>"
													+ "<td>"
													+ item.id
													+ "</td>"
													+ "<td>"
													+ item.name
													+ "</td>"
													+ "<td>"
													+ power
													+ "</td>"
													+ "<td align='center'>"
													+ "<a onclick='delUser("
													+ item.id
													+ ")' class='btn btn-primary btn-md'>删&nbsp;除</a></td>"
													+ "</tr>";

											$("#salesTab").append(con);
											var size = 0;
											size = parseInt(item.size) / 10 + 1; //  总页码
											$("#MaxCount").text(size);

										});
					},
					error : function() {
						alert('未连接到服务器,请检查服务器或数据库配置！');
					}
				});
	}

	function add() {
		$.ajax({
			cache : false,
			url : "addUser",
			method : "post",
			dataType : "json",
			data : $('#adduser').serialize(),
			success : function(result) {
				if (result == '1') {
					alert("新增用户成功!");
				}
				if (result == '0') {
					alert("新增用户失败,用户名已经存在!");
				}
			},
			error : function() {
				alert('未连接到服务器,请检查服务器或数据库配置！');
			}
		});
	}

	function jump(cz) {
		if (cz == 1) {
			$("#nowPage").val(parseInt(nowPage - 1));
			$("#btn-todo").click();
			nowPage -= 1;
			if (nowPage == 0) {
				nowPage = 1;
			}
			$("#nowPage").val(nowPage); //  为当前页码赋值到表单中
			$("#pageSpan").text(nowPage); //  当前页码
			showUser();
		}
		if (cz == 2) {
			$("#nowPage").val(parseInt(nowPage + 1));
			$("#btn-todo").click();
			nowPage += 1;

			$("#nowPage").val(nowPage); //  为当前页码赋值到表单中
			$("#pageSpan").text(nowPage); //  当前页码
			showUser();
		}
	}
</script>
<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
	<div class="row">
		<ol class="breadcrumb">
			<li><a href="#"><span class="glyphicon glyphicon-home"></span></a></li>
			<li class="active"><s:if test="#session.power != 1">
					<script>
						alert('对不起,你没有权限！此功能仅限超级管理员！');
						window.history.back();
					</script>
				</s:if> 用户管理</li>
		</ol>
	</div>
	<!--/.row-->

	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">用户管理</h1>
			<h5>你是超级管理员,拥有对平台所有用户的管理权限!</h5>
		</div>
	</div>
	${requestScope.msg }
	<table>
		<form action="" id="userfrom" method="">

			<tr>
				<td><a href="#addUser" class="btn btn-primary btn-md"
					id="btn-todo"> 新&nbsp;增&nbsp;用&nbsp;户</a></td>

				<td align="right"><input style="width: 300px;" type="text"
					id="finduser" name="finduser" class="form-control"
					placeholder="输入用户名/不填显示全部" required="required" value=""></td>

				<td align="right" width="100"><input type="hidden"
					name="nowPage" id="nowPage" value="1"> <a
					class="btn btn-primary btn-md" id="btn-todo" onclick="showUser()">搜&nbsp;索&nbsp;用&nbsp;户</a>
				</td>
			</tr>

		</form>
	</table>
	<table class="zebra">

		<!-- 表头表尾 -->
		<thead>
			<tr>

				<th><p>用户ID</p></th>
				<th><p>用户名</p></th>
				<th><p>用户权限</p></th>
				<th><p align=center>
						<b>操&nbsp;&nbsp;作</b>
					</p></th>
			</tr>
		</thead>
		<tbody id="salesTab">
		</tbody>
		<tfoot>
			<tr id="Page">
				<td colspan="9" align="center">当前第 <span id="pageSpan">1</span>
					页丨 <a><button onclick="jump(1)">上一页</button></a> <a><button
							onclick="jump(2)">下一页</button></a> <!--  span id="MaxPage">共<span id="MaxCount">1</span>页</span>-->
			</tr>
		</tfoot>
		<!-- 行列 -->
	</table>
	<!-- 结束 -->
</div>
<!--/.main-->

<!-- 改密弹出层 -->
<div class="remodal" data-remodal-id="addUser" role="dialog"
	aria-labelledby="modal1Title" aria-describedby="modal1Desc">

	<button data-remodal-action="close" class="remodal-close"
		aria-label="Close"></button>
	<div>
		<h2 id="modal1Title">新增用户</h2>
		<p id="modal1Desc"></p>
	</div>
	<div align="center">
		<form action="" id="adduser" method="get">
			用户名:<input placeholder="输入用户名/最好为英文" id="oldp" name="username"
				class="form-control" type="text" required="required"
				style="width: 300px;"> <br>密码:<input
				placeholder="输入密码/最好中英文混合" id="p1" name="password"
				class="form-control" type="password" required="required"
				style="width: 300px;"> <br> 权限: <select
				class="form-control" name="power" style="width:300px;"
				id="specialName">
				<option value="0">普通管理员</option>
				<option value="1">超级管理员</option>
			</select> <br>
			<button id="submitBtn" onclick="add()" class="remodal-confirm"
				type="button">提 交</button>

			<button class="remodal-cancel" type="reset">重置</button>

		</form>
	</div>
	<jsp:include page="foot.jsp"></jsp:include>