<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<script language="javascript" type="text/javascript" src="js/sales.js"></script>
<link rel="stylesheet" href="js/jq/animate.min.css" />
<!-- 动画效果 -->
<script src="js/jq/jquery-1.9.1.min.js"></script>
<script src="js/jq/jquery.hDialog.min.js"></script>
<style>
#date1 {
	width: 430px;
	height: 135px;
	margin: auto;
	margin-left: 20px;
	float: left;
}

#date2 {
	width: 430px;
	height: 135px;
	margin: auto;
	margin-left: 20px;
	float: left;
}

#lm {
	width: 430px;
	height: 130px;
	margin: auto;
	float: left;
	margin-left: 20px;
}
</style>

<jsp:include page="head.jsp"></jsp:include>

<script language="javascript" type="text/javascript"
	src="js/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript">
var nowPage=1;
function jump(cz){
    if(cz==1){
     $("#nowPage").val(parseInt(nowPage-1));
     $("#btn-todo").click();
     nowPage-=1;
     if(nowPage<=0){
         nowPage=1;
     }
      $("#nowPage").val(nowPage);      //  为当前页码赋值到表单中
      $("#pageSpan").text(nowPage);     //  当前页码
     
    }
    if(cz==2){
         $("#nowPage").val(parseInt(nowPage+1));
         $("#btn-todo").click();
         nowPage+=1;
   }
      $("#nowPage").val(nowPage);      //  为当前页码赋值到表单中
      $("#pageSpan").text(nowPage);     //  当前页码
     
}


function findSales(){
    $(".msg").text("查询中,请稍等...");
    $.ajax({

        url:"findStock",            
        method:"post",
        dataType:"json",
        data:$('#salesform').serialize(),
        success:function(result){

            var dataObj=eval(result);  
            con1 = "";
            $("#salesTab").empty();
            if(dataObj==""||dataObj==null)
            {
                nowPage=1;
                $("#nowPage").val(nowPage);
                 $("#pageSpan").text(nowPage); 
                $("#salesTab").append("<td id='msg' style='line-height: 5" +
                        ";color:blue;' align='center' " +
                        "colspan='9'><span class='msg'>没有查询结果！</span></td>");
                alert('没有查询到符合条件的库存信息哦！');
            }
            $.each(dataObj,function(index,item){
                con1 = "<tr>" + 
                "<td>" + item.goodsNo + "</td>" + 
                "<td>" + item.barNo + "</td>" + 
                "<td>" + item.barName + "</td>" + 
                "<td>" + item.zone + "</td>" + 
                "<td>" + item.stock + "</td>" + 
                "<td>" + item.date + "</td>" + 
                "</tr>";

                $("#salesTab").append(con1);
                $("#MaxCount").text(item.size);
            });
        },
        error:function(){
            alert('发生错误了,可能是服务器或数据库配置不正确哦,请联系开发人员吧！');
        }
    });

};

function exportStock(){
    $.ajax({
        url:"ExportStock",            
        method:"post",
        dataType:"json",
        data:$('#salesform').serialize(),
        success:function(result){
        	$.dialog('alert','提示','正在导出中...请注意下载弹窗!',5000); //不自动关闭
        },
        error:function(){
            alert('发生错误了,可能是服务器或数据库配置不正确哦,请联系开发人员吧！');
        }
    });

}
	
</script>



<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
	<div class="row">
		<ol class="breadcrumb">
			<li><a href="#"><span class="glyphicon glyphicon-home"></span></a></li>
			<li class="active">实时库存</li>

		</ol>
	</div>
	<!--/.row-->

	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">实时库存</h1>
		</div>
	</div>
	<!--/.row-->

	<div class="row">
		<s:form id="salesform" method="post" class="form-group" action=""
			theme="simple">
			<div id="date1">

				上架时间范围选择:(不填默认全部)<br> 开始日期:<input placeholder="点击选择开始日期"
					class="form-control" class="Wdate" type="text"
					onClick="WdatePicker()" style="width: 300px;" required="required"
					name="startTime" value=""> 结束日期:<input
					class="form-control" class="Wdate" type="text"
					placeholder="点击选择结束日期" onClick="WdatePicker()"
					style="width: 300px;" required="required" name="endTime"
					value="">

			</div>
			<br>
			<div id="date2">
                             商品条码:(不填显示全部)<input
                    placeholder="输入商品条码" name="barNo" class="form-control"
                    class="Wdate" type="text" required="required" value=""
                    style="width: 300px;">
				货号:(不填显示全部)<input placeholder="输入货号" name="goods"
					class="form-control" class="Wdate" type="text" required="required"
					value="" style="width: 300px;"> 
			</div>
			<div id="lm">
				仓库选择:(不填默认全部仓库) 
				<select class="form-control" name="zone"
					style="width: 150px;" id="specialName">
					<option value="all">全部</option>
					<option value="华北仓">华北仓</option>
					<option value="西南仓">西南仓</option>
					<option value="华中仓">华中仓</option>
					<option value="华北仓">华东仓</option>
					<option value="华南仓">华南仓</option>
					<option value="东北仓">东北仓</option>
					<option value="港澳台仓">港澳台仓</option>
				</select>
				<!-- 当前页码 -->
				<input id="nowPage" type="hidden" name="nowPage" value="1">

				<p style="margin-top: 20px;margin-right: 0px;" align="left">
					<button class="btn btn-primary btn-md" id="btn-todo"
						onclick="findSales()" type="button">提&nbsp;交&nbsp;查&nbsp;询</button>
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
				<th>货号</th>
				<th>条码</th>
				<th>商品名称</th>
				<th>地区</th>
				<th>库存</th>
				<th>上架时间</th>
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