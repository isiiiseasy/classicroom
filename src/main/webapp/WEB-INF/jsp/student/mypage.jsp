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
    <img src="logo.png">
    <div align="right">
        <a href="<%=request.getContextPath() %>/mypage">マイページ</a>
        <a href="<%=request.getContextPath() %>/logout">ログアウト</a>
    </div>
    <table class="header">
        <tr>
            <td class="header_font">
                <a class="header_link" href="<%=request.getContextPath() %>/lesson">授業</a>
            </td>
            <td class="header_font">
                <a class="header_link" href="<%=request.getContextPath() %>/selfstudy">自習</a>
            </td>
            <td class="header_font">
                <a class="header_link" href="<%=request.getContextPath() %>/practice">練習問題</a>
            </td>
            <td class="header_font">
                <a class="header_link" href="<%=request.getContextPath() %>/test">テスト</a>
            </td>
        </tr>
    </table>
    <div id="L_box">
        <img src="icon.jpg"> 設定
    </div>
    <div id="R_box">
        <h1 id="midasi_1">ようこそ、${ userName } さん</h1>
        <div class="tab">
            <button type="button" class="tab-buttons" onclick="switchTab(this,'subject-java')">Java</button>
            <button type="button" class="tab-buttons" onclick="switchTab(this,'subject-c')">C言語</button>
        </div>
        <div id="subject-java" class="tab-contents">
            <svg id="result-graph"></svg>
            <div class="result-info">
                <p id="test_name"></p>
                <p id="my_point"></p>
                <p id="average_point"></p>
            </div>
        </div>
        <div id="subject-c" class="tab-contents"></div>
    </div>
    <script src="<%= request.getContextPath() %>/js/d3.min.js" charset="utf-8"></script>
    <script src="<%= request.getContextPath() %>/js/tab.js" charset="utf-8"></script>
    <script src="<%= request.getContextPath() %>/js/result_graph.js" charset="utf-8"></script>
</body>
	<a href="chat.jsp" onclick="window.open('chat.jsp','subwin','width=300,height=300');
		document.input_form.target = "subwin";
    	document.input_form.method = "post";
    	document.input_form.action = "chat.jsp";
   		document.input_form.submit();
    	return false;">チャットをひらく</a>
</html>
