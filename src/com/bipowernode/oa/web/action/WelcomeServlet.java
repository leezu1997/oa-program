package com.bipowernode.oa.web.action;

import com.bipowernode.oa.bean.User;
import com.bipowernode.oa.utils.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        String username = null;
        String password = null;
        if(cookies!=null ){
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if("username".equals(name)){
                    username = cookie.getValue();
                } else if("password".equals(name)){
                    password = cookie.getValue();
                }
            }
        }

        if(username != null && password != null){
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
            if(success){
                HttpSession session = request.getSession();
                //session.setAttribute("username",username);
                User user = new User(username,password);
                session.setAttribute("user",user);
                response.sendRedirect(request.getContextPath()+"/dept/list");
            }else {
                response.sendRedirect(request.getContextPath()+"/index.jsp");
            }



        }else{
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }


    }
}
