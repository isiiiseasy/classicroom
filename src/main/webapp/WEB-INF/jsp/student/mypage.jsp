<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>

<head>
    <link href="<%= request.getContextPath() %>/css/main.css" rel="stylesheet" type="text/css" media="screen" />
    <link href="<%= request.getContextPath() %>/css/tab.css" rel="stylesheet" type="text/css" media="screen" />
    <link href="<%= request.getContextPath() %>/css/graph.css" rel="stylesheet" type="text/css" media="screen" />
    <title>生徒ホーム画面</title>
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
      <a class="normal_link" href="<%=request.getContextPath() %>/image">アイコン画像変更</a>
    </div>
    <div id="R_box">
        <h1 id="midasi_1">ようこそ、${ userName } さん</h1>
        <div id="graph-tab" class="tab"></div>
        <div id="graph-tab-contents"></div>
    </div>
    <script src="<%= request.getContextPath() %>/js/d3.min.js" charset="utf-8"></script>
    <script src="<%= request.getContextPath() %>/js/tab.js" charset="utf-8"></script>
    <script src="<%= request.getContextPath() %>/js/mypage_s.js" charset="utf-8"></script>

	<script type="text/javascript">
	    function window_open(){
			window.open("chat.jsp","window_name","width=640,height=480,scrollbars=yes");
			document.fdata.target = "window_name";
			document.fdata.method = "post";
			document.fdata.action = "example.shtml";
			document.fdata.submit();
		}
	</script>

	<div class="icon_box_bk">
		<a href="#" onclick="window_open()">チャットをひらく</a>
	</div>
</body>

</html>
