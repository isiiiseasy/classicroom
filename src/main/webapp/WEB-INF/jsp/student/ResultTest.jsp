<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.sql.*" %>

<!DOCTYPE html>

<html>
<head>
  <title>結果</title>
  <meta charset="utf-8">
</head>
<body>
  <h1>お疲れ様でした！！！</h1>

<%--オブジェクトじゃないとエラーはくよ--%>
<%
  Object strAns = request.getAttribute("score");
  out.println( strAns + "点でした");
%>

<br>

結果は自動採点されます。
マイページへ戻ってください

<br>
<a href="<%=request.getContextPath() %>/mypage" style="color: black;">マイページに戻る</a>
</body>
</html>