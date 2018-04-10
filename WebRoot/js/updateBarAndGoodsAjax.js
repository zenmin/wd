$(function(){
	$("#sub").click(function(){
//		var excel = new FormData().append("excel",$("#file")[0]);
		console.debug($("#excel").val());
		$.ajax({
			url:"updateBarAndGoods",//请求Url
			enctype:"multipart/form-data",
			method:"post",//请求方式
			cache:false,//不缓存
			contentType: false,//必须false才会自动加上正确的Content-Type  
			processData: false,//必须false才会避开jQuery对 formdata 的默认处理
			data:{
				excel:new FormData($("#excel")[0])
			}
		});
	});
});