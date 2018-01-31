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
		教科一覧<br>
		${ subject}

		<form method="POST" action="lessonupload">
			教科選択：<input type="number" min="1" max="255" name="subjectId" required><br>
			授業名入力：<input type="text" name="lessonName">
			<input type="submit" value="登録">
		</form>
		${ message }
		<br>

		<h2>授業ファイルアップロード</h2>
		授業一覧<br>
		${ lessonname}
		<br>
		<form method="POST" enctype="multipart/form-data" action="fileupload">
			<input type="hidden" name="flg" value="1">
			授業選択：<input type="number" min="1" max="255" name="lessonId" required>
  			<p><input type="file" name="file"><input type="submit" value="アップロード"></p>
		</form>


	</div>
</body>
</html>