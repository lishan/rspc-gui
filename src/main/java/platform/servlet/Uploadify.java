package platform.servlet;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Iterator;
import java.util.List;

/**
 * jQuery文件上传插件
 * @author Administrator
 *
 */
public class Uploadify extends HttpServlet{
	private static final long serialVersionUID = 1L;  
	  
    /** 
     * 实现多文件的同时上传 
     */   
    public void doGet(HttpServletRequest request,  
            HttpServletResponse response) throws ServletException, IOException {  
        //设置接收的编码格式  
        request.setCharacterEncoding("UTF-8");  
//        String newfileName = name;
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        try {
            DiskFileItemFactory fac = new DiskFileItemFactory();  
            ServletFileUpload upload = new ServletFileUpload(fac);  
            upload.setHeaderEncoding("UTF-8");

            // 获取多个上传文件
            List fileList = upload.parseRequest(request);
            // 遍历上传文件写入磁盘  
            Iterator it = fileList.iterator();  
            while (it.hasNext()) {  
            	Object obit = it.next();
            	if(obit instanceof DiskFileItem){
	                DiskFileItem item = (DiskFileItem) obit;  

	                // 如果item是文件上传表单域
	                // 获得文件名及路径     
	                String fileName = item.getName();  
	                if (fileName != null) {
                        InputStreamReader streamReader = new InputStreamReader(item.getInputStream());
                        BufferedReader reader = new BufferedReader(streamReader);
                        int b;
                        while((b=reader.read())!=-1){
                            writer.write(reader.readLine());
                        }
                    }
            	}
            }   
        } catch (Exception ex) {
           return;
		}   
        writer.flush();
        writer.close();
    }
   
    public void doPost(HttpServletRequest req, HttpServletResponse resp)  
            throws ServletException, IOException {  
        doGet(req, resp);  
    }  
}
