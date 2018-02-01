<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>

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
    </div>
    <div id="R_box">
    	<a href="<%=request.getContextPath() %>/lesson">第1回</a>
    		<div>

				<%--
					File newfile = new File("memo.txt");

					try{
					    newfile.createNewFile();
					}catch(IOException e){
					    out.println(e);
					}
				--%>
				<%--

					String template = "data/lessonfile/";
					StringBuffer buf = new StringBuffer();
					buf.append(template);
            		buf.append(request.getAttribute("folda"));
            		buf.append("/");
            		buf.append(request.getAttribute("filename"));
					String text = buf.toString();

					BufferedReader bufFileData = new BufferedReader(new InputStreamReader(new FileInputStream(text),"UTF-8"));
			  		while(bufFileData.ready()){
			    	out.println(bufFileData.readLine() + "<BR>");
			  		}
			  		bufFileData.close();
				--%>
				 <table border="1">
				 <tr>
				   <th>ファイル名</th><th>サイズ（キロバイト）</th>
				 </tr>
				 <%
				 File objFld=new File(application.getRealPath("."));
				 File[] aryFls=objFld.listFiles();
				 for(int i=0;i<aryFls.length;i++){
				 %>
				   <tr>
				   <td><%=aryFls[i].getName()%></td>
				   <td align="right">
				   <%
				   if(aryFls[i].isDirectory()){
				     out.print("<br />");
				   }else{
				     out.print(Math.ceil(aryFls[i].length()/1024+1) + "KB");
				   }
				   %>
				   </td>
				   </tr>
				 <% } %>
				 </table>
			</div>
    </div>
  </body>
</html>