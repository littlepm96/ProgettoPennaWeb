<%@ page import="com.example.ProgettoPennaWeb.model.ProgrammaTelevisivo" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.ProgettoPennaWeb.model.utility.FasciaOraria" %>
<%@ page import="java.time.LocalTime" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Programmi odierni di <%=request.getAttribute("nomeCanale")%>
    </title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dettaglio-canale.css">
</head>
<body>
</h1>
<!--INIZIO HEADER-->
<header>
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
            <a href="${pageContext.request.contextPath}/fasce-orarie.jsp">Fasce orarie</a>
            <a href="${pageContext.request.contextPath}/login.jsp" class="login-page-button">login</a>
            <a href="${pageContext.request.contextPath}/cerca.jsp">Cerca</a>
        </div>
    </nav>
    <!--FINE NAVIGAZIONE-->
</header>
<main>
    <h1>Programmi odierni di <%=request.getAttribute("nomeCanale")%>
        <!--INIZIO LISTA PROGRAMMI-->
        <div class="channels">

            <section class="channel ">
                <header class="channel-header">
                    <a href="https://guidatv.quotidiano.net/rai_1/" title="Programmi RaiUno">
            <span class="channel-logo">
    <picture>
        <img src="${pageContext.request.contextPath}/images/img_canale_1.jpg"
             class=" lazyloaded" title="RaiUno" alt="RaiUno" width="30" height="30">
    </picture>
            </span>
                        <span class="channel-name">RaiUno</span>

                    </a>
                </header>

                <div class="programs">

                    <!--INIZIO FASCIA MATTUTINA-->
                    <!--HEADER FASCIA-->
                    <div class="channel-schedule-divider mattina">
                        <span class="channel-schedule-icon">
                            <svg class="svg" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 32 17.45" role="img"
                                 fill="#666666" aria-labelledby="time_mattina_svg_title time_mattina_svg_desc">
    <g class="styleme">
        <path d="M0,16a1.46,1.46,0,0,1,1.47-1.45H30.53a1.45,1.45,0,1,1,0,2.91H1.47A1.45,1.45,0,0,1,0,16Z"></path>
        <path d="M25.27,8.79l2-2A1.45,1.45,0,1,0,25.25,4.7l-2,2a1.45,1.45,0,1,0,2.06,2.06Z"></path>
        <path d="M16,5.82a1.45,1.45,0,0,0,1.45-1.47V1.47a1.45,1.45,0,1,0-2.91,0V4.35A1.46,1.46,0,0,0,16,5.82Z"></path>
        <path d="M7.28,16h2.91a5.82,5.82,0,0,1,11.63,0h2.91A8.72,8.72,0,0,0,7.28,16Z"></path>
        <path d="M6.73,8.79A1.45,1.45,0,1,0,8.79,6.73l-2-2A1.45,1.45,0,1,0,4.7,6.75Z"></path>
    </g>
