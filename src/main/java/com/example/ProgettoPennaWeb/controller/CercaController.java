package com.example.ProgettoPennaWeb.controller;

import com.example.ProgettoPennaWeb.model.Canale;
import com.example.ProgettoPennaWeb.model.ProgrammaTelevisivo;
import com.example.ProgettoPennaWeb.model.comparators.TrasmissioneProgrammaComparator;
import com.example.ProgettoPennaWeb.model.enums.GenereProgramma;
import com.example.ProgettoPennaWeb.model.enums.ParametriDiRicerca;
import com.example.ProgettoPennaWeb.persistenza.dao.CanaleDAO;
import com.example.ProgettoPennaWeb.persistenza.dao.ProgrammaTelevisivoDAO;
import com.example.ProgettoPennaWeb.utility.*;

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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.regex.*;

public class CercaController extends HttpServlet {


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
        String dataTrasmissioneInizio = request.getParameter("data_trasmissione_inizio");
        String dataTrasmissioneFine = request.getParameter("data_trasmissione_fine");
        String fasciaOrariaInizio = request.getParameter("fascia_oraria_inizio");
        String fasciaOrariaFine = request.getParameter("fascia_oraria_fine");
        Map<ProgrammaTelevisivo, Canale> result = null; //risultato della ricerca

        //alleghiamo la lista dei generi selezionabili nel form
        GenereProgramma[] generiValues = GenereProgramma.values();
        String[] generi = new String[generiValues.length -1];

        for (int i = 0; i < generi.length; i++) {
            generi[i] = generiValues[i+1].toString(); //prendiamo i+1 così sostituamo "non assegnato" con "Tutti" tra i valori selezionabili nel form
        }
        request.setAttribute("generi_disponibili", generi);

        //verifichiamo se abbiamo parametri di ricerca in ingresso (abbiamo avviato la ricerca con almeno un campo riempito oppure siamo arrivati da una ricerca salvata)
        if ((titolo != null && !titolo.isEmpty()) || (genere != null && !genere.isEmpty()) || (numeroCanale != null && !numeroCanale.isEmpty())
                || (dataTrasmissioneInizio != null && !dataTrasmissioneInizio.isEmpty()) || (dataTrasmissioneFine != null && !dataTrasmissioneFine.isEmpty())
                || (fasciaOrariaInizio != null && !fasciaOrariaInizio.isEmpty()) || (fasciaOrariaFine != null && !fasciaOrariaFine.isEmpty())) {
            //segnalo al .jsp che ci sono parametri in ingresso (lo uso per decidere se mostrare o meno il link che permette il salvataggio di una ricerca)
            request.setAttribute("esistono_parametri", true);
            //Compiliamo una lista dei parametri in ingresso
            Ricerca parametriDiRicerca = new Ricerca();
            //Per il titolo controlliamo che non siano stato inseriti caratteri speciali (nel caso javascript sia disattivato o la validazione sia raggirata)
            if (titolo != null && !titolo.isEmpty()) {
                parametriDiRicerca.setTitolo(SecurityLayer.addSlashes(titolo));
            }
                if (genere != null && !genere.isEmpty()) {
                    parametriDiRicerca.setGenere(GenereProgramma.fromString(genere));
                }
                    if (numeroCanale != null && !numeroCanale.isEmpty()) {
                        parametriDiRicerca.setNumeroCanale(Short.parseShort(numeroCanale));
                    }
                    if (dataTrasmissioneInizio != null && dataTrasmissioneFine != null && !dataTrasmissioneInizio.isEmpty() && !dataTrasmissioneFine.isEmpty()) {
                        parametriDiRicerca.setDataTrasmissione(new LocalDate[]{LocalDate.parse(dataTrasmissioneInizio), LocalDate.parse(dataTrasmissioneFine)});
                    }
                    if (fasciaOrariaInizio != null && fasciaOrariaFine != null && !fasciaOrariaInizio.isEmpty() && !fasciaOrariaFine.isEmpty()) {
                        try {
                            //Chiamo FasciaOraria.formatOrario per aggiustare stringhe del tipo 0:13 o 12:7 che solleverebbero eccezioni a 00:13 e 12:07
                            parametriDiRicerca.setFasciaOraria(FasciaOraria.encode(LocalTime.parse(FasciaOraria.formatOrario(fasciaOrariaInizio)),
                                    LocalTime.parse(FasciaOraria.formatOrario(fasciaOrariaFine))));
                        } catch (MalformedFasciaOrariaException mfoe) {
                            request.setAttribute("exception", mfoe);
                            ErrorHandling.handleError(request, response);
                            return;
                        }
                    }
                    //allego la lista alla request
                    request.setAttribute("parametri_di_ricerca", parametriDiRicerca);
                    //ricerchiamo sul database i risultati, limitandoci ai primi 30 risultati
                    try {
                        result = search(request, response);

                    } catch (MalformedFasciaOrariaException mfoe) {
                        request.setAttribute("exception", mfoe);
                        ErrorHandling.handleError(request, response);
                        return;
                    } catch (SQLException sqe) {
                        request.setAttribute("exception", sqe);
                        ErrorHandling.handleError(request, response);
                        return;
                    } catch (NamingException ne) {
                        request.setAttribute("exception",ne);
                        ErrorHandling.handleError(request, response);
                        return;
                    }
        } else {//default senza parametri
                    //Mostra i primi 30 programmi presi dal database, ordinati rispetto all'ora di inizio (anche giorno di trasmissione se specificato nel form).
                    try {
                        result = defaultSearch(request, response);
                    } catch (MalformedFasciaOrariaException mfoe) {
                        request.setAttribute("exception", mfoe);
                        ErrorHandling.handleError(request, response);
                        return;
                    } catch (SQLException sqe) {
                        request.setAttribute("exception", sqe);
                        ErrorHandling.handleError(request, response);
                        return;
                    } catch (NamingException ne) {
                        request.setAttribute("exception", ne);
                        ErrorHandling.handleError(request, response);
                        return;
                    }
        }

