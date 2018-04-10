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
	margin-left: 20px;
}
</style>

<jsp:include page="head.jsp"></jsp:include>

<script language="javascript" type="text/javascript"
	src="js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">

	var nowPage = 1;
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

		}
		if (cz == 2) {
			$("#nowPage").val(parseInt(nowPage + 1));
			$("#btn-todo").click();
			nowPage += 1;
		}
		$("#nowPage").val(nowPage); //  为当前页码赋值到表单中
		$("#pageSpan").text(nowPage); //  当前页码

	}
	function findReturns() {
		$(".msg").text("查询中,请稍等...");

		$.ajax({
			        cache:false,
					url : "findReturns",
					method : "post",
					dataType : "json",
					data : $('#salesform').serialize(),
					success : function(result) {
						
						var dataObj = eval(result);
						var avg = 0.00;
						var avg1 = 0.00;
						con1 = "";
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
							alert('没有查询到符合条件的客退信息哦！');
						}
						$.each(dataObj, function(index, item) {
							con1 = "<tr>" + "<td>" + item.barNo + "</td>"
									+ "<td>" + item.goodsNo + "</td>" + "<td>"
									+ item.returnsReason + "</td>" + "<td>"
									+ item.returnsNum + "</td>" + "<td>"
									+ item.returnsRejectnum + "</td>" + "<td>"
									+ parseFloat(item.retuenrate) * 100 + "%"
									+ "</td>" + "<td>" + item.date + "</td>"
									+ "</tr>";

							//计算平均据退率  
							avg = (avg + parseFloat(item.retuenrate));
							// 	索引赋值给外面
							avg1 = index + 1;
							$("#salesTab").append(con1);
						
							  //替换undefined
// 	                          var bodyHtml = $("body").html();
// 	                          var x = bodyHtml.replace("undefined","空");
// 	                          $("body").html(x);
	                          

							$("#MaxCount").text(item.size);
							
							
						});

						//  据退率计算 
						var num = Math.round((avg / avg1) * 100);
					
						$("#salesTab").append(
								"<tr><td colspan='7' align='center'>"
										+ "<span style='color:blue;'>本页平均据退率:"
										+  num.toFixed(2)+ "%</span></td></tr>");
					},
					error : function() {
						alert('发生错误了,可能是服务器或数据库配置不正确哦,请联系开发人员吧！');
					}
				});

	};
	
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

	<div class="row">
		<s:form id="salesform" method="post" class="form-group"
			action="findRturns" theme="simple">
			<div id="date1">
			  日期段选择:(不填默认查询全部)<br>
				开始日期:<input placeholder="点击选择开始日期" class="form-control"
					class="Wdate" type="text" onClick="WdatePicker()"
					style="width: 300px;" required="required" name="startTime"
					value=""> 结束日期:<input class="form-control"
					class="Wdate" type="text" placeholder="点击选择结束日期"
					onClick="WdatePicker()" style="width: 300px;" required="required"
					name="endTime" value="">
			</div>
    <br>
			<div id="lm">
				商品条码:(不填默认查询全部)<input placeholder="输入商品条码" name="barNo" class="form-control"
					class="Wdate" type="text" required="required" value=""
					style="width: 300px;"> 原因选择: <br> <select
					class="form-control" name="area" style="width: 150px;"
					id="specialName">
					<option value="all">全部</option>
					<option value="尺码偏小">尺码偏小</option>
					<option value="尺码偏大">尺码偏大</option>
					<option value="发错商品/尺码等">发错商品/尺码等</option>
					<option value="找到价格更低的商品/性价比不高">找到价格更低的商品/性价比不高</option>
					<option value="有色差/瑕疵">有色差/瑕疵</option>
					<option value="漏发货/多发/少发商品配件">漏发货/多发/少发商品配件</option>
					<option value="收货迟/物流慢">收货迟/物流慢</option>
					<option value="商品大小/规格/容量不适合">商品大小/规格/容量不适合</option>
					<option value="与描述不符（颜色、面料、款式、功效等）">与描述不符（颜色、面料、款式、功效等）</option> 
					<option value="商品质量问题（脏污/掉色/起球/做工差等）">商品质量问题（脏污/掉色/起球/做工差等）</option>  
					<option value="商品有破损/变形/缺陷">商品有破损/变形/缺陷</option>  
				    <option value="上身效果差/不合身型">上身效果差/不合身型</option>   
					<option value="无退货">无退货</option>
				   <option value="其他">其他</option>   
				</select>
				<!-- 当前页码 -->
				<input id="nowPage" type="hidden" name="nowPage" value="1">

				<p style="margin-top: -35px;margin-left: 205px;" align="left">
                    <button class="btn btn-primary btn-md" id="btn-todo"
                        onclick="findReturns()" type="button">提&nbsp;交&nbsp;查&nbsp;询</button>
                    <!--button class="btn btn-primary btn-md" id="btn-todo" type="reset">重&nbsp;置</button -->
                </p>
			</div>
		</s:form>
		 <span id="msg1" style="color:red;float:right;">*注意：显示"undefined"表示记录为空！</span>
	</div>
	<!--/.row-->
	
	<table class="zebra" style="border:1;border-color: gray;"
		align="center">
		<thead>
			<tr>
				<th>条码</th>
				<th>货号</th>
				<th>退货原因</th>
				<th>退货量</th>
				<th>拒收量</th>
				<th>拒退率</th>
				<th>档期</th>
			</tr>
		</thead>
		<tbody id="salesTab">
			<tr>
				<td id="msg" style="line-height: 5;color:blue;" align="center"
					colspan="9"><span class="msg">没有查询结果！</span></td>
			</tr>
		</tbody>
		<tfoot>
			<tr id="Page">
				<td colspan="7" align="center">当前第 <span id="pageSpan">1</span>
					页丨 <a><button onclick="jump(1)">上一页</button></a> <a><button
							onclick="jump(2)">下一页</button></a> <span id="MaxPage">共<span id="MaxCount">1</span>页</span>
			</tr>
		</tfoot>

	</table>
</div>
<!--/.main-->

<jsp:include page="foot.jsp"></jsp:include>