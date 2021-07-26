<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.ProgettoPennaWeb.model.ProgrammaTelevisivo" %>
<%@ page import="com.example.ProgettoPennaWeb.model.Canale" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.LocalTime" %>
<%@ page import="com.example.ProgettoPennaWeb.utility.FasciaOraria" %>
<%@ page import="com.example.ProgettoPennaWeb.utility.MalformedFasciaOrariaException" %>
<%@ page import="java.util.TreeMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.ProgettoPennaWeb.utility.SecurityLayer" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<!--CSS-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cerca.css">
<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Lato&display=swap" rel="stylesheet">
<!--nascondo il bottone di submit che usa javascript se è disabilitato-->
<noscript>
  <style>
    #submit-with-script {display:none;}
  </style>
</noscript>

<head>
  <title>Cerca programmi TV</title>
</head>
<body>
<!--INIZIO HEADER-->
<%@ include file="header.jsp" %>
<%
  //Variabili usate per riempire il form e generare il link di salvataggio della ricerca
  String paramTitolo = null;
  String paramGenere = null;
  String paramNumeroCanale = null;
  String paramDataTrasmissioneInizio = null;
  String paramDataTrasmissioneFine = null;
  String paramFasciaOrariaInizio = null;
  String paramFasciaOrariaFine = null;

  if(request.getAttribute("esistono_parametri") != null) {
    //prendo tutti i parametri (stringhe vuote per quelli assenti)
    paramTitolo = request.getParameter("titolo") != null ? request.getParameter("titolo") : "";
    paramGenere = request.getParameter("genere") != null ? request.getParameter("genere") : "";
    paramNumeroCanale = request.getParameter("numero_canale") != null ? request.getParameter("numero_canale") : "";
    paramDataTrasmissioneInizio = request.getParameter("data_trasmissione_inizio") != null ? request.getParameter("data_trasmissione_inizio") : "";
    paramDataTrasmissioneFine = request.getParameter("data_trasmissione_fine") != null ? request.getParameter("data_trasmissione_fine") : "";
    paramFasciaOrariaInizio = request.getParameter("fascia_oraria_inizio") != null ? request.getParameter("fascia_oraria_inizio") : "";
    paramFasciaOrariaFine = request.getParameter("fascia_oraria_fine") != null ? request.getParameter("fascia_oraria_fine") : "";
  }
