package com.example.ProgettoPennaWeb.controller;

import com.example.ProgettoPennaWeb.model.RicercaSalvata;
import com.example.ProgettoPennaWeb.model.Utente;
import com.example.ProgettoPennaWeb.persistenza.dao.RicercaSalvataDAO;
import com.example.ProgettoPennaWeb.utility.ErrorHandling;
import com.example.ProgettoPennaWeb.utility.FasciaOraria;
import com.example.ProgettoPennaWeb.utility.SecurityLayer;

import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalTime;

public class SalvataggioRicercheController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response){
        //Prendiamo i parametri
        String titolo = request.getParameter("titolo");
        String genere = request.getParameter("genere");
        String numeroCanale = request.getParameter("numero_canale");
        String dataTrasmissione = request.getParameter("data_trasmissione");
        String fasciaOraria = request.getParameter("fascia_oraria");

        //Prendiamo i dati utente dalla sessione
        HttpSession s = SecurityLayer.checkSession(request);
        if(s== null){
            try {
                response.sendRedirect(getServletContext().getContextPath()+"/login.jsp");
            } catch (IOException ie) {
                request.setAttribute("exception", ie);
                ErrorHandling.handleError(request, response);
                return;
            }
        }

        Utente u = (Utente) s.getAttribute("infoUtente");
        String email = u.getEmail();

        //Creamo il dao e salviamo la ricerca in db
        RicercaSalvataDAO dao = new RicercaSalvataDAO();
        RicercaSalvata r = new RicercaSalvata();
        r.setId(null);
        r.setEmailUtente(email);
        r.setTitolo(SecurityLayer.addSlashes(titolo));
        r.setGenere(SecurityLayer.addSlashes(genere));
        if(numeroCanale!= null && !numeroCanale.isEmpty()) {
            r.setNumeroCanale(Short.parseShort(numeroCanale));
        }else{
            r.setNumeroCanale(null);
        }
        r.setDataTrasmissione(SecurityLayer.addSlashes(dataTrasmissione));
        //Aggiusto la formattazione degli orari in questa fascia oraria
        if(fasciaOraria!= null && !fasciaOraria.isEmpty()) {
            String[] orari = fasciaOraria.split("-");
            orari[0] = FasciaOraria.formatOrario(orari[0]);
            orari[1] = FasciaOraria.formatOrario(orari[1]);
            r.setFasciaOraria(orari[0] + "-" + orari[1]);
        }else{
            r.setFasciaOraria("");
        }
        boolean isSuccess = false;
        try {
         isSuccess = dao.save(r);
        } catch (SQLException sqe) {
            request.setAttribute("exception", sqe);
            ErrorHandling.handleError(request, response);
            return;
        } catch (NamingException ne) {
            request.setAttribute("exception", ne);
            ErrorHandling.handleError(request, response);
            return;
        }

        if(isSuccess) {
            request.setAttribute("success", "Ricerca salvata!");
        }else{
            request.setAttribute("error", "Si è verificato un problema nel salvataggio della ricerca");
        }

        try {
            RequestDispatcher ds = request.getRequestDispatcher("/cerca");
            ds.forward(request,response);
        } catch (IOException ie) {
            request.setAttribute("exception", ie);
            ErrorHandling.handleError(request, response);
            return;
        } catch (ServletException se) {
            request.setAttribute("exception", se);
            ErrorHandling.handleError(request, response);
            return;
        }
    }
}
