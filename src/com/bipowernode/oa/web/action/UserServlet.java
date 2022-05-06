package com.bipowernode.oa.web.action;

import com.bipowernode.oa.bean.User;
import com.bipowernode.oa.utils.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet({"/user/login","/user/exit"})
public class UserServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String servletPath = request.getServletPath();
        if("/user/login".equals(servletPath)){
            doLogin(request,response);
        }else if("/user/exit".equals(servletPath)){
            doExit(request,response);
        }
    }

    private void doExit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {

            session.removeAttribute("user");
            session.invalidate();
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if ("username".equals(name) || "password".equals(name)) {
                    cookie.setPath(request.getContextPath());
                    cookie.setMaxAge(0);

                    response.addCookie(cookie);
                }

            }
            response.sendRedirect(request.getContextPath());
        }
    }

    protected void doLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //username=leezu&password=123
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean success = false;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT `username`,`password`\n" +
                    "\tFROM\n" +
                    "\t\tt_user\n" +
                    "\tWHERE\n" +
                    "\t\t`username` = ? AND `password`=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);

            rs = ps.executeQuery();
            if(rs.next()){
                success = true;
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,null);
        }
        StringBuilder json = new StringBuilder();
        if(success){

            HttpSession session = request.getSession();
            //session.setAttribute("username",username);
            User user = new User(username,password);
            session.setAttribute("user",user);
            //request.getRequestDispatcher("/dept/list").forward(request,response);
            String f = request.getParameter("f");

            if("1".equals(f)){
                Cookie cookie = new Cookie("username",username);
                Cookie cookie2 = new Cookie("password",password);

                cookie.setMaxAge(60*60*24*10);
                cookie2.setMaxAge(60*60*24*10);
                cookie.setPath(request.getContextPath());
                cookie2.setPath(request.getContextPath());

                response.addCookie(cookie);
                response.addCookie(cookie2);
            }
            ;
            json.append("{\"success\":true}");

//            response.sendRedirect(request.getContextPath()+"/dept/list");
        }else {
//            response.sendRedirect(request.getContextPath()+"/error.jsp");
            json.append("{\"success\":false}");
            //out.print("<h2 align=\"center\">登录失败！<a href=\"${pageContext.request.contextPath}/index.jsp\">请重新登录</a></h2>");
        }
        //response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print(json);
    }



}

