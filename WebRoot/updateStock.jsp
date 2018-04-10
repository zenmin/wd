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
			if ($.trim(file.val()) == ''||$.trim(file.val()) == null) {
				alert("请选择Excel文件！");
				return false;
			} else {
				$("#msg").text("正在提交更新,请稍后...");
			    $.dialog('alert','提示','正在提交更新中...',5000); //自动关闭
				$.ajax({
					cache : false,
					url : "updateStock",
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
	
	$(document).ready(function() {
		$("#help").hide();
	});
	function showHelp(){
		$("#help").show();   
	}
	function hideHelp(){
        $("#help").hide();   
    }
	
</script>
<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
	<div class="row">
		<ol class="breadcrumb">
			<li><a href="#"><span class="glyphicon glyphicon-home"></span></a></li>
			<li class="active">实时库存更新</li>
		</ol>
	</div>
	<!--/.row-->

	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">实时库存更新</h1>
		</div>
	</div>
	<!--/.row-->
	<!-- 行列 -->
	<div align="center">
		<b> <span id="msg" style="color:#FF8247;font-size:16px;"></span>
		</b>
	</div>
	<br>

	<p align="center">
		实时库存更新 <br> <a href="excel/kucun.xlsx">点击下载示例库存Excel表</a>
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
	<!-- div align="center"
		style="width:680px;height:200px;margin-left:auto;margin-right:auto;margin-top:-100px">
		<p align="left">
			<span style="color:red;">警告：千万不能直接更新唯品会后台导出的 实时库存导出.xlsx 这个表格
				否则只能重启服务器; <br>你需要按以下步骤操作 来更新实时库存:
			</span> <br>1、首先打开唯品会后台导出的 实时库存导出.xlsx 这个表格<br> 2、选中并复制这个表格中的每一行数据<br>
			3、下载上面的示例库存Excel表 并且打开<br> 4、把刚刚复制的内容粘贴到示例库存Excel表<br>
			5、上传示例库存Excel进行更新<br> <a href="#" onclick="showHelp()"><span
				style="color:red;">还是不懂？点击查看图文说明!</span></a>
		</p>
	</div>

	<div id="help" align="center">
		<div align="center"
			style="width:50%;height:100%;margin-left:auto;margin-right:auto;margin-top:-30px">
			<p align="left">
				由于唯品会后台导出的实时库存的Excel表格内有筛选列 我们的系统无法完成读取<br>并且如果直接上传唯品会后台导出的
				实时库存导出.xlsx 这个表格 <br> <span style="color:red;">会直接导致系统崩溃!</span><br>所以
				你必须按照以下步骤操作,简单来说就是把唯品会后台导出的表格的内容复制到一个新的Excel表格中：
			</p>
			<span style="color:blue;float:left;">1、首先打开从唯品会后台导出的
				实时库存导出.xlsx 这个表格</span> <img alt="" width="100%"
				src="img/20170608161157.png" /> <span
				style="color:blue;float:left;">2、选中并复制表格中的内容</span> <img alt=""
				width="100%" src="img/20170608185345.png" /> <span
				style="color:blue;float:left;">3、点击下载示例库存表格 保存至桌面</span> <img alt=""
				width="100%" src="img/20170608185802.png" /> <img alt=""
				width="100%" src="img/20170608190036.png" /> <span
				style="color:blue;float:left;">4、打开下载的示例表格</span> <img alt=""
				width="100%" src="img/201706028111.png" /> <span
				style="color:blue;float:left;">点击第一个单元格 然后 Ctrl+V粘贴</span> <img
				alt="" width="100%" src="img/20170608190626.png" /> <img alt=""
				width="100%" src="img/20170608190640.png" /> <span
				style="color:blue;float:left;">5、保存此示例表格 然后上传本表格完成库存更新</span> <img
				alt="" width="100%" src="img/20160608999.png" /> <img alt=""
				width="100%" height="50%" src="img/20170608191034.png" />
				 <br>
				 <span style="color:blue;float:center;">
        <a href="#" onclick="hideHelp()" ><span style="font-szie:30px;color:red;"> 看完了！点击收起图文说明</span></a>
        </span>
        <br>
		</div>
		
		 
	</div>
 -->

</div>

<!--/.main-->

<jsp:include page="foot.jsp"></jsp:include>