</svg>
                        </span>
                        <span class="channel-schedule-text">Programmi TV mattina</span>
                    </div>
                    <!--FINE HEADER FASCIA-->
                    <%
                        List<ProgrammaTelevisivo> programmiDiMattina = (List<ProgrammaTelevisivo>) request.getAttribute("programmiDiMattina");
                        Long idProgramma;
                        String titoloProgramma;
                        String genereProgramma;
                        LocalTime oraInizio;
                        LocalTime oraFine;


                        for (ProgrammaTelevisivo programmaDiMattina : programmiDiMattina) {

                            idProgramma = programmaDiMattina.getId();
                            titoloProgramma = programmaDiMattina.getTitolo();
                            genereProgramma = programmaDiMattina.getGenere().toString();
                            oraInizio = programmaDiMattina.getOrarioInizio();
                            oraFine = programmaDiMattina.getOrarioFine();

                    %>
                    <!--Template di un singolo programma-->
                    <a class="program"
                       href="${pageContext.request.contextPath}/dettaglio-programma?id=<%=idProgramma%>"
                       title="<%=titoloProgramma%>">
                        <div class="program-time">

                            <div class="hour"><%=FasciaOraria.encode(oraInizio,oraFine)%> </div>

                        </div>
                        <div class="program-info">

                            <div class="program-category"><%=genereProgramma%></div>

                            <!--<div class="program-rating">
                                <span class="stars"></span>

                            </div>-->

                            <div class="program-title"><%=titoloProgramma%>>
                            </div>
                        </div>
                    </a>
                    <!--Fine template programma-->
                    <%
                        } //Fine ciclo for dei programmi mattutini
                    %>
                    <!--FINE FASCIA MATTUTINA-->
                    <!--INIZIO FASCIA POMERIDIANA-->
                    <!--HEADER FASCIA-->
                    <div class="channel-schedule-divider pomeriggio">
                        <span class="channel-schedule-icon">
                            <svg class="svg" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 32 32" role="img"
                                 fill="#666666" aria-labelledby="time_pomeriggio_svg_title time_pomeriggio_svg_desc">
    <g class="styleme">
        <path d="M16,5.82a1.46,1.46,0,0,1-1.45-1.47V1.47a1.45,1.45,0,1,1,2.91,0V4.35A1.45,1.45,0,0,1,16,5.82Zm11.31,21.5a1.46,1.46,0,0,1-2.07,0l-2-2a1.45,1.45,0,1,1,2.06-2.06l2,2A1.45,1.45,0,0,1,27.31,27.31ZM16,21.81A5.82,5.82,0,1,0,10.18,16,5.82,5.82,0,0,0,16,21.81ZM16,32a1.46,1.46,0,0,1-1.45-1.47V27.65a1.45,1.45,0,1,1,2.91,0v2.88A1.45,1.45,0,0,1,16,32ZM23.2,8.8a1.46,1.46,0,0,1,0-2.07l2-2A1.45,1.45,0,1,1,27.3,6.75l-2,2A1.45,1.45,0,0,1,23.2,8.8ZM8.8,23.2a1.46,1.46,0,0,1,0,2.07l-2,2A1.45,1.45,0,1,1,4.7,25.25l2-2A1.45,1.45,0,0,1,8.8,23.2ZM16,24.72A8.72,8.72,0,1,1,24.72,16,8.72,8.72,0,0,1,16,24.72ZM0,16a1.46,1.46,0,0,1,1.47-1.45H4.35a1.45,1.45,0,1,1,0,2.91H1.47A1.45,1.45,0,0,1,0,16Zm26.18,0a1.46,1.46,0,0,1,1.47-1.45h2.88a1.45,1.45,0,1,1,0,2.91H27.65A1.45,1.45,0,0,1,26.18,16ZM4.69,4.69a1.46,1.46,0,0,1,2.07,0l2,2A1.45,1.45,0,1,1,6.73,8.79l-2-2A1.45,1.45,0,0,1,4.69,4.69Z"></path>
    </g>
</svg>

                        </span>
                        <span class="channel-schedule-text">Programmi TV pomeriggio</span>
                    </div>
                    <!--FINE HEADER FASCIA-->
                    <%
                        List<ProgrammaTelevisivo> programmiDiPomeriggio = (List<ProgrammaTelevisivo>) request.getAttribute("programmiDiPomerigggio");
                        //DICHIARAZIONI FATTE IN MATTINA. VENGONO LETTE PER TUTTE LE FASCE

                        for (ProgrammaTelevisivo programmaDiPomeriggio : programmiDiPomeriggio) {

                            idProgramma = programmaDiPomeriggio.getId();
                            titoloProgramma = programmaDiPomeriggio.getTitolo();
                            genereProgramma = programmaDiPomeriggio.getGenere().toString();
                            oraInizio = programmaDiPomeriggio.getOrarioInizio();
                            oraFine = programmaDiPomeriggio.getOrarioFine();

                    %>
                    <!--Template di un singolo programma-->
                    <a class="program"
                       href="${pageContext.request.contextPath}/dettaglio-programma?id=<%=idProgramma%>"
                       title="<%=titoloProgramma%>">
                        <div class="program-time">

                            <div class="hour"><%=FasciaOraria.encode(oraInizio,oraFine)%></div>


                        </div>
                        <div class="program-info">


                            <div class="program-category"><%=genereProgramma%></div>


                            <!-- <div class="program-rating">
                                <span class="stars"></span>

                            </div>-->

                            <div class="program-title"><%=titoloProgramma%></div>
                        </div>
                    </a>
                    <!--Fine template singolo programma-->
                    <%
                        } //Fine ciclo for dei programmi pomeridiani
                    %>
                    <!--FINE FASCIA POMERIDIANA-->
                    <!--INIZIO FASCIA SERALE-->
                    <!--HEADER FASCIA-->
                    <div class="channel-schedule-divider sera">
                        <span class="channel-schedule-icon">
                            <svg class="svg" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 23.7 24.5" role="img"
                                 fill="#666666" aria-labelledby="time_sera_svg_title time_sera_svg_desc">
    <g class="styleme">
        <path d="M9.2,2.6C6.6,7.9,8.8,14.4,14.1,17c2.5,1.2,5.4,1.4,8.1,0.6c-3.2,4.6-9.6,5.8-14.3,2.5S2.2,10.5,5.4,5.9C6.4,4.5,7.7,3.3,9.2,2.6z"></path>
    </g>
