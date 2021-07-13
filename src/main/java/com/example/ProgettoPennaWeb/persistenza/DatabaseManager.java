package com.example.ProgettoPennaWeb.persistenza;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseManager {

    /*singleton*/
    private static DatabaseManager instance = null;

    private static final String POOLER_RESOURCE_NAME = "java:comp/env/jdbc/pennaweb";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/pennaweb?ServerTimezone=UTC";

    private InitialContext ctx;
    private DataSource ds;

    //Inizializzo il data source e apro le connessioni ad esso
    private DatabaseManager() throws NamingException{
        ctx = new InitialContext();
        ds = (DataSource) ctx.lookup(POOLER_RESOURCE_NAME);

    }

    public static DatabaseManager getInstance() throws NamingException{
        if(instance == null){
            instance = new DatabaseManager();
        }
        return instance;
    }

   public Connection getConnection() throws SQLException {
        Connection con = ds.getConnection();
        return con;
   }
}
