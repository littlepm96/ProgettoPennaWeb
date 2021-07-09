package com.example.ProgettoPennaWeb.persistenza.dao;

import com.example.ProgettoPennaWeb.model.Utente;

import java.util.List;
import java.util.Optional;

public class UtenteDao implements DAO<Utente> {
    @Override
    public Optional<Utente> get(String email) {
        return Optional.empty();
    }

    @Override
    public List<Utente> getAll(String[] params) {
        return null;
    }

    @Override
    public void save(Utente utente) {

    }

    @Override
    public void update(Utente utente, String[] params) {

    }

    @Override
    public void delete(Utente utente) {

    }
}
