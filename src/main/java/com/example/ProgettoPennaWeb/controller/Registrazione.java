package com.example.ProgettoPennaWeb.controller;

import com.example.ProgettoPennaWeb.model.Utente;
import com.example.ProgettoPennaWeb.persistenza.dao.UtenteDAO;
import com.example.ProgettoPennaWeb.utility.ErrorHandling;
import com.example.ProgettoPennaWeb.utility.SecurityLayer;
import org.apache.commons.codec.digest.DigestUtils;

import javax.naming.NamingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileAlreadyExistsException;
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

        String name = SecurityLayer.addSlashes(request.getParameter("name"));
        String surname = SecurityLayer.addSlashes(request.getParameter("surname"));
        String email = SecurityLayer.addSlashes(request.getParameter("email"));
        String pass = SecurityLayer.addSlashes(request.getParameter("pass"));

        UtenteDAO dao = new UtenteDAO();
        Utente u = new Utente();
        u.setEmail(email);
        u.setPassword(DigestUtils.sha256Hex(pass)); //La password viene criptata con una funzione hash (usiamo SHA-256)
        u.setNome(name);
        u.setCognome(surname);
        //Ci generiamo l'hash casuale
        Random r = new Random();
        r.nextInt(999999);
        String newHash = DigestUtils.md5Hex(""+r); //per l'hash casuale usiamo MD5
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
            request.setAttribute("exception", sqe);
            ErrorHandling.handleError(request,response);
            return;
        } catch (NamingException ne) {
            request.setAttribute("exception", ne);
            ErrorHandling.handleError(request,response);
            return;
        }

        //genero l'email di verifica (per il prototipo generiamo su un file testo)
        request.setAttribute("hash", newHash);
        try {
            generateVerificationEmail(request, response);
        }catch (IOException ie){
            request.setAttribute("exception", ie);
            ErrorHandling.handleError(request,response);
            return;
        }
    }

    private void generateVerificationEmail(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String hash = (String) request.getAttribute("hash");

        //apriamo uno stream su un nuovo file, per simulare l'email

        File pathEmail = new File( System.getProperty("user.dir")
                +File.separator+"test"+File.separator+email+".txt");
        pathEmail = pathEmail.getAbsoluteFile();
        System.out.println(pathEmail);
        pathEmail.getParentFile().mkdirs();
        boolean isCreated = pathEmail.createNewFile();
        if(isCreated) {
            try(PrintWriter writer = new PrintWriter(pathEmail)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Salve ");
                sb.append(name);
                sb.append(" ");
                sb.append(surname);
                sb.append(",");
                writer.print(sb); //prima riga
                sb.setLength(0); //svuoto il buffer
                writer.println();
                writer.print("questa è una email generata automaticamente,"); //prima riga
                writer.println();
                writer.print("clicca il seguente link per verificare il tuo account:"); //terza riga
                writer.println();
                //Generiamo il link di verifica
                sb.append("http://");
                sb.append(request.getServerName());
                sb.append(":");
                sb.append(request.getServerPort());
                sb.append(getServletContext().getContextPath());
                sb.append("/activate?email=");
                sb.append(email);
                sb.append("&hash=");
                sb.append(hash);
                writer.print(sb);
                sb.setLength(0);
                writer.println();
            }
        }else{
            System.err.println("Il file è già stato creato");
            throw new FileAlreadyExistsException("Il file è già stato creato");
        }

    }
}
