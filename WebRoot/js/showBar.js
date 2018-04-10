

function delBar(id){
	if (confirm("你确定删除此商品吗？")) {
		$.ajax({
			cache : false,
			url : "deleteBar",
			method : "post",
			dataType : "json",
			data : {
				"barNo" : id
			},
			success : function(result) {
				if (result == '1') {
					alert("删除商品成功!");

				}
				if (result == '0') {
					alert("删除商品失败!");
				}
			},
			error : function() {
				alert('未连接到服务器,请检查服务器或数据库配置！');
			}
		});
	}
}

	function showBar(){
		$(".msg").text("查询中,请稍等...");
		$.ajax({
			url : "showBarAction",//请求地址
			//请求参数
			data : {tiem:new Date(),
				contition:$("#contition").val(),
				contitionValue:$("#contitionValue").val(),
				ownerIndex:$("#owner").val(),
				classIndex:$("#classS").val(),
				arrivePage:$("#arrivePage").val()
			} ,
//			请求方式
			method:"post",
//			不缓存
			cache:false,
//			响应数据类型
			dataType:"json",
//			响应回调函数
			success:function(result){

				var batTable = $("#barTable");
				batTable.empty();
//				each()遍历方式
				$.each(result,function(index,item){
//					if(index != 0){
					var tr = $("<tr></tr>").append("<td>"+item.owner+"</td><td>"+item.barNo
							+"</td><td>"+item.goodsNo+"</td><td>"+item.barSimplename
							+"</td><td>"+item.alias+"</td>"
							+"<td>"+item.goodsPrice+"</td>" 
							+"<td>"+item.barShowprice+"</td>" 
							+"<td>"+item.barSaleprice+"</td>" 
							+"<td>"+item.barColor
							+"</td><td>"+item.barMaterial+"</td><td>"+item.barSpecifications
							+"</td><td>"+item._class		
							+"</td><td>"+item.barStandard+"</td>"		
							+"<td>"+item.remartks+"</td>"			
							+"<td>"+item.scale+"</td>"		
							+"<td>"+item.longs+"</td>"		
							+"<td>"+item.widths+"</td>"		
							+"<td>"+item.height+"</td>"		
							+"<td>"+item.rapidWear+"</td>"		
							+"<td>"+item.instructions+"</td>"		
							+"<td>"+item.packNo+"</td>"		
							+"<td>"+item.packCondition+"</td>"		
							+"<td>"+item.packSize+"</td>"		
							+"<td>"+item.isSize+"</td>"
							+"<td>"+item.tabs+"</td>"
							+"<td>"+item.terms+"</td>"
							);
					if(item.power == "1"){
						tr.append(""
								+"<td><a href='editBar?barNo="+item.barNo+"'" 
								+"class='btn btn-primary btn-md' id='btn-todo'>编&nbsp;辑</a>"
								+   "<a onclick='delBar(" + item.barNo + 
								")' class='btn btn-primary btn-md'>删&nbsp;除</a>" 
								+"</td>")
					}
					
					
					tr.appendTo(batTable);
				
					$("#pageSize").text(item.pageSize);
//					}else{
//					pageTable.append("<tr><td colspan='12 rowspan='1' align='center'>"
//					+"<input type='button' onclick='jumpPage(this.value)' id='previousPage' class='btn btn-primary btn-md' value='上一页'/>"
//					+"当前第 <span id='currentPage'>"+item.currentPage
//					+"</span> 页" 
//					+"<input type='button' onclick='jumpPage(this.value)' id='nextPage' class='btn btn-primary btn-md' value='下一页'/>"
//					+"<br>总 <span id='pageSize'>"
//					+item.pageSize+"</span> 页  　　 翻页:<input type='text' id='arrivePage' name='arrivePage' size='1'/>"
//					+"<input type='button' onclick='jumpPage()' id='jumpBtu' class='btn btn-primary btn-md' value='翻页'/></td></tr>"
//					);
//					}
				});
			},
			error:function(){
				$("#barTable").empty();
				alert('没有查询到该记录，请检查条件是否正确！');
				$("#barTable").append("<td id='msg' style='line-height: 5" +
						";color:blue;' align='center' " +
				"colspan='26'><span class='msg'>没有查询结果！</span></td>");


			}

		});
//		取消默认值
//		return false;
	}
function selectChange(selectIndex){
	var contitionValue = document.getElementById("contitionValue");
	var ownerLabel = document.getElementById("ownerLabel");
	var classLabel = document.getElementById("classLabel");
	
	
	switch(selectIndex){
	case 0:
		contitionValue.setAttribute("placeholder","无需填写条件");
		ownerLabel.style.display = "inline";
		contitionValue.style.display = "none";
		classLabel.style.display = "none";
		break;
	case 1:
		contitionValue.setAttribute("placeholder","请填写货号");
		contitionValue.style.display = "inline";
		ownerLabel.style.display = "none";
		classLabel.style.display = "none";
		break;
	case 2:
		contitionValue.setAttribute("placeholder","请填写条码");
		contitionValue.style.display = "inline";
		ownerLabel.style.display = "none";
		classLabel.style.display = "none";
		break;
	case 3:
		contitionValue.setAttribute("placeholder","请填写简称");
		contitionValue.style.display = "inline";
		ownerLabel.style.display = "none";
		classLabel.style.display = "none";
		break;
	case 4:
		contitionValue.style.display = "none";
		ownerLabel.style.display = "none";
		classLabel.style.display = "inline";
		break;
	}
}

