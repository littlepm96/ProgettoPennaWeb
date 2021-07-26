package com.example.ProgettoPennaWeb.controller;

import com.example.ProgettoPennaWeb.model.Canale;
import com.example.ProgettoPennaWeb.model.ProgrammaTelevisivo;
import com.example.ProgettoPennaWeb.model.enums.FasciaOrariaPredefinita;
import com.example.ProgettoPennaWeb.persistenza.dao.CanaleDAO;
import com.example.ProgettoPennaWeb.persistenza.dao.ProgrammaTelevisivoDAO;
import com.example.ProgettoPennaWeb.utility.ErrorHandling;
import com.example.ProgettoPennaWeb.utility.MalformedFasciaOrariaException;

import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class FasceOrarieController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        String fasciaOraria =  request.getParameter("fascia_oraria");
        if(fasciaOraria == null){
            fasciaOraria = "mattina";
        }

        //Prendiamo le informazioni su canali e programmi della fascia oraria selezionata dal DB
        CanaleDAO canaleDao = new CanaleDAO();
        ProgrammaTelevisivoDAO programmaTelevisivoDao = new ProgrammaTelevisivoDAO();

        Map<Canale, List<ProgrammaTelevisivo>> risultato = new TreeMap<>();
        try {
            List<Canale> canali = canaleDao.getAll();

            for (Canale c : canali) {
                risultato.put(c, programmaTelevisivoDao.getByCanaleAndFasciaOraria(c.getId(),fasciaOraria, true));
            }
        } catch (SQLException sqe) {
            request.setAttribute("exception", sqe);
            ErrorHandling.handleError(request, response);
            return;
        } catch (NamingException ne) {
            request.setAttribute("exception", ne);
            ErrorHandling.handleError(request, response);
            return;
        } catch (MalformedFasciaOrariaException mfoe) {
            request.setAttribute("exception", mfoe);
            ErrorHandling.handleError(request, response);
            return;
        }

        request.setAttribute("risultato", risultato);

        ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher("/fasce-orarie.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (IOException ie) {
            System.err.println("Si è verificato un errore nel trasferimento dei dati");
            request.setAttribute("exception", ie);
            ErrorHandling.handleError(request, response);
            return;
        } catch (ServletException se) {
            System.err.println("Si è verificato un errore nella servlet");
            request.setAttribute("exception", se);
            ErrorHandling.handleError(request, response);
            return;
        } catch (Exception e) {
            System.err.println("Si è verificato un errore sconosciuto");
            request.setAttribute("exception", e);
            ErrorHandling.handleError(request, response);
            return;
        }

    }
}