                //alleghiamo i dati dei programmi e dei canali alla request

                request.setAttribute("risultato_ricerca", result);

                //DISPATCH AL JSP
                ServletContext context = getServletContext();
                RequestDispatcher dispatcher = context.getRequestDispatcher("/cerca.jsp");
                try {
                    dispatcher.forward(request, response);
                } catch (IOException ie) {
                    request.setAttribute("exception", ie);
                    ErrorHandling.handleError(request, response);
                    return;

                } catch (ServletException se) {
                    request.setAttribute("exception", se);
                    ErrorHandling.handleError(request, response);
                    return;
                } catch (Exception ex) {
                    request.setAttribute("exception", ex);
                    ErrorHandling.handleError(request, response);
                    return;

                }
            }

            private Map<ProgrammaTelevisivo, Canale> defaultSearch (HttpServletRequest request, HttpServletResponse
            response) throws MalformedFasciaOrariaException, SQLException, NamingException {
                final int MAX_PROGRAMMI_IN_SEARCH_RESULT = Integer.parseInt(getServletContext().getInitParameter("MAX_PROGRAMMI_IN_SEARCH_RESULT"));
                ProgrammaTelevisivoDAO programmaDao = new ProgrammaTelevisivoDAO();
                CanaleDAO canaleDAO = new CanaleDAO();

                List<ProgrammaTelevisivo> programmi = programmaDao.getAll(MAX_PROGRAMMI_IN_SEARCH_RESULT);
                List<Canale> canaliList = canaleDAO.getAll();
                Canale[] canali = canaliList.toArray(new Canale[canaliList.size()]);

                Map<ProgrammaTelevisivo, Canale> result = new TreeMap<>();

                for (ProgrammaTelevisivo p : programmi) {

                    result.put(p, canali[(int) p.getIdCanale()-1]);//gli id partono da 1 in DB
                }
                return result;

            }

            private Map<ProgrammaTelevisivo, Canale> search (HttpServletRequest request, HttpServletResponse response) throws
            MalformedFasciaOrariaException, SQLException, NamingException {
                final int MAX_PROGRAMMI_IN_SEARCH_RESULT = Integer.parseInt(getServletContext().getInitParameter("MAX_PROGRAMMI_IN_SEARCH_RESULT"));
                Ricerca parametriDiRicerca = (Ricerca) request.getAttribute("parametri_di_ricerca");
                if (parametriDiRicerca == null) {
                    throw new NullPointerException("parametri di ricerca sono null");
                }
                //Ricaviamo i dati
                ProgrammaTelevisivoDAO programmaDao = new ProgrammaTelevisivoDAO();
                CanaleDAO canaleDAO = new CanaleDAO();

                List<ProgrammaTelevisivo> programmi = programmaDao.getBySearchParameters(parametriDiRicerca, MAX_PROGRAMMI_IN_SEARCH_RESULT);
                System.out.println("programmi trovati: "+programmi.size());
                List<Canale> canaliList = canaleDAO.getAll();
                Canale[] canali = canaliList.toArray(new Canale[canaliList.size()]);

                Map<ProgrammaTelevisivo, Canale> result = new TreeMap<>();

                for (ProgrammaTelevisivo p : programmi) {

                    result.put(p, canali[(int) p.getIdCanale()-1]); //gli id partono da 1 in DB
                }

                //Filtriamo (Dovrebbe essere stato già filtrato dalla query al DB (vedi ProgrammaDAO.getBySearchParameters))
                /*for (ParametriDiRicerca parametro : ParametriDiRicerca.values()) {
                    Map<ProgrammaTelevisivo,Canale> temp = new TreeMap<>();
                    switch (parametro) {
                        case TITOLO_PROGRAMMA:
                            if (parametriDiRicerca.getTitolo() != null) {
                                //Filtriamo per titolo
                                //Creamo il pattern
                                Pattern titoloRegex = Pattern.compile(".*" + parametriDiRicerca.getTitolo() + ".*");
                                //iteriamo sul result corrente per filtrare
                                for (Map.Entry<ProgrammaTelevisivo,Canale> entry : result.entrySet()) {
                                    //creamo il matcher dell'espressione regolare
                                    Matcher titoloMatcher = titoloRegex.matcher(entry.getKey().getTitolo());
                                    if (titoloMatcher.matches()) {
                                        temp.put(entry.getKey(), entry.getValue());
                                    }
                                }
                                result = temp;
                            }

                            break;
                        case GENERE_PROGRAMMA:
                            if (parametriDiRicerca.getGenere() != null) {
                                //Filtriamo per genere
                                GenereProgramma genereSelezionato = parametriDiRicerca.getGenere();
                                if (GenereProgramma.TUTTI.equals(genereSelezionato)) {
                                    break;
                                }
                                for (Map.Entry<ProgrammaTelevisivo,Canale> entry : result.entrySet()) {
                                    if (entry.getKey().getGenere().equals(genereSelezionato)) {
                                        temp.put(entry.getKey(), entry.getValue());
                                    }
                                }
                                result = temp;
                            }

                            break;
                        case NUMERO_CANALE:
                            if(parametriDiRicerca.getNumeroCanale()!=null){
                                for(Map.Entry<ProgrammaTelevisivo,Canale> entry : result.entrySet()){
                                    if(entry.getValue().getNumero().equals(parametriDiRicerca.getNumeroCanale())){
                                        temp.put(entry.getKey(), entry.getValue());
                                    }
                                }
                                result = temp;
                            }
                            break;
                        case DATA_TRASMISSIONE:
                            if(parametriDiRicerca.getDataTrasmissione() != null){
                                for(Map.Entry<ProgrammaTelevisivo,Canale> entry : result.entrySet()){
                                    boolean isAfterStart = entry.getKey().getDataTrasmissione().isAfter(parametriDiRicerca.getDataTrasmissione()[0]);
                                    boolean isBeforeEnd = entry.getKey().getDataTrasmissione().isBefore(parametriDiRicerca.getDataTrasmissione()[1]);
                                    if(isAfterStart && isBeforeEnd){
                                        temp.put(entry.getKey(), entry.getValue());
                                    }
                                }
                                result = temp;
                            }
                            break;
                        case FASCIA_ORARIA:
                            if(parametriDiRicerca.getFasciaOraria() != null){
                                for(Map.Entry<ProgrammaTelevisivo,Canale> entry : result.entrySet()){
                                    LocalTime[] fasciaOraria = FasciaOraria.decode(parametriDiRicerca.getFasciaOraria());
                                    boolean isAfterStart = entry.getKey().getOrarioInizio().isAfter(fasciaOraria[0]);
                                    boolean isBeforeEnd = entry.getKey().getOrarioInizio().isBefore(fasciaOraria[1]);
                                    if(isAfterStart && isBeforeEnd){
                                        temp.put(entry.getKey(), entry.getValue());
                                    }
                                }
                                result = temp;
                            }
                            break;
                    }
                }*/

            return result;

            }
        }

