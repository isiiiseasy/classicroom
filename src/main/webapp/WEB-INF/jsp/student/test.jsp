<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>マイページ</title>
<meta charset="UTF-8">
</head>
<body>
	<h1>ようこそ、${ userName } さん</h1>
	<p>勉強！</p>
	<a href="<%=request.getContextPath() %>/logout">ログアウト</a>
</body>
</html>
