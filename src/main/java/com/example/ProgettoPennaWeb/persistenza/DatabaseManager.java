package com.example.ProgettoPennaWeb.persistenza;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DatabaseManager {

    /*singleton*/
    private static DatabaseManager instance = null;

    private static final String POOLER_RESOURCE_NAME = "java:comp/env/jdbc/pennaweb";

    private InitialContext ctx;
    private DataSource ds;

    private DatabaseManager() throws NamingException{
        ctx = new InitialContext();
        ds = (DataSource) ctx.lookup(POOLER_RESOURCE_NAME);
    }

    public static DatabaseManager getInstance() throws NamingException {
        if(instance == null){
            instance = new DatabaseManager();
        }
        return instance;
    }
}
