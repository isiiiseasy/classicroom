<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.sql.*" %>
<%@
    try{
      Connection db = DriverManager.getConnection();
    }
%>
<%@include file="../../../css/main.css" %>

<!DOCTYPE html>
<html>
  <head>
    <title>生徒ホーム画面</title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="standard.css">
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
      <h2>
        <%@
            try{
              Connection db = DriverManager.getConnection();
              String sql = "select user_name from accounts";
              Statement st = db.createStatement();
              ResultSet rs = st.executeQuery(sql);
              String shimei = rs.getString("user_name");
              out.println(shimei);

              rs.close();
              st.close();
              db.close();

            }
            catch (Exception e) {
              out.println("error");
            }
        %>
      <h2>
      設定
    </div>
    <div id="R_box">
    	<h1 id="midasi_1">ようこそ、${ userID } さん</h1>
    </div>
  </body>
</html>
