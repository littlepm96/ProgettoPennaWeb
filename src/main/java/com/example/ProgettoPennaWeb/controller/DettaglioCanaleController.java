package com.example.ProgettoPennaWeb.controller;

import com.example.ProgettoPennaWeb.model.Canale;
import com.example.ProgettoPennaWeb.persistenza.dao.CanaleDAO;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Optional;

public class DettaglioCanaleController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doGet");

        processRequest(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost");

        processRequest(request,response);

    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response){
        System.out.println("processRequest");
        Integer idCanale;
        try{
            idCanale = Integer.parseInt(request.getParameter("id"));
        }catch (NumberFormatException nfe){
            System.err.println("L'id passato non è valido");
            nfe.printStackTrace();
            return;
        }
        CanaleDAO dao = new CanaleDAO();
        try {
            System.out.println("Sto effettuando la query");
            Optional<Canale> risultato = dao.get(idCanale.toString());
            if(risultato.isPresent()) {
                request.setAttribute("nome_canale", risultato);
            }else{
                request.setAttribute("nome_canale", "ERRORE");
            }
            if(risultato.isPresent())
            System.out.println(risultato);
            else
                System.out.println("la query non ha trovato il canale");


        } catch (SQLException sqe) {
            sqe.printStackTrace();
        } catch (NamingException ne) {
            ne.printStackTrace();
        }


        //Usare solo con le JSP
        ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher("/dettaglio-canale.jsp");
        try {
            System.out.println("Sto effettuando il dispatch");
            dispatcher.forward(request, response);
        } catch (IOException ex) {
            PrintWriter out = null;
            try {
                out = response.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.println("Si è verificato un errore nel trasferimento dei dati");
            ex.printStackTrace();

        } catch (ServletException ex) {
            PrintWriter out = null;
            try {
                out = response.getWriter();
            } catch (IOException e) {
                //niente
            }
            out.println("Si è verificato un errore nella servlet");
            ex.printStackTrace();
        } catch (Exception ex) {
            PrintWriter out = null;
            try {
                out = response.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.println("Si è verificato un errore sconosciuto");
            ex.printStackTrace();

        }
    }
}
