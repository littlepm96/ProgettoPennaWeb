<%@ page import="com.example.ProgettoPennaWeb.model.ProgrammaTelevisivo" %>
<%@ page import="com.example.ProgettoPennaWeb.model.Canale" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Guida TV - Fasce orarie</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/fasce-orarie.css">
</head>
<body>
<!--INIZIO HEADER-->
<%@ include file="header.jsp" %>
<!--FINE HEADER-->
<%
    String fasciaOraria = request.getParameter("fascia_oraria") == null ? "mattina" : request.getParameter("fascia_oraria");
    Map<Canale, List<ProgrammaTelevisivo>> risultato = (Map<Canale, List<ProgrammaTelevisivo>>) request.getAttribute("risultato");
%>
<!--INIZIO CONTENUTO PRINCIPALE-->
<main>

    <h1>Programmi in onda
        di <%=fasciaOraria%>
    </h1>
    <!--INIZIO SELETTORE FASCIA ORARIA-->
    <form action="${pageContext.request.contextPath}/fasce-orarie" method="get">
        <div>
            <select name="fascia_oraria" id="fascia-oraria-select">
                <%
                    if("mattina".equalsIgnoreCase(fasciaOraria)){
                %>
                <option class="fascia-oraria" value="mattina" selected>Mattina</option>
                <%
                    }else{
                %>
                <option class="fascia-oraria" value="mattina">Mattina</option>
                <%
                    }
                    if("pomeriggio".equalsIgnoreCase(fasciaOraria)){

                %>
                <option class="fascia-oraria" value="pomeriggio" selected>Pomeriggio</option>
                <%
                    }else{
                %>
                <option class="fascia-oraria" value="pomeriggio">Pomeriggio</option>
                <%
                    }
                    if("sera".equalsIgnoreCase(fasciaOraria)){

                %>
                <option class="fascia-oraria" value="sera" selected>Sera</option>
                <%
                    }else{
                %>
                <option class="fascia-oraria" value="sera">Sera</option>
                <%
                    }
                    if("notte".equalsIgnoreCase(fasciaOraria)){

                %>
                <option class="fascia-oraria" value="notte" selected>Notte</option>
                <%
                    }else{
                %>
                <option class="fascia-oraria" value="notte">Notte</option>
                <%
                    }
                %>
            </select>
            <button type="submit">Vai!</button>
        </div>
    </form>
    <!--FINE SELETTORE FASCIA ORARIA-->
    <!--INIZIO LISTA CANALI-->
    <div class="canale-flex-container">
        <%
            for(Canale c : risultato.keySet()){


        %>
        <div class="canale">
            <div class="info-canale">
                <a href="${pageContext.request.contextPath}/dettaglio-canale.jsp?id=<%=c.getId()%>">
                    <h1><%=c.getNome()%></h1>
                    <img src="images/img_canale_<%=c.getNumero()%>.jpg" alt="<%=c.getNome()%>" width="125" height="111">
                </a>
            </div>
            <!--INIZIO LISTA PROGRAMMI DEL CANALE-->
            <%
                for(ProgrammaTelevisivo p : risultato.get(c)){


            %>
            <div class="info-programma">
            <div class="orario-programma">
            <h2><%=p.getOrarioInizio()%>-<%=p.getOrarioFine()%></h2>
            </div>
            <div class="programma">
                <a href="${pageContext.request.contextPath}/dettaglio-programma?id=<%=p.getId()%>">
                    <h2><%=p.getTitolo()%></h2>
                    <img src="<%=p.getUrlRelativoImmagine()%>" alt="<%=p.getTitolo()%>" width="125" height="111">
                </a>
            </div>
            </div>
            <%
                }//for(ProgrammaTelevisivo...
            %>
        </div>
        <%
            }//for(Canale c :...
        %>
    </div>
    <!--FINE LISTA CANALI-->
</main>
<!--FINE CONTENUTO PRINCIPALE-->
<!--INIZIO FOOTER-->
<%@ include file="footer.jsp" %>
<!--FINE FOOTER-->
</body>
</html>
