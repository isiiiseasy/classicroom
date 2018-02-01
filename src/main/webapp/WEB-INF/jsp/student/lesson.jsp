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

					BufferedReader bufFileData = new BufferedReader(new InputStreamReader(new FileInputStream(text),"UTF-8"));
			  		while(bufFileData.ready()){
			    	out.println(bufFileData.readLine() + "<BR>");
			  		}
			  		bufFileData.close();
				--%>



				 <%--
				 StringBuffer buf = new StringBuffer;
				 buf.append("./lesson"+"/");
				 buf.append(aryFls[0].getName());
				 String url = buf.toString();

				 File objFld2=new File(application.getRealPath(url));
				 File[] aryFls2=objFld2.listFiles();
				 for(int i=0;i<aryFls2.length;i++){}
				 --%>
				 				 <table border="1">
				 <tr>
				   <th>ファイル名</th>
				 </tr>
				 <%
				 File objFld=new File(application.getRealPath("./lessonfile"));
				 File[] aryFls=objFld.listFiles();
				 for(int i=0;i<aryFls.length;i++){
				 %>
				   <tr>
				   <td>
					<%=aryFls[i].getName()%>

				   </td>
				   </tr>
				 <% }
				 %>
				 <%
				    BufferedReader bufFileData = new BufferedReader(new InputStreamReader(new FileInputStream(aryFls[0]),"UTF-8"));
			  		while(bufFileData.ready()){
			    	out.println(bufFileData.readLine() + "<BR>");
			  		}
			  		bufFileData.close();

				   %>

				 </table>
			</div>
    </div>
  </body>
</html>