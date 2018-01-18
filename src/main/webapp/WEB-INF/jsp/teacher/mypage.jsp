<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.sql.*" %>

<html>
  <head>
  	<link href="<%=request.getContextPath() %>/css/main.css" rel="stylesheet" type="text/css" media="screen" />
    <title>教師ホーム画面</title>
    <meta charset="utf-8">
  </head>
  <body>
    <img src="">
      <div align="right">
     	<a href="<%=request.getContextPath() %>/mypage" style="color: black;">マイページ</a>
        <a href="<%=request.getContextPath() %>/logout" style="color: black;">ログアウト</a>
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
      <br>
      <a class="normal_link" href="<%=request.getContextPath() %>/image">
      	<img src="img/${ userId }_img.jpg" alt="No Image" height="250px" width="250px">
      </a><br>
      <a class="normal_link">name : ${ userName }</a><br><br>
      設定<br>
      <a class="normal_link" href="<%=request.getContextPath() %>/register">ユーザ登録</a><br>
      <a class="normal_link" href="<%=request.getContextPath() %>/image">アイコン画像変更</a>
    </div>
    <div id="R_box">
    	<h1 id="midasi_1">ようこそ、${ userName } 先生</h1>
    </div>
  </body>
</html>