<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<jsp:include page="head.jsp"></jsp:include>

<link rel="stylesheet" type="text/css" media="screen" href="css-table.css" />
<link rel="stylesheet" type="test/css" media="screen" href="bookstrap.css" />
<script type="text/javascript" src="js/jquery-1.2.6.min.js"></script>
<script type="text/javascript" src="js/style-table.js"></script>

	   <div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">           
        <div class="row">
            <ol class="breadcrumb">
                <li><a href="#"><span class="glyphicon glyphicon-home"></span></a></li>
                <li class="active">类目资料</li>
            </ol>
        </div><!--/.row-->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">类目资料</h1>
            </div>
        </div><!--/.row-->
<div class="form-group" style="width:600px;height: 400px;margin: auto;" align="center">

		<form  method="get" action="updateClass">
		 
		  	<br>
			类目ID：<input class="form-control" name="id" value="${request.tbGoods.goodsId}" readonly="readonly" onclick="javascript:alert('类目ID不可修改！');" type="text" /> 
			   <br>
			类目名称：<input class="form-control"  name="className" placeholder="请输入类目名称" value="${request.tbGoods.class_ }"  type="text" /> 
			   <br>
			<button class="btn btn-primary btn-md" id="btn-todo" type="submit">提&nbsp;交</button>
			&nbsp; &nbsp;
			<button class="btn btn-primary btn-md" id="btn-todo" type="reset">重&nbsp;置</button>
		</form>
	</div>

    </div><!--/.main-->

  <jsp:include page="foot.jsp"></jsp:include>