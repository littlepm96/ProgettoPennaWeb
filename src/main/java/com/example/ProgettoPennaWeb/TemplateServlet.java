package com.example.ProgettoPennaWeb;

import freemarker.core.HTMLOutputFormat;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.Template;
import freemarker.template.TemplateDateModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author IngegneriaDelWeb
 */
public class TemplateServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");

        //configurazione di Freemarker (compatibile con la versione 2.3.31)
        //Freemarker configuration (compatible with version 2.3.31)
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        //impostiamo l'encoding di default per l'input e l'output
        //set the default input and outpout encoding
        cfg.setOutputEncoding("utf-8");
        cfg.setDefaultEncoding("utf-8");
        //impostiamo la directory (relativa al contesto) da cui caricare i templates
        //set the (context relative) directory for template loading
        cfg.setServletContextForTemplateLoading(getServletContext(), "templates");
        //impostiamo un handler per gli errori nei template - utile per il debug
        //set an error handler for debug purposes
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        //impostiamo il gestore degli oggetti - trasformerà in hash i Java beans
        //set the object handler that allows us to "view" Java beans as hashes
        DefaultObjectWrapperBuilder owb = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_31);
        owb.setForceLegacyNonListCollections(false);
        owb.setDefaultDateType(TemplateDateModel.DATETIME);
        cfg.setObjectWrapper(owb.build());
        //impostiamo il tipo di output: in questo modo freemarker abiliterà il necessario escaping
        //set the output format, so that freemarker will enable the correspondoing escaping
        cfg.setOutputFormat(HTMLOutputFormat.INSTANCE);

        //creiamo il data model - la root deve essere una hash (Map)
        //create the data model - the root must be an hash (Map)
        Map<String, Object> datamodel = new HashMap<>();
        /*datamodel.put("name", "paolino");
        datamodel.put("surname", "paperino");
        datamodel.put("number", 1);
        //una hash nidificata
        //a nested hash
        Map<String, Object> nesteddata = new HashMap<>();
        nesteddata.put("street", "qualcosa");
        nesteddata.put("number", "13");
        nesteddata.put("city", "Paperopoli");
        datamodel.put("address", nesteddata);
        //una lista nidificata
        //a nested list
        List<Map> relatives = new ArrayList();
        //ciascun elemento della lista è a sua volta una hash
        //each list element is an hash
        Map<String, Object> relative = new HashMap<>();
        relative.put("name", "Qui");
        relative.put("age", "13");
        relatives.add(relative);
        relative = new HashMap<>();
        relative.put("name", "Quo");
        relative.put("age", "12");
        relatives.add(relative);
        relative = new HashMap<>();
        relative.put("name", "Qua");
        relative.put("age", "11");
        relatives.add(relative);
        datamodel.put("relatives", relatives);
        //possiamo anche passare dei beans a freemarker: verranno interpretati come hash
        //we can also pass beans to freemarker: they are interpreted as hashes
        Person p = new Person();
        p.setName("Mickey");
        p.setSurname("Mouse");
        p.setAge(50);
        p.setAddress(new Address());
        p.getAddress().setStreet("via dei platani");
        p.getAddress().setCity("Topolinia");
        datamodel.put("person", p);*/
        datamodel.put("title", "My template test");

        //impostiamo il nome del file che verrà incluso tramite la direttiva include
        //set the file name to be included through the include directive
        datamodel.put("head", "nested_template.ftl.html");
        //notare come il template incluso veda lo stesso data model di quello principale
        //note that the included template views the same data model as the main tamplate

        //carichiamo il template
        //load the template
        Template t = cfg.getTemplate("template_test.ftl.html");
        try {
            //...e lo compiliamo insieme ai dati
            //...and compile it with the data
            t.process(datamodel, response.getWriter());
        } catch (TemplateException ex) {
            Logger.getLogger(TemplateServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "A test that generate a page using freemarker template language";
    }// </editor-fold>
}
