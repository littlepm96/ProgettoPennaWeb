package com.example.ProgettoPennaWeb.persistenza.dao;

import com.example.ProgettoPennaWeb.model.Utente;
import com.example.ProgettoPennaWeb.persistenza.DatabaseManager;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public class UtenteDAO {
    private final String INSERT_QUERY = "Insert into pennaweb.utente values(?,?,?,?,?,?)";
    private final String UPDATE_QUERY = "Update pennaweb.utente SET email = ?, nome = ?, cognome = ?, password = ?, hash = ?, verificato = ? WHERE email = ?";
    private final String DELETE_QUERY = "Delete from pennaweb.utente where email = ?";
    private final String SELECT_QUERY_CON_EMAIL = "select * from pennaweb.utente WHERE email = ?";
    private final String SELECT_QUERY_CON_EMAIL_E_PASSWORD = "select * from pennaweb.utente WHERE email = ? and password = ?";
    private final String SELECT_ALL_QUERY = "select * from pennaweb.utente";

    public Optional<Utente> getUtenteByEmail(String email) throws SQLException, NamingException {

        try (Connection con = DatabaseManager.getInstance().getConnection();
             PreparedStatement st = con.prepareStatement(SELECT_QUERY_CON_EMAIL)) {
            st.setString(1, email);

            try (ResultSet resultSet = st.executeQuery()) {
                if (resultSet.next()) {
                    Utente u = new Utente();
                    u.setEmail(resultSet.getString("email"));
                    u.setNome(resultSet.getString("nome"));
                    u.setCognome(resultSet.getString("cognome"));
                    u.setPassword(resultSet.getString("password"));
                    u.setHash(resultSet.getString("hash"));
                    u.setVerificato(resultSet.getBoolean("verificato"));

                    return Optional.of(u);
                } else {
                    return Optional.empty();
                }
            }
        }
    }

    public Optional<Utente> getUtenteByEmailAndPassword(String email, String password) throws SQLException, NamingException {

        try (Connection con = DatabaseManager.getInstance().getConnection();
             PreparedStatement st = con.prepareStatement(SELECT_QUERY_CON_EMAIL_E_PASSWORD)) {
            st.setString(1, email);
            st.setString(2, password);

            try (ResultSet resultSet = st.executeQuery()) {
                if (resultSet.next()) {
                    Utente u = new Utente();
                    u.setEmail(resultSet.getString("email"));
                    u.setNome(resultSet.getString("nome"));
                    u.setCognome(resultSet.getString("cognome"));
                    u.setPassword(resultSet.getString("password"));
                    u.setHash(resultSet.getString("hash"));
                    u.setVerificato(resultSet.getBoolean("verificato"));

                    return Optional.of(u);
                } else {
                    return Optional.empty();
                }
            }
        }
    }

    public List<Utente> getAll(String[] params) throws SQLException, NamingException{
        try (Connection con = DatabaseManager.getInstance().getConnection();
             PreparedStatement st = con.prepareStatement(SELECT_ALL_QUERY)) {

            try (ResultSet resultSet = st.executeQuery()) {
                List<Utente> result = new ArrayList<>();
                while(resultSet.next()) {
                    Utente u = new Utente();
                    u.setEmail(resultSet.getString("email"));
                    u.setNome(resultSet.getString("nome"));
                    u.setCognome(resultSet.getString("cognome"));
                    u.setPassword(resultSet.getString("password"));
                    u.setHash(resultSet.getString("hash"));
                    u.setVerificato(resultSet.getBoolean("verificato"));

                   result.add(u);
                }
                return  result;
            }
        }

    }

    public boolean save(Utente utente) throws SQLException, NamingException {
        try (Connection con = DatabaseManager.getInstance().getConnection();
             PreparedStatement st = con.prepareStatement(INSERT_QUERY)) {
            st.setString(1, utente.getEmail());
            st.setString(2, utente.getNome());
            st.setString(3, utente.getCognome());
            st.setString(4, utente.getPassword());
            st.setString(5, utente.getHash());
            st.setBoolean(6, utente.getVerificato());

            int i = st.executeUpdate();
            return (i > 0);
        }
    }

    public void update(Utente utente, String email) throws SQLException, NamingException {
        try (Connection con = DatabaseManager.getInstance().getConnection();
             PreparedStatement st = con.prepareStatement(UPDATE_QUERY)) {
            st.setString(1, utente.getEmail());
            st.setString(2, utente.getNome());
            st.setString(3, utente.getCognome());
            st.setString(4, utente.getPassword());
            st.setString(5, utente.getHash());
            st.setBoolean(6, utente.getVerificato());
            st.setString(7, email);

            st.executeUpdate();
        }
    }

    public void update(Utente utente) throws NamingException, SQLException {
        try (Connection con = DatabaseManager.getInstance().getConnection();
             PreparedStatement st = con.prepareStatement(UPDATE_QUERY)) {
            st.setString(1, utente.getEmail());
            st.setString(2, utente.getNome());
            st.setString(3, utente.getCognome());
            st.setString(4, utente.getPassword());
            st.setString(5, utente.getHash());
            st.setBoolean(6, utente.getVerificato());
            st.setString(7, utente.getEmail());

            st.executeUpdate();
        }
    }

    public void delete(Utente utente) throws NamingException, SQLException {
        try (Connection con = DatabaseManager.getInstance().getConnection();
             PreparedStatement st = con.prepareStatement(DELETE_QUERY)) {
            st.setString(1, utente.getEmail());

            st.executeUpdate();
        }
    }
}
