package com.example.ProgettoPennaWeb.persistenza.dao;

import com.example.ProgettoPennaWeb.model.Canale;
import com.example.ProgettoPennaWeb.persistenza.DatabaseManager;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CanaleDAO{

    //query strings
    private final String SELECT_BY_ID = "select * from pennaweb.canale WHERE ID = ?";

    public Optional<Canale> getCanaleById(long id) throws SQLException, NamingException{
        try(Connection con = DatabaseManager.getInstance().getConnection();
            PreparedStatement st = con.prepareStatement(SELECT_BY_ID)){
            st.setLong(1, id);

            ResultSet resultSet = st.executeQuery();
            if(resultSet.next()){
                Canale c = new Canale();
                c.setId(resultSet.getLong("ID"));
                c.setNumero(resultSet.getShort("numero"));
                c.setNome(resultSet.getString("nome"));

                return Optional.of(c);
            }else
            {
                return Optional.empty();
            }
        }

    }

    public List<Canale> getAll(String[] params) {
        return null;
    }

    public void save(Canale canale) throws SQLException, NamingException{
        try(Connection con = DatabaseManager.getInstance().getConnection();
            PreparedStatement st = con.prepareStatement("Insert into pennaweb.canale values(?,?,?,?)")) {
            st.setString(1, canale.getId().toString());
            st.setString(2, canale.getNumero().toString());
            st.setString(3, canale.getNome());

            st.executeQuery();
        }
    }


    public void update(Canale canale, String id) throws SQLException, NamingException{
        try(Connection con = DatabaseManager.getInstance().getConnection();
            PreparedStatement st = con.prepareStatement ("Update pennaweb.canale SET ID = ?, nome = ?, numero = ? where ID = ?")) {
            st.setString(1, canale.getId().toString());
            st.setString(2, canale.getNome());
            st.setString(3, canale.getNumero().toString());
            st.setString(5, id);

            st.executeQuery();
        }
    }


    public void update(Canale canale) throws NamingException, SQLException {
        try(Connection con = DatabaseManager.getInstance().getConnection();
            PreparedStatement st = con.prepareStatement ("Update pennaweb.canale SET ID = ?, nome = ?, numero = ? where ID = ?")) {
            st.setString(1, canale.getId().toString());
            st.setString(2, canale.getNome());
            st.setString(3, canale.getNumero().toString());

            st.executeQuery();
        }
    }


    public void delete(Canale canale) throws NamingException, SQLException {
        try(Connection con = DatabaseManager.getInstance().getConnection();
            PreparedStatement st = con.prepareStatement ("Delete from pennaweb.canale where ID = ?")) {
            st.setString(1, canale.getId().toString());

            st.executeQuery();
        }
    }
}
