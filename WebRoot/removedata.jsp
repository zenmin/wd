<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<jsp:include page="head.jsp"></jsp:include>
<script type="text/javascript">
function delData() {
	var name = $('#tableName').find("option:selected").text();
	var table = $('#tableName').val();
    if (confirm("你确定要清空 "  + name +" 吗？")) {
        $.ajax({
            cache : false,
            url : "delData",
            method : "post",
            dataType : "json",
            data : {
                "table" : table
            },
            success : function(result) {
                if (result == '1') {
                    alert("清空 "  + name +" 成功！");
                    showUser();
                }
                if (result == '0') {
                	   alert("清空 "  + name +" 失败！");
                }
            },
            error : function() {
                alert('未连接到服务器,请检查服务器或数据库配置！');
            }
        });
    }
}
</script>
<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
	<div class="row">
		<ol class="breadcrumb">
			<li><a href="#"><span class="glyphicon glyphicon-home"></span></a></li>
			<li class="active">
			<s:if test="#session.power != 1">
                    <script>
                        alert('对不起,你没有权限！此功能仅限超级管理员！');
                        window.history.back();
                    </script>
                </s:if> 清空数据</li>
		</ol>
	</div>
	<!--/.row-->

	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">清空数据</h1>
		</div>
	</div>
	<!--/.row-->
	<!-- 行列 -->

	<div class="form-group" align="center"
		style="width:600px;height: 400px;margin: auto;">

<span style="color:red;font-size:16px;">*请注意：此功能慎用，提交后会直接清空数据表，建议清空前备份！</span><br>

		<form role="search" method="post" action=""><br>
    请选择需要清空的表： <select class="form-control" name="table" style="width: 150px;"
				id="tableName">
				<option value="bar">产品资料</option>
				<option value="goods">类目资料</option>
				<option value="special">专场资料</option>
				<option value="sales">销售资料</option>
				<option value="returns">客退资料</option>
				<option value="stock">库存资料</option>
				<option value="gg">首页公告</option>
				<option value="bwl">首页备忘录</option>
			</select>
			
			
			<br>
			<button class="btn btn-primary btn-md" id="btn-todo" onclick="delData()" type="button">提&nbsp;交</button>
			&nbsp; &nbsp;
			<button class="btn btn-primary btn-md" id="btn-todo1" type="reset">重&nbsp;置</button>
		</form>



	</div>

	<!-- 结束 -->


</div>
<!--/.main-->

<jsp:include page="foot.jsp"></jsp:include>