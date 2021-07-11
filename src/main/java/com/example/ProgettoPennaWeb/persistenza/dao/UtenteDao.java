package com.example.ProgettoPennaWeb.persistenza.dao;

import com.example.ProgettoPennaWeb.model.Utente;
import com.example.ProgettoPennaWeb.persistenza.DatabaseManager;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
    public void save(Utente utente) throws SQLException, NamingException, ClassNotFoundException {
        try(Connection con = DatabaseManager.getInstance().getConnection();
        PreparedStatement st = con.prepareStatement("Insert into pennaweb.utente values(?,?,?,?)")) {
            st.setString(1, utente.getEmail());
            st.setString(2, utente.getNome());
            st.setString(3, utente.getCognome());
            st.setString(4, utente.getPassword());

            st.executeQuery();
        }

    }

    @Override
    public void update(Utente utente, String id) throws SQLException, NamingException, ClassNotFoundException{
        try(Connection con = DatabaseManager.getInstance().getConnection();
            PreparedStatement st = con.prepareStatement ("Update pennaweb.utente SET email = ?, nome = ?, cognome = ?, password = ? WHERE email = ?")) {
            st.setString(1, utente.getEmail());
            st.setString(2, utente.getNome());
            st.setString(3, utente.getCognome());
            st.setString(4, utente.getPassword());
            st.setString(5, id);

            st.executeQuery();
        }
    }

    @Override
    public void update(Utente utente) throws NamingException, ClassNotFoundException, SQLException {
        try(Connection con = DatabaseManager.getInstance().getConnection();
            PreparedStatement st = con.prepareStatement ("Update pennaweb.utente SET email = ?, nome = ?, cognome = ?, password = ? WHERE email = ?")) {
            st.setString(1, utente.getEmail());
            st.setString(2, utente.getNome());
            st.setString(3, utente.getCognome());
            st.setString(4, utente.getPassword());
            st.setString(5, utente.getEmail());

            st.executeQuery();
        }
    }

    @Override
    public void delete(Utente utente) throws NamingException, ClassNotFoundException, SQLException {
        try(Connection con = DatabaseManager.getInstance().getConnection();
            PreparedStatement st = con.prepareStatement ("Delete from pennaweb.utente where email = ?")) {
            st.setString(1, utente.getEmail());

            st.executeQuery();
        }
    }
}
