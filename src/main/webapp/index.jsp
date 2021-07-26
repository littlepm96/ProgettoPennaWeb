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
<%@ include file="header.jsp" %>
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
                    <h2>Dettagli Programmi</h2>
                    <img src="images/info.jpg" alt="info" width="125" height="111">
                </a>
            </div>
            <div class="descrizione-programma">Rai 1 è il primo canale televisivo della Rai, l'azienda pubblica italiana concessionaria in esclusiva
                del servizio pubblico radiotelevisivo italiano. È il primo canale italiano in assoluto in termini di ascolti e si presenta con una
                programmazione generalista per le famiglie.</div>
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
                    <h2>Dettagli Programmi</h2>
                    <img src="images/info.jpg" alt="info" width="125" height="111">
                </a>
            </div>
            <div class="descrizione-programma">Rai 2 è il secondo canale televisivo della Rai,
                l'azienda pubblica concessionaria in esclusiva del servizio pubblico radiotelevisivo
                italiano. Ha una programmazione di tipo generalista rivolta ad un pubblico giovane,
                proponendo reality show, intrattenimento, telefilm, docufiction, informazione, cartoni animati,
                divulgazione scientifica e sport.</div>
        </div>
        <div class="canale">
            <div class="info-canale">
                <a href="${pageContext.request.contextPath}/dettaglio-canale?id=3">
                    <h1>Rai 3</h1>
                    <img src="images/tg3.jpg" alt="tg3" width="125" height="111">
                </a>
            </div>

            <div class="immagine">
                <a href="${pageContext.request.contextPath}/dettaglio-programma?id=2355">
                    <h2>Dettagli Programmi</h2>
                    <img src="images/info.jpg" alt="info" width="125" height="111">
                </a>
            </div>
            <div class="descrizione-programma">Rai 3 è il terzo canale televisivo della Rai, l'azienda
                concessionaria del servizio pubblico radiotelevisivo italiano. Il canale è di tipo generalista e
                propone una programmazione improntata all'approfondimento giornalistico e alla cultura, caratterizzata
                da ampi spazi dedicati alle autonomie locali italiane.</div>
        </div>
    </div>
    <!--FINE LISTA CANALI-->
</main>
<!--FINE CONTENUTO PRINCIPALE-->
<!--INIZIO FOOTER-->
<%@ include file="footer.jsp" %>
<!--FINE FOOTER-->
</body>
</html>
