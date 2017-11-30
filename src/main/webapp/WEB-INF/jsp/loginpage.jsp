<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>ログインページ</title>
<meta charset="UTF-8">
</head>
<body>
  <table style="margin: auto;">
      <tr>
        <td align="center" valign="middle">
          <img src="" alt="ロゴ" width="400px" height="300px">
        </td>
      </tr>
      <tr>
        <td align="center" valign="middle">
  	     <form method="post" action="login">
  		       <label>ユーザーID<input type="text" id="userId" name="userId" /></label><br>
  		       <label>パスワード<input type="password" id="password" name="password" /></label><br><br>
             <input type="submit" id="submit" value="ログイン" /><br>
        </form>
        </td>
      </tr>
      <tr>
        <td align="center" valign="middle">
        <p style="color:red;">${ warning }</p>
        </td>
      </tr>
    </table>
</body>
</html>
