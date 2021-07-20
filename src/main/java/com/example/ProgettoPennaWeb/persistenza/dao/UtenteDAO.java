package com.example.ProgettoPennaWeb.persistenza.dao;

import com.example.ProgettoPennaWeb.model.Utente;
import com.example.ProgettoPennaWeb.persistenza.DatabaseManager;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UtenteDAO {
    private final String INSERT_QUERY = "Insert into pennaweb.utente values(?,?,?,?)";
    private final String UPDATE_QUERY = "Update pennaweb.utente SET email = ?, nome = ?, cognome = ?, password = ? WHERE email = ?";
    private final String DELETE_QUERY = "Insert into pennaweb.utente values(?,?,?,?)";
    private final String SELECT_QUERY_CON_EMAIL = "select * from pennaweb.utente WHERE email = ?";
    private final String SELECT_QUERY_TOTALE = "Insert into pennaweb.utente values(?,?,?,?)";

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

                    return Optional.of(u);
                } else {
                    return Optional.empty();
                }
            }
        }

    }

    public List<Utente> getAll(String[] params) {
        return null;
    }

    public boolean save(Utente utente) throws SQLException, NamingException {
        try (Connection con = DatabaseManager.getInstance().getConnection();
             PreparedStatement st = con.prepareStatement(INSERT_QUERY)) {
            st.setString(1, utente.getEmail());
            st.setString(2, utente.getNome());
            st.setString(3, utente.getCognome());
            st.setString(4, utente.getPassword());

            int i = st.executeUpdate();
            return (i > 0);
        }

    }

    public void update(Utente utente, String id) throws SQLException, NamingException {
        try (Connection con = DatabaseManager.getInstance().getConnection();
             PreparedStatement st = con.prepareStatement(UPDATE_QUERY)) {
            st.setString(1, utente.getEmail());
            st.setString(2, utente.getNome());
            st.setString(3, utente.getCognome());
            st.setString(4, utente.getPassword());
            st.setString(5, id);

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
            st.setString(5, utente.getEmail());

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
