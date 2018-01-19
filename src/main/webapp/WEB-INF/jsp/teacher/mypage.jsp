<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.sql.*" %>

<html>
  <head>
  	<link href="<%=request.getContextPath() %>/css/main.css" rel="stylesheet" type="text/css" media="screen" />
    <title>教師ホーム画面</title>
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
      <a class="normal_link" href="<%=request.getContextPath() %>/register">ユーザ登録</a><br>
      <a class="normal_link" href="<%=request.getContextPath() %>/image">アイコン画像変更</a>
    </div>
    <div id="R_box">
    	<h1 id="midasi_1">ようこそ、${ userName } 先生</h1>
    </div>
  </body>
</html>