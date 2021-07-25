package com.example.ProgettoPennaWeb.controller;

import com.example.ProgettoPennaWeb.model.Utente;
import com.example.ProgettoPennaWeb.utility.SecurityLayer;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Optional;

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
            Optional<Utente> container = Validate.checkUser(email, pass);
            if (container.isPresent()) {
                Utente utente = container.get();
                //creo la sessione
                HttpSession session = SecurityLayer.createSession(request, utente);
                request.setAttribute("session", session);
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