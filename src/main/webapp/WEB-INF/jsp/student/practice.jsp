<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.sql.*" %>

<!DOCTYPE html>
<html>
  <head>
  	<link href="<%=request.getContextPath() %>/css/main.css" rel="stylesheet" type="text/css" media="screen" />
    <title>練習問題ページ画面</title>
    <meta charset="utf-8">
	<script src="<%=request.getContextPath()%>/js/practice_s.js" charset="utf-8"></script>
  </head>
  <body>

   	<%@ include file="/WEB-INF/jsp/pageheader.jsp" %>

    <div id="L_box">
		<h2>学習項目</h2>
		<ul id="section-list"></ul>
    </div>
    <div id="R_box">
    	<h1 id="midasi_1">練習問題ページ</h1>
		<h2 id="section-title">練習問題</h2>
		<ol id="question-list"></ol>
		<button id="check-practice">答え合わせ</button>
    </div>
  </body>
</html>
