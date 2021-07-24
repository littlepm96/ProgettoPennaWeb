package com.example.ProgettoPennaWeb.model;

public class Utente {
    private String email;
    private String nome;
    private String cognome;
    private String password;
    private String hash;
    private Boolean verificato;

    public Utente(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) { this.hash = hash; }

    public void setVerificato(Boolean verificato) { this.verificato = verificato; }

    public Boolean getVerificato() { return verificato; }


}
