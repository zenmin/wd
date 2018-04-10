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
}
#date2{
    width: 660px;
    height: 166px;
    margin: auto;
    margin-left: 120px;
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

var nowPage=1;

/*获取URL参数值的方法
var getParam = function(name){  
    var search = document.location.search;  
    var pattern = new RegExp("[?&]"+name+"\=([^&]+)", "g");  
    var matcher = pattern.exec(search);  
    var items = null;  
    if(null != matcher){  
            try{  
                    items = decodeURIComponent(decodeURIComponent(matcher[1]));  
            }catch(e){  
                    try{  
                            items = decodeURIComponent(matcher[1]);  
                    }catch(e){  
                            items = matcher[1];  
                    }  
            }  
    }  
    return items;  
};  
*/
  
	$(document).ready(function() {
		shwospceial();
		$(function(){
			$("#btn-todo").click(function(){
				$.dialog('alert','提示','正在导出中...请注意下载弹窗!',5000); //不自动关闭
			});
		})
	});
	
	function jump(cz){
		if(cz==1){
		 $("#nowPage").val(parseInt(nowPage-1));
		 $("#btn-todo").click();
		 nowPage-=1;
		 if(nowPage==0){
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
	


	
</script>
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
			<li class="active">销售资料导出</li>

		</ol>
	</div>
	<!--/.row-->

	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">销售资料导出</h1>
		</div>
	</div>
	<!--/.row-->

${requestScope.msg }
	<div class="row">
		<s:form id="salesform" method="post" class="form-group" action="findAndExportSales"
			theme="simple">
			<div id="date1">
		  日期段选择:(不填默认查询全部)<br>
				开始日期:<input placeholder="点击选择开始日期" class="form-control"
					class="Wdate" type="text" onClick="WdatePicker()"
					style="width: 300px;"  name="startTime"
					value=""> 结束日期:<input class="form-control"
					class="Wdate" type="text" placeholder="点击选择结束日期"
					onClick="WdatePicker()" style="width: 300px;" 
					name="endTime" value="">
			</div>
    <br>
  
			<div id="lm">
				商品条码:(不填默认查询全部)<input placeholder="输入商品条码" name="barNo" class="form-control"
					class="Wdate" type="text"  value=""
					style="width: 300px;" onkeyup="if(!/^\d+$/.test(this.value)) {alert('只能输入数字 !'); this.value=this.value.replace(/[^\d]+/g,'');}"> 
					专场选择: <br> <select
					class="form-control" name="specialId" style="width: 300px;"
					id="specialName">
					<option value="all">全部</option>
				</select>
				  
				<!-- 当前页码 -->
				<input id="nowPage" type="hidden" name="nowPage" value="1">

				
				</div>
			<div id="date2">
			 选择导出列：
			 <%-- 
			 <s:checkboxlist name="checkCondition" 
			list="#{'0':'','1':'日期','2':'总销售量','3':'专场名称','4':'区域','5':'货号','6':'品名','7':'小组','8':'类目'}"
			listKey="key" listValue="value" value="0"
			></s:checkboxlist>
			--%>
			<label><input name="checkCondition" type="checkbox" checked="checked" value="0" />条码 </label>
			<label><input name="checkCondition" type="checkbox" value="1" />日期 </label>
			<label><input name="checkCondition" type="checkbox" value="2" />总销售量 </label>
			<label><input name="checkCondition" type="checkbox" value="3" />专场名称 </label>
			<label><input name="checkCondition" type="checkbox" value="4" />区域 </label>
			<label><input name="checkCondition" type="checkbox" value="5" />货号</label>
			<label><input name="checkCondition" type="checkbox" value="6" />商品名称 </label>
			<label><input name="checkCondition" type="checkbox" value="7" />采购员 </label>
			<label><input name="checkCondition" type="checkbox" value="8" />类别 </label>
			<br>
			<span style="color:red;">*注意：条码默认必选,选择的范围越大,越详细,导出的等待时间越长,请根据实际需要选择。<br>
									 请保证要导出的条码信息存在于商品信息中！
			</span>
			</div>
			<p style="margin-top: 80px;margin-left: 330px;">
                    <input class="btn btn-primary btn-md" id="btn-todo"
                        type="submit" value="导  出  数  据"></input>
                    <!--button class="btn btn-primary btn-md" id="btn-todo" type="reset">重&nbsp;置</button -->
                </p>
		</s:form>
	</div>
	<!--/.row-->
    
</div>
<!--/.main-->

<jsp:include page="foot.jsp"></jsp:include>