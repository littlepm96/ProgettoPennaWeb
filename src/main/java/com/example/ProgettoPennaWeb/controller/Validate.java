package com.example.ProgettoPennaWeb.controller;

import com.example.ProgettoPennaWeb.model.Utente;
import com.example.ProgettoPennaWeb.persistenza.dao.UtenteDAO;
import org.apache.commons.codec.digest.DigestUtils;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.Optional;

public class Validate {
    public static boolean checkUser(String email, String pass) throws SQLException, NamingException{
        boolean st = false;
        UtenteDAO dao  = new UtenteDAO();
        Optional<Utente> result = dao.getUtenteByEmailAndPassword(email, DigestUtils.sha256Hex(pass));
        return result.isPresent();
    }
}
