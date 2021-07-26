package com.example.ProgettoPennaWeb.model.enums;

import java.util.Map;
import java.util.TreeMap;

public enum FasciaOrariaPredefinita {
    MATTINA("06:00-11:59"),
    POMERIGGIO("12:00-17:59"),
    SERA("18:00-23:59"),
    NOTTE("00:00-05:59");

    private String stringa;

    private static final Map<String, FasciaOrariaPredefinita> dizionario = new TreeMap<>();

    //Inizializzatore statico
    static{
        //Inizializziamo il dizionario
        for(FasciaOrariaPredefinita f : FasciaOrariaPredefinita.values()){
            dizionario.put(f.toString(), f);
        }
    }

    public String getStringa(){
        return this.stringa;
    }

    private FasciaOrariaPredefinita(String stringa){
        this.stringa = stringa;
    }


    @Override
    public String toString() {
        switch (this){
            case MATTINA:
                return "mattina";
            case POMERIGGIO:
                return "pomeriggio";
            case SERA:
                return "sera";
            case NOTTE:
                return "notte";
            default:
                return "ERRORE";
        }
    }

    public static FasciaOrariaPredefinita fromString(String s){
        FasciaOrariaPredefinita f = dizionario.get(s);
        if(f == null){
            System.err.println("La stringa passata a FasciaOrariaPredefinita.fromString (\""+s+"\") non è stata trovata! Stringhe accettate: \n");
            for(String k : dizionario.keySet()){
                System.err.println(k);
            }
            return FasciaOrariaPredefinita.MATTINA;
        }

        return f;
    }
}
