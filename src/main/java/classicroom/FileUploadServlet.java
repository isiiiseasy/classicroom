package classicroom;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@SuppressWarnings("serial")
@WebServlet("fileupload")
@MultipartConfig(maxFileSize=1048576)  // 最大1M
public class FileUploadServlet extends HttpServlet {
  public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    // ファイルの保存
    DataBase db = new DataBase();

    String flg = request.getParameter("flg");

	if(flg.equals("0")) {	//アイコン画像保存

		File img = new File(this.getServletContext().getRealPath("/img"));

		HttpSession session = request.getSession(false);

		String userId = (String) session.getAttribute("userId");
	    Part filePart = request.getPart("file");
	    String fileName = (new StringBuilder(userId)
	      .append("_img.jpg").toString());

	    save(filePart, new File(img, fileName));

	    db.IconSet(userId, fileName);

	    response.sendRedirect("mypage");

	}else {					//授業ファイル保存

	    Part filePart = request.getPart("file");
	    String fileName = extractFileName(filePart);

	    String lessonId = request.getParameter("lessonId");

	    try {
            Files.createDirectories(Paths.get((this.getServletContext().getRealPath("/lesson/")+lessonId)));
        } catch (IOException e) {
            System.out.println(e);
        }

		File lessonfile = new File(this.getServletContext().getRealPath("/lesson/")+lessonId);

	    save(filePart, new File(lessonfile, fileName));

	    response.sendRedirect("lessonupload");
	}


  }

  private String extractFileName(Part part) { //ファイル名取得
	  String[] splitedHeader = part.getHeader("Content-Disposition").split(";");

	  String fileName = null;
	  for(String item: splitedHeader) {
	      if(item.trim().startsWith("filename")) {
	          fileName = item.substring(item.indexOf('"')).replaceAll("\"", "");
	      }
	  }
	  return fileName;
	}

  public void save(Part in, File out) throws IOException {
    BufferedInputStream br
      = new BufferedInputStream(in.getInputStream());
    try (BufferedOutputStream bw =
      new BufferedOutputStream(new FileOutputStream(out))
    ) {
      int len = 0;
      byte[] buff = new byte[1024];
      while ((len = br.read(buff)) != -1) {
        bw.write(buff, 0, len);
      }
    }
  }
}