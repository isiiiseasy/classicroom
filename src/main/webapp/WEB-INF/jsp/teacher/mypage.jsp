<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>マイページ</title>
<meta charset="UTF-8">
</head>
<body>
	<h1>ようこそ、${ userName } 先生</h1>
	<p>お疲れ様です！</p>
	<a href="<%=request.getContextPath() %>/logout">ログアウト</a>
	<a href="<%=request.getContextPath() %>/useradd">ユーザ登録</a>
</body>
</html>
