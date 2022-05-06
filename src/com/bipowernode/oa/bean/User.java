package com.bipowernode.oa.bean;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class User implements HttpSessionBindingListener {
    private String username;
    private String password;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    @Override
    public void valueBound(HttpSessionBindingEvent httpSessionBindingEvent) {
        ServletContext application = httpSessionBindingEvent.getSession().getServletContext();
        Object onlinecount = application.getAttribute("onlinecount");
        if(onlinecount == null){
            application.setAttribute("onlinecount",1);
        }else {
            int count = (Integer)onlinecount;
            count++;
            application.setAttribute("onlinecount",count);
        }

    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent httpSessionBindingEvent) {
        ServletContext application = httpSessionBindingEvent.getSession().getServletContext();
        Integer onlinecount = (Integer) application.getAttribute("onlinecount");

            onlinecount--;
            application.setAttribute("onlinecount",onlinecount);
        }


    /**
     * 获取
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return "User{username = " + username + ", password = " + password + "}";
    }
}
