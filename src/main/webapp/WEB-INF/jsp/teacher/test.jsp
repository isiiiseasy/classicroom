<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.sql.*"%>

<!DOCTYPE html>
<html>
<head>
<link href="<%=request.getContextPath()%>/css/main.css"
	rel="stylesheet" type="text/css" media="screen" />
<title>授業ページ画面</title>
<meta charset="utf-8">
<script src="<%=request.getContextPath()%>/js/test_t.js"
	charset="utf-8"></script>
</head>
<body>

	<%@ include file="/WEB-INF/jsp/pageheader.jsp"%>

	<div id="L_box">
		<h2>テスト一覧</h2>
		<ul id="test-list"></ul>
	</div>

	<div id="R_box">
		<h1 id="midasi_1">テストページ</h1>

		<h2 id="test-title">問題</h2>
		<ol id="question-list"></ol>
		<button id="add-practice">増やす</button>
		<button id="delete-practice">減らす</button>
		<button id="submit-practice">更新</button>
	</div>
</body>
</html>