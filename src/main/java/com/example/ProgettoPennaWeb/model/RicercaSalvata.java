package com.example.ProgettoPennaWeb.model;

import com.example.ProgettoPennaWeb.model.enums.GenereProgramma;

public class RicercaSalvata {
    private long id;
    private String emailUtente;
    private String titolo;
    private GenereProgramma genere;
    private short numeroCanale;
    private String fasciaOraria;

    public RicercaSalvata(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmailUtente() {
        return emailUtente;
    }

    public void setEmailUtente(String emailUtente) {
        this.emailUtente = emailUtente;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public GenereProgramma getGenere() {
        return genere;
    }

    public void setGenere(GenereProgramma genere) {
        this.genere = genere;
    }

    public short getNumeroCanale() {
        return numeroCanale;
    }

    public void setNumeroCanale(short numeroCanale) {
        this.numeroCanale = numeroCanale;
    }

    public String getFasciaOraria() {
        return fasciaOraria;
    }

    public void setFasciaOraria(String fasciaOraria) {
        this.fasciaOraria = fasciaOraria;
    }
}
