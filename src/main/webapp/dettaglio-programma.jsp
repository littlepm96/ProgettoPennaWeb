<%@ page import="com.example.ProgettoPennaWeb.model.ProgrammaTelevisivo" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dettaglio-programma.css">

</head>
<body>
<!--INIZIO HEADER-->
<%@ include file="header.jsp" %>
<!--FINE HEADER-->

<%
    ProgrammaTelevisivo programma = (ProgrammaTelevisivo) request.getAttribute("programma");
    List<ProgrammaTelevisivo> episodi = (List<ProgrammaTelevisivo>) request.getAttribute("episodi");
%>
<!--INIZIO CONTENUTO PRINCIPALE-->
<main>
    <div id="container-programma">
        <div id="titolo-programma">
            <h1><%=programma.getTitolo()%>
            </h1>
        </div>
        <div id="info-programma">
            <div id="sezione-immagine">
                <img id="immagine-programma" src="${pageContext.request.contextPath}/<%=programma.getUrlRelativoImmagine()%>" alt="<%=programma.getTitolo()%>" height="200" width="300">

                <p>Genere: <a href="/cerca?genere=<%=programma.getGenere()%>"><%=programma.getGenere()%></a></p>
                <p>Trasmette il: <%=programma.getDataTrasmissione()%></p>
                <p>alle <%=programma.getOrarioInizio()%>-<%=programma.getOrarioFine()%></p>
                <%
                    if(programma.getUrlApprofondimento()!=null && !programma.getUrlApprofondimento().toString().isEmpty()){
                %>
                    <p>Sito web: <a href="<%=programma.getUrlApprofondimento()%>"><%=programma.getUrlApprofondimento()%></a></p>
                <%
                    }
                %>
            </div>
            <div id="descrizione-programma"><%= episodi== null ? "" : "ST. "+programma.getStagione()+", EP. "+programma.getEpisodio()+"- " %><%=programma.getDescrizione()%></div>
        </div>
        <%
            if(episodi !=null){
        %>
        <div id="info-serie">
            <p>Altre puntate in onda questo mese:</p>
            <ul id="lista-puntate">
            <%
                for(ProgrammaTelevisivo episodio : episodi){
            %>
                <li><a href="${pageContext.request.contextPath}/dettaglio-programma?id=<%=episodio.getId()%>">Stagione <%=episodio.getStagione()%>, episodio <%=episodio.getEpisodio()%></a></li>
                <%
                    }
                %>
            </ul>
        </div>
        <%
            }
        %>
    </div>
</main>
<!--FINE CONTENUTO PRINCIPALE-->
<!--INIZIO FOOTER-->
<%@ include file="footer.jsp" %>
<!--FINE FOOTER-->
</body>
</html>
