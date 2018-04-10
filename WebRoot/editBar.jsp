<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<jsp:include page="head.jsp"></jsp:include>

<link rel="stylesheet" type="text/css" media="screen"
	href="css-table.css" />
<link rel="stylesheet" type="test/css" media="screen"
	href="bookstrap.css" />
<script type="text/javascript" src="js/jquery-1.2.6.min.js"></script>
<script type="text/javascript" src="js/style-table.js"></script>

<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
	<div class="row">
		<ol class="breadcrumb">
			<li><a href="#"><span class="glyphicon glyphicon-home"></span></a></li>
			<li class="active">类目资料</li>
		</ol>
	</div>
	<!--/.row-->

	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">类目资料</h1>
		</div>
	</div>
	<s:debug></s:debug>
	<!--/.row-->
	<div class="form-group" style="width:600px;height: 400px;margin: auto;"
		align="center">
		<form method="post" action="updateBar">
			<br> <input type="hidden" name="barId" value="${request.barId }" />
			<input type="hidden" name="goodsId"
				value="${request.project.goodsId }" /> 条码：<input
				class="form-control" name="barNo" value="${request.project.barNo }"
				readonly="readonly" onclick="javascript:alert('条码不可修改！');"
				type="text" /> <br> 货号：<input class="form-control"
				name="goodsNo" placeholder="请输入货号"
				value="${request.project.goodsNo }" readonly="readonly"
				onclick="javascript:alert('货号不可修改！');" type="text" /> <br>
			小组 ：<input class="form-control" name="Owner" placeholder="请输入小组"
				value="${request.owner}" type="text" /> <br> 简称：<input
				class="form-control" name="barSimplename" placeholder="请输入简称"
				value="${request.project.barSimplename }" type="text" /> <br>
			成本：<input class="form-control" name="goodsPrice" placeholder="请输入成本"
				value="${request.project.goodsPrice }" type="text" /> <br>
			吊牌价：<input class="form-control" name="barShowprice"
				placeholder="请输入吊牌价" value="${request.project.barShowprice }"
				type="text" /> <br> 售价：<input class="form-control"
				name="barSaleprice" placeholder="请输入售价"
				value="${request.project.barSaleprice }" type="text" /> <br>
			颜色：<input class="form-control" name="barColor" placeholder="请输入颜色"
				value="${request.project.barColor }" type="text" /> <br> 材质：<input
				class="form-control" name="barMaterial" placeholder="请输入材质"
				value="${request.project.barMaterial }" type="text" /> <br>
			规格：<input class="form-control" name="barSpecifications"
				placeholder="请输入规格" value="${request.project.barSpecifications }"
				type="text" /> <br> 执行标准：<input class="form-control"
				name="barStandard" placeholder="请输入执行标准"
				value="${request.project.barStandard }" type="text" /> <br>
				
			备注：<input class="form-control" name="remarks"
				placeholder="请输入备注" value="${request.project.remarks }"
				type="text" /> <br>
			是否易损：<input class="form-control" name="rapidWear"
				placeholder="请输入是否易损" value="${request.project.rapidWear }"
				type="text" /> <br>
			说明书:<input class="form-control" name="instructions"
				placeholder="请输入说明书" value="${request.project.instructions }"
				type="text" /> <br>
			包装货号：<input class="form-control" name="packNo"
				placeholder="请输入包装货号" value="${request.project.packNo }"
				type="text" /> <br>
			包装情况:<input class="form-control" name="packCondition"
				placeholder="请输入包装情况" value="${request.project.packCondition }"
				type="text" /> <br>
			包装尺寸：<input class="form-control" name="packSize"
				placeholder="请输入包装尺寸" value="${request.project.packSize }"
				type="text" /> <br>
			是否大小区分:
			<input class="form-control" name="isSize"
				placeholder="请输入是否大小区分" value="${request.project.isSize }"
				type="text" /> <br>
			重量：<input class="form-control" name="scale"
				placeholder="请输入重量" value="${request.project.scale }"
				type="text" /> <br>
			长:<input class="form-control" name="longs"
				placeholder="请输入长" value="${request.project.longs }"
				type="text" /> <br>
			宽：<input class="form-control" name="widths"
				placeholder="请输入宽" value="${request.project.widths }"
				type="text" /> <br>
			高：<input class="form-control" name="heights"
				placeholder="请输入高" value="${request.project.heights }"
				type="text" /> <br>
			标记：<input class="form-control" name="tabs"
				placeholder="请输入标记" value="${request.project.tabs }"
				type="text" /> <br>
			保质期：<input class="form-control" name="terms"
				placeholder="请输入保质期" value="${request.project.terms }"
				type="text" /> <br>
			别名：<input class="form-control" name="alias"
				placeholder="请输入别名" value="${request.project.alias }"
				type="text" /> <br>
				
				
			<button class="btn btn-primary btn-md" id="btn-todo" type="submit">提&nbsp;交</button>
			&nbsp; &nbsp;
			<button class="btn btn-primary btn-md" id="btn-todo" type="reset">重&nbsp;置</button>
		</form>
	</div>

</div>
<!--/.main-->

<jsp:include page="foot.jsp"></jsp:include>