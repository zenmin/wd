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
                <li class="active">帮助中心</li>
            </ol>
        </div><!--/.row-->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">帮助中心</h1>
            </div>
        </div><!--/.row-->
<div class="form-group" style="width:600px;height: 400px;margin: auto;" align="center">
<p align="left">
	1、销售资料导出可能发生的错误：<br>
	错误提示：没有找到该记录，请查看填写的条件是否有误。 即将导出的销售记录中的商品信息不存在!<br>
	解决办法: 销售记录中的商品信息是否存在，也就是销售资料的条码 在产品资料不存在！请先添加这个产品再导出销售资料！<br><br>
	2、更新资料可能发生的错误：<br>
		错误提示：更新失败!
	<br>
	解决办法:①:请确定你上传的是客退流水Excel表格。	<br><span style="text-indent:50px;">②:填写数字的表格不能填汉字,比如叫你填写价格的单元格 只能填写数字！请修改Excel表格</span>
    <br><br>
3、货号与条码更新可能发生的错误：<br>
错误提示：提示：出错了！在读取Excel时,类型转换错误。可能原因:1.请确认你上传的是新建货号表格！2.价格数量等必须为数字的单元格内必须填写数字！
<br>
解决办法：请检查excel表格的所有单元格，填写成本价、售价、吊牌价、重量、长、宽、高等单元格只能为数字，不能有汉字出现！
</p>
    陆续更新可能发生的错误！如果你在使用过程中遇到错误请<a
				href="http://wpa.qq.com/msgrd?v=3&uin=741703967&site=qq&menu=yes" target="_blank">请点击此处</a>给我反馈！
	</div>

    </div><!--/.main-->

  <jsp:include page="foot.jsp"></jsp:include>