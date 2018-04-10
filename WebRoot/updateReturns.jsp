<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<jsp:include page="head.jsp"></jsp:include>
<link rel="stylesheet" href="js/jq/animate.min.css" />
<!-- 动画效果 -->
<script src="js/jq/jquery-1.9.1.min.js"></script>
<script src="js/jq/jquery.hDialog.min.js"></script>
<script type="text/javascript">
	$(function() {
		$('#sub').click(function() {
		
			var file = $("#file");
			if ($.trim(file.val()) == '') {
				alert("请选择销售流水Excel文件！");
				return false;
			} else {
			    $("#msg").text("正在提交更新,请稍后...");
			    $.dialog('alert','提示','正在提交更新中...',5000); //不自动关闭
				$.ajax({
					cache : false,
					url : "updateReturns",
					method : "post",
					enctype : 'multipart/form-data',
					data : new FormData($('#fileForm')[0]),
					processData : false,
					contentType : false,
					success : function(result) {
						$.tooltip(result, 3000, true);
						$("#msg").text("提示：" +  result);
					},
			        error:function(){
			        	$("#errorMsg").text("提交到服务器错误或响应超时,请重启Tomcat服务器,如果还是不能解决请联系开发人员!");
			        }
				});
			}
// 			window.reload();
		});

// 		var $el = $('.dialog');
// 		$el.hDialog(); //默认调用
// 		alert
// 		$('#tc').click(function() {
// 				        $.dialog('alert','提示','正在处理中...'); 或者
// 				        $.dialog('alert','提示','正在处理中...',0); //不自动关闭
// 			$.dialog('alert', '提示', '正在处理中...', 2000, function() {
// 				$.tooltip('执行回调...', 4000, true);
// 			}); //2s自动关闭
// 		});
	});
</script>
<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
	<div class="row">
		<ol class="breadcrumb">
			<li><a href="#"><span class="glyphicon glyphicon-home"></span></a></li>
			<li class="active">客退数据更新</li>
		</ol>
	</div>
	<!--/.row-->

	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">客退数据更新</h1>
		</div>
	</div>
	<!--/.row-->
	<!-- 行列 -->
	<div align="center">
	<b>
		<span id="msg" style="color:#FF8247;font-size:16px;"></span>
		</b>
	</div>
	<br>

	<p align="center">更新客退数据
	<br>
	  <a href="excel/ketui.xlsx">点击下载示例客退流水Excel表</a>
	  </p>
	
	<div class="form-group" align="center"
		style="width:600px;height: 400px;margin: auto;">

		<s:form role="search" method="post" action="" id="fileForm"
			enctype="multipart/form-data" theme="simple">
			<!--             <s:token></s:token> -->
			<br> 选择Excel表格文件：<br>
			<br>
			<s:file name="sales" label="sales" id="file" accept=".xls,.xlsx"
				required="required"></s:file>
			<br>
			<button class="btn btn-primary btn-md" id="sub" type="button">提&nbsp;交&nbsp;更&nbsp;新</button>
			&nbsp;&nbsp;
			<button class="btn btn-default" id="btn-todo" type="reset">重&nbsp;置</button>
		</s:form>
		<br>
		
		 <div>
        <span style="color:bule;">更新预计等待时间：</span><br>
          1000行：3秒<br>
           5000行：8秒<br>
           10000行：21秒<br>
           15000行：40秒<br>
           20000行：61秒<br>
        </div>
	</div>
    
 
	<!-- 结束 -->


</div>
<!--/.main-->

<jsp:include page="foot.jsp"></jsp:include>