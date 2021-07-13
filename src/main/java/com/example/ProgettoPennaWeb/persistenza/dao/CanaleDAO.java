package com.example.ProgettoPennaWeb.persistenza.dao;

import com.example.ProgettoPennaWeb.model.Canale;
import com.example.ProgettoPennaWeb.persistenza.DatabaseManager;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CanaleDAO{

    //query strings
    private final String INSERT_QUERY="Insert into pennaweb.canale values(?,?,?)";
    private final String SELECT_BY_ID_QUERY = "select * from pennaweb.canale WHERE ID = ?";
    private final String SELECT_ALL_QUERY = "select * from pennaweb.canale";
    private final String UPDATE_BY_ID_QUERY = "Update pennaweb.canale SET ID = ?, numero = ?, nome = ? where ID = ?";
    private final String DELETE_BY_ID_QUERY = "Delete from pennaweb.canale where ID = ?";

    public Optional<Canale> getCanaleById(long id) throws SQLException, NamingException{
        try(Connection con = DatabaseManager.getInstance().getConnection();
            PreparedStatement st = con.prepareStatement(SELECT_BY_ID_QUERY)){
            st.setLong(1, id);

            try(ResultSet resultSet = st.executeQuery()) {
                if (resultSet.next()) {
                    Canale c = new Canale();
                    c.setId(resultSet.getLong("ID"));
                    c.setNumero(resultSet.getShort("numero"));
                    c.setNome(resultSet.getString("nome"));

                    return Optional.of(c);
                } else {
                    return Optional.empty();
                }
            }
        }

    }

    public List<Canale> getAll() throws SQLException, NamingException{
        try(Connection con = DatabaseManager.getInstance().getConnection();
            PreparedStatement st = con.prepareStatement(SELECT_ALL_QUERY);
            ResultSet resultSet = st.executeQuery()) {

            List<Canale> risultato = new ArrayList<>();
            while (resultSet.next()) {
                Canale c = new Canale();
                c.setId(resultSet.getLong("ID"));
                c.setNumero(resultSet.getShort("numero"));
                c.setNome(resultSet.getString("nome"));
                risultato.add(c);
            }
            return risultato;
        }
    }

    public void save(Canale canale) throws SQLException, NamingException{
        try(Connection con = DatabaseManager.getInstance().getConnection();
            PreparedStatement st = con.prepareStatement(INSERT_QUERY)) {
            st.setLong(1, canale.getId());
            st.setShort(2, canale.getNumero());
            st.setString(3, canale.getNome());

            st.executeQuery();
        }
    }

    public void update(Canale canale, long id) throws SQLException, NamingException{
        try(Connection con = DatabaseManager.getInstance().getConnection();
            PreparedStatement st = con.prepareStatement(UPDATE_BY_ID_QUERY)){
            st.setLong(1, canale.getId());
            st.setShort(2, canale.getNumero());
            st.setString(3, canale.getNome());
            st.setLong(4, id);

            st.executeQuery();
        }
    }


    public void update(Canale canale) throws NamingException, SQLException {
        try(Connection con = DatabaseManager.getInstance().getConnection();
            PreparedStatement st = con.prepareStatement(UPDATE_BY_ID_QUERY)){
            st.setLong(1, canale.getId());
            st.setShort(2, canale.getNumero());
            st.setString(3, canale.getNome());
            st.setLong(4, canale.getId());


            st.executeQuery();
        }
    }


    public void delete(Canale canale) throws NamingException, SQLException {
        try(Connection con = DatabaseManager.getInstance().getConnection();
            PreparedStatement st = con.prepareStatement (DELETE_BY_ID_QUERY)) {
            st.setLong(1, canale.getId());

            st.executeQuery();
        }
    }
}
