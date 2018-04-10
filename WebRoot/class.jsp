<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<jsp:include page="head.jsp"></jsp:include>

<link rel="stylesheet" type="text/css" media="screen" href="css-table.css" />
<script type="text/javascript" src="js/jquery-1.2.6.min.js"></script>
<script type="text/javascript" src="js/style-table.js"></script>
<script type="text/javascript" >
$(function(){
	$("#btn-submit").click(function(){
		$("#currentPage").text("1");
		$("#classCurrentPage").val("1");
		var classNameValue = document.getElementById("className").value;
		//判断是否有输入条件
		if(classNameValue == ""){
			$("#previousPage").attr("href","class");
			$("#nextPage").attr("href","class");
			$("#fanPage").attr("href","class");
		}else{
			
			$("#previousPage").attr("href","showClassByName");
			$("#nextPage").attr("href","showClassByName");
			$("#fanPage").attr("href","showClassByName");
		}
		return false;
	});
});
	function jumpPage(value){
		currentPage = parseInt($("#currentPage").text());//当前页数
		pageSize = parseInt($("#pageSize").text());//总共页数
		arrivePage = $("#classCurrentPage").val();//目标页数
		var href = $(value).attr('href');
		var isNum = /^[0-9]+$/g;
		
// 		判断是上一页还是下一页
		if(value.text == "上一页"){
			//判断是否第一页		
			if(currentPage <= 1){
				alert("这已经是第一页了，没有上一页了！");
				return false;
			}else{
			currentPage--;//页数减一
			$("#previousPage").attr("href",href+"?classCurrentPage="+currentPage);
			}
		}else if(value.text == "翻页"){
			if(arrivePage >= 1 && arrivePage <= pageSize && isNum.test(arrivePage)){
				$("#fanPage").attr("href",href+"?classCurrentPage="+arrivePage);
				return true;
			}else{
				alert("请输入正确的页数");
				$("#classCurrentPage").val(currentPage);
				return false;
			}
		}else{
// 			判断是否是最后一页
			if(currentPage >= pageSize){
				alert("这已经是最后一页了，没有下一页了！");
				return false;
			}else{
			currentPage++;//页数加一
			$("#nextPage").attr("href",href+"?classCurrentPage="+currentPage);
			}
		}
	}
	
	function delGoods(id){
		if (confirm("确定删除此类目吗？")){
            $.ajax({
                cache : false,
                url : "deleteClass",
                method : "post",
                dataType : "json",
                data : {
                    "id" : id
                },
                success : function(result) {
                    if (result == '1') {
                    	 alert("删除成功!");
                        window.location.href="class?classCurrentPage=1";
                    }
                },
                error : function() {
                	  alert("删除失败,可能是该分类下有对应的商品,请先删除改商品才能删除该分类！");
                }
            });
        }
	}
</script>


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
                
    <table>
        <form action="showClassByName" method="post">

            <tr>
                <td><a href="addClass.jsp" class="btn btn-primary btn-md"
                    id="btn-todo"> 新&nbsp;增&nbsp;类&nbsp;目</a></td>

                <td align="right">
               <input style="width:300px;" class="form-control"  id="className" name="className" size="16"  placeholder="类目名称" 
                    />
                    </td>

                <td align="right" width="100">
                <button
                        class="btn btn-primary btn-md" id="btn-todo" type="submit"
                        >搜&nbsp;索&nbsp;类&nbsp;目</button></td>
            </tr>

        </form>
    </table>
    <table class="zebra">
	    <thead>
	    <tr>
	        <th>类目编号</th>        
	        <th>类目名称</th>
	        <th>编辑</th>
	    </tr>
	    </thead>
	    
	    <s:iterator value="#request.classs" >
	    	<tr>
	    		<td><s:property value="goodsId"/></td>
	    		<td><s:property value="class_"/></td>
	    		<td>
	    			<a href="editClass?id=${goodsId}" class="btn btn-primary btn-md" id="btn-todo">编&nbsp;辑</a>
	    			<a class="btn btn-primary btn-md" onclick="delGoods(<s:property value="goodsId"/>)">删&nbsp;除</a>
	    		</td>
	    	</tr>
	    </s:iterator>
	    	    <tfoot id="pageTable">
			    	<tr>
			    		<td colspan="12" rowspan="1" align="center">
			    			<a href="class" id="previousPage"  onclick="jumpPage(this)"
			    				><button>上一页</button></a>
							当前第 <span id="currentPage">${request.page.currentPage }</span> 页
			    			<a id="nextPage" onclick="jumpPage(this)"
			    				href="class"><button>下一页</button></a>
			    			<br>总 <span id="pageSize">${request.page.pageSize }</span> 页
				    			翻页:<input type="text" id="classCurrentPage" name="classCurrentPage" value="${request.page.currentPage }" size="1"/>
				    			<a id="fanPage" onclick="jumpPage(this)"
			    				href="class"><button>翻页</button></a>
			    		</td>
			    	</tr>
			    </tfoot>

	</table>

    </div><!--/.main-->

  <jsp:include page="foot.jsp"></jsp:include>