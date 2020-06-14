package com.cjc.filesystem.web.servlet;

import com.cjc.filesystem.domain.PageBean;
import com.cjc.filesystem.domain.User;
import com.cjc.filesystem.service.UserService;
import com.cjc.filesystem.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private UserService service= new UserServiceImpl();
    public void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> map = req.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        boolean flag = service.registerUser(user);
        if(flag){
            //注册成功
            resp.setContentType("text/html;charset=utf-8");
            resp.sendRedirect(req.getContextPath()+"/login.jsp");
        }else{
            //注册失败
            req.setAttribute("errmsg","注册失败，请重试");
            req.getRequestDispatcher("/register.jsp").forward(req,resp);
        }

    }

    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        User user = service.login(name, password);
//        System.out.println(user);
//        登陆成功
        if(user!=null){
            HttpSession session = req.getSession();
            session.setAttribute("user",user);
            resp.setContentType("text/html,charset=utf-8");
            resp.sendRedirect(req.getContextPath()+"/index.jsp?tid=0");//跳转页面首页+tid=0;默认首页页面资源
        }else{
            req.setAttribute("errmsg","登录名或密码错误，请重试");
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }
    }
//退出登录
    public void exit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("user");
        resp.sendRedirect(req.getContextPath()+"/index.jsp?tid=0");
    }
    public void updateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> map = req.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        service.updateUser(user);
        resp.sendRedirect(req.getContextPath()+"/login.jsp");
    }
    //查询用户信息并进入用户管理页面
    public void userManage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int currentPage=1;
        int pageSize=5;//
        try {
            String currentPageStr = req.getParameter("currentPage");
            String pageSizeStr=req.getParameter("pageSize");
            if(currentPageStr!=null && currentPageStr.length()>0){
                currentPage=Integer.parseInt(currentPageStr);
            }
            if(pageSizeStr!=null && pageSizeStr.length()>0){
                pageSize=Integer.parseInt(pageSizeStr);
            }
        } catch (NumberFormatException e) {
             currentPage=1;
             pageSize=5;//
        }
        PageBean<User> userPageBean = service.queryUser(currentPage, pageSize);
        HttpSession session = req.getSession();
        session.setAttribute("userPageBean",userPageBean);
        resp.sendRedirect(req.getContextPath()+"/userManage.jsp");

    }
    public void changeScore(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        String scoreStr = req.getParameter("score");
        service.changeScore(Integer.parseInt(idStr),Integer.parseInt(scoreStr));
        userManage(req,resp);
    }
    public void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        service.deleterUser(Integer.parseInt(idStr));
        userManage(req,resp);
    }

}
