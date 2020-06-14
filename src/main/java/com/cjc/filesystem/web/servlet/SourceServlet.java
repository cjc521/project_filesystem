package com.cjc.filesystem.web.servlet;

import com.cjc.filesystem.domain.Ffile;
import com.cjc.filesystem.domain.Ftype;
import com.cjc.filesystem.domain.PageBean;
import com.cjc.filesystem.domain.User;
import com.cjc.filesystem.service.SourceService;
import com.cjc.filesystem.service.UserService;
import com.cjc.filesystem.service.impl.SourceServiceImpl;
import com.cjc.filesystem.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@WebServlet("/source/*")
public class SourceServlet extends BaseServlet {
    private SourceService service= new SourceServiceImpl();
    public void querySourceType(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Ftype> ftypes = service.queryType();

//        序列化为json对象
        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json,charset=utf-8");
         mapper.writeValue(resp.getOutputStream(),ftypes);
    }
//    分页查询资源
    public void querySource(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tidStr= req.getParameter("tid");
        String currentPageStr = req.getParameter("currentPage");
        String pageSizeStr = req.getParameter("pageSize");
        int tid=0; //设置默认页面为首页
        int currentPage=1;//设置默认第一页
        int pageSize=5;//设置默认每页条数
        if(tidStr !=null && tidStr.length() >0){
            tid=Integer.parseInt(tidStr);
        }
        if(currentPageStr !=null && currentPageStr.length() >0){
            currentPage=Integer.parseInt(currentPageStr);
        }
        if(pageSizeStr !=null && pageSizeStr.length() >0){
            pageSize=Integer.parseInt(pageSizeStr);
        }
        PageBean<Ffile> pageBean = service.querySource(tid, currentPage, pageSize);

//        序列化为json对象
        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json,charset=utf-8");
        mapper.writeValue(resp.getOutputStream(),pageBean);
    }

    public void uploadSource(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, FileUploadException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        Ffile ffile = new Ffile();//实例化一个资源对象
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();//创造该对象对上传文件进行设置
        diskFileItemFactory.setSizeThreshold(1024*1024*1024);//设置最大文件上传大小1G;
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);//创造该对象用来获取提交表单的文件及表单内容
        List<FileItem> fileItems = servletFileUpload.parseRequest(req);
        try {
            for (FileItem item:fileItems) {//遍历表单项
                if(item.isFormField()){//判断是否是普通项
                    String name = item.getFieldName();
                    String value= item.getString("utf-8");//解决乱码
                    if("tid".equals(name)){
                        int tid=Integer.parseInt(value);
                        ffile.setTid(tid);
                    }else{
                        ffile.setFdescribe(value);//设置资源描述
                    }
//
                }else {//处理上传项
                    String fname = item.getName();//获取上传资源名字
                    ffile.setFname(fname);//设置文件名
                    String sourcePath="source";
                    File sourceDir = new File(req.getServletContext().getRealPath("/")+sourcePath);//资源上传目录
                    if(!sourceDir.exists()){
                        sourceDir.mkdir();
                    }
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");//时间格式
                    Date date = new Date();
                    String  uploadTime= format.format(date);
                    //设置文件上传时间
                    ffile.setUploadTime(uploadTime);//设置文件上传时间
                    String file=fname.substring(0,fname.lastIndexOf("."))+"_"+date.getTime()+fname.substring(fname.lastIndexOf("."));//设置保存文件
                    ffile.setSavename(file);
                    File file1 = new File(sourceDir+File.separator+file);
                    try {
                        item.write(file1);
                    } catch (Exception e) {
                        req.setAttribute("errmsg","上传失败");
                        req.getRequestDispatcher("/sourceUpload.jsp");
                    }
                }
            }
            service.sourceUpload(ffile);//在数据库中保存
        } catch (Exception e) {
            req.setAttribute("errmsg","上传失败");
            req.getRequestDispatcher("/sourceUpload.jsp");
        }
        resp.sendRedirect(req.getContextPath()+"/index.jsp");
    }
    public String downloadSource(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fidStr = req.getParameter("fid");//获取资源id
        int fid = Integer.parseInt(fidStr);
        Ffile ffile = service.downloadSource(fid);
        String fname=ffile.getFname();//获取文件名
        String source = req.getServletContext().getRealPath("/source/");
        String realPath=source+File.separator+ffile.getSavename();//文件资源真实路径
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(realPath)));//用缓冲流读取数据
        OutputStream os = resp.getOutputStream();
        byte[] bytes = new byte[1024];
        resp.setContentType("text/html,charset=utf-8");
        String mimeType = req.getServletContext().getMimeType(fname);
        resp.setContentType(mimeType);
        //解决下载时中文名不显示问题
        if (req.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
            fname = new String(fname.getBytes("UTF-8"), "ISO8859-1"); // firefox浏览器    
        } else if (req.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
            fname = URLEncoder.encode(fname, "UTF-8");// IE浏览器
        }else if (req.getHeader("User-Agent").toUpperCase().indexOf("CHROME") > 0) {
            fname = new String(fname.getBytes("UTF-8"), "ISO8859-1");// 谷歌    
        }
        resp.setContentType(mimeType);
        resp.setHeader("Content-Disposition","attachment;filename="+fname);
        int len;
         while ((len=bis.read(bytes))!=-1){
             os.write(bytes,0,len);
         }
         //关闭流
         bis.close();
         os.close();
         return null;
    }
    //失败的尝试
   /* public String view(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fidStr = req.getParameter("fid");//获取资源id
        int fid = Integer.parseInt(fidStr);
        Ffile ffile = service.downloadSource(fid);
        String fname=ffile.getFname();//获取文件名
        String source = req.getServletContext().getRealPath("/source/");
        String realPath=source+File.separator+ffile.getFname();//文件资源真实路径
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(realPath)));//用缓冲流读取数据
        byte[] bytes = new byte[1024];
        resp.setContentType("text/html,charset=utf-8");
        String mimeType = req.getServletContext().getMimeType(fname);
        resp.setContentType(mimeType);
        //解决下载时中文名不显示问题
        if (req.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
            fname = new String(fname.getBytes("UTF-8"), "ISO8859-1"); // firefox浏览器
        } else if (req.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
            fname = URLEncoder.encode(fname, "UTF-8");// IE浏览器
        }else if (req.getHeader("User-Agent").toUpperCase().indexOf("CHROME") > 0) {
            fname = new String(fname.getBytes("UTF-8"), "ISO8859-1");// 谷歌
        }
        resp.setContentType(mimeType);
        resp.setHeader("Content-Disposition","attachment;filename="+fname);
        int len;
        String view=null;
        while ((len=bis.read(bytes))!=-1){
             view= new String(bytes, 0, len);
        }
        resp.getWriter().write(view);
        return null;
    }*/
}
