<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.sql.*" %>

<!DOCTYPE html>
<html>
  <head>
  	<link href="<%=request.getContextPath() %>/css/main.css" rel="stylesheet" type="text/css" media="screen" />
    <title>練習問題ページ画面</title>
    <meta charset="utf-8">
  </head>
  <body>
    <img src="">
      <div align="right">
      	<a href="<%=request.getContextPath() %>/mypage">マイページ</a>
        <a href="<%=request.getContextPath() %>/logout">ログアウト</a>
      </div>
    <table class="header">
      <tr><td class="header_font"><a class="header_link" href="<%=request.getContextPath() %>/jugyou">授業</a></td>
        <td class="header_font"><a class="header_link" href="<%=request.getContextPath() %>/zisyu">自習</a></td>
        <td class="header_font"><a class="header_link" href="<%=request.getContextPath() %>/rensyumondai">練習問題</a></td>
        <td class="header_font"><a class="header_link" href="<%=request.getContextPath() %>/tesuto">テスト</a></td></tr>
    </table>
    <div id="L_box">
      <img src="">


    </div>
    <div id="R_box">
    	<h1 id="midasi_1">練習問題ページ</h1>
    </div>
  </body>
</html>