package com.example.ProgettoPennaWeb.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.*;


public class FasceOrarieController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getParameter("fascia-oraria"));
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        String parametroGet = request.getParameter("fascia-oraria");
        String fasciaSelezionata;
        switch (parametroGet) {
            default:
            case "mattina":
                fasciaSelezionata = "mattina";
                break;
            case "pomeriggio":
                fasciaSelezionata = "pomeriggio";
                break;
            case "sera":
                fasciaSelezionata = "sera";
                break;
            case "notte":
                fasciaSelezionata = "notte";
        }

        request.setAttribute("fascia-selezionata", fasciaSelezionata);

        ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher("/fasce-orarie.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (IOException ex) {
            PrintWriter out = response.getWriter();
            out.println("Si è verificato un errore nel trasferimento dei dati");
            ex.printStackTrace();

        } catch (ServletException ex) {
            PrintWriter out = response.getWriter();
            out.println("Si è verificato un errore nella servlet");
            ex.printStackTrace();
        } catch (Exception ex) {
            PrintWriter out = response.getWriter();
            out.println("Si è verificato un errore sconosciuto");
            ex.printStackTrace();

        }

    }
}