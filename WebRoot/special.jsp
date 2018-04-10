<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<jsp:include page="head.jsp"></jsp:include>

<script type="text/javascript">
  $(document).ready(function () {
    $("#jumpPage").keydown(function(e) {
      var curKey = e.which;
      if (curKey == 13) {
    	 
              if(parseInt($('#jumpPage').val()) <= parseInt($('#MaxCount').text()) ){
            	  window.location.href = '<%=path%>/special?nowPage=' +  $('#jumpPage').val();
              }else{
            	  alert('总共只有' + $('#MaxCount').text() + '页哦!');
              }
      }
    });
    
    if(parseInt($('#jumpPage').val()) == parseInt($('#MaxCount').text())){
    	$("#nextPage").hide();         //  如果当前页码==总页码  隐藏下一页按钮
    }
    
});
</script>

<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
	<div class="row">
		<ol class="breadcrumb">
			<li><a href="#"><span class="glyphicon glyphicon-home"></span></a></li>
			<li class="active">档期资料</li>
		</ol>
	</div>
	<!--/.row-->

	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">专场资料</h1>
			 <span id="msg1" style="color:red;float:left;">*注意：只有在这里增加了专场名称，查询销售资料的下拉列表里面才会显示专场名！</span>
		</div>
	</div>
	<!--/.row-->
	${requestScope.finderror }
	<table>
		<form action="findSpecial" method="get">

			<tr>
				<td><a href="addspecial.jsp" class="btn btn-primary btn-md"
					id="btn-todo"> 新&nbsp;增&nbsp;专&nbsp;场</a></td>

				<td align="right"><input style="width: 300px;" type="text"
					id="specialName" name="specialName" class="form-control"
					placeholder="请输入简写的专场名称" required="required"></td>

				<td align="right" width="100">
					<button class="btn btn-primary btn-md" id="btn-todo" type="submit">搜&nbsp;索&nbsp;专&nbsp;场</button>
				</td>
			</tr>

		</form>
	</table>
	
	<table class="zebra">

		<!-- 表头表尾 -->
		<thead>
			<tr>

				<th><p>专场编号</p></th>
			
				<th><p>专&nbsp;场&nbsp;名</p></th>
				<th><p align=center>
						<b>操&nbsp;&nbsp;作</b>
					</p></th>
			</tr>
		</thead>
		<tfoot>
			<tr id="Page">
				<td colspan="9" align="center">第 <input align="center"
					id="jumpPage" type="text" size="3" value="${nowPage }"
					onkeyup="if(!/^\d+$/.test(this.value)) {alert('只能输入数字 !'); this.value=this.value.replace(/[^\d]+/g,'');}">
					页 <a href="<%=path%>/special?nowPage=${nowPage-1}"><button>上一页</button></a>
					<a href="<%=path%>/special?nowPage=${nowPage+1}"><button
							id="nextPage">下一页</button></a> &nbsp; 共<span id="MaxCount">${MaxCount }</span>页
				</td>
			</tr>
		</tfoot>

		<!-- 行列 -->
		<s:iterator value="#request.special" id="special">
			<tr>
				<td><s:property value="#special.specialId" /></td>
				
				<td><s:property value="#special.specialName" /></td>
				<td align="center"><a
					href="editspecial?id=<s:property value="#special.specialId" />"
					class="btn btn-primary btn-md" id="btn-todo">编&nbsp;辑</a>&nbsp;&nbsp;
					<a
					onclick="javascript:if(confirm('你确定删除此专场吗？')){window.location.href='deleteSpecial?id=<s:property value="#special.specialId"/>'}"
					class="btn btn-primary btn-md" id="btn-todo">删&nbsp;除</a></td>
			</tr>
		</s:iterator>
	</table>
	<!-- 结束 -->
</div>
<!--/.main-->

<jsp:include page="foot.jsp"></jsp:include>