package com.example.ProgettoPennaWeb.controller;

import com.example.ProgettoPennaWeb.model.ProgrammaTelevisivo;
import com.example.ProgettoPennaWeb.model.enums.GenereProgramma;
import com.example.ProgettoPennaWeb.model.utility.FasciaOraria;
import com.example.ProgettoPennaWeb.model.utility.MalformedFasciaOrariaException;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CercaController extends HttpServlet {

    private List<ProgrammaTelevisivo> programmi = new ArrayList<ProgrammaTelevisivo>(20);

    @Override
    public void init() throws ServletException {
        super.init();
        //Genero dei programmi fasulli per il testing (Da rimuovere dopo aver implementato il Dao)
        Random r = new Random();
        String[] names = new String[]{"Programma1", "Programma2", "Programma3", "Programma4"};
        String[] descriptions = new String[]{"ass","gtrhrg","efuweoad","aaaaaaaaaaaaaaaaaaaaaaaaaaaaa","ooooooooooooooooooooooooo"};
        for(int i=0; i < 20; i++) {
            LocalTime curr_fine = LocalTime.of(r.nextInt(24), r.nextInt(60));
            LocalTime curr_inizio = LocalTime.of(r.nextInt(curr_fine.getHour()), r.nextInt(60));
            ProgrammaTelevisivo p = new ProgrammaTelevisivo(
                    r.nextLong(),
                    names[r.nextInt(names.length)],
                    GenereProgramma.values()[r.nextInt(GenereProgramma.values().length)],
                    descriptions[r.nextInt(descriptions.length)],
                    LocalDate.now(),
                    curr_inizio,
                    curr_fine);
            programmi.add(p);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response){
        String titolo=request.getParameter("titolo");
        String genere=request.getParameter("genere");
        String numeroCanale=request.getParameter("numero_canale");
        String fasciaOraria=request.getParameter("fascia_oraria");
        boolean cercaAltriGiorni = request.getParameter("cerca_altri_giorni")!=null;
        //verifichiamo se abbiamo parametri di ricerca in ingresso (abbiamo avviato la ricerca oppure siamo arrivati da una ricerca salvata)
        if(titolo!=null||genere!=null||numeroCanale!=null||fasciaOraria!=null){
            //riempiamo il form con i parametri forniti
            //compileForm(request,response);
            //ricerchiamo sul database i risultati
            //search(request,response);
        }else{
            //Mostra i primi 15 programmi presi dal database, ordinati rispetto al canale (quindi Rai1 sarà il primo) e poi rispetto all'ora di inizio (anche giorno di trasmissione se specificato nel form).
            try {
                defaultSearch(request,response);
            } catch (MalformedFasciaOrariaException mfoe) {
                System.err.println(mfoe.getMessage());
                mfoe.printStackTrace();
            }
        }
    }

    private List<ProgrammaTelevisivo> defaultSearch(HttpServletRequest request, HttpServletResponse response) throws MalformedFasciaOrariaException {
        //Ritorna una lista fasulla (vedi metodo Init()), da rimuovere quando implementiamo il dao
        return programmi;

        //ProgrammaTelevisivoDAO dao = new ProgrammaTelevisivoDAO();
        //...
    }

    private List<ProgrammaTelevisivo> search(HttpServletRequest request, HttpServletResponse response) throws MalformedFasciaOrariaException {

        //INSERIRE QUI LA RICERCA CON IL DAO
        //ProgrammaTelevisivoDAO dao = new ProgrammaTelevisivoDAO();
        //...

        //codice stub da sostituire con il DAO
        List<ProgrammaTelevisivo> programmi = new ArrayList<>();
        String fasciaOraria=request.getParameter("fascia_oraria");
        LocalTime inizio;
        LocalTime fine;
        if(fasciaOraria!=null) {
            LocalTime[] fasciaOrariaDecodificata = FasciaOraria.decode(fasciaOraria);
            inizio = fasciaOrariaDecodificata[0];
            fine = fasciaOrariaDecodificata[1];
        }
        /*if("TG1".equalsIgnoreCase(request.getParameter("titolo"))
                ||GenereProgramma.NEWS.toString().equalsIgnoreCase(request.getParameter("genere"))
                ||1==Integer.parseInt(request.getParameter("numero_canale"))
                ||LocalTime.of(8,0).compareTo(inizio)){
            programmi.add(new ProgrammaTelevisivo(0,"Tg1", GenereProgramma.NEWS,"Informazione delle ore 8:00", LocalDate.now(), LocalTime.of(8,0), LocalTime.of(8,15)));
        }*/
        return programmi;
    }
}
