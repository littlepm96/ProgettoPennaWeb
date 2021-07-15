package com.example.ProgettoPennaWeb.model.enums;

public enum GenereProgramma {
    NON_ASSEGNATO, NEWS, SHOW, SERIE, FILM, DOCUMENTARIO, RELIGIONE,
    SPORT;

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
