<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE jsp PUBLIC "-//W3C//DTD jsp 4.01 Transitional//EN">
<head>
<title>唯品会后台管理系统</title>
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/datepicker3.css" rel="stylesheet">
<link href="css/styles.css" rel="stylesheet">
<link href="css/table.css" rel="stylesheet">
<link rel="Shortcut Icon" href=/favicon.ico>
<!--[if lt IE 9]>
	<script src="js/html5shiv.js"></script>
	<script src="js/respond.min.js"></script>
	<![endif]-->
<script src="js/jquery-1.11.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/chart.min.js"></script>
<script src="js/chart-data.js"></script>
<script src="js/easypiechart.js"></script>
<script src="js/easypiechart-data.js"></script>
<script src="js/bootstrap-datepicker.js"></script>
<script src="js/bootstrap-table.js"></script>
<link rel="stylesheet" href="js/dist/remodal.css">
<link rel="stylesheet" href="js/dist/remodal-default-theme.css">
</head>
<style>
.remodal-bg.with-red-theme.remodal-is-opening,.remodal-bg.with-red-theme.remodal-is-opened
	{
	filter: none;
}

.remodal-overlay.with-red-theme {
	background-color: #f44336;
}

.remodal.with-red-theme {
	background: #fff;
}
</style>
<body>

	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#sidebar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="index.jsp"><span>唯品会</span>后台管理</a>
			<ul class="user-menu">
				<li class="dropdown pull-right"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown"><span
						class="glyphicon glyphicon-user"></span> 欢迎你&nbsp;
						<s:if test="#session.power == 1">超级管理员:</s:if>
						<s:else>普通管理员:</s:else>
						<c:out value="${sessionScope.user}" escapeXml="false">
							<script>
				            alert('你还未登陆,或登陆已经超时！\n请你重新登陆哦！');
				            window.location.href = "${pageContext.request.contextPath}/login.jsp";
				        </script>
						</c:out> <span class="caret"></span></a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="#modal"><span
								class="glyphicon glyphicon-user"></span> 改密</a></li>
						<li><a href="#" onclick="javascript:if(confirm('你确定要注销吗？')){this.href='logoff';}"><span class="glyphicon glyphicon-log-out"></span>
								注销</a></li>
					</ul></li>
			</ul>
		</div>

	</div>
	<!-- /.container-fluid --> </nav>
	<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">

		<div class="form-group" align="right"></div>
		<div>
			<span style="color:#30a5ff;font-size:24px;">&nbsp;&nbsp;VIPSHOP </span>
		</div>

		<ul class="nav menu">
			<li role="presentation" class="divider"></li>
			<li><a href="index.jsp"> <span
					class="glyphicon glyphicon-dashboard"></span>后台信息
			</a></li>
			
			
            <li><a href="manageUser.jsp"> <span
                    class="glyphicon glyphicon-user"></span>用户管理
            </a></li>
          
            
			<li><a href="bar"><span
					class="glyphicon glyphicon-list-alt"></span>产品资料 </a></li>

			<li><a href="class?classCurrentPage=1"><span class="glyphicon glyphicon-th"></span>类目资料
			</a></li>
			<li><a href="special"><span
					class="glyphicon glyphicon-stats"></span>专场资料</a></li>
			<li><a href="sales.jsp"><span
					class="glyphicon glyphicon-pencil"></span>销售资料</a></li>

			<li><a href="returns.jsp"><span
					class=" glyphicon glyphicon-log-in"></span>客退资料</a></li>
			<li><a href="stock.jsp"><span
					class=" glyphicon glyphicon-sound-dolby"></span>实时库存</a></li>


			<li class="parent "><a id="zk" data-toggle="collapse"
				href="#sub-item-1" onmousemove="zk1()"> <span
					class="glyphicon glyphicon-list"></span> 资料更新 <span
					data-toggle="collapse" href="#sub-item-1" class="icon pull-right"><em
						class="glyphicon glyphicon-s glyphicon-plus"></em></span>
			</a>
				<ul class="children collapse" id="sub-item-1">
				<s:if test="#session.power == 1">
					<li><a class="" href="updateBarAndGoods.jsp"> <span
							class="glyphicon glyphicon-share-alt"></span> 货号与条码更新
					</a></li>
					<li><a class="" href="updateSales.jsp"> <span
							class="glyphicon glyphicon-share-alt"></span> 销售数据更新
					</a></li>
					<li><a class="" href="updateReturns.jsp"> <span
							class="glyphicon glyphicon-share-alt"></span> 客退数据更新
					</a></li>
					<li><a class="" href="updateStock.jsp"> <span
							class="glyphicon glyphicon-share-alt"></span> 实时库存更新
					</a></li>
		      	</s:if>
			        <li><a class="" href="export"> <span
                            class="glyphicon glyphicon-share-alt"></span> 产品资料导出
                    </a></li>
					<li><a class="" href="exportSales.jsp"> <span
							class="glyphicon glyphicon-share-alt"></span> 销售数据导出
					</a></li>
                    <li><a class="" href="exportReturns.jsp"> <span
                            class="glyphicon glyphicon-share-alt"></span> 客退数据导出
                    </a></li>
                    <li><a class="" href="exportStock.jsp"> <span
                            class="glyphicon glyphicon-share-alt"></span> 实时库存导出
                    </a></li>
                </ul></li>
                <s:if test="#session.power == 1">
             <li><a href="removedata.jsp"> <span
                    class="glyphicon glyphicon-remove-sign"></span>清空数据
            </a></li>
            </s:if>


			<li role="presentation" class="divider"></li>

			<!--   form role="search">
				<div class="form-group" align="center">
					<input type="text" class="form-control" placeholder="Search">
					<br>
					<button class="btn btn-primary btn-md" id="btn-todo" type="submit">搜&nbsp;索</button>
					&nbsp; &nbsp;
					<button class="btn btn-primary btn-md" id="btn-todo" type="reset">重&nbsp;置</button>
				</div>
			</form>
			-->

			<li style="vertical-align: bottom;"><a href="help.jsp"> <span
					class="glyphicon glyphicon-question-sign"></span>帮助中心
			</a></li>
			<li style="vertical-align: bottom;"><a href="version.jsp"> <span
					class="glyphicon glyphicon-cloud-download"></span>关于程序
			</a></li>
		</ul>

	</div>

	<!-- 改密弹出层 -->
	<div class="remodal" data-remodal-id="modal" role="dialog"
		aria-labelledby="modal1Title" aria-describedby="modal1Desc">
		<button data-remodal-action="close" class="remodal-close"
			aria-label="Close"></button>
		<div>
			<h2 id="modal1Title">密码修改</h2>
			<p id="modal1Desc"></p>
		</div>
		<div align="center">
			<form action="" id="alterPass">

				原密码:<input placeholder="输入原密码" id="oldp" name="password1"
					class="form-control" type="password" required="required"
					style="width: 300px;"> <br> 新密码:<input
					placeholder="输入新密码" id="p1" name="password" class="form-control"
					type="password" required="required" style="width: 300px;">
				<br> 确认密码: <input placeholder="再次输入新密码" name="password2"
					id="p2" class="form-control" type="password" required="required"
					style="width: 300px;"> <br> <input type="hidden"
					name="username" value="${sessionScope.user }">
				<button id="submitBtn" class="remodal-confirm" type="button">提
					交</button>

				<button class="remodal-cancel" type="reset">重置</button>

			</form>
		</div>
	</div>
	<script src="js/dist/zepto.js"></script>
	<script src="js/dist/remodal.js"></script>
	<script>
$(function(){
    $('#submitBtn').click(function() {
       if($("#oldp").val()==''||$("#p1").val()==''||$("#p2").val()==''
    		   ||$("#p1").val()!=$("#p2").val()){
    	   alert("请检查你的信息,两次输入密码必须一致！");
       }else{
    	   $.ajax({
               cache : false,
               url : "alterPass",
               method : "get",
               dataType : "json",
               data : $('#alterPass').serialize(),
               success : function(result) {
                   if (result == '1') {
                       alert("修改密码成功,请重新登陆！");
                       window.location.href="login.jsp";
                   }
                   if (result == '0') {
                       alert("修改密码失败,请检查你输入是否正确");
                   }
                   if (result == '2') {
                       alert("原密码不正确！");
                   }
               },
               error : function() {
                   alert('未连接到服务器,请检查服务器或数据库配置！');
               }
           });
       }
   
    });
    
    
});
</script>

	<!--/.sidebar-->