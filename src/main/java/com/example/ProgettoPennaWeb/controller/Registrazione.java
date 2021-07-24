package com.example.ProgettoPennaWeb.controller;

import com.example.ProgettoPennaWeb.model.Utente;
import com.example.ProgettoPennaWeb.persistenza.dao.UtenteDAO;
import org.apache.commons.codec.digest.DigestUtils;

import javax.naming.NamingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Random;

public class Registrazione extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");

        UtenteDAO dao = new UtenteDAO();
        Utente u = new Utente();
        u.setEmail(email);
        u.setPassword(pass);
        u.setNome(name);
        u.setCognome(surname);
        //Ci generiamo l'hash casuale
        Random r = new Random();
        r.nextInt(999999);
        String newHash = DigestUtils.md5Hex(""+r);
        u.setHash(newHash);
        u.setVerificato(false);
        try {

            //Salvo sul DB
            boolean isSuccess = dao.save(u);

            if (isSuccess) {
                //Apriamo la pagina di benvenuto
                response.sendRedirect(getServletContext().getContextPath()+"/welcome");
            }

        } catch (SQLException sqe) {
            sqe.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }

        //genero l'email di verifica (per il prototipo generiamo su un file testo)
        request.setAttribute("hash", newHash);
        generateVerificationEmail(request, response);
    }

    private void generateVerificationEmail(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String hash = (String) request.getAttribute("hash");

        //apriamo uno stream su un nuovo file, per simulare l'email

        File pathEmail = new File(getServletContext().getContextPath()+File.separator+"test"+File.separator+email+".txt");
        pathEmail.getParentFile().mkdirs();
        boolean isCreated = pathEmail.createNewFile();
        if(isCreated) {
            PrintWriter writer = new PrintWriter(pathEmail);
            //OutputStream out = new FileOutputStream(pathEmail);
            StringBuilder sb = new StringBuilder();
            sb.append("Salve ");
            sb.append(name);
            sb.append(" ");
            sb.append(surname);
            sb.append(",\n");
            writer.append(sb.toString()); //prima riga
            writer.append("questa è una email generata automaticamente,\n"); //prima riga
            writer.append("Clicca il seguente link per verificare il profilo:\n"); //terza riga
            //Generiamo il link di verifica
            sb.append(getServletContext().getContextPath());
            sb.append("/activate?email=");
            sb.append(email);
            sb.append("&hash=");
            sb.append(hash);
            writer.append(sb.toString());
        }else{
            System.err.println("Il file è già stato creato");
            return;
        }

    }
}
