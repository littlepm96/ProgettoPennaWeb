package com.example.ProgettoPennaWeb.utility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Classe con metodi utilizzati per la gestione degli errori
 * @author Dario D'Ercole
 */
public class ErrorHandling {

    /**
     * Gestisce l'eccezzione passata come attributo nella request ("exception"). Da chiamare in ogni clausola catch.
     * @param request la richiesta http
     * @param response la risposta http
     */
    public static void handleError(HttpServletRequest request, HttpServletResponse response){
        Exception e = (Exception) request.getAttribute("exception");
        System.err.println(e.getMessage());
        e.printStackTrace();
        try {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (IOException ioException) {
            //do nothing
        }
    }
}
