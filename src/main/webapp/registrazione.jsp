<Html>
<head>
    <title>
        Registrazione
    </title>
</head>
<body bgcolor="Lightskyblue">
<br>
<br>
<form>

    <label> Nome </label>
    <input type="text" name="firstname" size="15"/> <br> <br>
    <label> Cognome: </label>
    <input type="text" name="middlename" size="15"/> <br> <br>
    <label>
        Corso :
    </label>
    <select>
        <option value="informatica">informatica</option>
        <option value="scienze disumane">scienze disumane</option>
        <option value="merendine">merendine</option>
        <option value="filosofeggiando">filosofeggiando</option>
    </select>

    <br>
    <br>
    <label>
        Genere:
    </label>

    <br>
    <input type="radio" name="uomo"/> Uomo <br>
    <input type="radio" name="donna"/> Donna <br>
    <input type="radio" name="altro"/> Altro <br>
    <br>

    <label>
        Telefono :
    </label>
    <input type="text" name="prefisso paese"  value="+39" size="5"/>
    <input type="text" name="numero" size="10"/> <br> <br>
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
    <input type="button" value="Submit"/>
</form>
</body>
</html>