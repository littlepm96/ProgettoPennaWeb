package com.example.ProgettoPennaWeb.controller;

import com.example.ProgettoPennaWeb.utility.ErrorHandling;
import com.example.ProgettoPennaWeb.utility.SecurityLayer;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

public class ProfiloUtenteController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    private void processRequest(HttpServletRequest request,HttpServletResponse response){
        //Controllo che sono loggato
        HttpSession s = SecurityLayer.checkSession(request);
        if(s==null){
            try {
                response.sendRedirect(getServletContext().getContextPath()+"/login.jsp");
            } catch (IOException ie) {
                request.setAttribute("exception", ie);
                ErrorHandling.handleError(request,response);
                return;
            }
        }
        else{

            //dispatch al jsp
            request.setAttribute("sessione", s);
            ServletContext context = getServletContext();
            RequestDispatcher rd = context.getRequestDispatcher("/profilo-utente.jsp");
            try {
                rd.forward(request,response);
            } catch (ServletException se) {
                request.setAttribute("exception", se);
                ErrorHandling.handleError(request,response);
                return;
            } catch (IOException ie) {
                request.setAttribute("exception", ie);
                ErrorHandling.handleError(request,response);
                return;
            }
        }
    }
}
