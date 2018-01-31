<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!DOCTYPE html>
<html>
<head>
<link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css" media="screen" />
<title>新規授業登録画面</title>
<meta charset="utf-8">

</head>
<body>

	<%@ include file="/WEB-INF/jsp/pageheader.jsp" %>

	<div id="L_box">
	</div>

	<div id="R_box">
		<h1 id="midasi_1">新規授業登録画面</h1>

		<%
  			Object obj = request.getAttribute("subject");
  			out.println( obj );
		%>

		<form method="POST" enctype="multipart/form-data" action="fileupload">
  			<p><input type="file" name="file"><input type="submit" value="設定"></p>
		</form>


	</div>
</body>
</html>