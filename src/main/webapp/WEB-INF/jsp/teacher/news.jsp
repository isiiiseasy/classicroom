<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.io.*" %>

<!DOCTYPE html>
<html>
    <head>
        <title>お知らせ登録@iTeach</title>
        <link href="<%= request.getContextPath() %>/css/main.css" rel="stylesheet" type="text/css" media="screen" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    </head>
    <body>
	    <h1 id="chat_main">お知らせを入力してください。</h1>
		<form name="news" method="post" action="checknews">
			<textarea name="newstext" rows="10" cols="40"></textarea>
			<input type="submit" name="bt1" value="Check!">

		</form>
    </body>
</html>