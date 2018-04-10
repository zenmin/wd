<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<style>
#date1 {
	width: 430px;
	height: 166px;
	margin: auto;
	margin-left: 20px;
	float: left;
}

#lm {
	width: 430px;
	height: 166px;
	margin: auto;
	float: left;
	margin-left: 0px;
}
</style>

<jsp:include page="head.jsp"></jsp:include>

<script language="javascript" type="text/javascript"
	src="js/My97DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" href="js/jq/animate.min.css" />
<!-- 动画效果 -->
<script src="js/jq/jquery-1.9.1.min.js"></script>
<script src="js/jq/jquery.hDialog.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
    $(function(){
        $("#btn-todo").click(function(){
            $.dialog('alert','提示','正在导出中...请注意下载弹窗!',5000); //不自动关闭
        });
    })
});
</script>


<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
	<div class="row">
		<ol class="breadcrumb">
			<li><a href="#"><span class="glyphicon glyphicon-home"></span></a></li>
			<li class="active">客退资料</li>

		</ol>
	</div>
	<!--/.row-->

	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">客退资料查询</h1>
		</div>
	</div>
	<!--/.row-->
    ${requestScope.msg }
	<div class="row">
		<s:form id="exportReturns" method="post" class="form-group"
			action="exportReturns" theme="simple">
			<div id="date1">
				日期段选择:(不填默认查询全部)<br> 开始日期:<input placeholder="点击选择开始日期"
					class="form-control" class="Wdate" type="text"
					onClick="WdatePicker()" style="width: 300px;" 
					name="startTime" value=""> 结束日期:<input class="form-control"
					class="Wdate" type="text" placeholder="点击选择结束日期"
					onClick="WdatePicker()" style="width: 300px;" 
					name="endTime" value="">
			</div>
			<br>
			<div id="lm">
				商品条码:(不填默认查询全部)<input placeholder="输入商品条码" name="barNo"
					class="form-control" class="Wdate" type="text" 
					value="" style="width: 320px;" onkeyup="if(!/^\d+$/.test(this.value)) {alert('只能输入数字 !'); this.value=this.value.replace(/[^\d]+/g,'');}"> 
					原因选择: <br> <select
					class="form-control" name="area" style="width: 150px;"
					id="specialName">
					<option value="all">全部</option>
				</select>
				<!-- 当前页码 -->
				<input id="nowPage" type="hidden" name="nowPage" value="1">

				<p style="margin-top: -35px;margin-left: 205px;" align="left">
					<button class="btn btn-primary btn-md" id="btn-todo" type="submit">导出客退资料</button>
					<!--button class="btn btn-primary btn-md" id="btn-todo" type="reset">重&nbsp;置</button -->
				</p>
			</div>
		</s:form>
	</div>
	<!--/.row-->
</div>
<!--/.main-->

<jsp:include page="foot.jsp"></jsp:include>