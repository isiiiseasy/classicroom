<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.sql.*" %>

<!DOCTYPE html>
<html>
  <head>
  	<link href="<%=request.getContextPath() %>/css/main.css" rel="stylesheet" type="text/css" media="screen" />
    <title>授業ページ画面</title>
    <meta charset="utf-8">
  </head>
  <body>

  	<%@ include file="/WEB-INF/jsp/pageheader.jsp" %>

	<%
  Object strAns = request.getAttribute("point");
  out.println( strAns );
%>

  </body>
</html>