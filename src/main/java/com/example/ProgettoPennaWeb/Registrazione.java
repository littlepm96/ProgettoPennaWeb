package com.example.ProgettoPennaWeb;

import com.example.ProgettoPennaWeb.model.Utente;
import com.example.ProgettoPennaWeb.persistenza.dao.UtenteDAO;

import javax.naming.NamingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Registrazione extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");


        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");

        try {

            // loading drivers for mysql
            Class.forName("com.mysql.jdbc.Driver");

            //creating connection with the database
            Connection con = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/PennaWeb", "root", "Admin_96");

            UtenteDAO dao = new UtenteDAO();
            Utente u = new Utente();
            u.setEmail(email);
            u.setPassword(pass);
            u.setNome(name);
            u.setCognome(surname);
            boolean isSuccess = dao.save(u);

            PrintWriter out = response.getWriter();
            if (isSuccess) {
                out.println("correttamente registrato");
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (SQLException sqe) {
            sqe.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
