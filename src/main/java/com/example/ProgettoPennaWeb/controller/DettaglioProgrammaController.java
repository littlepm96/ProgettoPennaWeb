package com.example.ProgettoPennaWeb.controller;

import com.example.ProgettoPennaWeb.model.ProgrammaTelevisivo;
import com.example.ProgettoPennaWeb.model.enums.GenereProgramma;
import com.example.ProgettoPennaWeb.persistenza.dao.ProgrammaTelevisivoDAO;
import com.example.ProgettoPennaWeb.utility.ErrorHandling;
import com.example.ProgettoPennaWeb.utility.MalformedFasciaOrariaException;
import com.example.ProgettoPennaWeb.utility.Ricerca;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class DettaglioProgrammaController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        processRequest(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request,response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response){
        //Parametri
      Long idProgramma = null;
      try {
          idProgramma = Long.parseLong(request.getParameter("id"));

      }catch (NumberFormatException nfe){
          request.setAttribute("exception", nfe);
          ErrorHandling.handleError(request, response);
          return;
      }
        ProgrammaTelevisivoDAO dao = new ProgrammaTelevisivoDAO();
        Optional<ProgrammaTelevisivo> risultato;
        try {
            risultato = dao.getProgrammaById(idProgramma);
        } catch (SQLException sqe) {
            request.setAttribute("exception", sqe);
            ErrorHandling.handleError(request, response);
            return;
        } catch (NamingException ne) {
            request.setAttribute("exception", ne);
            ErrorHandling.handleError(request, response);
            return;
        }

        if(risultato != null && risultato.isPresent()){
            ProgrammaTelevisivo p = risultato.get();
            request.setAttribute("programma", p);

            //se il programma è una serie tv, cerca gli altri episodi in onda in questo mese
            if(p.getGenere()== GenereProgramma.SERIE){
                Ricerca r = new Ricerca();
                r.setTitolo(p.getTitolo());
                LocalDate inizio = LocalDate.now();
                inizio = inizio.withDayOfMonth(1);
                LocalDate fine = inizio.withMonth(inizio.getMonthValue()+1);
                fine = fine.minusDays(1);
                r.setDataTrasmissione(new LocalDate[]{inizio,fine});
                List<ProgrammaTelevisivo> episodi;
                try {
                    episodi = dao.getBySearchParameters(r,500);
                } catch (NamingException ne) {
                    request.setAttribute("exception", ne);
                    ErrorHandling.handleError(request, response);
                    return;
                } catch (SQLException sqe) {
                    request.setAttribute("exception", sqe);
                    ErrorHandling.handleError(request, response);
                    return;
                } catch (MalformedFasciaOrariaException mfoe) {
                    request.setAttribute("exception", mfoe);
                    ErrorHandling.handleError(request, response);
                    return;
                }

                request.setAttribute("episodi", episodi);
            }

            RequestDispatcher rs = request.getRequestDispatcher("/dettaglio-programma.jsp");
            try {
                rs.forward(request,response);
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

}
