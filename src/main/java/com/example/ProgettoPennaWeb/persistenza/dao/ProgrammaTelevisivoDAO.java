package com.example.ProgettoPennaWeb.persistenza.dao;

import com.example.ProgettoPennaWeb.model.ProgrammaTelevisivo;
import com.example.ProgettoPennaWeb.model.enums.GenereProgramma;
import com.example.ProgettoPennaWeb.model.enums.ParametriDiRicerca;
import com.example.ProgettoPennaWeb.utility.FasciaOraria;
import com.example.ProgettoPennaWeb.utility.MalformedFasciaOrariaException;
import com.example.ProgettoPennaWeb.utility.Ricerca;
import com.example.ProgettoPennaWeb.persistenza.DatabaseManager;

import javax.naming.NamingException;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class ProgrammaTelevisivoDAO {

    private final String INSERT_QUERY="Insert into pennaweb.programma_televisivo values(?,?,?,?,?,?,?,?,?,?,?,?)";
    private final String SELECT_BY_ID_QUERY = "select * from pennaweb.programma_televisivo WHERE ID = ?";
    private final String SELECT_BY_ID_JOIN_CANALE_QUERY = "select * from pennaweb.programma_televisivo JOIN pennaweb.p ON programma_televisivo.p = p.ID WHERE ID = ?";
    private final String SELECT_BY_CANALE_TODAY_QUERY = "select * from pennaweb.programma_televisivo join pennaweb.canale on programma_televisivo.canale = canale.ID where canale = ? and data_trasmissione = ? order by ora_inizio";
    private final String SELECT_ALL_QUERY = "select * from pennaweb.programma_televisivo";
    private final String UPDATE_BY_ID_QUERY = "Update pennaweb.programma_televisivo SET ID = ?, genere = ?, canale = ?, descrizione = ?, data_trasmissione = ?, ora_inizio = ?, ora_fine = ?, url_immagine = ?, " +
            "url_approfondimento =?, stagione = ?, episodio =? where ID = ?";
    private final String DELETE_BY_ID_QUERY = "Delete from pennaweb.programma_televisivo where ID = ?";


    //Query di select usata nella pagina di ricerca: viene costruita dinamicamente in base ai parametri di ricerca usati
    private final String SELECT_SEARCH_PAGE_QUERY_BASE = "select * from pennaweb.programma_televisivo join pennaweb.canale on programma_televisivo.canale = canale.ID";
    //mappa con gli spezzoni della clausola WHERE della query
    private final Map<ParametriDiRicerca,String> SELECT_SEARCH_PAGE_QUERY_WHERE_CLAUSES = new TreeMap<>(){{
        put(ParametriDiRicerca.TITOLO_PROGRAMMA, "programma_televisivo.titolo LIKE ?");
        put(ParametriDiRicerca.GENERE_PROGRAMMA, "programma_televisivo.genere = ?");
        put(ParametriDiRicerca.NUMERO_CANALE, "canale.numero = ?");
        put(ParametriDiRicerca.FASCIA_ORARIA, "programma_televisivo.ora_inizio >= ? and programma_televisivo.ora_inizio <= ?");
        put(ParametriDiRicerca.CERCA_ALTRI_GIORNI, "programma_televisivo.data_trasmissione = ?");
    }};


    public Optional<ProgrammaTelevisivo> getCanaleById(long id) throws SQLException, NamingException {
        try (Connection con = DatabaseManager.getInstance().getConnection();
             PreparedStatement st = con.prepareStatement(SELECT_BY_ID_QUERY)) {
            st.setLong(1, id);

            try (ResultSet resultSet = st.executeQuery()) {
                if (resultSet.next()) {
                    ProgrammaTelevisivo p = new ProgrammaTelevisivo();
                    p.setId(resultSet.getLong("ID"));
                    p.setTitolo(resultSet.getString("titolo"));
                    //ricaviamo il genere dalla rappresentazione in stringa
                    p.setGenere(GenereProgramma.fromString(resultSet.getString("genere")));
                    p.setDescrizione(resultSet.getString("descrizione"));
                    //Convertiamo da sql.Date a LocalDate
                    Date date = resultSet.getDate("data_trasmissione");
                    LocalDate localD = date.toLocalDate();
                    p.setDataTrasmissione(localD);
                    //Convertiamo da sql.Time a LocalTime
                    Time time = resultSet.getTime("ora_inizio");
                    LocalTime localT = time.toLocalTime().truncatedTo(ChronoUnit.MINUTES); //tronco via i secondi
                    p.setOrarioInizio(localT);
                    time = resultSet.getTime("ora_fine");
                    localT = time.toLocalTime().truncatedTo(ChronoUnit.MINUTES); //tronco via i secondi
                    p.setOrarioFine(localT);
                    p.setUrlRelativoImmagine(resultSet.getString("url_immagine"));
                    p.setUrlApprofondimento(resultSet.getURL("url_approfondimento"));
                    p.setStagione(resultSet.getShort("stagione"));
                    p.setEpisodio(resultSet.getShort("episodio"));
                    p.setIdCanale(resultSet.getLong("canale"));
                    return Optional.of(p);
                } else {
                    return Optional.empty();
                }

            }
        }
    }

    public List<ProgrammaTelevisivo> getByCanale(long idCanale, boolean soloProgrammiOdierni) throws NamingException, SQLException {
        try (Connection con = DatabaseManager.getInstance().getConnection()){
            List<ProgrammaTelevisivo> risultato = new ArrayList<>();
             if(soloProgrammiOdierni){
             try(PreparedStatement st = con.prepareStatement(SELECT_BY_CANALE_TODAY_QUERY)){
                st.setLong(1,idCanale);
                st.setDate(2,Date.valueOf(LocalDate.now()));
                try(ResultSet resultSet = st.executeQuery()){
                    while (resultSet.next()) {
                        ProgrammaTelevisivo p = new ProgrammaTelevisivo();
                        p.setId(resultSet.getLong("ID"));
                        p.setTitolo(resultSet.getString("titolo"));
                        //ricaviamo il genere dalla rappresentazione in stringa
                        p.setGenere(GenereProgramma.fromString(resultSet.getString("genere")));
                        p.setDescrizione(resultSet.getString("descrizione"));
                        //Convertiamo da sql.Date a LocalDate
                        Date date = resultSet.getDate("data_trasmissione");
                        LocalDate localD = date.toLocalDate();
                        p.setDataTrasmissione(localD);
                        //Convertiamo da sql.Time a LocalTime
                        Time time = resultSet.getTime("ora_inizio");
                        LocalTime localT = time.toLocalTime().truncatedTo(ChronoUnit.MINUTES); //tronco via i secondi
                        p.setOrarioInizio(localT);
                        time = resultSet.getTime("ora_fine");
                        localT = time.toLocalTime().truncatedTo(ChronoUnit.MINUTES); //tronco via i secondi
                        p.setOrarioFine(localT);
                        p.setUrlRelativoImmagine(resultSet.getString("url_immagine"));
                        p.setUrlApprofondimento(resultSet.getURL("url_approfondimento"));
                        p.setStagione(resultSet.getShort("stagione"));
                        p.setEpisodio(resultSet.getShort("episodio"));
                        p.setIdCanale(resultSet.getLong("canale"));
                        risultato.add(p);
                    }

                }
             }
            }else{
                 /*try(PreparedStatement st = con.prepareStatement(SELECT_BY_CANALE_ALL_DAYS_QUERY)){

                 }*/
             }
             return risultato;
        }
    }

    /**
     * Ritorna i canali che soddisfano i parametri passati.
     * @param params i parametri di ricerca
     * @return una lista (eventualmente vuota) che contiene tutti i programmi televisivi che hanno soddisfatto i parametri di ricerca.
     */
    public List<ProgrammaTelevisivo> getBySearchParameters(Ricerca params) throws NamingException, SQLException, MalformedFasciaOrariaException {
        //Costruiamo la query al database in base ai parametri passati.
        int parametriRimanenti = params.getParametriSettati();
        StringBuilder sb = new StringBuilder(SELECT_SEARCH_PAGE_QUERY_BASE);
        sb.append(" where"); //appendo la clausola where
        //titolo programma
        String titolo = params.getTitolo();
        if(titolo!=null){
            sb.append(" ");
            sb.append(SELECT_SEARCH_PAGE_QUERY_WHERE_CLAUSES.get(ParametriDiRicerca.TITOLO_PROGRAMMA));
            if(parametriRimanenti > 0){
                sb.append(" and");
                parametriRimanenti--;
            }
        }

        //Genere programma
        GenereProgramma genere = params.getGenere();
        if(genere!=null){
            sb.append(" ");
            sb.append(SELECT_SEARCH_PAGE_QUERY_WHERE_CLAUSES.get(ParametriDiRicerca.GENERE_PROGRAMMA));
            if(parametriRimanenti > 0){
                sb.append(" and");
                parametriRimanenti--;
            }
        }

        //Numero canale
        Short numeroCanale = params.getNumeroCanale();
        if(numeroCanale!=null){
            sb.append(" ");
            sb.append(SELECT_SEARCH_PAGE_QUERY_WHERE_CLAUSES.get(ParametriDiRicerca.NUMERO_CANALE));
            if(parametriRimanenti > 0){
                sb.append(" and");
                parametriRimanenti--;
            }
        }

        //Fascia oraria
        String fasciaOraria = params.getFasciaOraria();
        if(fasciaOraria!=null){
            sb.append(" ");
            sb.append(SELECT_SEARCH_PAGE_QUERY_WHERE_CLAUSES.get(ParametriDiRicerca.FASCIA_ORARIA));

            //"and" nella query viene aggiunto dal controllo sul flag "cercaAltriGiorni" se serve
        }

        //Flag per ottenere anche i programmi degli altri giorni
        Boolean cercaAltriGiorni = params.getCercaAltriGiorni();
        if(cercaAltriGiorni){
            sb.append(" and ");
            sb.append(SELECT_SEARCH_PAGE_QUERY_WHERE_CLAUSES.get(ParametriDiRicerca.CERCA_ALTRI_GIORNI));
        }


        //Ora possiamo procedere con la preparazione dello statement
        String queryCompleta = sb.toString();
        try(Connection con = DatabaseManager.getInstance().getConnection();
        PreparedStatement st = con.prepareStatement(queryCompleta)){
            //Ora settiamo i parametri giusti
            int nextParameter = 1;
            if(titolo!=null){
                st.setString(nextParameter++,titolo);
            }
            if(genere!=null){
                st.setString(nextParameter++,genere.toString());
            }
            if(numeroCanale!=null){
                st.setShort(nextParameter++,numeroCanale);
            }
            if(fasciaOraria!=null){
                //decodifichiamo, convertiamo e settiamo
                LocalTime[] fascia = FasciaOraria.decode(fasciaOraria);
                Time t = Time.valueOf(fascia[0]);
                st.setTime(nextParameter++, t);
                t = Time.valueOf(fascia[1]);
                st.setTime(nextParameter++, t);
            }
            //Se l'utente vuole solo i programmi odierni
            if(!cercaAltriGiorni){
                //settiamo la data di oggi per parametro
                st.setDate(nextParameter++, Date.valueOf(LocalDate.now()));
            }

            //Finalmente possiamo eseguire la query
            try(ResultSet resultSet = st.executeQuery()){

            }
        }
        return null;
    }

    public List<ProgrammaTelevisivo> getAll() throws SQLException, NamingException{
        try(Connection con = DatabaseManager.getInstance().getConnection();
            PreparedStatement st = con.prepareStatement(SELECT_ALL_QUERY);
            ResultSet resultSet = st.executeQuery()) {

            List<ProgrammaTelevisivo> risultato = new ArrayList<>();
            while (resultSet.next()) {
                ProgrammaTelevisivo p = new ProgrammaTelevisivo();
                p.setId(resultSet.getLong("ID"));
                p.setTitolo(resultSet.getString("titolo"));
                //ricaviamo il genere dalla rappresentazione in stringa
                p.setGenere(GenereProgramma.fromString(resultSet.getString("genere")));
                p.setDescrizione(resultSet.getString("descrizione"));
                //Convertiamo da sql.Date a LocalDate
                Date date = resultSet.getDate("data_trasmissione");
                LocalDate localD = date.toLocalDate();
                p.setDataTrasmissione(localD);
                //Convertiamo da sql.Time a LocalTime
                Time time = resultSet.getTime("ora_inizio");
                LocalTime localT = time.toLocalTime().truncatedTo(ChronoUnit.MINUTES); //tronco via i secondi
                p.setOrarioInizio(localT);
                time = resultSet.getTime("ora_fine");
                localT = time.toLocalTime().truncatedTo(ChronoUnit.MINUTES); //tronco via i secondi
                p.setOrarioFine(localT);
                p.setUrlRelativoImmagine(resultSet.getString("url_immagine"));
                p.setUrlApprofondimento(resultSet.getURL("url_approfondimento"));
                p.setStagione(resultSet.getShort("stagione"));
                p.setEpisodio(resultSet.getShort("episodio"));
                p.setIdCanale(resultSet.getLong("canale"));
                risultato.add(p);
            }
            return risultato;
        }
    }

    public void save(ProgrammaTelevisivo p) throws SQLException, NamingException{
        try(Connection con = DatabaseManager.getInstance().getConnection();
            PreparedStatement st = con.prepareStatement(INSERT_QUERY)) {
            st.setLong(1, p.getId()); //id
            st.setString(2, p.getTitolo()); //titolo
            st.setString(3, p.getGenere().toString()); //genere
            st.setString(4, p.getDescrizione()); //descrizione
            //Conversione da LocalDate a sql.Date
            Date dateConversion = Date.valueOf(p.getDataTrasmissione());
            st.setDate(5, dateConversion); //data di trasmissione
            //Conversione da LocalTime a sql.Time
            Time timeConversion = Time.valueOf(p.getOrarioInizio());
            st.setTime(6,timeConversion); //ora inizio
            timeConversion = Time.valueOf(p.getOrarioFine());
            st.setTime(7,timeConversion); //ora fine
            st.setString(8,p.getUrlRelativoImmagine()); //url relativo immagine
            st.setURL(9,p.getUrlApprofondimento()); //url assoluto all'approfondimento
            st.setShort(10,p.getStagione()); //stagione (0 se non è una serie)
            st.setShort(11, p.getEpisodio()); //episodio (0 se non è una serie)
            st.setLong(12,p.getIdCanale()); //canale su cui viene trasmesso

            st.executeUpdate();
        }
    }

    public void update(ProgrammaTelevisivo p, long id) throws SQLException, NamingException{
        try(Connection con = DatabaseManager.getInstance().getConnection();
            PreparedStatement st = con.prepareStatement(UPDATE_BY_ID_QUERY)){
            st.setLong(1, p.getId()); //id
            st.setString(2, p.getTitolo()); //titolo
            st.setString(3, p.getGenere().toString()); //genere
            st.setString(4, p.getDescrizione()); //descrizione
            //Conversione da LocalDate a sql.Date
            Date dateConversion = Date.valueOf(p.getDataTrasmissione());
            st.setDate(5, dateConversion); //data di trasmissione
            //Conversione da LocalTime a sql.Time
            Time timeConversion = Time.valueOf(p.getOrarioInizio());
            st.setTime(6,timeConversion); //ora inizio
            timeConversion = Time.valueOf(p.getOrarioFine());
            st.setTime(7,timeConversion); //ora fine
            st.setString(8,p.getUrlRelativoImmagine()); //url relativo immagine
            st.setURL(9,p.getUrlApprofondimento()); //url assoluto all'approfondimento
            st.setShort(10,p.getStagione()); //stagione (0 se non è una serie)
            st.setShort(11, p.getEpisodio()); //episodio (0 se non è una serie)
            st.setLong(12, id);

            st.executeUpdate();
        }
    }


    public void update(ProgrammaTelevisivo p) throws NamingException, SQLException {
        try(Connection con = DatabaseManager.getInstance().getConnection();
            PreparedStatement st = con.prepareStatement(UPDATE_BY_ID_QUERY)){
            st.setLong(1, p.getId());
            st.setString(2, p.getGenere().toString());
            st.setLong(3, p.getIdCanale());
            st.setString(4, p.getDescrizione());
            //Conversione da LocalDate a sql.Date
            Date dateConversion = Date.valueOf(p.getDataTrasmissione());
            st.setDate(5, dateConversion); //data di trasmissione
            //Conversione da LocalTime a sql.Time
            Time timeConversion = Time.valueOf(p.getOrarioInizio());
            st.setTime(6,timeConversion); //ora inizio
            timeConversion = Time.valueOf(p.getOrarioFine());
            st.setTime(7,timeConversion); //ora fine
            st.setString(8,p.getUrlRelativoImmagine()); //url relativo immagine
            st.setURL(9,p.getUrlApprofondimento()); //url assoluto all'approfondimento
            st.setShort(10,p.getStagione()); //stagione (0 se non è una serie)
            st.setShort(11, p.getEpisodio()); //episodio (0 se non è una serie)
            st.setLong(12, p.getId());

            st.executeUpdate();
        }
    }


    public void delete(ProgrammaTelevisivo p) throws NamingException, SQLException {
        try(Connection con = DatabaseManager.getInstance().getConnection();
            PreparedStatement st = con.prepareStatement (DELETE_BY_ID_QUERY)) {
            st.setLong(1, p.getId());

            st.executeUpdate();
        }
    }
}
