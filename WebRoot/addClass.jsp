<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<jsp:include page="head.jsp"></jsp:include>

<link rel="stylesheet" type="text/css" media="screen" href="css-table.css" />
<link rel="stylesheet" type="test/css" media="screen" href="bookstrap.css" />
<script type="text/javascript" src="js/jquery-1.2.6.min.js"></script>
<script type="text/javascript" src="js/style-table.js"></script>
<script type="text/javascript">
<!--

//-->

function addClass(){
	$.ajax({
        cache : false,
        url : "addClass",
        method : "post",
        dataType : "json",
        data : $("#class").serialize(),
        success : function(result) {
            if (result == '1') {
            	alert("添加分类成功!");
            }
            if (result == '0') {
                alert("添加分类失败!可能已经含有同名类目!");
            }
        },
        error : function() {
            alert('未连接到服务器,请检查服务器或数据库配置！');
        }
    });
}


</script>
	   <div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">           
        <div class="row">
            <ol class="breadcrumb">
                <li><a href="#"><span class="glyphicon glyphicon-home"></span></a></li>
                <li class="active">新增类目</li>
            </ol>
        </div><!--/.row-->
        
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">新增类目</h1>
            </div>
        </div><!--/.row-->
<div class="form-group" style="width:600px;height: 400px;margin: auto;" align="center">

		<form  action="addClass" id="class" method="post" >
		 
		  	<br>
			类目ID：<input class="form-control" name="id" value="" readonly="readonly" placeholder="本ID无需填写 由系统自动生成" type="text" /> 
			   <br>
			货号：<input class="form-control" name="goodsNo" value=""  placeholder="请输入货号" type="text" /> 
			   <br>
			类目名称：<input class="form-control"  name="addClassName" placeholder="请输入类目名称"  type="text" /> 
			   <br>
			      <br>
            小组：(如"1组" 请输入 "1" )<input class="form-control"  name="xz" placeholder="请输入小组序号"  type="number" /> 
               <br>
			<button class="btn btn-primary btn-md" id="btn-todo" onclick="" type="submit">提&nbsp;交</button>
			&nbsp; &nbsp;
			<button class="btn btn-primary btn-md" id="btn-todo" type="reset">重&nbsp;置</button>
		</form>
	</div>

    </div><!--/.main-->

  <jsp:include page="foot.jsp"></jsp:include>