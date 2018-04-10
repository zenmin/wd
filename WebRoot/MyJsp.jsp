<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyJsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
 <s:iterator value="#request.special" id="special">
            <tr>
                <td><s:property value="#special.specialId" /></td>
                <td><s:property value="#special.special" /></td>
                <td><s:property value="#special.specialName" /></td>
                <td align="center">
                <a href="editspecial?id=<s:property value="#special.specialId" />" class="btn btn-primary btn-md"
                    id="btn-todo">编&nbsp;辑</a>&nbsp;&nbsp; <a
                href="www"  class="btn btn-primary btn-md" id="btn-todo">删&nbsp;除</a></td>
            </tr>
        </s:iterator>
              
  </body>
</html>