%>
<!--FINE HEADER-->
<!--INIZIO CONTENUTO PRINCIPALE-->
<main>
  <div id="form-container">
  <!--Form con i parametri di ricerca-->
  <form id="form-ricerca" action="${pageContext.request.contextPath}/cerca" method="get">
    <div class="form-row">
      <!--Titolo programma-->
      <div class="autocomplete">
        <label for="titolo">Titolo: </label>
        <input type="text" id="titolo" name="titolo" placeholder="titolo programma" title="Titolo del programma da cercare" pattern="([0-9]|[a-z]|[A-Z]|\(|\)|\[|\]|/|\s)*" value="<%=paramTitolo!=null ? paramTitolo : ""%>">
      </div>
      <!--Genere programma-->
      <div id="genere-select">
        <label for="genere">Genere: </label>
        <select id="genere" name="genere">
          <!--Generiamo la lista degli option-->
          <%String[] generi = (String[]) request.getAttribute("generi_disponibili");
          for(int i = 0; i<generi.length; i++){
            //Controllo se c'è il genere corrente è da selezionare (ricerca salvata o ripopolamento del form)
            if(paramGenere!=null && paramGenere.equals(generi[i])){

              //Seleziona questo genere
          %>
          <option value="<%=generi[i]%>" selected><%=generi[i]%></option>
          <%
            }else{ //non selezionare questo genere
          %>
          <option value="<%=generi[i]%>"><%=generi[i]%></option>

          <%
              }
            }
          %>
        </select>
      </div>
      <!--Numero canale-->
      <div class="autocomplete">
        <label for="numero-canale">Canale: </label>
        <input type="number" id="numero-canale" name="numero_canale" placeholder="es.: 1" title="Numero del canale" value="<%=paramNumeroCanale%>">
      </div>
      <!--Data trasmissione-->
      <fieldset>
        <legend>Cerca per data</legend>
      <div class="form-column">
        <div id="data-trasmissione-inizio-d">
          <label for="data-trasmissione-inizio">Da: </label>
          <input type="date" id="data-trasmissione-inizio" name="data_trasmissione_inizio" class="date-selector" value="<%=paramDataTrasmissioneInizio%>">
        </div>
        <div id="data-trasmissione-fine-d">
          <label for="data-trasmissione-fine">A: </label>
          <input type="date" id="data-trasmissione-fine" name="data_trasmissione_fine" class="date-selector" value="<%=paramDataTrasmissioneFine%>">
        </div>
      </div><!--form-column-->
      </fieldset>
      <!--Fascia oraria-->
      <%
        //Ricavo i LocalTime se bisogna popolare i campi (Chiamata FasciaOraria.formatOrario necessaria per evitare eccezioni su stringhe tipo 0:17 e 12:7 (vengono convertiti a 00:17 e 12:07)
        LocalTime inizio = paramFasciaOrariaInizio!=null && !paramFasciaOrariaInizio.isEmpty() ? LocalTime.parse(FasciaOraria.formatOrario(paramFasciaOrariaInizio)) : null;
        LocalTime fine = paramFasciaOrariaFine!=null && !paramFasciaOrariaFine.isEmpty() ? LocalTime.parse(FasciaOraria.formatOrario(paramFasciaOrariaFine)) : null;
      %>
      <fieldset>
        <legend>Cerca per ora di inizio</legend>
      <div class="form-column">
        <div id="fascia-oraria-inizio">
          <input type="hidden" name="fascia_oraria_inizio">
          <label for="fascia-oraria-inizio-ore">Da: </label>
          <input type="number" id="fascia-oraria-inizio-ore" placeholder="(0-23)" class="time-selector" value="<%=inizio != null ? inizio.getHour() : ""%>">
          <label for="fascia-oraria-inizio-minuti"><strong> : </strong></label>
          <input type="number" id="fascia-oraria-inizio-minuti" placeholder="(0-59)" class="time-selector" value="<%=inizio != null ? inizio.getMinute() : ""%>">
        </div>
        <div id="fascia-oraria-fine">
          <input type="hidden" name="fascia_oraria_fine">
          <label for="fascia-oraria-fine-ore">A:  </label>
          <input type="number" id="fascia-oraria-fine-ore" placeholder="(0-23)" class="time-selector" value="<%=fine != null ? fine.getHour() : ""%>">
          <label for="fascia-oraria-fine-minuti"><strong> : </strong></label>
          <input type="number" id="fascia-oraria-fine-minuti" placeholder="(0-59)" class="time-selector" value="<%=fine != null ? fine.getMinute() : ""%>">
        </div>
      </div><!--form-column-->
      </fieldset>
      <div class="form-column">
        <!--In caso di script disabilitati usa un bottone di tipo submit-->
        <noscript>
          <button type="submit" id="submit-no-script" class="submit"onclick="validate()">Cerca</button>
        </noscript>
      <button type="button" id="submit-with-script" class="submit" onclick="validate()">Cerca</button>
      <%//inseriamo il link per salvare la ricerca se siamo loggati e abbiamo parametri
        if(sessione != null && request.getAttribute("esistono_parametri") != null){
          //costruisco il link
          StringBuilder sb = new StringBuilder(application.getContextPath());
          sb.append("/salva-ricerca?");
          sb.append("titolo=");
          sb.append(paramTitolo);
          sb.append("&genere=");
          sb.append(paramGenere);
          sb.append("&numero_canale=");
          sb.append(paramNumeroCanale);
          sb.append("&data_trasmissione=");
          //Codifichiamo il range di date nella ricerca salvata come <data inizio>/<data fine>
          if(paramDataTrasmissioneInizio!=null && !paramDataTrasmissioneInizio.isEmpty() && paramDataTrasmissioneFine!=null && !paramDataTrasmissioneFine.isEmpty()) {
            sb.append(paramDataTrasmissioneInizio);
            sb.append("/");
            sb.append(paramDataTrasmissioneFine);
          }
          sb.append("&fascia_oraria=");
          if(paramFasciaOrariaInizio!=null && !paramFasciaOrariaInizio.isEmpty() && paramFasciaOrariaFine!=null && !paramFasciaOrariaFine.isEmpty()) {
            sb.append(FasciaOraria.encode(LocalTime.parse(paramFasciaOrariaInizio), LocalTime.parse(paramFasciaOrariaFine)));
          }
          String linkDiSalvataggio = sb.toString();
      %>
        <a href="<%=linkDiSalvataggio%>">Salva la ricerca corrente</a>
        <%
        }
        %>
      </div>
    </div> <!--form-row-->
  </form>
  <!--Fine del form-->
    <!--Pulsante per mostrare/nascondere il form di ricerca-->
    <div id="form-toggle">
    <button type="button" onclick="toggleForm(this)">x</button>
    </div>
  </div>
  <!--Pagina dei risultati.-->
  <div id="risultati-ricerca" class="canale-flex-container">
      <%
        Map<ProgrammaTelevisivo,Canale> risultato = (Map<ProgrammaTelevisivo,Canale>) request.getAttribute("risultato_ricerca");
        for(Map.Entry<ProgrammaTelevisivo,Canale> entry : risultato.entrySet()){
          Canale canaleDelProgramma = entry.getValue(); //Info canale
          Long idCanale = canaleDelProgramma.getId(); //Ci serve per ottenere il riferimento all'immagine del canale
          String nomeCanale = canaleDelProgramma.getNome();

          ProgrammaTelevisivo programma = entry.getKey(); //Info programma
          Long idProgramma = programma.getId(); //Ci serve per ottenere il riferimento all'immagine del programma (se c'è)
          String titolo = programma.getTitolo();
          String genere =  programma.getGenere().toString();
          String dataTrasmissione = programma.getDataTrasmissione().toString();
          LocalTime oraInizio = programma.getOrarioInizio();
          LocalTime oraFine = programma.getOrarioFine();
          String fasciaOraria = "ERRORE_FASCIA";
          try {
             fasciaOraria = FasciaOraria.encode(oraInizio,oraFine);
          } catch (MalformedFasciaOrariaException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            /*request.setAttribute("exception", e);
            ErrorHandling.handleError(request,response);
            return;
            */
          }
          String descrizione = programma.getDescrizione();

      %>
    <!--Inizio del for loop-->
    <div id="<%=programma.getId()%>" class="programma">
      <div class="info-canale">
        <a href="${pageContext.request.contextPath}/dettaglio-canale?id=<%=idCanale%>">
          <h1><%=nomeCanale%></h1>
          <img src="${pageContext.request.contextPath}/images/canale_<%=idCanale%>.jpg" alt="canale_<%=idCanale%>.jpg" width="125" height="111">
        </a>
      </div>
      <div class="info-programma">
        <a href="${pageContext.request.contextPath}/dettaglio-programma?id=<%=idProgramma%>>">
          <h2><%=titolo%></h2>
          <img src="images/programma_<%=idProgramma%>.jpg" alt="programma_<%=idProgramma%>.jpg" width="125" height="111">
        </a>
        <a class="tag-genere" href="cerca?genere=<%=genere%>"><%=genere%></a>
        <span class="data-trasmissione"><%=dataTrasmissione%></span>
        <span class="ora-trasmissione"><%=fasciaOraria%></span>
      </div>
      <div class="descrizione-programma"><%=descrizione%></div>
    </div>
    <!--Fine del for loop-->
    <%
      }
    %>
  </div>
