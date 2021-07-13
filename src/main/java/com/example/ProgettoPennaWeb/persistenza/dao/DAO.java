package com.example.ProgettoPennaWeb.persistenza.dao;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface DAO<T> {

    /**
     * Questo metodo implementa la ricerca della istanza corrispondente all'identificativo (ad esempio una chiave primaria in MySQL) sul sistema di persistenza.
     * @param id l'identificativo univoco dell'istanza da cercare
     * @return un'oggetto optional contenente l'oggetto cercato se esiste, un valore vuoto altrimenti.
     */
    Optional<T> get(String id) throws SQLException, NamingException;

    /**
     * Questo metodo implementa la ricerca di tutte le istanze corrispondenti ai criteri specificati dai parametri.
     * @param params la lista dei parametri da usare nella ricera (L'ORDINE CONTA!)
     * @return La lista (eventualmente vuota) delle istanze
     */
    List<T> getAll(String[] params);

    /**
     * Questo metodo implementa il salvataggio sul sistema di persistenza dell'oggetto fornito come parametro.
     * @param t L'oggetto da salvare sul sistema di persistenza
     */
    void save(T t) throws SQLException, NamingException;

    /**
     * Questo metodo implementa l'aggiornamento di un'istanza sul sistema di persistenza. I campi dell'oggetto t sovrascriveranno quelli trovati nell'istanza.
     * @param t L'oggetto contenente i nuovi dati da salvare
     * @param id La chiave primaria che identifica l'istanza
     */
    void update(T t, String id) throws SQLException, NamingException;

    /**
     * Questo metodo in overload implementa l'aggiornamento di un'istanza sul sistema di persistenza tramite il suo ID (trovato nell'oggetto passato). I campi dell'oggetto t sovrascriveranno quelli trovati nell'istanza.
     * @param t L'oggetto contenente i nuovi dati da salvare
     */
    void update(T t) throws NamingException, SQLException;

    /**
     * Questo metodo implementa la rimozione di un'istanza (se esiste) dal sistema di persistenza
     * @param t L'oggetto corrispondente all'istanza da eliminare
     */
    void delete(T t) throws NamingException, SQLException;
}