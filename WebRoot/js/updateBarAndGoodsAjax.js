$(function(){
	$("#sub").click(function(){
//		var excel = new FormData().append("excel",$("#file")[0]);
		console.debug($("#excel").val());
		$.ajax({
			url:"updateBarAndGoods",//����Url
			enctype:"multipart/form-data",
			method:"post",//����ʽ
			cache:false,//������
			contentType: false,//����false�Ż��Զ�������ȷ��Content-Type  
			processData: false,//����false�Ż�ܿ�jQuery�� formdata ��Ĭ�ϴ���
			data:{
				excel:new FormData($("#excel")[0])
			}
		});
	});
});