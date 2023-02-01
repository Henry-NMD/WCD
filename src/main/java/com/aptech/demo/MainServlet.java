package com.aptech.demo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//@WebServlet(urlPatterns = {"/home","*.do"} , initParams = {@WebInitParam(name = "productName",value = "Demo")}) // accept mutil link from url
@WebServlet(urlPatterns = {"/home","*.do"},name = "Main")
public class MainServlet extends HttpServlet {
    String productName = "";
    String connectStr = "";
    @Override
    public void init() throws ServletException {
        productName = getInitParameter("productName");
        connectStr = getServletContext().getInitParameter("connectStr");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/xml");
        String name = req.getParameter("name");
        resp.getWriter().write(String.format("<application>" +
                "<msg>Hello %s</msg>" +
                "<product>%s</product>" +
                "<connectStr>%s</connectStr>" +
                "</application>",name, productName,connectStr));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        if(name != null && name != "") {
            resp.getWriter().write("Hello " + name);
        } else {
            resp.sendRedirect("index.html");
        }
    }
}
