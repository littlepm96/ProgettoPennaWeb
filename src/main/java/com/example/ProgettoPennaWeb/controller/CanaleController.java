package com.example.ProgettoPennaWeb.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CanaleController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //La URL contiene il numero del canale selezionato (?canale=<numero>)
        String canale = request.getParameter("numero");
        //QUERY AL DATABASE
        try {
            //mi connetto qui al DB

            //query JDBC qui

            //programmi della giornata ritornati dalla query
            Object[][] result = new Object[][]{{"123", "titolo1", "descrizione1", "immagine1"}, {"124", "titolo2", "descrizione2", "immagine2"}};
        } catch (Exception e) {

        }
    }
}
