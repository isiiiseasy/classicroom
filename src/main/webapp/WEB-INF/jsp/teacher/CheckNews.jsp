<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.io.*" %>

<!DOCTYPE html>
<html>
    <head>
        <title>お知らせ登録@iTeach</title>
        <link href="<%= request.getContextPath() %>/css/main.css" rel="stylesheet" type="text/css" media="screen" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    </head>
    <body>
	    <h1 id="chat_main">入力内容を確認してください。</h1>
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
		<br>
		<a href="<%=request.getContextPath() %>/mypage" style="color: black;">マイページ</a>
    </body>
</html>