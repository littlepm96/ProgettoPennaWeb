package com.example.ProgettoPennaWeb.controller;

import com.example.ProgettoPennaWeb.utility.ErrorHandling;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Welcome extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException ie) {
            request.setAttribute("exception", ie);
            ErrorHandling.handleError(request,response);
            return;
        }

        //Controlla se arriviamo da un link di registrazione
        if (request.getParameter("email") != null && request.getParameter("hash") != null){
            //Stiamo arrivando da un'attivazione tramite link che ha avuto successo
            out.println("<h1>Account attivato con successo! Puoi chiudere questa pagina.</h1>");
        }
        else if(request.getParameter("not_found")!= null) {
            //arriviamo da un'attivazione che è fallita (utente non trovato)
            out.println("<h1>L'Account che si sta attivando non è stato trovato! <a href=\"/registrazione.jsp\">Registrarsi</a> nuovamente e ritentare.</h1>");
        }else{
            //Arriviamo da una registrazione
            out.println("<h1>Controlla l'email per attivare il tuo account</h1>");
        }
    }
}
