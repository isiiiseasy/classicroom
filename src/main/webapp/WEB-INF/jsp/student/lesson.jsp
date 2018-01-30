<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.sql.*" %>

<!DOCTYPE html>
<html>
  <head>
  	<link href="<%=request.getContextPath() %>/css/main.css" rel="stylesheet" type="text/css" media="screen" />
    <title>授業ページ画面</title>
    <meta charset="utf-8">
  </head>
  <body>

  	<%@ include file="/WEB-INF/jsp/pageheader.jsp" %>

    <div id="L_box">
    <div align="left">
      <ol>
        <li>Java言語
        <ul>
          <li>Javaの仕組み
          <li>クラスとオブジェクト
          <li>とりあえずHello World!!
        </ul>
        <li>基本情報技術者試験
        <ul>
          <li>基本情報ってどんな試験？
          <li>午前対策:テクノロジ
          <li>午前対策:ストラテジ
          <li>午前対策:マネジメント
          <li>午後対策:セキュリティ
          <li>午後対策:ネットワーク
          <li>午後対策:データベース
        </ul>
        </div>
    </div>
    <div id="R_box">
      こちらは授業ページです。<br>
      新しい範囲の学習をする際に使用します。<br>
      また、普段でも教科書の代わりとして使用できますので、<br>
      自習でわからないことがあればこのページで調べてみてください。<br>
    </div>
  </body>
</html>