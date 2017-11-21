<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザ登録</title>
</head>
<body>
	<h1>ユーザ登録</h1>
	<form method="post" action="useradd">
		<label>ユーザーID:<input type="text" id="userID" name="userID" /></label><br>
		<label>パスワード:<input type="text" id="password" name="password" /></label><br>
		<label>氏名:<input type="text" id="userName" name="userName" /></label><br>
		<label>教師フラグ:<input type="radio" id="teacher_flg" name="teacherF" value="false" />生徒</label>
						  <input type="radio" id="teacher_flg" name="teacherF" value="true"/>教師<br>
		<input type="submit" id="submit" value="登録" />${ message }
	</form>
</body>
</html>