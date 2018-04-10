<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<jsp:include page="head.jsp"></jsp:include>
<script type="text/javascript" src="js/sales.js"></script>
<script type="text/javascript" src="js/index.js"></script>
<script type="text/javascript">

</script>

<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
	<div class="row">
		<ol class="breadcrumb">
			<li><a href="#"><span class="glyphicon glyphicon-home"></span></a></li>
			<li class="active">后台总览</li>
		</ol>
	</div>
	<!--/.row-->

	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">后台总览</h1>
		</div>
	</div>
	<!--/.row-->


	<div class="row">
		<div class="col-xs-12 col-md-6 col-lg-3">
			<div class="panel panel-blue panel-widget ">
				<div class="row no-padding">
					<div class="col-sm-3 col-lg-5 widget-left">
						<em class="glyphicon glyphicon-shopping-cart glyphicon-l"></em>
					</div>
					<div class="col-sm-9 col-lg-7 widget-right">
						<div class="large">
							<span id="barCount">0</span>
						</div>
						<div class="text-muted">商品数量</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-xs-12 col-md-6 col-lg-3">
			<div class="panel panel-orange panel-widget">
				<div class="row no-padding">
					<div class="col-sm-3 col-lg-5 widget-left">
						<em class="   glyphicon glyphicon-th glyphicon-l"></em>
					</div>
					<div class="col-sm-9 col-lg-7 widget-right">
						<div class="large">
							<span id="specialCount">0</span>
						</div>
						<div class="text-muted">专场数量</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-xs-12 col-md-6 col-lg-3">
			<div class="panel panel-teal panel-widget">
				<div class="row no-padding">
					<div class="col-sm-3 col-lg-5 widget-left">
						<em class=" glyphicon glyphicon-sound-dolby glyphicon-l"></em>
					</div>
					<div class="col-sm-9 col-lg-7 widget-right">
						<div class="large">
							<span id="stockCount">0</span>
						</div>
						<div class="text-muted">库存总量</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-xs-12 col-md-6 col-lg-3">
			<div class="panel panel-red panel-widget">
				<div class="row no-padding">
					<div class="col-sm-3 col-lg-5 widget-left">
						<em class="glyphicon glyphicon-stats glyphicon-l"></em>
					</div>
					<div class="col-sm-9 col-lg-7 widget-right">
						<div class="large">
							<span id="salesCount">0</span>K
						</div>
						<div class="text-muted">总销售额</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--/.row-->
	<hr>
	<!-- 主要事项 -->
	<script src="js/chart.min.js"></script>
	<script src="js/chart-data.js"></script>
	<div class="row">
		<div class="col-lg-12">
			<div class="col-md-8">
				<div class="panel panel-default chat">
					<div class="panel-heading" id="accordion">
						<span class="glyphicon glyphicon-comment"></span> 管理员公告
					</div>
					<div class="panel-body">
						<ul id="gg_ul">
							<li class="left clearfix"><span class="chat-img pull-left">
									<span class=" glyphicon glyphicon-tags glyphicon-l"></span>
							</span>
								<div class="chat-body clearfix">
									<p>管理员未发布公告！</p>

								</div></li>
						</ul>
					</div>
					   <form action="" id="fbgg">
					<s:if test="#session.power == 1">
						<div class="panel-footer"><span style="font-size:12px;color:grey;">尊敬的超级管理员，你可以在此处发布公告！</span>
							<div class="input-group">
						
								<input id="btn-input" type="text" name="content" class="form-control input-md"
									placeholder="输入公告信息" /> <span class="input-group-btn">
							     <input type="hidden" name="username" value="${sessionScope.user }">
									<input class="btn btn-success btn-md" id="btn-chat" type="button" onclick="addGg()" value="&nbsp;发&nbsp;&nbsp;布&nbsp;">
								</span>
						
							</div>
						</div>
					</s:if>
					   </form>
				</div>
			</div>
			<div class="col-md-4">
				<div class="panel panel-blue">
					<div class="panel-heading dark-overlay">
						<span class="glyphicon glyphicon-check"></span>备忘录
					</div>
					<div class="panel-body">
						<ul class="todo-list">
						</ul>
					</div>
					<div class="panel-footer">
						<form action="" id="bwlform">
							<div class="input-group">
								<input id="btn-input1" type="text" name="content"
									class="form-control input-md" placeholder="添加一个备忘录"
									required="required" maxlength="20" /> <span
									class="input-group-btn">
									<button class="btn btn-primary btn-md" id="btn-todo1"
										onclick="addBwl()" type="button">添加</button>
								</span>
							</div>
						</form>
					</div>
				</div>

			</div>
		</div>
		<!--/.row-->
		<!--/.col-->

		<!--/.col-->
	</div>
	<!--/.row-->
</div>
<!--/.main-->

<jsp:include page="foot.jsp"></jsp:include>