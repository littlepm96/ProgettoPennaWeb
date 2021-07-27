package com.example.ProgettoPennaWeb.controller;

import com.example.ProgettoPennaWeb.model.Canale;
import com.example.ProgettoPennaWeb.model.ProgrammaTelevisivo;
import com.example.ProgettoPennaWeb.persistenza.dao.CanaleDAO;
import com.example.ProgettoPennaWeb.persistenza.dao.ProgrammaTelevisivoDAO;
import com.example.ProgettoPennaWeb.utility.ErrorHandling;

import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class HomePageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response){

        //Prendi la lista dei canali e dei programmi in onda
        CanaleDAO canaleDAO = new CanaleDAO();
        ProgrammaTelevisivoDAO programmaTelevisivoDAO = new ProgrammaTelevisivoDAO();
        List<Canale> canali;
        Map<Canale, ProgrammaTelevisivo>  programmiInOnda = new TreeMap<>();
        try {
            canali = canaleDAO.getAll();
            for(Canale c : canali){
                Optional<ProgrammaTelevisivo> programma = programmaTelevisivoDAO.getProgrammaInOndaByCanale(c.getId());
                if(programma.isPresent()){
                    programmiInOnda.put(c,programma.get());
                }else{
                    System.out.println("Programma in onda non trovato per "+c.getNome());
                    programmiInOnda.put(c, new ProgrammaTelevisivo());
                }
            }

            request.setAttribute("risultato", programmiInOnda);

            RequestDispatcher rs = request.getRequestDispatcher("/index2.jsp");
            rs.forward(request,response);
        } catch (SQLException sqe) {
            request.setAttribute("exception", sqe);
            ErrorHandling.handleError(request, response);
            return;
        } catch (NamingException ne) {
            request.setAttribute("exception", ne);
            ErrorHandling.handleError(request, response);
            return;
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
}
