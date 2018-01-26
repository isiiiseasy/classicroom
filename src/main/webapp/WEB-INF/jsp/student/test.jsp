<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.sql.*" %>

<!DOCTYPE html>
<html>
  <head>
  	<link href="<%=request.getContextPath() %>/css/main.css" rel="stylesheet" type="text/css" media="screen" />
    <title>テストページ画面</title>
    <meta charset="utf-8">
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
  </head>
  <body>

  	<%@ include file="/WEB-INF/jsp/pageheader.jsp" %>

    <div id="L_box">
      <img src="">


    </div>
    <div id="R_box">

    <h1 id="midasi_1">テストページ</h1>

	<p>解答は一回しかできないので、見直しをしっかりおこないましょう！！！！</p>
    <%--<HR style="margin: 3em 0 ;">--%>

   	<div id="modal-content">
   	 <form method="post" action="result">
      <h3>問1.あああああああ</h3>
      <input type="radio" name="que-1" value="no1">1.aaa
      <input type="radio" name="que-1" value="no2">2.bbb
      <input type="radio" name="que-1" value="no3">3.ccc
      <input type="radio" name="que-1" value="no4">4.ddd

      <h3>問2.おおおおおおお</h3>
      <input type="radio" name="que-2" value="no1">1.aaa
      <input type="radio" name="que-2" value="no2">2.bbb
      <input type="radio" name="que-2" value="no3">3.ccc
      <input type="radio" name="que-2" value="no4">4.ddd

      <h3>問3.いやあああああ</h3>
      <input type="radio" name="que-3" value="no1">1.aaa
      <input type="radio" name="que-3" value="no2">2.bbb
      <input type="radio" name="que-3" value="no3">3.ccc
      <input type="radio" name="que-3" value="no4">4.ddd

      <h3>問4.ぬおおおおおお</h3>
      <input type="radio" name="que-4" value="no1">1.aaa
      <input type="radio" name="que-4" value="no2">2.bbb
      <input type="radio" name="que-4" value="no3">3.ccc
      <input type="radio" name="que-4" value="no4">4.ddd

    <br><br>

    <input type="submit" value="採点"><input type="reset" value="リセット">
    </form>
    <p><a id="modal-close" class="button-link">閉じる</a></p>
    </div>

    <%--<HR style="margin: 3em 0 ;">--%>

    <p><a id="modal-open" class="button-link">テスト開始</a></p>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
	<script src="<%=request.getContextPath() %>/js/modal.js"></script>

  </body>
</html>