</svg>

                        </span>
                        <span class="channel-schedule-text">Programmi TV sera</span>
                    </div>
                    <!--FINE HEADER FASCIA-->
                    <%
                        List<ProgrammaTelevisivo> programmiDiSera = (List<ProgrammaTelevisivo>) request.getAttribute("programmiDiSera");
                        //DICHIARAZIONI FATTE IN MATTINA. VENGONO LETTE PER TUTTE LE FASCE

                        for (ProgrammaTelevisivo programmaDiSera : programmiDiSera){
                            idProgramma = programmaDiSera.getId();
                            titoloProgramma = programmaDiSera.getTitolo();
                            genereProgramma = programmaDiSera.getGenere().toString();
                            oraInizio = programmaDiSera.getOrarioInizio();
                            oraFine = programmaDiSera.getOrarioFine();

                    %>
                    <!--Template singolo programma-->
                        <a class="program"
                       href="${pageContext.request.contextPath}/dettaglio-programma?id=<%=idProgramma%>"
                       title="<%=titoloProgramma%>">
                        <div class="program-time">

                            <div class="hour"><%=FasciaOraria.encode(oraInizio,oraFine)%> </div>

                        </div>
                        <div class="program-info">

                            <div class="program-category"><%=genereProgramma%></div>

                            <!--<div class="program-rating">
                                <span class="stars"></span>

                            </div>-->

                            <div class="program-title"><%=titoloProgramma%>>
                            </div>
                        </div>
                    </a>

                    <!--Fine template singolo programma-->
                    <%
                        } //fine ciclo for per i programmi serali
                    %>

                    <!--FINE FASCIA SERALE-->
                    <!--INIZIO FASCIA NOTTURNA-->
                    <!--HEADER FASCIA-->
                    <div class="channel-schedule-divider notte">
                        <span class="channel-schedule-icon">
                            <svg class="svg" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 23.83 23.83" role="img"
                                 fill="#666666" aria-labelledby="time_notte_svg_title time_notte_svg_desc">
    <g class="styleme">
        <path d="M11.92,0A11.92,11.92,0,1,0,23.83,11.92,11.92,11.92,0,0,0,11.92,0Zm0,20.17A8.17,8.17,0,1,1,20.08,12,8.17,8.17,0,0,1,11.92,20.17Z"></path>
    </g>
</svg>

                        </span>
                        <span class="channel-schedule-text">Programmi TV notte</span>
                    </div>
                    <!--FINE HEADER FASCIA-->
                    <%
                        List<ProgrammaTelevisivo> programmiDiNotte = (List<ProgrammaTelevisivo>) request.getAttribute("programmiDiNotte");
                        //DICHIARAZIONI FATTE IN MATTINA. VENGONO LETTE PER TUTTE LE FASCE

                        for (ProgrammaTelevisivo programmaDiNotte : programmiDiNotte) {

                            idProgramma = programmaDiNotte.getId();
                            titoloProgramma = programmaDiNotte.getTitolo();
                            genereProgramma = programmaDiNotte.getGenere().toString();
                            oraInizio = programmaDiNotte.getOrarioInizio();
                            oraFine = programmaDiNotte.getOrarioFine();

                    %>
                    <!--Template di un singolo programma-->
                    <a class="program"
                       href="${pageContext.request.contextPath}/dettaglio-programma?id=<%=idProgramma%>"
                       title="<%=titoloProgramma%>">
                        <div class="program-time">

                            <div class="hour"><%=FasciaOraria.encode(oraInizio,oraFine)%> </div>

                        </div>
                        <div class="program-info">

                            <div class="program-category"><%=genereProgramma%></div>

                            <!--<div class="program-rating">
                                <span class="stars"></span>

                            </div>-->

                            <div class="program-title"><%=titoloProgramma%>>
                            </div>
                        </div>
                    </a>
                    <!--Fine template programma-->
                    <%
                        } //Fine del ciclo for per i programmi notturni
                    %>
                    <!--FINE FASCIA NOTTURNA-->
                </div>

            </section>


        </div>
        <!--FINE LISTA PROGRAMMI-->
</main>
</body>
</html>
