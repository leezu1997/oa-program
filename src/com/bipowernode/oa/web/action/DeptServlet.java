package com.bipowernode.oa.web.action;

import com.bipowernode.oa.bean.Dept;
import com.bipowernode.oa.utils.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet({"/dept/list","/dept/save","/dept/detail","/dept/delete","/dept/modify"})
public class DeptServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        HttpSession session = request.getSession(false);
//        if (session != null && session.getAttribute("username")!= null) {
//            String servletPath = request.getServletPath();
//            if ("/dept/list".equals(servletPath)) {
//                doList(request, response);
//            } else if ("/dept/save".equals(servletPath)) {
//                deSave(request, response);
////
//            } else if ("/dept/detail".equals(servletPath)) {
//                doDetail(request, response);
//            } else if ("/dept/delete".equals(servletPath)) {
//                doDel(request, response);
//            } else if ("/dept/modify".equals(servletPath)) {
//                doModify(request, response);
////        }
//            }
//        }else{
//            response.sendRedirect(request.getContextPath()+"/index.jsp");
//        }
        String servletPath = request.getServletPath();
            if ("/dept/list".equals(servletPath)) {
                doList(request, response);
            } else if ("/dept/save".equals(servletPath)) {
                deSave(request, response);

            } else if ("/dept/detail".equals(servletPath)) {
                doDetail(request, response);
            } else if ("/dept/delete".equals(servletPath)) {
                doDel(request, response);
            } else if ("/dept/modify".equals(servletPath)) {
                doModify(request, response);
          }
    }

    private void doModify(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=UTF-8");
            String deptno = request.getParameter("deptno");
            String dname = request.getParameter("dname");
            String loc = request.getParameter("loc");

            PrintWriter out = response.getWriter();
            String contextPath = request.getContextPath();

            Connection conn = null;
            PreparedStatement ps = null;
            int count = 0;
            try {
                conn = DBUtil.getConnection();
                String sql = "UPDATE dept \n" +
                        "\tSET\n" +
                        "\t\tdname = ?,loc = ?\n" +
                        "\tWHERE\n" +
                        "\t\tdeptno = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1,dname);
                ps.setString(2,loc);
                ps.setString(3,deptno);
                count = ps.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                DBUtil.close(conn,ps,null);
            }
            if(count == 1){
                //request.getRequestDispatcher("/dept/list").forward(request,response);
                response.sendRedirect(request.getContextPath()+"/dept/list");
            }

        }

        

    private void deSave(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException{
            int count = 0;
            request.setCharacterEncoding("utf-8");
            String deptno = request.getParameter("deptno");
            String dname = request.getParameter("dname");
            String loc = request.getParameter("loc");
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;

            try {
                conn = DBUtil.getConnection();
                String sql = "insert into dept(deptno,dname,loc) values(?,?,?)";
                ps = conn.prepareStatement(sql);
                ps.setString(1,deptno);
                ps.setString(2,dname);
                ps.setString(3,loc);
                count = ps.executeUpdate();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                DBUtil.close(conn,ps,null);
            }

            if(count == 1){
                //request.getRequestDispatcher("/dept/list").forward(request,response);
                response.sendRedirect(request.getContextPath()+"/dept/list");
            }
//        if(count == 1){
//            request.getRequestDispatcher("/dept/list").forward(request,response);
//        }else{
//            request.getRequestDispatcher("/error.html").forward(request,response);
//        }
        }



    private void doDel(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String deptno = request.getParameter("deptno");
        //Dept dept = new Dept();
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtil.getConnection();
            String sql = "delete from dept where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,deptno);
            count = ps.executeUpdate();

            } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,null);
        }
        String contextPath = request.getContextPath();
        if (count == 1){
            response.sendRedirect(contextPath + "/dept/list");
        }


    }

    private void doDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String dno = request.getParameter("dno");
        Dept dept = new Dept();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "select dname,loc from dept where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,dno);
            rs = ps.executeQuery();

            if (rs.next()){

                String dname = rs.getString("dname");
                String loc = rs.getString("loc");


                dept.setDeptno(dno);
                dept.setDname(dname);
                dept.setLoc(loc);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,rs);
        }
        request.setAttribute("dept",dept);
        //request.getRequestDispatcher("/detail.jsp").forward(request,response);
        String f = request.getParameter("f");
        if("m".equals(f)){
            request.getRequestDispatcher("/edit.jsp").forward(request,response);
        }else if("d".equals(f)){
            request.getRequestDispatcher("/detail.jsp").forward(request,response);
        }
    }






    private void doList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        List<Dept> depts = new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "select deptno,dname,loc from dept";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()){
                String deptno = rs.getString("deptno");
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");

                Dept dept = new Dept();
                dept.setDeptno(deptno);
                dept.setDname(dname);
                dept.setLoc(loc);

                depts.add(dept);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,rs);
        }
        request.setAttribute("deptList",depts);
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }
    }

