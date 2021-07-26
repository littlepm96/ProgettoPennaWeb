package com.example.ProgettoPennaWeb.persistenza.dao;

import com.example.ProgettoPennaWeb.model.RicercaSalvata;
import com.example.ProgettoPennaWeb.model.Utente;
import com.example.ProgettoPennaWeb.model.enums.GenereProgramma;
import com.example.ProgettoPennaWeb.persistenza.DatabaseManager;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RicercaSalvataDAO {

    private final String SELECT_BY_ID_QUERY = "select * from pennaweb.ricerca_salvata WHERE ID = ?";
    private final String SELECT_BY_EMAIL_UTENTE_QUERY = "select * from pennaweb.ricerca_salvata WHERE email_utente = ?";
    private final String INSERT_QUERY = "Insert into pennaweb.ricerca_salvata values(?,?,?,?,?,?,?)";
    private final String UPDATE_QUERY = "Update pennaweb.ricerca_salvata SET ID = ?, email_utente = ?, titolo = ?, genere = ?, numero_canale = ?, data_trasmissione = ?, fascia_oraria = ? WHERE ID = ?";
    private final String DELETE_QUERY = "Delete from pennaweb.ricerca_salvata where ID = ?";
    private final String SELECT_ALL_QUERY = "select * from pennaweb.ricerca_salvata";

    public Optional<RicercaSalvata> getRicercaById(long id) throws SQLException, NamingException {

        try (Connection con = DatabaseManager.getInstance().getConnection();
             PreparedStatement st = con.prepareStatement(SELECT_BY_ID_QUERY)) {
            st.setLong(1, id);

            try (ResultSet resultSet = st.executeQuery()) {
                if (resultSet.next()) {
                    RicercaSalvata r = new RicercaSalvata();
                    r.setId(resultSet.getLong("ID"));
                    r.setEmailUtente(resultSet.getString("email_utente"));
                    r.setTitolo(resultSet.getString("titolo"));
                    r.setGenere(resultSet.getString("genere"));
                    r.setNumeroCanale(resultSet.getShort("numero_canale"));
                    r.setDataTrasmissione(resultSet.getString("data_trasmissione"));
                    r.setFasciaOraria(resultSet.getString("fascia_oraria"));

                    return Optional.of(r);
                } else {
                    return Optional.empty();
                }
            }
        }
    }

    public List<RicercaSalvata> getByEmailUtente(String emailUtente) throws SQLException, NamingException {

        try (Connection con = DatabaseManager.getInstance().getConnection();
             PreparedStatement st = con.prepareStatement(SELECT_BY_EMAIL_UTENTE_QUERY)) {
            st.setString(1, emailUtente);

            List<RicercaSalvata> risultato = new ArrayList<>();
            try (ResultSet resultSet = st.executeQuery()) {
                while (resultSet.next()) {
                    RicercaSalvata r = new RicercaSalvata();
                    r.setId(resultSet.getLong("ID"));
                    r.setEmailUtente(resultSet.getString("email_utente"));
                    r.setTitolo(resultSet.getString("titolo"));
                    r.setGenere(resultSet.getString("genere"));
                    r.setNumeroCanale(resultSet.getShort("numero_canale"));
                    r.setDataTrasmissione(resultSet.getString("data_trasmissione"));
                    r.setFasciaOraria(resultSet.getString("fascia_oraria"));

                    risultato.add(r);
                }
            }
            return risultato;
        }
    }

    public List<RicercaSalvata> getAll(String[] params) throws SQLException, NamingException{
        try (Connection con = DatabaseManager.getInstance().getConnection();
             PreparedStatement st = con.prepareStatement(SELECT_BY_EMAIL_UTENTE_QUERY)) {
            List<RicercaSalvata> risultato = new ArrayList<>();

            try (ResultSet resultSet = st.executeQuery()) {
                while (resultSet.next()) {
                    RicercaSalvata r = new RicercaSalvata();
                    r.setId(resultSet.getLong("ID"));
                    r.setEmailUtente(resultSet.getString("email_utente"));
                    r.setTitolo(resultSet.getString("titolo"));
                    r.setGenere(resultSet.getString("genere"));
                    r.setNumeroCanale(resultSet.getShort("numero_canale"));
                    r.setDataTrasmissione(resultSet.getString("data_trasmissione"));
                    r.setFasciaOraria(resultSet.getString("fascia_oraria"));

                    risultato.add(r);
                }
            }
            return risultato;
        }
    }

    public boolean save(RicercaSalvata r) throws SQLException, NamingException {
        try (Connection con = DatabaseManager.getInstance().getConnection();
             PreparedStatement st = con.prepareStatement(INSERT_QUERY)) {
            st.setNull(1, Types.INTEGER); //Null poichè auto-increment
            st.setString(2, r.getEmailUtente());
            st.setString(3, r.getTitolo());
            st.setString(4, r.getGenere());
            if(r.getNumeroCanale()==null){
                st.setNull(5, Types.SMALLINT);
            }else {
                st.setShort(5, r.getNumeroCanale());
            }
            st.setString(6, r.getDataTrasmissione().toString());
            st.setString(7,r.getFasciaOraria());

            int i = st.executeUpdate();
            return (i > 0);
        }
    }

    public void update(RicercaSalvata r, long id) throws SQLException, NamingException {
        try (Connection con = DatabaseManager.getInstance().getConnection();
             PreparedStatement st = con.prepareStatement(UPDATE_QUERY)) {
            st.setLong(1, r.getId());
            st.setString(2, r.getEmailUtente());
            st.setString(3, r.getTitolo());
            st.setString(4, r.getGenere().toString());
            st.setShort(5, r.getNumeroCanale());
            st.setString(6, r.getDataTrasmissione().toString());
            st.setString(7,r.getFasciaOraria());
            st.setLong(8, id);

            st.executeUpdate();
        }
    }

    public void update(RicercaSalvata r) throws NamingException, SQLException {
        try (Connection con = DatabaseManager.getInstance().getConnection();
             PreparedStatement st = con.prepareStatement(UPDATE_QUERY)) {
            st.setLong(1, r.getId());
            st.setString(2, r.getEmailUtente());
            st.setString(3, r.getTitolo());
            st.setString(4, r.getGenere().toString());
            st.setShort(5, r.getNumeroCanale());
            st.setString(6, r.getDataTrasmissione().toString());
            st.setString(7,r.getFasciaOraria());
            st.setLong(8, r.getId());

            st.executeUpdate();
        }
    }

    public void delete(RicercaSalvata r) throws NamingException, SQLException {
        try (Connection con = DatabaseManager.getInstance().getConnection();
             PreparedStatement st = con.prepareStatement(DELETE_QUERY)) {
            st.setLong(1, r.getId());

            st.executeUpdate();
        }
    }
}
