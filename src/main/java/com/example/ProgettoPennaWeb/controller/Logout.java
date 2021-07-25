package com.example.ProgettoPennaWeb.controller;

import com.example.ProgettoPennaWeb.utility.SecurityLayer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class Logout extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        SecurityLayer.disposeSession(request);
        response.sendRedirect(getServletContext().getContextPath()+"/index.jsp");

    }

}
