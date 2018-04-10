/**
 * 
 */
	$(document).ready(function() {
		showCount();
		showBwl();
		showGg();
	});

	function showBwl() {
		$.ajax({
					cache : true,
					url : "bwl",
					method : "post",
					dataType : "json",
					success : function(result) {
						var dataObj = result, //返回json
						con = "";
						$(".todo-list").empty();
						$
								.each(
										dataObj,
										function(index, item) {
											con1 = "<li  class='todo-list-item'><div  class='checkbox'><label for='checkbox'>"
													+ item.content
													+ "</div>"
													+ "<div class='pull-right action-buttons'>"
													+ "<span>"
													+ item.date
													+ "</span>&nbsp;"

													+ "<a href='#' onclick='deleteBwl("
													+ item.id
													+ ")' class='trash'><span class='glyphicon glyphicon-trash'></span></a>"
													+ "</div>" + "</li>";
											$(".todo-list").append(con1);
										});
					},
					error : function() {
						alert('未连接到服务器,请检查服务器或数据库配置！');
					}
				});
	}

	function deleteBwl(id) {
		if (confirm("确定删除此备忘录吗？")) {
			$.ajax({
				cache : false,
				url : "deleteBwl",
				method : "post",
				dataType : "json",
				data : {
					"bwid" : id
				},
				success : function(result) {
					if (result == '1') {
						showBwl();
					}
					if (result == '0') {
						alert("删除失败!");
					}
				},
				error : function() {
					alert('未连接到服务器,请检查服务器或数据库配置！');
				}
			});
		}
	}

	function addBwl() {
		 if($("#btn-input1").val()==""){
			   alert("备忘录内容不能为空！");
			   return false;
		   }else{
		$.ajax({
			cache : false,
			url : "addBwl",
			method : "get",
			dataType : "json",
			data : $('#bwlform').serialize(),
			success : function(result) {
				if (result == '1') {
					alert("添加备忘录成功!");
					$("#btn-input1").val("");
					showBwl();

				}
				if (result == '0') {
					alert("添加备忘录失败!");
				}
			},
			error : function() {
				alert('未连接到服务器,请检查服务器或数据库配置！');
			}
		});
		   }
	}

	function showCount() {
		$.ajax({
			cache : true,
			url : "showCount",
			method : "get",
			dataType : "json",
			success : function(result) {
				var dataObj = result, //返回json
				con = "";
				$.each(dataObj, function(index, item) {
					$("#barCount").text(item.barCount);
					$("#specialCount").text(item.specialCount);
					$("#stockCount").text(item.stockCount);
					$("#salesCount").text(parseFloat(item.salesCount)/1000);

				});
			},
			error : function() {
				alert('提示：你可能还没有添加 商品/专场/库存 哦！');
			}
		});
	}
	
	   function deleteGg() {
	        if (confirm("确定删除此公告吗？")) {
	            $.ajax({
	                cache : false,
	                url : "delGg",
	                method : "post",
	                dataType : "json",
	                data : $("#delGgForm").serialize(),
	                success : function(result) {
	                    if (result == '1') {
	                    	alert("删除成功!");
	                    	showGg();
	                    }
	                    if (result == '0') {
	                        alert("删除失败,你可能没有权限!");
	                    }
	                },
	                error : function() {
	                    alert('未连接到服务器,请检查服务器或数据库配置！');
	                }
	            });
	        }
	    }
	   
	function showGg() {
        $.ajax({
            cache : false,
            url : "showGg",
            method : "get",
            dataType : "json",
            success : function(result) {
                var dataObj = result; //返回json
                var con = "";
                $("#gg_ul").empty();
                if(dataObj==""||dataObj==null)
	            {
                	 con = "<li class='left clearfix'><span class='chat-img pull-left'><span class=' glyphicon glyphicon-tags glyphicon-l'></span></span><div class='chat-body clearfix'><p>管理员未发布公告！</p></div></li>";
                	 $("#gg_ul").append(con);
	            }
              
                $.each(dataObj, function(index, item) {
                    con = "<li class='left clearfix'>"  +
                    "<span class='chat-img pull-left'>" + 
                        "<span class='glyphicon glyphicon-tags glyphicon-l'></span></span><div class='chat-body clearfix'><div class='header'><strong class='primary-font'>超级管理员:"
                     +item.ggusername +"</strong> <small class='text-muted'>" 
                     + item.ggdate +
                     "</small></div><p>"  +
                        item.ggcontent +                                  
                        "</p><span style='float:right;margin-right:-15px;'>" +
                        "<form action='' id='delGgForm'><input type='hidden' name='ggid' value='" +
                        item.ggid+
                        "' /><input type='hidden' name='ggusername' value='" +
                        item.ggusername +
                        "' />" + 
                        "<input type='button' onclick='deleteGg()' class='btn btn-success btn-md' id='btn-chat' value='&nbsp;删&nbsp;&nbsp;除&nbsp;' /></form></span></div></li>";
                        $("#gg_ul").append(con);
                });
            },
            error : function() {
            }
        });
    }
	   function addGg() {
		   if($("#btn-input").val()==""){
			   alert("公告内容不能为空！");
			   return false;
		   }else{
	        $.ajax({
	            cache : false,
	            url : "addGg",
	            method : "post",
	            dataType : "json",
	            data : $('#fbgg').serialize(),
	            success : function(result) {
	                if (result == '1') {
	                    alert("发布公告成功!");
	                    showGg();
	                }
	                if (result == '0') {
	                    alert("发布公告失败!");
	                }
	            },
	            error : function() {
	                alert('未连接到服务器,请检查服务器或数据库配置！');
	            }
	        });
	    }
	   }
