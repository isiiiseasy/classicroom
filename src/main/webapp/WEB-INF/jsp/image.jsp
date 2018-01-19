<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
  <head>
  	<link href="<%=request.getContextPath() %>/css/main.css" rel="stylesheet" type="text/css" media="screen" />
    <title>アイコン設定ページ</title>
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
    </div>

    <div id="R_box">
    	<h1>アイコン画像変更</h1>
	    <form method="POST" enctype="multipart/form-data" action="fileupload">
  		<p><input type="file" name="file"><input type="submit" value="設定"></p>
		</form>
    </div>
  </body>
</html>