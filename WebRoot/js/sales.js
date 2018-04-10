/**
 * 处理销售表的js
 */

//显示专场

function shwospceial(){
	$.ajax({
		cache: true,
		url:"sales",			
		method:"get",
		dataType:"json",
		success:function(result){
			var dataObj = result, //返回json
			con = "";
			$.each(dataObj, function(index, item){
				con = "<option value="+ item.name +">" + item.name+"</option>";
				$("#specialName").append(con);
			});
		}
	});
	
}

