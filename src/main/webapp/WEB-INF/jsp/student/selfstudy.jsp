<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.sql.*" %>

<!DOCTYPE html>
<html>
  <head>
  	<link href="<%=request.getContextPath() %>/css/main.css" rel="stylesheet" type="text/css" media="screen" />
    <title>自習ページ画面</title>
    <meta charset="utf-8">
  </head>
  <body>

  	<%@ include file="/WEB-INF/jsp/pageheader.jsp" %>

    <div id="L_box">
      <img src="">
      <div align="left">
	      <p id="tabcontrol">


	1.Java言語<br>
        <a href="#tabpage1">  Javaの仕組み</a><br>
        <a href="#tabpage2">  クラスとオブジェクト</a><br>
        <a href="#tabpage3">  とりあえずHello World!!</a><br>

    2.基本情報技術者試験<br>
        <a href="#tabpage4">  基本情報ってどんな試験？</a><br>
        <a href="#tabpage5">  午前対策:テクノロジ</a><br>
        <a href="#tabpage6">  午前対策:ストラテジ</a><br>
        <a href="#tabpage7">  午前対策:マネジメント</a><br>
        <a href="#tabpage8">  午後対策:セキュリティ</a><br>
        <a href="#tabpage9">  午後対策:ネットワーク</a><br>
        <a href="#tabpage10">  午後対策:データベース</a><br>

      </p>
      </div>
	</div>
    <div id="R_box">
    	<h1 id="midasi_1">自習ページ</h1>
    	<br><br>
    	ここは自習ページです。<br>
    	あらかたの知識や情報はこのページに書いてあります。<br>
    	予習や復習に使用してください。<br>
    	ここを見てわからないことがあったら、友達や先生に質問してください。絶対です！！！<br>
    	聞くは一時の恥聞かぬは一生の恥、ですよ。<br>
    	      <div id="tabbody">
         <div id="tabpage1"><h2>Javaの仕組み</h2>
         	Javaはオブジェクト指向の言語です。<br>JVMを介して実行されるのでOSの影響を受けません。</div>
         <div id="tabpage2"><h2>クラスとオブジェクト</h2></div>
         <div id="tabpage3"><h2>とりあえずHello World!!</h2></div>
         <div id="tabpage4"><h2>基本情報ってどんな試験？</h2></div>
         <div id="tabpage5"><h2>午前対策:テクノロジ</h2>
         	ここは午後の問題に活きる問題が多いので、丁寧に一問一問理解していこう！</div>
         <div id="tabpage6"><h2>午前対策:ストラテジ</h2>
         	苦しかったら選択肢でおぼえてもいいところ。</div>
         <div id="tabpage7"><h2>午前対策:マネジメント</h2>
         	ビジネスコースの人は後に活きる分野。</div>
         <div id="tabpage8"><h2>午後対策:セキュリティ</h2>
         	必須問題。今後のためにも完全に理解したい。</div>
         <div id="tabpage9"><h2>午後対策:ネットワーク</h2>
         	ポート番号、IPアドレス、プロキシサーバにDNS<br>必ずヒントがある。</div>
         <div id="tabpage10"><h2>午後対策:データベース</h2>
         	SQLの基礎を覚えれば、きっと点取り問題になる。</div>
      </div>
    </div>
    <script type="text/javascript">
      // ---------------------------
      // ▼A：対象要素を得る
      // ---------------------------
      var tabs = document.getElementById('tabcontrol').getElementsByTagName('a');
      var pages = document.getElementById('tabbody').getElementsByTagName('div');

      // ---------------------------
      // ▼B：タブの切り替え処理
      // ---------------------------
      function changeTab() {
         // ▼B-1. href属性値から対象のid名を抜き出す
         var targetid = this.href.substring(this.href.indexOf('#')+1,this.href.length);

         // ▼B-2. 指定のタブページだけを表示する
         for(var i=0; i<pages.length; i++) {
            if( pages[i].id != targetid ) {
               pages[i].style.display = "none";
            }
            else {
               pages[i].style.display = "block";
            }
         }

         // ▼B-3. クリックされたタブを前面に表示する
         for(var i=0; i<tabs.length; i++) {
            tabs[i].style.zIndex = "0";
         }
         this.style.zIndex = "10";

         // ▼B-4. ページ遷移しないようにfalseを返す
         return false;
      }

      // ---------------------------
      // ▼C：すべてのタブに対して、クリック時にchangeTab関数が実行されるよう指定する
      // ---------------------------
      for(var i=0; i<tabs.length; i++) {
         tabs[i].onclick = changeTab;
      }

      // ---------------------------
      // ▼D：最初は先頭のタブを選択しておく
      // ---------------------------
      tabs[0].onclick();

      </script>
  </body>
</html>