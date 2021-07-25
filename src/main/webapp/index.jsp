<%@ page import="com.example.ProgettoPennaWeb.utility.SecurityLayer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Guida TV</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
</head>
<body>
<!--INIZIO HEADER-->
<header>
    <div class="float-right">
        <%
            session = SecurityLayer.checkSession(request);
            if(session!=null){
        %>
        <a href="${pageContext.request.contextPath}/profilo-utente">Profilo</a>
        ||
        <a href="${pageContext.request.contextPath}/logout">Logout</a>
        <%
        }else{
        %>
        <a href="${pageContext.request.contextPath}/login.jsp">Login</a>
        <%
            }
        %>
    </div>
    <div>
        <h1> Guida TV</h1>
    </div>
    <div>
        <h1><a href="https://www.disim.univaq.it/didattica/content.php?corso=77&pid=86&did=0"> bella zì </a></h1>
    </div>
    <!--INIZIO NAVIGAZIONE-->
    <nav>
        <div class="topnav">
            <a href="${pageContext.request.contextPath}/index.jsp">Home</a>
            <a href="${pageContext.request.contextPath}/fasce-orarie">Fasce orarie</a>
            <a href="${pageContext.request.contextPath}/cerca">Cerca</a>
        </div>
    </nav>
    <!--FINE NAVIGAZIONE-->
</header>
<!--FINE HEADER-->
<!--INIZIO CONTENUTO PRINCIPALE-->
<main>
    <h1>Programmi in onda</h1>
    <!--INIZIO LISTA CANALI-->
    <div class="canale-flex-container">
        <div class="canale">
            <div class="info-canale">
                <a href="${pageContext.request.contextPath}/dettaglio-canale?id=1">
                    <h1>Rai 1</h1>
                    <img src="images/tg1.jpg" alt="tg1" width="125" height="111">
                </a>
            </div>
            <div class="programma-in-onda">
                <a href="${pageContext.request.contextPath}/dettaglio-programma?id=2353">
                    <h2>TG1</h2>
                    <img src="images/info.jpg" alt="info" width="125" height="111">
                </a>
            </div>
            <div class="descrizione-programma">Informazione delle 20:00</div>
        </div>
        <div class="canale">
            <div class="info-canale">
                <a href="${pageContext.request.contextPath}/dettaglio-canale?id=2">
                    <h1>Rai 2</h1>
                    <img src="images/tg2.jpg" alt="tg2" width="125" height="111">
                </a>
            </div>
            <div class="programma-in-onda">
                <a href="${pageContext.request.contextPath}/dettaglio-programma?id=2354">
                    <h2>NCIS</h2>
                    <img src="images/info.jpg" alt="info" width="125" height="111">
                </a>
            </div>
            <div class="descrizione-programma">NCIS Stagione 8 Episodio 35</div>
        </div>
        <div class="canale">
            <div class="info-canale">
                <a href="${pageContext.request.contextPath}/dettaglio-canale?id=3">
                    <h1>Rai 3</h1>
                    <img src="images/tg3.jpg" alt="tg3" width="125" height="111">
                </a>
            </div>
            <div class="programma-in-onda">
                <a href="${pageContext.request.contextPath}/dettaglio-programma?id=2355">
                    <h2>Report</h2>
                    <img src="images/info.jpg" alt="info" width="125" height="111">
                </a>
            </div>
            <div class="descrizione-programma">Sappiamo quello che stiamo facendo?</div>
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
