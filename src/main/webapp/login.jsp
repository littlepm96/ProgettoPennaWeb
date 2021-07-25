<%@ page import="com.example.ProgettoPennaWeb.utility.SecurityLayer" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%
    if (SecurityLayer.checkSession(request) != null) {
        response.sendRedirect("/profilo-utente.jsp");
    }
%>

<html>
<head>
    <title> Login Page </title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>
<!--INIZIO HEADER-->
<header>
    <h1 class="title">Login Test</h1>
    <!--INIZIO NAVIGAZIONE-->
    <nav>
        <a href="${pageContext.request.contextPath}/index.jsp"> Home </a>
    </nav>
</header>
<!--FINE HEADER-->
<!--INIZIO CONTENUTO PRINCIPALE-->
<main>
    <div class="flex-container">
        <div class="space"></div>
        <form method="POST" action="login">
            <div id="sezione-credenziali">
                <label for="email">Email : </label>
                <input type="text" placeholder="Enter Email" id="email" name="email" required>
                <label for="password">Password : </label>
                <input type="password" placeholder="Enter Password" id="password" name="pass" required>
                <input type="checkbox" id="remember">
                <label for="remember"> Remember me</label>
                <button type="submit" class="testo-grande"> Login</button>
            </div>
            <div id="link-secondari">
                <a href="${pageContext.request.contextPath}/registrazione.jsp" class="testo-grande bottone"> Non
                    possiedi un account?</a>
                <span>Forgot <a href="#"> password?</a></span>
            </div>
        </form>
        <div class="space"></div>
    </div>
</main>
<!--FINE CONTENUTO PRINCIPALE-->
<!--INIZIO FOOTER-->
<footer></footer>
<!--FINE FOOTER-->
</body>
</html>
