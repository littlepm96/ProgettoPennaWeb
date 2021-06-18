<Html>
<head>
    <title> Registrazione </title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/registrazione.css">
</head>
<body bgcolor="Lightskyblue">
<!--INIZIO Header-->
<header>
    <h1 class="title"> Registrazione </h1>
    <!--INIZIO NAVIGAZIONE-->
    <nav>
        <a href="${pageContext.request.contextPath}/login.jsp" > indietro </a>
    </nav>
    <!--FINE NAVIGAZIONE-->
</header>
<!--FINE HEADER-->
<br>
<br>
<form>

    <label for="firstname"> Nome: </label>
    <input type="text" id="firstname" name="firstname" size="15"/> <br> <br>
    <label for="middlename"> Cognome: </label>
    <input type="text" id="middlename" name="middlename" size="15"/> <br> <br>
    <label for="course">
        Corso :
    </label>
    <select id="course">
        <option value="informatica">informatica</option>
        <option value="scienze disumane">scienze disumane</option>
        <option value="merendine">merendine</option>
        <option value="filosofeggiando">filosofeggiando</option>
    </select>

    <br>
    <br>
    <p>Genere:</p>
    <div>
    <input type="radio" name="gender" value="uomo" id="uomo"/>
    <label for="uomo">Uomo</label>
    </div>
    <div>
    <input type="radio" name="gender" value="donna" id="donna"/>
    <label for="donna">Donna</label>
    </div>
    <div>
    <input type="radio" name="gender" value="altro" id="altro"/>
    <label for="altro">Altro</label>
    </div>
    <br>

    <label for="telephone">
        Telefono :
    </label>
    <input type="text" name="prefisso paese" id="prefix"  value="+39" size="4"/>
    <input type="text" name="numero" id="telephone" size="10"/> <br> <br>
    Indirizzo
    <br>
    <textarea cols="80" rows="5" value="address"> </textarea>
    <br> <br>
    Email:
    <input type="email" id="email" name="email"/> <br>
    <br> <br>
    Password:
    <input type="Password" id="pass" name="pass"> <br>
    <br> <br>
    Conferma password:
    <input type="Password" id="repass" name="repass"> <br> <br>
    <input type="button" value="Registrami"/>
</form>
</body>
</html>