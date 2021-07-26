package com.example.ProgettoPennaWeb.model;

public class Canale implements Comparable<Canale>{
    private Long id;
    private Short numero;
    private String nome;

    public Canale() {
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Short getNumero() {
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

    @Override
    public int compareTo(Canale other) {
        if(other == null || other.id == null) return -1;
        if(this.id == null) return 1;

        return this.id.compareTo(other.id);
    }
}
