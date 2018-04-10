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
	height: 156px;
	margin: auto;
	float: left;
	margin-top: -20px;
}

#date2 {
	width: 370px;
	height: 126px;
	margin: auto;
	margin-left: 20px;
	float: left;
	margin-top: -15px;
}
</style>

<jsp:include page="head.jsp"></jsp:include>

<script language="javascript" type="text/javascript"
	src="js/My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript" src="js/sales.js"></script>
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
  window.onload = function(){
     /*
      var pager = getParam('nowPage');
      document.getElementById("jumpPage").value = pager;
      if(getParam('nowPage')==1){
          $("#firstPage").hide();
      }else{
          $("#firstPage").show();
      }
      */
	
  };
  
    $(document).ready(function() {
        shwospceial();
        $("#barclass").hide();
//         $(".msg2").hide();
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
    

    function findSales(){
    	
        $(".msg").text("查询中,请稍等...");
        $.ajax({
            cache:false,
            url:"findSales",            
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
                    alert('没有查询到符合条件的销售信息哦！');
                }
                $.each(dataObj,function(index,item){
                    con1 = "<tr>" + 
                    "<td>" + item.id + "</td>" + 
                    "<td>" + item.barNo + "</td>" + 
                    "<td>" + item.special + "</td>" + 
                    "<td>" + item.date + "</td>" + 
                    "<td>" + item.zone + "</td>" + 
                    "<td>" + item.salesSoldnum + "</td>" + 
                    "<td>" + item.salesStock + "</td>" + 
                    "<td>" + item.salesNum + "</td>" + 
                    "<td>" + item.salesMoney + "</td>" + 
                    "<td>" + item.salesPeople + "</td>" + 
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
    $(function (){
    $("#tj").click(function(){
    	  $("#barNo").val("");
          $("#barclass").val('');
    	var name = $("#tj").find("option:selected").text();
    	if(name=='条码'){
    		 $("#barNo").show();
    		$("#barclass").hide();
    	}
    	if(name=='类目'){
            $("#barclass").show();
            $("#barNo").hide();
          
        }
    })
    })
</script>



<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
	<div class="row">
		<ol class="breadcrumb">
			<li><a href="#"><span class="glyphicon glyphicon-home"></span></a></li>
			<li class="active">销售资料</li>

		</ol>
	</div>
	<!--/.row-->

	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">销售资料</h1>
		</div>
	</div>
	<!--/.row-->

	<div class="row">
		<s:form id="salesform" method="post" class="form-group" action=""
			theme="simple">
			<div id="date1">
				档期段选择:(不填默认查询全部)<br> 开始日期:<input placeholder="点击选择开始日期"
					class="form-control" class="Wdate" type="text"
					onClick="WdatePicker()" style="width: 300px;" name="startTime"
					value=""> 结束日期:<input class="form-control" class="Wdate"
					type="text" placeholder="点击选择结束日期" onClick="WdatePicker()"
					style="width: 300px;" name="endTime" value="">
			</div>
			<br>

			<div id="date2"><br> 
			<span style="">查询条件:(不填默认查询全部)</span><br>
				<select id="tj" class="form-control" style="width: 80px;float:left;">
					<option value="1">条码</option>
					<option value="2">类目</option>
				</select> <br> <br> 
				<input placeholder="输入商品条码" name="barNo"
					id="barNo" class="form-control" class="Wdate" type="text" value=""
					style="width: 220px;float:left;" /> 
			   <input placeholder="输入类目名称"
					name="barclass" id="barclass" class="form-control" class="Wdate"
					type="text" value="" style="width: 220px;float:left;"
					onclick="javascript:alert('*提示：\n 1、这里只需要输入简写类目名称,比如 文体用品/美体塑身/健身器材/瑜伽用品 查一级类目销售情况直接输入  文体 ,查四级类目销售情况直接输入  瑜伽 即可. \n 2、请保证销售资料里面的条码存在于产品资料中,否则查询出来的销售情况可能不完整!')" />

			</div>



			<!-- div id="lm">
                 
   
   </div -->
     专场选择:   <br>
			<select class="form-control" name="specialId" style="width: 150px;"
				id="specialName">
				<option value="all">全部</option>
			</select>
			<%-- 当前页码--%>
			<input id="nowPage" type="hidden" name="nowPage" value="1">
			<br>

			<button style="width: 150px;margin-left: 2px;"
				class="btn btn-primary btn-md" id="btn-todo" onclick="findSales()"
				type="button">提&nbsp;交&nbsp;查&nbsp;询</button>

		</s:form>

		<!--          <div class="msg2" style="color:red;font-size:14px;margin:auto;height:56px;width:800px;"> -->
		<!--                   </div><br>   -->
	</div>
	<!--/.row-->
	<table class="zebra" style="border:1;border-color: gray;"
		align="center">
		<span id="msg1" style="color:red;float:right;margin-top: -30px;">*注意：显示"undefined"表示记录为空！</span>
		<thead>
			<tr>
				<th>销售流水号</th>
				<th>商品条码</th>
				<th>专场名</th>
				<th>档期</th>
				<th>销售城市</th>
				<th>总销售量</th>
				<th>总库存量</th>
				<th>总备货量</th>
				<th>销售额(扣满减未扣拒退)</th>
				<th>购买人数</th>

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
				<td colspan="9" align="center">当前第 <span id="pageSpan">1</span>
					页丨 <a><button onclick="jump(1)">上一页</button></a> <a><button
							onclick="jump(2)">下一页</button></a> <span id="MaxPage">共<span id="MaxCount">1</span>页</span>
			</tr>
		</tfoot>

	</table>
</div>
<!--/.main-->

<jsp:include page="foot.jsp"></jsp:include>