</main>
<!--FINE CONTENUTO PRINCIPALE-->
<!--INIZIO FOOTER-->
<%@ include file="footer.jsp" %>
<!--FINE FOOTER-->
<!--SCRIPT-->
<script type="text/javascript">
  //Toggle del form
  function toggleForm(button) {
    var x = document.getElementById("form-ricerca");
    if (x.classList.contains("hidden")) {
      x.classList.remove("hidden");
      button.innerHTML="x";
    } else {
      x.classList.add("hidden");
      button.innerHTML="+";

    }
  }
  //Validazione del form
  function validate(){
    const INVALID_CLASS = "invalid";
    var form = document.forms[0];
    var usingData = false, usingFascia = false;
    var fasciaIsValid = true;
    var dataIsValid = true;
    var data_inizio, data_inizio_value, data_fine, data_fine_value, inizio_ore, inizio_ore_value, inizio_minuti, inizio_minuti_value, fine_ore, fine_ore_value, fine_minuti, fine_minuti_value;
    if(form) {
      var elems = form.elements;

      //Data trasmissione
      data_fine = elems.namedItem("data-trasmissione-fine");
      data_inizio = elems.namedItem("data-trasmissione-inizio");
      data_fine_value = new Date(data_fine.value);
      data_inizio_value = new Date(data_inizio.value);
      if((data_inizio_value != null && data_inizio_value != "") || (data_fine_value != null && data_fine_value!= "")) {
        console.log(data_inizio_value > data_fine_value);
        usingData = true;
        if (data_inizio_value > data_fine_value) {
          data_inizio.classList.add(INVALID_CLASS);
          data_fine.classList.add(INVALID_CLASS);
          dataIsValid = false;
        } else {
          data_inizio.classList.remove(INVALID_CLASS);
          data_fine.classList.remove(INVALID_CLASS);
        }
      }

      inizio_ore = elems.namedItem("fascia-oraria-inizio-ore");
      inizio_ore_value = inizio_ore.value;
      inizio_minuti = elems.namedItem("fascia-oraria-inizio-minuti");
      inizio_minuti_value = inizio_minuti.value;
      fine_ore = elems.namedItem("fascia-oraria-fine-ore");
      fine_ore_value = fine_ore.value;
      fine_minuti = elems.namedItem("fascia-oraria-fine-minuti");
      fine_minuti_value = fine_minuti.value;

      if((inizio_ore_value != null && inizio_ore_value != "")||
              (inizio_minuti_value != null && inizio_minuti_value != "")||
              (fine_ore_value != null && fine_ore_value != "")||
              (fine_minuti_value != null && fine_minuti_value != "")) {
        usingFascia = true;
        //Inizio (ore)
        if (inizio_ore_value == null || inizio_ore_value == "" || inizio_ore_value < 0 || inizio_ore_value > 23) {
          inizio_ore.classList.add(INVALID_CLASS);
          fasciaIsValid = false;
        } else {
          inizio_ore.classList.remove(INVALID_CLASS);
        }
        //Inizio (minuti)
        if (inizio_minuti_value == null || inizio_minuti_value == "" || inizio_minuti_value < 0 || inizio_minuti_value > 59) {
          inizio_minuti.classList.add(INVALID_CLASS);
          fasciaIsValid = false;
        } else {
          inizio_minuti.classList.remove(INVALID_CLASS);
        }
        //Fine(ore)
        if (fine_ore_value == null || fine_ore_value == "" || fine_ore_value < 0 || fine_ore_value > 23) {
          fine_ore.classList.add(INVALID_CLASS);
          fasciaIsValid = false;
        } else {
          fine_ore.classList.remove(INVALID_CLASS);
        }
        //Fine (minuti)
        if (fine_minuti_value == null || fine_minuti_value == "" || fine_minuti_value < 0 || fine_minuti_value > 59) {
          fine_minuti.classList.add(INVALID_CLASS);
          fasciaIsValid = false;
        } else {
          fine_minuti.classList.remove(INVALID_CLASS);
        }
      }//if(inizio_ore_value!=null...
      //Controllo che il form sia validato
      if(dataIsValid && fasciaIsValid){


        //compilo la fascia oraria da mandare al server usando i campo hidden (solo se stiamo effettivamente usando la fascia oraria per filtrare)
        if(usingFascia) {
          form.elements.namedItem("fascia_oraria_inizio").value = inizio_ore_value + ":" + inizio_minuti_value;
          alert(form.elements.namedItem("fascia_oraria_inizio").value);

          form.elements.namedItem("fascia_oraria_fine").value = fine_ore_value + ":" + fine_minuti_value;
          alert(form.elements.namedItem("fascia_oraria_fine").value);
        }
        form.requestSubmit();
      }else{
        //Form invalido, mostra il messaggio di errore corrispondente
        //Rimuovi eventualmente messaggi di errori precedenti
        let error_element= document.getElementById("error-message");
        if(error_element) {
        error_element.remove();
        }
        //creane il nuovo messaggio di errore
        error_element = document.createElement("div");
        error_element.id = "error-message";
        error_element.innerHTML = error_element.innerHTML + "<ul>";
        if(!fasciaIsValid) {
          error_element.innerHTML = error_element.innerHTML+"<li>Inserisca un orario valido.</li>";
        }
        if(!dataIsValid){
          error_element.innerHTML = error_element.innerHTML+"<li>La data di inizio deve precedere quella di fine.</li>";

        }
        error_element.innerHTML = error_element.innerHTML +"</ul>";
        form.parentElement.insertBefore(error_element,form);
      }
    } //if(form)
  }
</script>
<!--Box animato di Marco-->
<!--
<div class="search-container">
      <input type="text" id="myinput" onkeyup="cerca()" placeholder="scrivi qualcosa.." title="Type in a name">
      <a href="#" class="search-btn">
        <i class="fas fa-search"></i>
      </a>
    </div>

    <ul id="myUL">
  <li><a href="#">sta</a></li>
  <li><a href="#">roba</a></li>

  <li><a href="#">va</a></li>
  <li><a href="#">press</a></li>

  <li><a href="#">dal</a></li>
  <li><a href="#">db</a></li>
  <li><a href="#">cazzo dio</a></li>
</ul>
</main>
<footer>
</footer>
<script>
  function cerca() {
    var input, filter, ul, li, a, i, txtValue;
    input = document.getElementById("myInput");
    filter = input.value.toUpperCase();
    ul = document.getElementById("myUL");
    li = ul.getElementsByTagName("li");
    for (i = 0; i < li.length; i++) {
      a = li[i].getElementsByTagName("a")[0];
      txtValue = a.textContent || a.innerText;
      if (txtValue.toUpperCase().indexOf(filter) > -1) {
        li[i].style.display = "";
      } else {
        li[i].style.display = "none";
      }
    }
  }
-->
</body>
</html>