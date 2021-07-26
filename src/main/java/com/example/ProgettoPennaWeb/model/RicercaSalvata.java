package com.example.ProgettoPennaWeb.model;

import com.example.ProgettoPennaWeb.model.enums.GenereProgramma;

public class RicercaSalvata {
    private Long id;
    private String emailUtente;
    private String titolo;
    private String genere;
    private Short numeroCanale;
    private String dataTrasmissione;
    private String fasciaOraria;

    public RicercaSalvata(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public Short getNumeroCanale() {
        return numeroCanale;
    }

    public void setNumeroCanale(Short numeroCanale) {
        this.numeroCanale = numeroCanale;
    }

    public String getDataTrasmissione() {
        return dataTrasmissione;
    }

    public void setDataTrasmissione(String dataTrasmissione) {
        this.dataTrasmissione = dataTrasmissione;
    }

    public String getFasciaOraria() {
        return fasciaOraria;
    }

    public void setFasciaOraria(String fasciaOraria) {
        this.fasciaOraria = fasciaOraria;
    }
}
