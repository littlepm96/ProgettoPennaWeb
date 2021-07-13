<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Programmi odierni di <%=request.getAttribute("nomeCanale")%></title>
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
        <source data-srcset="https://immagini.quotidiano.net/?url=https%3A%2F%2Fs3.eu-west-1.amazonaws.com%2Fstatic.guidatv.quotidiano.net%2Fimg%2Floghi_tv%2Frai_1.png&amp;w=100&amp;h=30&amp;fmt=webp&amp;mode=fill&amp;bg=ffffff"
                type="image/webp"
                srcset="https://immagini.quotidiano.net/?url=https%3A%2F%2Fs3.eu-west-1.amazonaws.com%2Fstatic.guidatv.quotidiano.net%2Fimg%2Floghi_tv%2Frai_1.png&amp;w=100&amp;h=30&amp;fmt=webp&amp;mode=fill&amp;bg=ffffff">
        <source data-srcset="https://immagini.quotidiano.net/?url=https%3A%2F%2Fs3.eu-west-1.amazonaws.com%2Fstatic.guidatv.quotidiano.net%2Fimg%2Floghi_tv%2Frai_1.png&amp;w=100&amp;h=30&amp;mode=fill&amp;bg=ffffff"
                srcset="https://immagini.quotidiano.net/?url=https%3A%2F%2Fs3.eu-west-1.amazonaws.com%2Fstatic.guidatv.quotidiano.net%2Fimg%2Floghi_tv%2Frai_1.png&amp;w=100&amp;h=30&amp;mode=fill&amp;bg=ffffff">
        <img src="./quotidiano.net - palinsesto_files/saved_resource(17)"
             data-src="https://immagini.quotidiano.net/?url=https%3A%2F%2Fs3.eu-west-1.amazonaws.com%2Fstatic.guidatv.quotidiano.net%2Fimg%2Floghi_tv%2Frai_1.png&amp;w=100&amp;h=30&amp;mode=fill&amp;bg=ffffff"
             class=" lazyloaded" title="RaiUno" alt="RaiUno" width="100" height="30">
    </picture>



            </span>
                <span class="channel-name">RaiUno</span>

            </a>
        </header>

        <div class="programs">


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

            <a class="program"
               href="https://guidatv.quotidiano.net/rainews24-previsioni-sulla-viabilita-cciss-viaggiare-informati/28-05-2021/rai_1/14950281/"
               title="RaiNews24 - Previsioni sulla viabilità CCISS Viaggiare informati">
                <div class="program-time">


                    <div class="hour">06:00</div>


                </div>
                <div class="program-info">


                    <div class="program-category">Informazione</div>


                    <div class="program-rating">
                        <span class="stars"></span>

                    </div>


                    <div class="program-title">RaiNews24 - Previsioni sulla viabilità CCISS
                        Viaggiare informati
                    </div>
                </div>
            </a>


            <a class="program"
               href="https://guidatv.quotidiano.net/unomattina/28-05-2021/rai_1/14950282/"
               title="Unomattina">
                <div class="program-time">


                    <div class="hour">06:45</div>


                </div>
                <div class="program-info">

                    <div class="program-category">PROGRAMMA TV</div>

                    <div class="program-title">Unomattina</div>
                </div>
            </a>


            <a class="program"
               href="https://guidatv.quotidiano.net/tg1-che-tempo-fa/28-05-2021/rai_1/14950283/"
               title="TG1 - Che tempo fa">
                <div class="program-time">


                    <div class="hour">07:00</div>


                </div>
                <div class="program-info">


                    <div class="program-category">Informazione</div>


                    <div class="program-rating">
                        <span class="stars"></span>

                    </div>


                    <div class="program-title">TG1 - Che tempo fa</div>
                </div>
            </a>


            <a class="program"
               href="https://guidatv.quotidiano.net/tg1-lis-che-tempo-fa/28-05-2021/rai_1/14950284/"
               title="TG1 L.I.S. - Che tempo fa">
                <div class="program-time">


                    <div class="hour">07:30</div>


                </div>
                <div class="program-info">


                    <div class="program-category">Informazione</div>


                    <div class="program-rating">
                        <span class="stars"></span>

                    </div>


                    <div class="program-title">TG1 L.I.S. - Che tempo fa</div>
                </div>
            </a>


            <a class="program"
               href="https://guidatv.quotidiano.net/tg1-che-tempo-fa/28-05-2021/rai_1/14950285/"
               title="TG1 - Che tempo fa">
                <div class="program-time">


                    <div class="hour">08:00</div>


                </div>
                <div class="program-info">


                    <div class="program-category">Informazione</div>


                    <div class="program-rating">
                        <span class="stars"></span>

                    </div>


                    <div class="program-title">TG1 - Che tempo fa</div>
                </div>
            </a>


            <a class="program"
               href="https://guidatv.quotidiano.net/tg1-flash/28-05-2021/rai_1/14950287/"
               title="TG1 Flash">
                <div class="program-time">


                    <div class="hour">09:30</div>


                </div>
                <div class="program-info">


                    <div class="program-category">Informazione</div>


                    <div class="program-rating">
                        <span class="stars"></span>

                    </div>


                    <div class="program-title">TG1 Flash</div>
                </div>
            </a>


            <a class="program"
               href="https://guidatv.quotidiano.net/rai-parlamento-telegiornale/28-05-2021/rai_1/14950288/"
               title="Rai Parlamento Telegiornale">
                <div class="program-time">


                    <div class="hour">09:35</div>


                </div>
                <div class="program-info">

                    <div class="program-category">PROGRAMMA TV</div>

                    <div class="program-title">Rai Parlamento Telegiornale</div>
                </div>
            </a>


            <a class="program"
               href="https://guidatv.quotidiano.net/tg1/28-05-2021/rai_1/14950289/"
               title="TG1">
                <div class="program-time">


                    <div class="hour">09:50</div>


                </div>
                <div class="program-info">


                    <div class="program-category">Informazione</div>


                    <div class="program-rating">
                        <span class="stars"></span>

                    </div>


                    <div class="program-title">TG1</div>
                </div>
            </a>


            <a class="program"
               href="https://guidatv.quotidiano.net/storie-italiane/28-05-2021/rai_1/14950290/"
               title="Storie Italiane">
                <div class="program-time">


                    <div class="hour live">IN ONDA</div>


                </div>
                <div class="program-info">

                    <div class="program-category">PROGRAMMA TV</div>

                    <div class="program-title">Storie Italiane</div>
                </div>
            </a>


            <a class="program"
               href="https://guidatv.quotidiano.net/e-sempre-mezzogiorno/28-05-2021/rai_1/14950291/"
               title="E&#39; sempre mezzogiorno">
                <div class="program-time">


                    <div class="hour">11:55</div>


                </div>
                <div class="program-info">


                    <div class="program-category">Show</div>


                    <div class="program-title">E' sempre mezzogiorno</div>
                </div>
            </a>


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

            <a class="program"
               href="https://guidatv.quotidiano.net/tg1/28-05-2021/rai_1/14950292/"
               title="TG1">
                <div class="program-time">


                    <div class="hour">13:30</div>


                </div>
                <div class="program-info">


                    <div class="program-category">Informazione</div>


                    <div class="program-rating">
                        <span class="stars"></span>

                    </div>


                    <div class="program-title">TG1</div>
                </div>
            </a>


            <a class="program"
               href="https://guidatv.quotidiano.net/oggi-e-un-altro-giorno/28-05-2021/rai_1/14950293/"
               title="Oggi è un altro giorno">
                <div class="program-time">


                    <div class="hour">14:00</div>


                </div>
                <div class="program-info">

                    <div class="program-category">PROGRAMMA TV</div>

                    <div class="program-title">Oggi è un altro giorno</div>
                </div>
            </a>


            <a class="program"
               href="https://guidatv.quotidiano.net/il-paradiso-delle-signore-daily-terza-stagione-160a-puntata/28-05-2021/rai_1/14950295/"
               title="Il paradiso delle signore - Daily-Terza stagione, 160a puntata">
                <div class="program-time">


                    <div class="hour">15:55</div>


                </div>
                <div class="program-info">


                    <div class="program-category">Serie TV</div>


                    <div class="program-rating">
                        <span class="stars"></span>
                        <span class="year">2018</span>
                    </div>


                    <div class="program-title">Il paradiso delle signore - Daily-Terza
                        stagione, 160a puntata
                    </div>
                </div>
            </a>


            <a class="program"
               href="https://guidatv.quotidiano.net/la-prima-donna-che/28-05-2021/rai_1/14950294/"
               title="La prima donna che">
                <div class="program-time">


                    <div class="hour">16:40</div>


                </div>
                <div class="program-info">


                    <div class="program-category">rubrica</div>


                    <div class="program-rating">
                        <span class="stars"></span>

                    </div>


                    <div class="program-title">La prima donna che</div>
                </div>
            </a>


            <a class="program"
               href="https://guidatv.quotidiano.net/tg1-tg1-economia-che-tempo-fa-previsioni-sulla-viabilita-cciss-viaggiare-informati/28-05-2021/rai_1/14950296/"
               title="TG1 - TG1 Economia - Che tempo fa - Previsioni sulla viabilità CCISS Viaggiare informati">
                <div class="program-time">


                    <div class="hour">16:45</div>


                </div>
                <div class="program-info">


                    <div class="program-category">Informazione</div>


                    <div class="program-rating">
                        <span class="stars"></span>

                    </div>


                    <div class="program-title">TG1 - TG1 Economia - Che tempo fa -
                        Previsioni sulla viabilità CCISS Viaggiare informati
                    </div>
                </div>
            </a>


            <a class="program"
               href="https://guidatv.quotidiano.net/la-vita-in-diretta/28-05-2021/rai_1/14950297/"
               title="La vita in diretta">
                <div class="program-time">


                    <div class="hour">17:05</div>


                </div>
                <div class="program-info">

                    <div class="program-category">PROGRAMMA TV</div>

                    <div class="program-title">La vita in diretta</div>
                </div>
            </a>


            <a class="program"
               href="https://guidatv.quotidiano.net/leredita/28-05-2021/rai_1/14950298/"
               title="L&#39;eredità">
                <div class="program-time">


                    <div class="hour">18:45</div>


                </div>
                <div class="program-info">


                    <div class="program-category">Gioco a quiz</div>


                    <div class="program-title">L'eredità</div>
                </div>
            </a>


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

            <a class="program"
               href="https://guidatv.quotidiano.net/tg1/28-05-2021/rai_1/14950299/"
               title="TG1">
                <div class="program-time">


                    <div class="hour">20:00</div>


                </div>
                <div class="program-info">


                    <div class="program-category">Informazione</div>


                    <div class="program-rating">
                        <span class="stars"></span>

                    </div>


                    <div class="program-title">TG1</div>
                </div>
            </a>


            <a class="program"
               href="https://guidatv.quotidiano.net/incontro-amichevole-italia-san-marino-da-cagliari/28-05-2021/rai_1/14950300/"
               title="Incontro amichevole-Italia - San Marino (da Cagliari)">
                <div class="program-time">


                    <div class="hour">20:30</div>


                </div>
                <div class="program-info">


                    <div class="program-category">sport</div>


                    <div class="program-rating">
                        <span class="stars"></span>

                    </div>


                    <div class="program-title">Incontro amichevole-Italia - San Marino (da
                        Cagliari)
                    </div>
                </div>
            </a>


            <a class="program"
               href="https://guidatv.quotidiano.net/tg1-sera/28-05-2021/rai_1/14950275/"
               title="TG1 Sera">
                <div class="program-time">


                    <div class="hour">23:00</div>


                </div>
                <div class="program-info">


                    <div class="program-category">Informazione</div>


                    <div class="program-rating">
                        <span class="stars"></span>

                    </div>


                    <div class="program-title">TG1 Sera</div>
                </div>
            </a>


            <a class="program"
               href="https://guidatv.quotidiano.net/tv7/28-05-2021/rai_1/14950276/"
               title="TV7">
                <div class="program-time">


                    <div class="hour">23:05</div>


                </div>
                <div class="program-info">

                    <div class="program-category">PROGRAMMA TV</div>

                    <div class="program-title">TV7</div>
                </div>
            </a>


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

            <a class="program"
               href="https://guidatv.quotidiano.net/tg1-sera/29-05-2021/rai_1/14963868/"
               title="TG1 Sera">
                <div class="program-time">


                    <div class="hour">00:00</div>


                </div>
                <div class="program-info">


                    <div class="program-category">Informazione</div>


                    <div class="program-rating">
                        <span class="stars"></span>

                    </div>


                    <div class="program-title">TG1 Sera</div>
                </div>
            </a>


            <a class="program"
               href="https://guidatv.quotidiano.net/alida-alida-valli-lantidiva/29-05-2021/rai_1/14950335/"
               title="Alida - Alida Valli l&#39;antidiva">
                <div class="program-time">


                    <div class="hour">00:15</div>


                </div>
                <div class="program-info">


                    <div class="program-category">Film</div>


                    <div class="program-rating">
                        <span class="stars"></span>
                        <span class="year">2020</span>
                    </div>


                    <div class="program-title">Alida - Alida Valli l'antidiva</div>
                </div>
            </a>


            <a class="program"
               href="https://guidatv.quotidiano.net/rainews24-che-tempo-fa/29-05-2021/rai_1/14950306/"
               title="RaiNews24 - Che tempo fa">
                <div class="program-time">


                    <div class="hour">02:05</div>


                </div>
                <div class="program-info">


                    <div class="program-category">Informazione</div>


                    <div class="program-rating">
                        <span class="stars"></span>

                    </div>


                    <div class="program-title">RaiNews24 - Che tempo fa</div>
                </div>
            </a>


            <a class="program"
               href="https://guidatv.quotidiano.net/sottovoce/29-05-2021/rai_1/14950307/"
               title="Sottovoce">
                <div class="program-time">


                    <div class="hour">02:40</div>


                </div>
                <div class="program-info">


                    <div class="program-category">rubrica</div>


                    <div class="program-rating">
                        <span class="stars"></span>

                    </div>


                    <div class="program-title">Sottovoce</div>
                </div>
            </a>


            <a class="program"
               href="https://guidatv.quotidiano.net/mille-e-un-libro/29-05-2021/rai_1/14950308/"
               title="Mille e un Libro">
                <div class="program-time">


                    <div class="hour">03:10</div>


                </div>
                <div class="program-info">


                    <div class="program-category">rubrica</div>


                    <div class="program-rating">
                        <span class="stars"></span>

                    </div>


                    <div class="program-title">Mille e un Libro</div>
                </div>
            </a>


            <a class="program"
               href="https://guidatv.quotidiano.net/rainews24/29-05-2021/rai_1/14950309/"
               title="RaiNews24">
                <div class="program-time">


                    <div class="hour">04:10</div>


                </div>
                <div class="program-info">


                    <div class="program-category">Informazione</div>


                    <div class="program-rating">
                        <span class="stars"></span>

                    </div>


                    <div class="program-title">RaiNews24</div>
                </div>
            </a>


            <a class="program"
               href="https://guidatv.quotidiano.net/a-sua-immagine/29-05-2021/rai_1/14950310/"
               title="A sua immagine">
                <div class="program-time">


                    <div class="hour">05:45</div>


                </div>
                <div class="program-info">


                    <div class="program-category">Religione</div>


                    <div class="program-rating">
                        <span class="stars"></span>

                    </div>


                    <div class="program-title">A sua immagine</div>
                </div>
            </a>


            <a class="program"
               href="https://guidatv.quotidiano.net/il-caffe-di-raiuno/29-05-2021/rai_1/14950311/"
               title="Il caffè di Raiuno">
                <div class="program-time">


                    <div class="hour">06:00</div>


                </div>
                <div class="program-info">


                    <div class="program-category">rubrica</div>


                    <div class="program-title">Il caffè di Raiuno</div>
                </div>
            </a>


        </div>

    </section>


</div>
<!--FINE LISTA PROGRAMMI-->
</main>
</body>
</html>
