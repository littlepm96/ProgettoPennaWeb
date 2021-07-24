package com.example.ProgettoPennaWeb.controller;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class Login extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)  {
        response.setContentType("text/html;charset=UTF-8");

        String email = request.getParameter("email");
        String pass = request.getParameter("pass");

        try {
            if (Validate.checkUser(email, pass)) {
                RequestDispatcher rs = request.getRequestDispatcher("profilo-utente.jsp");
                try {
                    rs.forward(request, response);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                PrintWriter out = null;
                try {
                    out = response.getWriter();
                    out.println("Username or Password incorretto");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException sqe) {
            sqe.printStackTrace();
        } catch (NamingException ne) {
            ne.printStackTrace();
        }
    }
}