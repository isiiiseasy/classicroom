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

  	<div id="L_box">
      <br>
      <a class="normal_link" href="<%=request.getContextPath() %>/image">
      	<img src="img/${ userId }_img.jpg" alt="No Image" height="250px" width="250px">
      </a><br>
      <a class="normal_link">name : ${ userName }</a><br><br>
      設定<br>
      <a href="<%=request.getContextPath() %>/register" style="color: white;text-decoration: none;">ユーザ登録</a>
      <a class="normal_link" href="<%=request.getContextPath() %>/image">アイコン画像変更</a>
      <a class="normal_link" href="<%=request.getContextPath() %>/attendances">出欠状況確認</a>
      <a class="normal_link" href="<%=request.getContextPath() %>/manage-section">教科・学習項目管理</a>
    </div>

    <div id="R_box">

    </div>
  </body>
</html>