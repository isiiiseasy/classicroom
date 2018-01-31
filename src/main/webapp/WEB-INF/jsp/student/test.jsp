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
      <h3>問1.正しい表記はどれ？</h3>
      <input type="radio" name="que-1" value="no1">1.Jaav
      <input type="radio" name="que-1" value="no2">2.Java
      <input type="radio" name="que-1" value="no3">3.Baka
      <input type="radio" name="que-1" value="no4">4.jaxa

      <h3>問2.整数型はどれ？</h3>
      <input type="radio" name="que-2" value="no1">1.String
      <input type="radio" name="que-2" value="no2">2.float
      <input type="radio" name="que-2" value="no3">3.boolean
      <input type="radio" name="que-2" value="no4">4.int

      <h3>問3.コンストラクタを説明した文章として最も適したものを選びなさい。</h3>
      1.処理内容の無いメソッドだけを定義し、実際の処理はインタフェースの実装したクラスで実装する。<br>
      2.スーパークラスにおいて定義されているインスタンスメソッドを、 サブクラス内で再定義すること。<br>
      3.クラスからインスタンスを生成する際に呼び出される部分。インスタンス生成時には必ず呼び出される。<br>
      4.実行中のプログラムのメモリが不足しないように自動的に管理する仕組み。<br>
      <input type="radio" name="que-3" value="no1">1
      <input type="radio" name="que-3" value="no2">2
      <input type="radio" name="que-3" value="no3">3
      <input type="radio" name="que-3" value="no4">4

      <h3>問4.あなたの今日の気分は？</h3>
      <input type="radio" name="que-4" value="no1">1.寿司
      <input type="radio" name="que-4" value="no2">2.カレー
      <input type="radio" name="que-4" value="no3">3.ラーメン
      <input type="radio" name="que-4" value="no4">4.ハンバーグ

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