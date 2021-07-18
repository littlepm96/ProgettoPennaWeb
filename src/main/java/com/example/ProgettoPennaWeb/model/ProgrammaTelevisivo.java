package com.example.ProgettoPennaWeb.model;

import com.example.ProgettoPennaWeb.model.enums.GenereProgramma;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;

public class ProgrammaTelevisivo implements Comparable<ProgrammaTelevisivo>{
    private long id;
    private String titolo;
    private GenereProgramma genere;
    private String descrizione;
    private LocalDate dataTrasmissione;
    private LocalTime orarioInizio;
    private LocalTime orarioFine;
    private String urlRelativoImmagine; //essendo un url relativo, usiamo la classe String
    private URL urlApprofondimento;
    private short stagione; //valore speciale -1 se non si tratta di una serie
    private short episodio; //valore speciale -1 se non si tratta di una serie

    public ProgrammaTelevisivo(){}

    public ProgrammaTelevisivo(long id, String titolo, GenereProgramma genere, String descrizione, LocalDate dataTrasmissione, LocalTime orarioInizio, LocalTime orarioFine) {
        this.id = id;
        this.titolo = titolo;
        this.genere = genere;
        this.descrizione = descrizione;
        this.dataTrasmissione = dataTrasmissione;
        this.orarioInizio = orarioInizio;
        this.orarioFine = orarioFine;
        this.urlRelativoImmagine= "";
        this.urlApprofondimento = null;
        this.stagione = 1;
        this.episodio = 1;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public LocalDate getDataTrasmissione() {
        return dataTrasmissione;
    }

    public void setDataTrasmissione(LocalDate dataTrasmissione) {
        this.dataTrasmissione = dataTrasmissione;
    }

    public LocalTime getOrarioInizio() {
        return orarioInizio;
    }

    public void setOrarioInizio(LocalTime orarioInizio) {
        this.orarioInizio = orarioInizio;
    }

    public LocalTime getOrarioFine() {
        return orarioFine;
    }

    public void setOrarioFine(LocalTime orarioFine) {
        this.orarioFine = orarioFine;
    }

    public String getUrlRelativoImmagine() {
        return urlRelativoImmagine;
    }

    public void setUrlRelativoImmagine(String urlRelativoImmagine) {
        this.urlRelativoImmagine = urlRelativoImmagine;
    }

    public URL getUrlApprofondimento() {
        return urlApprofondimento;
    }

    public void setUrlApprofondimento(URL urlApprofondimento) {
        this.urlApprofondimento = urlApprofondimento;
    }

    public short getStagione() {
        return stagione;
    }

    public void setStagione(short stagione) {
        this.stagione = stagione;
    }

    public short getEpisodio() {
        return episodio;
    }

    public void setEpisodio(short episodio) {
        this.episodio = episodio;
    }

    /**
     * Ordinamento naturale dei programmi televisivi: Confrontiamo gli ID numerici.
     * @param other l'altro programma televisivo da comparare
     * @return 1 se questo programma ha un id minore dell'altro, -1 altrimenti. 0 Se hanno lo stesso ID (sono lo stesso programma).
     */
    @Override
    public int compareTo(ProgrammaTelevisivo other) {
        return Long.compare(this.id, other.id);
    }



}
