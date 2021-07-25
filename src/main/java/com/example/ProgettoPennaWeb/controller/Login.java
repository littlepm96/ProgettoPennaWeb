package com.example.ProgettoPennaWeb.controller;

import com.example.ProgettoPennaWeb.model.Utente;
import com.example.ProgettoPennaWeb.utility.ErrorHandling;
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
                } catch (ServletException se) {
                    request.setAttribute("exception", se);
                    ErrorHandling.handleError(request, response);
                    return;
                } catch (IOException ie) {
                    request.setAttribute("exception", ie);
                    ErrorHandling.handleError(request, response);
                    return;
                }
            } else {
                request.setAttribute("errore", "Username e/o password incorretto");
                RequestDispatcher rs = request.getRequestDispatcher("login.jsp");
                try {
                    rs.forward(request, response);
                } catch (ServletException se) {
                    request.setAttribute("exception", se);
                    ErrorHandling.handleError(request, response);
                    return;
                } catch (IOException ie) {
                    request.setAttribute("exception", ie);
                    ErrorHandling.handleError(request, response);
                    return;
                }
            }
        } catch (SQLException sqe) {
            request.setAttribute("exception", sqe);
            ErrorHandling.handleError(request, response);
            return;
        } catch (NamingException ne) {
            request.setAttribute("exception", ne);
            ErrorHandling.handleError(request, response);
            return;
        }
    }
}