<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
  <head>
  	<link href="<%=request.getContextPath() %>/css/main.css" rel="stylesheet" type="text/css" media="screen" />
    <title>ユーザ登録画面</title>
    <meta charset="utf-8">
  </head>
  <body>
    <img src="" alt="ロゴ">
      <div align="right">
     	<a href="<%=request.getContextPath() %>/mypage">マイページ</a>
        <a href="<%=request.getContextPath() %>/logout">ログアウト</a>
      </div>
    <table class="header">
      <tr>
        <td class="header_font" ><a class="header_link" href="">授業</a></td>
        <td class="header_font"><a class="header_link" href="">自習</a></td>
        <td class="header_font"><a class="header_link" href="">練習問題</a></td>
        <td class="header_font"><a class="header_link" href="">テスト</a></td>
      </tr>
    </table>
    
    <div id="L_box">
      <br><img src="" alt="アイコン画像" width="250px"><br>
      設定<br>
      <a href="<%=request.getContextPath() %>/register" style="color: white;text-decoration: none;">ユーザ登録</a>
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
