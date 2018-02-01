<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<html>
  <head>
  	<link href="<%=request.getContextPath() %>/css/main.css" rel="stylesheet" type="text/css" media="screen" />
    <title>教師ホーム画面</title>
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
      <a class="normal_link" href="<%=request.getContextPath() %>/lessonupload">新規授業登録</a><br>
    </div>
    <div id="R_box">
    	<h1 id="midasi_1">ようこそ、${ userName } 先生</h1>
		<a href="<%=request.getContextPath() %>/news" style="color: black;">お知らせを登録</a>
    </div>
    	<script type="text/javascript">
	    function window_open(){
			window.open("chat.jsp","window_name","width=640,height=480,scrollbars=yes");
			document.fdata.target = "window_name";
			document.fdata.method = "post";
			document.fdata.action = "example.shtml";
			document.fdata.submit();
		}
	</script>

	<div class="box22">
	<h2>お知らせですよ！！</h2>
		<%
			File newfile = new File("newnews.txt");

			try{
			    newfile.createNewFile();
			}catch(IOException e){
			    out.println(e);
			}

			BufferedReader bufFileData = new BufferedReader(new InputStreamReader(new FileInputStream("newnews.txt"),"UTF-8"));
	  		while(bufFileData.ready()){
	    	out.println(bufFileData.readLine() + "<BR>");
	  		}
	  		bufFileData.close();
		%>
	</div>

	<div class="icon_box_bk">
		<a href="#" onclick="window_open()">チャットをひらく</a>
	</div>
  </body>
</html>