<%@ page import="com.example.ProgettoPennaWeb.utility.SecurityLayer" %>
<%@ page import="com.example.ProgettoPennaWeb.model.Canale" %>
<%@ page import="com.example.ProgettoPennaWeb.model.ProgrammaTelevisivo" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Guida TV</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
</head>
<body>
<!--INIZIO HEADER-->
<%@ include file="header.jsp" %>
<!--FINE HEADER-->
<!--INIZIO CONTENUTO PRINCIPALE-->
<%
    //prendiamo il risultato della query al DB e li carichiamo nella pagina
    Map<Canale, ProgrammaTelevisivo> risultato = (Map<Canale, ProgrammaTelevisivo>) request.getAttribute("risultato");
%>
<main>
    <h1>Programmi in onda</h1>
    <!--INIZIO LISTA CANALI-->
    <div class="canale-flex-container">
        <%
            for(Map.Entry<Canale,ProgrammaTelevisivo> entry : risultato.entrySet()){
                Canale canale = entry.getKey();
                ProgrammaTelevisivo programmaInOnda = entry.getValue();
        %>
        <div class="canale">
            <div class="info-canale">
                <a href="${pageContext.request.contextPath}/dettaglio-canale?id=<%=canale.getId()%>">
                    <h1><%=canale.getNome()%></h1>
                    <img src="images/img_programma_<%=canale.getNome()%>.jpg" alt="<%=canale.getNome()%>" width="125" height="111">
                </a>
            </div>
            <div class="programma-in-onda">
                <a href="${pageContext.request.contextPath}/dettaglio-programma?id=<%=programmaInOnda.getId()%>">
                    <h2><%=programmaInOnda.getTitolo()%></h2>
                    <img src="images/img_programma_<%=programmaInOnda.getTitolo()%>.jpg" alt="info" width="125" height="111">
                </a>
            </div>
            <div class="descrizione-programma"><%=programmaInOnda.getDescrizione()%></div>
        </div>
        <%
            }
        %>

    <!--FINE LISTA CANALI-->
</main>
<!--FINE CONTENUTO PRINCIPALE-->
<!--INIZIO FOOTER-->
<%@ include file="footer.jsp" %>
<!--FINE FOOTER-->
</body>
</html>