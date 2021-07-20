package com.example.ProgettoPennaWeb.controller;

import com.example.ProgettoPennaWeb.model.Canale;
import com.example.ProgettoPennaWeb.model.ProgrammaTelevisivo;
import com.example.ProgettoPennaWeb.model.comparators.TrasmissioneProgrammaComparator;
import com.example.ProgettoPennaWeb.model.enums.GenereProgramma;
import com.example.ProgettoPennaWeb.model.utility.MalformedFasciaOrariaException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.regex.*;

public class CercaController extends HttpServlet {
    private final Map<String, Object> data = new TreeMap<>();
    private final List<Canale> canali = new ArrayList<>(2);
    private final List<ProgrammaTelevisivo> programmi = new ArrayList<ProgrammaTelevisivo>(20);
    private final Map<ProgrammaTelevisivo, Canale> canaleDiUnProgramma = new TreeMap<>();

    @Override
    public void init() throws ServletException {
        super.init();
        //canali
        Canale c1 = new Canale();
        c1.setId(0);
        c1.setNome("Rai1");
        c1.setNumero((short) 1);
        Canale c2 = new Canale();
        c2.setId(1);
        c2.setNome("Rai2");
        c1.setNumero((short) 2);
        canali.add(c1);
        canali.add(c2);
        //Genero dei programmi fasulli per il testing (Da rimuovere dopo aver implementato il Dao)
        Random r = new Random();
        String[] names = new String[]{"Programma1", "Programma2", "Programma3", "Programma4"};
        String[] descriptions = new String[]{"ass", "gtrhrg", "efuweoad", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "ooooooooooooooooooooooooo"};

        for (int i = 0; i < 20; i++) {
            LocalTime curr_fine = LocalTime.of(r.nextInt(24), r.nextInt(60));
            int bound = curr_fine.getHour() == 0 ? 23 : curr_fine.getHour();
            LocalTime curr_inizio = LocalTime.of(r.nextInt(bound), r.nextInt(60));
            Canale canaleAssegnato = canali.get(r.nextInt(canali.size()));
            ProgrammaTelevisivo p = new ProgrammaTelevisivo(
                    r.nextLong(),
                    names[r.nextInt(names.length)],
                    GenereProgramma.values()[r.nextInt(GenereProgramma.values().length)],
                    canaleAssegnato.getId(),
                    descriptions[r.nextInt(descriptions.length)],
                    LocalDate.now(),
                    curr_inizio,
                    curr_fine);
            programmi.add(p);
            canaleDiUnProgramma.put(p, canaleAssegnato); //associo il programma ad un canale casuale

        }
        programmi.sort(new TrasmissioneProgrammaComparator());

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //parametri di ricerca
        String titolo = request.getParameter("titolo");
        String genere = request.getParameter("genere");
        String numeroCanale = request.getParameter("numero_canale");
        String fasciaOraria = request.getParameter("fascia_oraria");
        Boolean cercaAltriGiorni = request.getParameter("cerca_altri_giorni") != null;
        List<ProgrammaTelevisivo> result = null; //risultato della ricerca

        //alleghiamo la lista dei generi selezionabili nel form
        GenereProgramma[] generiValues = GenereProgramma.values();
        String[] generi = new String[generiValues.length];
        generi[0] = "Tutti";
        for (int i = 1; i < generi.length; i++) {
            generi[i] = generiValues[i].toString(); //prendiamo i+1 così sostituamo "non assegnato" con "Tutti" tra i valori selezionabili nel form
        }
        request.setAttribute("generi_disponibili", generi);

        //verifichiamo se abbiamo parametri di ricerca in ingresso (abbiamo avviato la ricerca oppure siamo arrivati da una ricerca salvata)
        if (titolo != null || genere != null || numeroCanale != null || fasciaOraria != null) {
            //Compiliamo una lista dei parametri in ingresso
            Map<String, String> parametriDiRicerca = new TreeMap<>();
            if (titolo != null && !titolo.isEmpty()) {
                parametriDiRicerca.put("titolo", titolo);
            }else{
                parametriDiRicerca.put("titolo", null);
            }
            if (genere != null && !genere.isEmpty()) {
                parametriDiRicerca.put("genere", genere);
            }else{
                parametriDiRicerca.put("genere", null);

            }
            if (numeroCanale != null && !numeroCanale.isEmpty()) {
                parametriDiRicerca.put("numero_canale", numeroCanale);
            }else{
                parametriDiRicerca.put("numero_canale", null);
            }
            if (fasciaOraria != null && !"-".equals(fasciaOraria)) {
                parametriDiRicerca.put("fascia_oraria", fasciaOraria);
            }else{
                parametriDiRicerca.put("fascia_oraria", null);

            }
            parametriDiRicerca.put("cerca_altri_giorni", cercaAltriGiorni.toString());
            //allego la lista alla request
            request.setAttribute("parametri_di_ricerca", parametriDiRicerca);
            //riempiamo il form con i parametri forniti
            //compileForm(request,response);
            //ricerchiamo sul database i risultati (AD ORA USIAMO LISTE GENERATE)
            try {
                result = search(request, response);

            } catch (MalformedFasciaOrariaException mfoe) {
                System.err.println(mfoe.getMessage());
                mfoe.printStackTrace();
            }
        } else {//default senza parametri
            //Mostra i primi 15 programmi presi dal database, ordinati rispetto all'ora di inizio (anche giorno di trasmissione se specificato nel form).
            try {
                result = defaultSearch(request, response);
            } catch (MalformedFasciaOrariaException mfoe) {
                System.err.println(mfoe.getMessage());
                mfoe.printStackTrace();
            }
        }

        //alleghiamo i dati dei programmi e dei canali alla request
        data.put("risultati_ricerca", result);
        data.put("canali", canali);
        data.put("canale_di_un_programma", canaleDiUnProgramma);
        request.setAttribute("data", data);

        //DISPATCH AL JSP
        ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher("/cerca.jsp");
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

    private List<ProgrammaTelevisivo> defaultSearch(HttpServletRequest request, HttpServletResponse response) throws MalformedFasciaOrariaException {
        final int MAX_PROGRAMMI_IN_SEARCH_RESULT = 15; //Integer.parseInt(getInitParameter("MAX_PROGRAMMI_IN_SEARCH_RESULT"));
        //Ritorna una lista fasulla (vedi metodo Init()), da rimuovere quando implementiamo il dao
        List<ProgrammaTelevisivo> result = programmi.subList(0, MAX_PROGRAMMI_IN_SEARCH_RESULT - 1);
        return result;

        //ProgrammaTelevisivoDAO dao = new ProgrammaTelevisivoDAO();
        //...
    }

    private List<ProgrammaTelevisivo> search(HttpServletRequest request, HttpServletResponse response) throws MalformedFasciaOrariaException {
        final int MAX_PROGRAMMI_IN_SEARCH_RESULT = 15; //Integer.parseInt(getInitParameter("MAX_PROGRAMMI_IN_SEARCH_RESULT"));
        Map<String,String> parametriDiRicerca = (Map<String,String>) request.getAttribute("parametri_di_ricerca");
        if(parametriDiRicerca==null){
            throw new NullPointerException("parametri di ricerca sono null");
        }
        //INSERIRE QUI LA RICERCA CON IL DAO
        //ProgrammaTelevisivoDAO dao = new ProgrammaTelevisivoDAO();
        //...

        //codice stub da sostituire con il DAO
        List<ProgrammaTelevisivo> result = new ArrayList(programmi);

        for(Map.Entry<String,String> entry : parametriDiRicerca.entrySet()){
            List<ProgrammaTelevisivo> temp = new ArrayList();
            switch(entry.getKey()){
                case "titolo":
                    if (entry.getValue()!=null){
                        //Filtriamo per titolo
                        //Creamo il pattern
                        Pattern titoloRegex = Pattern.compile(".*"+entry.getValue()+".*");
                        //iteriamo sul result corrente per filtrare
                        for(ProgrammaTelevisivo p : result){
                            //creamo il matcher dell'espressione regolare
                            Matcher titoloMatcher = titoloRegex.matcher(p.getTitolo());
                            if(titoloMatcher.matches()){
                                temp.add(p);
                            }
                        }
                        result = temp;
                    }

                    break;
                case "genere":
                    if (entry.getValue()!=null){
                        //Filtriamo per genere
                        String genereSelezionato = entry.getValue();
                        if("Tutti".equalsIgnoreCase(genereSelezionato)){
                            break;
                        }
                        for(ProgrammaTelevisivo p : result){
                            if(p.getGenere().toString().equalsIgnoreCase(genereSelezionato)){
                                temp.add(p);
                            }
                        }
                        result = temp;
                    }

                    break;
                /*case "numero_canale":
                    if(entry.getValue()!=null){
                        for(ProgrammaTelevisivo p : result){
                            if(p.){
                                temp.add(p);
                            }
                        }
                    }

                    break;*/
            }
        }

        /*if("TG1".equalsIgnoreCase(request.getParameter("titolo"))
                ||GenereProgramma.NEWS.toString().equalsIgnoreCase(request.getParameter("genere"))
                ||1==Integer.parseInt(request.getParameter("numero_canale"))
                ||LocalTime.of(8,0).compareTo(inizio)){
            programmi.add(new ProgrammaTelevisivo(0,"Tg1", GenereProgramma.NEWS,"Informazione delle ore 8:00", LocalDate.now(), LocalTime.of(8,0), LocalTime.of(8,15)));
        }*/
        return result;
    }
}
