<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Guida TV - Fasce orarie</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/fasce-orarie.css">
</head>
<body>
<!--INIZIO HEADER-->
<header>
    <div>
        <h1> Elenco programmi</h1>
    </div>
    <div>
        <h1><a href="https://www.disim.univaq.it/didattica/content.php?corso=77&pid=86&did=0"> Disim </a></h1>
    </div>
    <!--INIZIO NAVIGAZIONE-->
    <nav>
        <div class="topnav">
            <a href="${pageContext.request.contextPath}/index.jsp">Home</a>
        </div>
    </nav>
    <!--FINE NAVIGAZIONE-->
</header>
<!--FINE HEADER-->
<!--INIZIO CONTENUTO PRINCIPALE-->
<main>

    <h1>Programmi in onda
        di <%=request.getAttribute("fascia-selezionata") == null ? "mattina" : request.getAttribute("fascia-selezionata")%>
    </h1>
    <!--INIZIO SELETTORE FASCIA ORARIA-->
    <form action="${pageContext.request.contextPath}/fasce-orarie" method="get">
        <div>
            <select name="fascia-oraria" id="fascia-oraria-select">
                <option class="fascia-oraria" value="mattina">Mattina</option>
                <option class="fascia-oraria" value="pomeriggio">Pomeriggio</option>
                <option class="fascia-oraria" value="sera">Sera</option>
                <option class="fascia-oraria" value="notte">Notte</option>
            </select>
            <button type="submit">Vai!</button>
        </div>
    </form>
    <!--FINE SELETTORE FASCIA ORARIA-->
    <!--INIZIO LISTA CANALI-->
    <div class="canale-flex-container">
        <div class="canale">
            <div class="info-canale">
                <a href="${pageContext.request.contextPath}/dettaglio-canale.jsp?numero=1">
                    <h1>Rai 1</h1>
                    <img src="images/tg1.jpg" alt="lolle" width="125" height="111">
                </a>
            </div>
            <div class="orario-programma">

            </div>
            <div class="programma">
                <a href="${pageContext.request.contextPath}/programma.jsp?id=2353">
                    <h2>TG1</h2>
                    <img src="images/tg1.jpg" alt="TG1" width="125" height="111">
                </a>
            </div>
        </div>
    </div>
    <!--FINE LISTA CANALI-->
</main>
<!--FINE CONTENUTO PRINCIPALE-->
<!--INIZIO FOOTER-->
<footer></footer>
<!--FINE FOOTER-->
</body>
</html>
