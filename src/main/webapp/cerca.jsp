<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.ProgettoPennaWeb.model.ProgrammaTelevisivo" %>
<%@ page import="com.example.ProgettoPennaWeb.model.Canale" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.LocalTime" %>
<%@ page import="com.example.ProgettoPennaWeb.model.utility.FasciaOraria" %>
<%@ page import="com.example.ProgettoPennaWeb.model.utility.MalformedFasciaOrariaException" %>
<%@ page import="java.util.TreeMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<!--CSS-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cerca.css">
<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Lato&display=swap" rel="stylesheet">

<!--SCRIPTS-->
<!--Autocompletamento (W3C)-->
<link rel="script" href="${pageContext.request.contextPath}/js/autocomplete.js">
<head>
  <title>Cerca programmi TV</title>
</head>
<body>
<!--INIZIO HEADER-->
<header>
  <div>
    <h1>Ricerca programmi</h1>
  </div>
  <div>
    <h1><a href="https://www.disim.univaq.it/didattica/content.php?corso=77&pid=86&did=0"> bella zì </a></h1>
  </div>
  <!--INIZIO NAVIGAZIONE-->
  <nav>
    <div class="topnav">
      <a href="${pageContext.request.contextPath}/index.jsp">Home</a>
      <a href="${pageContext.request.contextPath}/fasce-orarie.jsp">Fasce orarie</a>
      <a href="${pageContext.request.contextPath}/login.jsp" class="login-page-button">login</a>
      <a href="${pageContext.request.contextPath}/cerca.jsp">Cerca</a>
    </div>
  </nav>
  <!--FINE NAVIGAZIONE-->
</header>
<!--FINE HEADER-->
<!--INIZIO CONTENUTO PRINCIPALE-->
<main>
  <!--Form con i parametri di ricerca-->
  <form autocomplete="off" action="${pageContext.request.contextPath}/cerca" method="get">
    <div class="form-row">
      <!--Titolo programma-->
      <div class="autocomplete">
        <label for="titolo">Titolo programma: </label>
        <input type="text" id="titolo" name="titolo" placeholder="titolo" title="Titolo del programma da cercare">
      </div>
      <!--Genere programma-->
      <div id="genere-select">
        <label for="genere">Genere: </label>
        <select id="genere" name="genere">
          <!--Generiamo la lista degli option-->
          <%String[] generi = (String[]) request.getAttribute("generi_disponibili");
          for(int i = 0; i<generi.length; i++){
          %>
          <!--CODICE HTML DEL FOR LOOP QUI-->
          <option value="<%=generi[i]%>"><%=generi[i]%></option>
          <!--FINE HTML DEL FOR LOOP-->
          <%
            }
          %>
        </select>
      </div>
      <!--Numero canale-->
      <div class="autocomplete">
        <label for="numero-canale">Canale: </label>
        <input type="number" id="numero-canale" name="numero_canale" placeholder="titolo" title="Titolo del programma da cercare">
      </div>
      <!--Fascia oraria-->
      <div class="form-column">
        <input type="hidden" id="fascia-oraria" name="fascia_oraria"> <!--Campo effettivamente passato al server-->
        <div id="fascia-oraria-inizio">
          <label for="fascia-oraria-inizio-ore">Da: </label>
          <input type="number" id="fascia-oraria-inizio-ore" class="time-selector">
          <label for="fascia-oraria-inizio-minuti"><strong> : </strong></label>
          <input type="number" id="fascia-oraria-inizio-minuti" class="time-selector">
        </div>
        <div id="fascia-oraria-fine">
          <label for="fascia-oraria-fine-ore">A:  </label>
          <input type="number" id="fascia-oraria-fine-ore" class="time-selector">
          <label for="fascia-oraria-fine-minuti"><strong> : </strong></label>
          <input type="number" id="fascia-oraria-fine-minuti" class="time-selector">
        </div>
      </div><!--form-column-->
    </div> <!--form-row-->
    <button type="button" id="submit" value="Cerca" onclick="validate()"></button>
  </form>
  <!--Fine del form-->
  <!--Pagina dei risultati.-->
  <div id="risultati-ricerca" class="canale-flex-container">
      <%
        Map<String,Object> data = (Map<String,Object>) request.getAttribute("data");
        List<ProgrammaTelevisivo> risultatiRicerca = (List<ProgrammaTelevisivo>) data.get("risultati_ricerca");
        List<Canale> canali = (List<Canale>) data.get("canali");
        Map<ProgrammaTelevisivo,Canale> canaleDiUnProgramma = (Map<ProgrammaTelevisivo, Canale>) data.get("canale_di_un_programma");
        for(ProgrammaTelevisivo programma : risultatiRicerca){
          Canale canaleDelProgramma = canaleDiUnProgramma.get(programma);
          Long idCanale = canaleDelProgramma.getId(); //Ci serve per ottenere il riferimento all'immagine del canale
          String nomeCanale = canaleDelProgramma.getNome();
          Long idProgramma = programma.getId(); //Ci server per ottenere il riferimento all'immagine del programma (se c'è)
          String titolo = programma.getTitolo();
          String genere =  programma.getGenere().toString();
          LocalDate dataTrasmissione = programma.getDataTrasmissione();
          LocalTime oraInizio = programma.getOrarioInizio();
          LocalTime oraFine = programma.getOrarioFine();
          String fasciaOraria = "ERRORE";
          try {
             fasciaOraria = FasciaOraria.encode(oraInizio,oraFine);
          } catch (MalformedFasciaOrariaException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
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
          <h2><%=titolo%>></h2>
          <img src="images/programma_<%=idProgramma%>.jpg" alt="programma_<%=idProgramma%>.jpg" width="125" height="111">
        </a>
        <a class="tag-genere" href="cerca?genere=<%=genere%>"><%=genere%></a>
        <span class="data-trasmissione"><%=dataTrasmissione.toString()%></span>
        <span class="ora-trasmissione"><%=fasciaOraria%></span>
      </div>
      <div class="descrizione-programma"><%=descrizione%></div>
    </div>
    <!--Fine del for loop-->
    <%
      }
    %>
  </div>

  </div>
</main>
<!--FINE CONTENUTO PRINCIPALE-->
<!--INIZIO FOOTER-->
<footer>
</footer>
<!--FINE FOOTER-->
<!--SCRIPT-->
<script type="text/javascript">
  function validate(){
    var form = document.forms[0];
    var isValid = true;
    if(form) {
      var elems = form.elements;
      for (var i = 0;i < elems.length;i++){
        switch (elems[i].id){
          case "fascia-oraria-fine-ore":
          case "fascia-oraria-inizio-ore":
            let value = elems[i].value;
            if(value < 0 || value > 23){
              elems[i].setCustomValidity("Inserisca un orario valido.");
              isValid = false;
            }else{
              elems[i].setCustomValidity("");
            }
            break;
            case "fascia-oraria-fine-minuti":
            case "fascia-oraria-inizio-minuti":
              if(value < 0 || value > 59){
                elems[i].setCustomValidity("Inserisca un orario valido.");
                isValid = false;
              }else{
                elems[i].setCustomValidity("");
              }

        } //switch
      } //for loop
      if(isValid){
        form.submit();
      }
    } //if(form)
  }

//Inizializziamo l'autocompletamento sui campi che ne fanno uso
  var titoli=["aaa","bb","prova","tg1","no","NOOOOO"];
  var canali=[1,2,3,4,5,6,7,8,9,10,11,12];
  autocomplete(document.getElementById("titolo"),titoli);
  autocomplete(document.getElementById("numero-canale"),canali);
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