<Html>
<head>
    <title> Registrazione </title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/registrazione.css">
</head>
<body>
<!--INIZIO Header-->
<header>
    <h1 class="title"> Registrazione </h1>
    <!--INIZIO NAVIGAZIONE-->
    <nav>
        <a href="${pageContext.request.contextPath}/index.jsp">Home</a>
        <a href="${pageContext.request.contextPath}/login.jsp"> indietro </a>
    </nav>
    <!--FINE NAVIGAZIONE-->
</header>
<!--FINE HEADER-->
<main>
    <p>
    <form action="${pageContext.request.contextPath}/registrazione" method="post">
        <p>
            <label for="name"> Nome: </label>
            <input type="text" id="name" name="name" size="15"/>
        </p>
        <p>
            <label for="surname"> Cognome: </label>
            <input type="text" id="surname" name="surname" size="15"/>
        </p>
        <p>
            <label for="email"> Email: </label>
            <input type="text" id="email" name="email"/>
        </p>
        <p>
            <label for="pass">Password:</label>
            <input type="Password" id="pass" name="pass">
        </p>
        <p>
            <label for="repass">Conferma password:</label>
            <input type="Password" id="repass" name="repass">
        </p>
        <input type="submit" value="Registrami"/>
    </form>
    </p>
</main>
<footer>
</footer>
</body>
</html>