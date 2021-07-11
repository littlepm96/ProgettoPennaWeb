package com.example.ProgettoPennaWeb.persistenza.dao;

import com.example.ProgettoPennaWeb.model.Canale;
import com.example.ProgettoPennaWeb.persistenza.DatabaseManager;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CanaleDAO implements DAO<Canale> {

    @Override
    public Optional<Canale> get(String id) throws SQLException, NamingException, ClassNotFoundException {
        try(Connection con = DatabaseManager.getInstance().getConnection();
            PreparedStatement st = con.prepareStatement("select * from pennaweb.canale WHERE ID = ?")){
            st.setString(1, id);

            st.executeQuery();
        }
        return Optional.empty();
    }

    @Override
    public List<Canale> getAll(String[] params) {
        return null;
    }

    @Override
    public void save(Canale canale) throws SQLException, NamingException, ClassNotFoundException {
        try(Connection con = DatabaseManager.getInstance().getConnection();
            PreparedStatement st = con.prepareStatement("Insert into pennaweb.canale values(?,?,?,?)")) {
            st.setString(1, canale.getId().toString());
            st.setString(2, canale.getNumero().toString());
            st.setString(3, canale.getNome());

            st.executeQuery();
        }
    }

    @Override
    public void update(Canale canale, String id) throws SQLException, NamingException, ClassNotFoundException {
        try(Connection con = DatabaseManager.getInstance().getConnection();
            PreparedStatement st = con.prepareStatement ("Update pennaweb.canale SET ID = ?, nome = ?, numero = ? where ID = ?")) {
            st.setString(1, canale.getId().toString());
            st.setString(2, canale.getNome());
            st.setString(3, canale.getNumero().toString());
            st.setString(5, id);

            st.executeQuery();
        }
    }

    @Override
    public void update(Canale canale) throws NamingException, ClassNotFoundException, SQLException {
        try(Connection con = DatabaseManager.getInstance().getConnection();
            PreparedStatement st = con.prepareStatement ("Update pennaweb.canale SET ID = ?, nome = ?, numero = ? where ID = ?")) {
            st.setString(1, canale.getId().toString());
            st.setString(2, canale.getNome());
            st.setString(3, canale.getNumero().toString());

            st.executeQuery();
        }
    }

    @Override
    public void delete(Canale canale) throws NamingException, ClassNotFoundException, SQLException {
        try(Connection con = DatabaseManager.getInstance().getConnection();
            PreparedStatement st = con.prepareStatement ("Delete from pennaweb.canale where ID = ?")) {
            st.setString(1, canale.getId().toString());

            st.executeQuery();
        }
    }
}
