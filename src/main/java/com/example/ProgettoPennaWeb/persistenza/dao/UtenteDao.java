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
    private final String INSERT_QUERY = "Insert into pennaweb.utente values(?,?,?,?)";
    private final String UPDATE_QUERY = "Update pennaweb.utente SET email = ?, nome = ?, cognome = ?, password = ? WHERE email = ?";
    private final String DELETE_QUERY = "Insert into pennaweb.utente values(?,?,?,?)";
    private final String SELECT_QUERY_CON_EMAIL = "select * from pennaweb.utente WHERE email = ?";
    private final String SELECT_QUERY_TOTALE = "Insert into pennaweb.utente values(?,?,?,?)";


    @Override
    public Optional<Utente> get(String email) throws SQLException, NamingException {

        try(Connection con = DatabaseManager.getInstance().getConnection();
            PreparedStatement st = con.prepareStatement(SELECT_QUERY_CON_EMAIL)){
            st.setString(1, email);

            st.executeQuery();
        }
        return Optional.empty();
    }

    @Override
    public List<Utente> getAll(String[] params) {
        return null;
    }

    @Override
    public void save(Utente utente) throws SQLException, NamingException{
        try(Connection con = DatabaseManager.getInstance().getConnection();
        PreparedStatement st = con.prepareStatement(INSERT_QUERY)) {
            st.setString(1, utente.getEmail());
            st.setString(2, utente.getNome());
            st.setString(3, utente.getCognome());
            st.setString(4, utente.getPassword());

            st.executeQuery();
        }

    }

    @Override
    public void update(Utente utente, String id) throws SQLException, NamingException{
        try(Connection con = DatabaseManager.getInstance().getConnection();
            PreparedStatement st = con.prepareStatement (UPDATE_QUERY)) {
            st.setString(1, utente.getEmail());
            st.setString(2, utente.getNome());
            st.setString(3, utente.getCognome());
            st.setString(4, utente.getPassword());
            st.setString(5, id);

            st.executeQuery();
        }
    }

    @Override
    public void update(Utente utente) throws NamingException, SQLException {
        try(Connection con = DatabaseManager.getInstance().getConnection();
            PreparedStatement st = con.prepareStatement (UPDATE_QUERY)) {
            st.setString(1, utente.getEmail());
            st.setString(2, utente.getNome());
            st.setString(3, utente.getCognome());
            st.setString(4, utente.getPassword());
            st.setString(5, utente.getEmail());

            st.executeQuery();
        }
    }

    @Override
    public void delete(Utente utente) throws NamingException, SQLException {
        try(Connection con = DatabaseManager.getInstance().getConnection();
            PreparedStatement st = con.prepareStatement (DELETE_QUERY)) {
            st.setString(1, utente.getEmail());

            st.executeQuery();
        }
    }
}
