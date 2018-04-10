<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<jsp:include page="head.jsp"></jsp:include>
<link rel="stylesheet" href="js/jq/animate.min.css"/> <!-- 动画效果 -->
<script src="js/jq/jquery-1.9.1.min.js"></script>
<script src="js/jq/jquery.hDialog.min.js"></script>
<%-- <script src="js/jquery-3.2.1.js"></script> --%>
<script type="text/javascript">
	$(function() {
		$('#sub').click(function() {
			var file = $("#excel");
			if ($.trim(file.val()) == '') {
				alert("请选择新建货号Excel文件！");
				return false;
			}
// 				var excel = new FormData().append("excel",$("#file")[0]);
// 				console.debug($("#excel").val());
// 				$.ajax({
// 					url:"updateBarAndGoods",//请求Url
// 					enctype:"multipart/form-data",
// 					method:"post",//请求方式
// 					cache:false,//不缓存
// 					contentType: false,//必须false才会自动加上正确的Content-Type  
// 					processData: false,//必须false才会避开jQuery对 formdata 的默认处理
// 					data : new FormData($('#fileForm')[0]),
// 				success:function(){
// 					alert("111");
// 				}
// 				});

// 			$("#msg").text("提示：" +  "请稍等，数据录入中");
// 			$.dialog('alert','提示','正在处理中...',0);
                $("#msg").text("正在提交更新,请稍后...");
                $.dialog('alert','提示','正在提交更新中...',5000); //
			$.ajax({
				cache : false,
				url : "updateBarAndGoods",
				method : "post",
				enctype : 'multipart/form-data',
				data : new FormData($('#fileForm')[0]),
				processData : false,
				contentType : false,
				success : function(result) {
// 					$.tooltip(result, 3000, true);
					$.tooltip(result, 3000, true);
                    $("#msg").text("提示：" +  result);
				},
		        error:function(){
		        	$("#errorMsg").text("提交到服务器错误或响应超时,请重启Tomcat服务器,如果还是不能解决请联系开发人员!");
		        }
			});
		});
	});
</script>
<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
	<div class="row">
		<ol class="breadcrumb">
			<li><a href="#"><span class="glyphicon glyphicon-home"></span></a></li>
			<li class="active">货号与条码更新</li>
		</ol>
	</div>
	<!--/.row-->

	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">货号与条码更新</h1>
		</div>
	</div>
	<!--/.row-->
	<!-- 行列 -->
	<div align="center">
	</div>
	<div align="center">
	<b>
        <span id="msg" style="color:#FF8247;font-size:16px;"></span>
        </b>
        </div>
     <p align="center">
        货号与条码更新<br> <a href="excel/xinjianhuohao.xlsx">点击下载示例新建货号Excel表</a>
    </p>   
     
	<p align="center">从唯品会后台-商品-商品详情,一次更新一个表格</p>
	<div class="form-group" align="center"
		style="width:600px;height: 400px;margin: auto;">

		<s:form role="search" method="post" action="action" id="fileForm" enctype="multipart/form-data" theme="simple">
<!--             <s:token></s:token> -->
			<br> 选择Excel表格文件：<br>
			<br>
			<s:file name="excel" label="sales" id="excel" accept=".xls,.xlsx"
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