package com.example.ProgettoPennaWeb.controller;

import com.example.ProgettoPennaWeb.model.Canale;
import com.example.ProgettoPennaWeb.model.ProgrammaTelevisivo;
import com.example.ProgettoPennaWeb.model.utility.FasciaOraria;
import com.example.ProgettoPennaWeb.model.utility.MalformedFasciaOrariaException;
import com.example.ProgettoPennaWeb.persistenza.dao.CanaleDAO;
import com.example.ProgettoPennaWeb.persistenza.dao.ProgrammaTelevisivoDAO;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DettaglioCanaleController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        processRequest(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        processRequest(request,response);

    }
 private void processRequest(HttpServletRequest request, HttpServletResponse response){

        Integer idCanale;
        try{
            idCanale = Integer.parseInt(request.getParameter("id"));
        }catch (NumberFormatException nfe){
            System.err.println("L'id passato non è valido");
            nfe.printStackTrace();
            return;
        }
        //Inizializziamo i dao
        CanaleDAO canaleDAO = new CanaleDAO();
        ProgrammaTelevisivoDAO programmaTelevisivoDAO = new ProgrammaTelevisivoDAO();
        try {
            //informazione del canale
            System.out.println("Sto effettuando la query per le informazioni del canale");
            Optional<Canale> risultato = canaleDAO.getCanaleById(idCanale);
            if(risultato.isPresent()) {
                request.setAttribute("nomeCanale", risultato.get().getNome());
                request.setAttribute("numeroCanale", risultato.get().getNumero());
            }else{
                request.setAttribute("nomeCanale", "ERRORE");
            }

            if(risultato.isPresent())
            System.out.println(risultato);
            else
                System.out.println("la query non ha trovato il canale");

            //Lista dei programmi televisivi in onda oggi
            List <ProgrammaTelevisivo> programmi = programmaTelevisivoDAO.getByCanale(idCanale,true);
            if(programmi.size()>0){
                request.setAttribute("programmi", programmi);
                separaProgrammiPerFascia(request,response);
            }else{
                System.out.println("La lista dei programmi è vuota!");
            }

        } catch (SQLException sqe) {
            sqe.printStackTrace();
        } catch (NamingException ne) {
            ne.printStackTrace();
        } catch (MalformedFasciaOrariaException e) {
            e.printStackTrace();
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

    private void separaProgrammiPerFascia(HttpServletRequest request, HttpServletResponse response) throws MalformedFasciaOrariaException {
        final String FASCIA_ORARIA_MATTINA = "06:00-11:59";
        final String FASCIA_ORARIA_POMERIGGIO = "12:00-17:59";
        final String FASCIA_ORARIA_SERA = "18:00-23:59";
        final String FASCIA_ORARIA_NOTTE = "00:00-05:59";

        //Ripeschiamo la lista dai attribute
        List<ProgrammaTelevisivo> programmi = (List<ProgrammaTelevisivo>) request.getAttribute("programmi");

        //Andiamo a suddividere i programmi nelle 4 fasce orarie (mattina, pomeriggio, sera e notte)
        List<ProgrammaTelevisivo> programmiDiMattina = new ArrayList<>();
        List<ProgrammaTelevisivo> programmiDiPomeriggio = new ArrayList<>();
        List<ProgrammaTelevisivo> programmiDiSera = new ArrayList<>();
        List<ProgrammaTelevisivo> programmiDiNotte = new ArrayList<>();

        int count = 0;
        ProgrammaTelevisivo p;
        //Programmi di mattina
        for(;count<programmi.size();count++){
            p = programmi.get(count);
            if(FasciaOraria.isContained(p.getOrarioInizio(),FASCIA_ORARIA_MATTINA)){
                programmiDiMattina.add(p);
            }else{
                //Dal momento che la lista è ordinata per orario di messa in onda direttamente nella query al database, se finiamo fuori la fascia oraria possiamo chiudere il ciclo
                break;
            }
        }
        //Programmi di pomeriggio
        for(;count<programmi.size();count++){
            p = programmi.get(count);
            if(FasciaOraria.isContained(p.getOrarioInizio(),FASCIA_ORARIA_POMERIGGIO)){
                programmiDiPomeriggio.add(p);
            }else{
                //Dal momento che la lista è ordinata per orario di messa in onda direttamente nella query al database, se finiamo fuori la fascia oraria possiamo chiudere il ciclo
                break;
            }
        }//Programmi di sera
        for(;count<programmi.size();count++){
            p = programmi.get(count);
            if(FasciaOraria.isContained(p.getOrarioInizio(),FASCIA_ORARIA_SERA)){
                programmiDiSera.add(p);
            }else{
                //Dal momento che la lista è ordinata per orario di messa in onda direttamente nella query al database, se finiamo fuori la fascia oraria possiamo chiudere il ciclo
                break;
            }
        }//Programmi di notte
        for(;count<programmi.size();count++){
            p = programmi.get(count);
            if(FasciaOraria.isContained(p.getOrarioInizio(),FASCIA_ORARIA_NOTTE)){
                programmiDiNotte.add(p);
            }else{
                //Dal momento che la lista è ordinata per orario di messa in onda direttamente nella query al database, se finiamo fuori la fascia oraria possiamo chiudere il ciclo
                break;
            }
        }

        //Alleghiamo le 4 liste alla request
        request.setAttribute("programmiDiMattina", programmiDiMattina);
        request.setAttribute("programmiDiPomeriggio", programmiDiPomeriggio);
        request.setAttribute("programmiDiSera", programmiDiSera);
        request.setAttribute("programmiDiNotte", programmiDiNotte);

    }
}
