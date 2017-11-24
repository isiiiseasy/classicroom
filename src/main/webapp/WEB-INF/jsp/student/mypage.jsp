<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.sql.*" %>

<!DOCTYPE html>
<html>
  <head>
  	<link href="<%=request.getContextPath() %>/css/main.css" rel="stylesheet" type="text/css" media="screen" />
    <title>生徒ホーム画面</title>
    <meta charset="utf-8">
  </head>
  <body>
    <img src="">
      <div align="right">
        <a href="<%=request.getContextPath() %>/logout">ログアウト</a>
      </div>
    <table class="header">
      <tr><td class="header_font">授業</td>
        <td class="header_font">自習</td>
        <td class="header_font">練習問題</td>
        <td class="header_font">テスト</td></tr>
    </table>
    <div id="L_box">
      <img src="">

      設定
    </div>
    <div id="R_box">
    	<h1 id="midasi_1">ようこそ、${ userName } さん</h1>
    </div>
  </body>
</html>
