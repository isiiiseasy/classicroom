<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>ログインページ</title>
<meta charset="UTF-8">
</head>
<body>
	<h1>ログイン</h1>
	<p>id:student,pass:student または id:teacher,pass:teacher で入れます。</p>
	<form method="post" action="login">
		<label>ユーザーID:<input type="text" id="userID" name="userID" /></label><br>
		<label>パスワード:<input type="password" id="password"
			name="password" /></label><br> <input type="submit" id="submit"
			value="ログイン" />${ warning }
	</form>
</body>
</html>
