package platform.servlet;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        Date date = new Date();//获取当前时间  
        SimpleDateFormat sdfFileName = new SimpleDateFormat("yyyyMMddHHmmss");  
        SimpleDateFormat sdfFolder = new SimpleDateFormat("yyMM");  
//        String newfileName = sdfFileName.format(date);//文件名称  
        String fileRealPath = "";//文件存放真实地址  
          
        String fileRealResistPath = "";//文件存放真实相对路径  
          
        //名称  界面编码 必须 和request 保存一致..否则乱码  
        String name = request.getParameter("name");  
        String id = request.getParameter("id");  //内容的ID,必须先添加内容，然后才能上传图片
//        String newfileName = name;    
           
        String firstFileName="";  
        // 获得容器中上传文件夹所在的物理路径  
        String savePath = this.getServletConfig().getServletContext().getRealPath("/") + "upload\\";
        if(StringUtils.isNotBlank(name)){
        	savePath +=name +"\\";
        }
        if(StringUtils.isNotBlank(id)){
        	savePath +=id +"\\";
        }
        File file = new File(savePath);  
        if (!file.isDirectory()) {  
            file.mkdirs();  
        }
  
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
	                    firstFileName=item.getName().substring(item.getName().lastIndexOf("\\")+1);
	                    String formatName = firstFileName.substring(firstFileName.lastIndexOf("."));//获取文件后缀名
	                    fileRealPath = savePath + fileName;//+ formatName;//文件存放真实地址
	                    BufferedInputStream in = new BufferedInputStream(item.getInputStream());// 获得文件输入流
	                    BufferedOutputStream outStream = new BufferedOutputStream(new FileOutputStream(new File(fileRealPath)));// 获得文件输出流
	                    Streams.copy(in, outStream, true);// 开始把文件写到你指定的上传文件夹

                        
	                }   
            	}
            }   
        } catch (Exception ex) {
    	   ex.printStackTrace();  
           return;  
		}   
       response.getWriter().write("1");  
          
    }  
   
    public void doPost(HttpServletRequest req, HttpServletResponse resp)  
            throws ServletException, IOException {  
        doGet(req, resp);  
    }  
}
