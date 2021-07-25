package com.example.ProgettoPennaWeb.utility;

import com.example.ProgettoPennaWeb.model.enums.GenereProgramma;
import com.example.ProgettoPennaWeb.model.enums.ParametriDiRicerca;

import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;


/**
 * Classe contenitore dei parametri di ricerca dei programmi televisivi nella pagina di ricerca
 */
public class Ricerca {
    private final Map<ParametriDiRicerca, Object> parametri = new TreeMap<>();
    private int parametriSettati = 0;

    public Ricerca(){
        //Inizializziamo a null i parametri di ricerca
        for(ParametriDiRicerca p : ParametriDiRicerca.values()){
            parametri.put(p,null);
        }
    }
    //Setters
    public void setTitolo(String titolo){
        parametri.put(ParametriDiRicerca.TITOLO_PROGRAMMA,titolo);
        parametriSettati = contaParametriSettati();
    }

    public void setGenere(GenereProgramma genere){
        parametri.put(ParametriDiRicerca.GENERE_PROGRAMMA,genere);
        parametriSettati = contaParametriSettati();
    }
    public void setNumeroCanale(Short numeroCanale){
        parametri.put(ParametriDiRicerca.NUMERO_CANALE,numeroCanale);
        parametriSettati = contaParametriSettati();
    }
    public void setFasciaOraria(String fasciaOraria){
        parametri.put(ParametriDiRicerca.FASCIA_ORARIA,fasciaOraria);
        parametriSettati = contaParametriSettati();
    }

    public void setDataTrasmissione(LocalDate[] dataTrasmissione){
        parametri.put(ParametriDiRicerca.DATA_TRASMISSIONE, dataTrasmissione);
        parametriSettati = contaParametriSettati();
    }


    //Getters
    public String getTitolo(){
        return (String) parametri.get(ParametriDiRicerca.TITOLO_PROGRAMMA);
    }

    public GenereProgramma getGenere(){
        return (GenereProgramma) parametri.get(ParametriDiRicerca.GENERE_PROGRAMMA);
    }
    public Short getNumeroCanale(){
        return (Short) parametri.get(ParametriDiRicerca.NUMERO_CANALE);
    }
    public String getFasciaOraria(){
        return (String) parametri.get(ParametriDiRicerca.FASCIA_ORARIA);
    }

    public LocalDate[] getDataTrasmissione(){
        return (LocalDate[]) parametri.get(ParametriDiRicerca.DATA_TRASMISSIONE);
    }

    public int getParametriSettati(){
        return parametriSettati;
    }

    //metodi privati
    private int contaParametriSettati(){
        int count = 0;
        for(Object parametro : parametri.values()){
            if(parametro!= null){
                count++;
            }
        }
        return count;
    }
}
