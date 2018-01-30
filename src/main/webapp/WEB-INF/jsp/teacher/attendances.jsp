<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.io.*" %>

<!DOCTYPE html>
<html>
    <head>
        <title>出欠状況管理画面</title>
        <link href="<%= request.getContextPath() %>/css/main.css" rel="stylesheet" type="text/css" media="screen" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    </head>
    <body>

  	<%@ include file="/WEB-INF/jsp/pageheader.jsp" %>

    <div id="L_box">
      <br>
      <a class="normal_link" href="<%=request.getContextPath() %>/image">
      	<img src="img/${ userId }_img.jpg" alt="No Image" height="250px" width="250px">
      </a><br>
      <a class="normal_link">name : ${ userName }</a><br><br>
      設定<br>
      <a href="<%=request.getContextPath() %>/register" style="color: white;text-decoration: none;">ユーザ登録</a>
    </div>

    <div id="R_box">
    	<h1>出欠状況確認・変更</h1>

    	 <form method="POST" action="attendances">
    	 	<input type="hidden" name="flg" value="0">
			<input type="number" min="1" max="255" name="lessonNum" required>
			<input type="submit" value="出欠状況確認">
		 </form>

		${ attendancestable}<br>

		<h2>内容変更</h2>

		<form method="POST" action="attendances">
			<input type="hidden" name="flg" value="1">
			授業回数：第<input type="number" min="1" max="255" name="lessonNum" required>回<br>
			ユーザ名：<input type="text" name="userName" required><br>
			出欠区分(1.欠席 2.遅刻 3.早退 4.行削除)：<input type="number" min="1" max="4" name="attendance_situation" required><br>
			備考欄：<br>
			<textarea name="note" rows="4" cols="40"></textarea><br>
			<input type="submit" value="出欠状況変更">
		</form>
		<p style="color:red;">${ message }</p>
    </div>

    </body>
</html>