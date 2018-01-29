<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.sql.*" %>


    <img src="./img/pagelogo1.png" alt="ロゴ" width="300px" height="117px">

    <div align="right">
        <a href="<%=request.getContextPath() %>/mypage" style="color: black; font-family: Meiryo UI; text-decoration: none;">マイページ</a>
        <a href="<%=request.getContextPath() %>/logout" style="color: black; font-family: Meiryo UI; text-decoration: none;">ログアウト</a>
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
