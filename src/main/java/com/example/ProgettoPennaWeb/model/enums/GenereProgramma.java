package com.example.ProgettoPennaWeb.model.enums;

import java.util.Map;
import java.util.TreeMap;

public enum GenereProgramma {
    NON_ASSEGNATO, NEWS, SHOW, SERIE, FILM, DOCUMENTARIO, RELIGIONE,
    SPORT;

    private static final Map<String, GenereProgramma> dizionario = new TreeMap<>();

    //Inizializzatore statico
    static{
        //Inizializziamo il dizionario
        for(GenereProgramma g : GenereProgramma.values()){
            dizionario.put(g.toString(), g);
        }
    }

    /**
     * Restituisce la costante enum corrispondente alla stringa passata (la si pensi come l'inverso di GenereProgramma.toString()).
     * @param s La stringa da cui ricavare la costante
     * @return La costante nell'enum corrispondente alla stringa se si ha riscontro, GenereProgramma.NON_ASSEGNATO altrimenti.
     */
    public static GenereProgramma fromString(String s){
        GenereProgramma g = dizionario.get(s);
        if(g == null){
            System.err.println("La stringa passata a GenereProgramma.fromString (\""+s+"\") non è stata trovata! Stringhe accettate: \n");
            for(String k : dizionario.keySet()){
                System.err.println(k);
            }
            return GenereProgramma.NON_ASSEGNATO;
        }

        return g;
    }

    @Override
    public String toString() {
        switch (this){
            default:
            case NON_ASSEGNATO:
                return "Non assegnato";
            case NEWS:
                return "Notizie";
            case SHOW:
                return "Show";
            case SERIE:
                return "Serie TV";
            case FILM:
                return "Film";
            case DOCUMENTARIO:
                return "Documentario";
            case RELIGIONE:
                return "Religione";
            case SPORT:
                return "Sport";
        }
    }
}
