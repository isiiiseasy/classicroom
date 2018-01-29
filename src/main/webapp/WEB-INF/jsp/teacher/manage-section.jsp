<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!DOCTYPE html>
<html>
<head>
<link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css" media="screen" />
<title>学習項目管理画面</title>
<meta charset="utf-8">
<script src="<%=request.getContextPath()%>/js/manage-section_t.js" charset="utf-8"></script>
</head>
<body>

	<%@ include file="/WEB-INF/jsp/pageheader.jsp" %>

	<div id="L_box">
	</div>

	<div id="R_box">
		<h1 id="midasi_1">学習項目管理画面</h1>

		<h2>学習項目</h2>
		<ul id="section-list"></ul>
	</div>
</body>
</html>