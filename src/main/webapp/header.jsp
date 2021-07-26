<header>
    <div class="float-right">
        <%
            HttpSession sessione = SecurityLayer.checkSession(request);
            if(sessione!=null){
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
        <h1><a href="https://www.disim.univaq.it/didattica/content.php?corso=77&pid=86&did=0"> Disim </a></h1>
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
