package com.bipowernode.oa.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class LoginCheckFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String servletPath = request.getServletPath();


        HttpSession session = request.getSession(false);
        if ("/index.jsp".equals(servletPath) || "/welcome".equals(servletPath)||"/user/login".equals(servletPath)||
              "/user/exit".equals(servletPath) ||(session != null && session.getAttribute("user")!= null)) {
            filterChain.doFilter(request,response);
        }else{
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }

    }

    @Override
    public void destroy() {

    }
}
