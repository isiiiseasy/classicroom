<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<html>
  <head>
  	<link href="<%=request.getContextPath() %>/css/main.css" rel="stylesheet" type="text/css" media="screen" />
    <title>ユーザ登録画面</title>
    <meta charset="utf-8">
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
      <a class="normal_link" href="<%=request.getContextPath() %>/register">ユーザ登録</a><br>
      <a class="normal_link" href="<%=request.getContextPath() %>/image">アイコン画像変更</a><br>
      <a class="normal_link" href="<%=request.getContextPath() %>/attendances">出欠状況確認</a><br>
      <a class="normal_link" href="<%=request.getContextPath() %>/manage-section">教科・学習項目管理</a><br>
    </div>

    <div id="R_box">
    	<h1>ユーザ登録</h1>
	    <form method="post" action="register">
	      <table>
	        <tr>
	          <td>ユーザーID</td>
	          <td><input type="text" id="userId" name="userId"></td>
	        </tr>
	        <tr>
	          <td>パスワード</td>
	          <td><input type="text" id="password" name="password"></td>
	        </tr>
	        <tr>
	          <td>氏名</td>
	          <td><input type="text" id="userName" name="userName"></td>
	        </tr>
	        <tr>
	          <td>教師フラグ</td>
	          <td>
	            <input type="radio" id="teacherFlg" name="teacherFlg" value="false">生徒
	            <input type="radio" id="teacherFlg" name="teacherFlg" value="false">教師
	          </td>
	        </tr>
	        <tr>
	          <td></td>
	          <td>　　　　　　　　<input type="submit" id="submit" value="登録" /></td>
	        </tr>
	      </table>
	      <p style="color:red;">${ message }</p>
	    </form>
    </div>
  </body>
</html>
