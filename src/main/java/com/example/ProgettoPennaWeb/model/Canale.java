package com.example.ProgettoPennaWeb.model;

public class Canale {
    private long id;
    private short numero;
    private String nome;

    public Canale(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public short getNumero() {
        return numero;
    }

    public void setNumero(short numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
