package com.example.ProgettoPennaWeb.controller;

import com.example.ProgettoPennaWeb.model.Utente;
import com.example.ProgettoPennaWeb.persistenza.dao.UtenteDAO;
import com.example.ProgettoPennaWeb.utility.ErrorHandling;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class ActivateAccount extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String hash = request.getParameter("hash");

        UtenteDAO dao = new UtenteDAO();
        Optional<Utente> result = null;
        try {
            result = dao.getUtenteByEmail(email);

            if (result.isPresent()) {
                Utente u = result.get();
                String hashSalvato = u.getHash();
                if (hashSalvato.equals(hash)) {
                    u.setVerificato(true);
                    dao.update(u);

                    //Faccio una redirect alla pagina di benvenuto con il messaggio di avvenuta attivazione
                    StringBuilder sb = new StringBuilder(getServletContext().getContextPath());
                    sb.append("/welcome?email=");
                    sb.append(email);
                    sb.append("&hash=");
                    sb.append(hash);
                    response.sendRedirect(sb.toString());
                }
            } else {
                //Faccio una redirect alla pagina di benvenuto con il messaggio di errore
                response.sendRedirect(getServletContext().getContextPath()+"/welcome?not_found=1");
            }
        } catch (SQLException sqe) {
            request.setAttribute("exception", sqe);
            ErrorHandling.handleError(request,response);
            return;
        } catch (NamingException ne) {
            request.setAttribute("exception", ne);
            ErrorHandling.handleError(request,response);
            return;
        } catch (IOException ie) {
            request.setAttribute("exception", ie);
            ErrorHandling.handleError(request,response);
            return;
        }
    }
}
