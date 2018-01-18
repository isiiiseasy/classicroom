<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.sql.*" %>

<!DOCTYPE html>

<html>
<head>
  <title>結果</title>
  <meta charset="utf-8">
</head>
<body>
  <h1>結果は・・・</h1>

<%
  String strAns = request.getParameter("score");
  out.println( strAns + "点でした");
%>

でした
</body>
</html>