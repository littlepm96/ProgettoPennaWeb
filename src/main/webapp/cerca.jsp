<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cerca.css">
<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Lato&display=swap" rel="stylesheet">

<head>
  <title>Cerca programmi TV</title>
</head>
<body>
<!--INIZIO HEADER-->
<header>
  <div>
    <h1>Ricerca programmi</h1>
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
<!--FINE HEADER-->
<!--INIZIO CONTENUTO PRINCIPALE-->
<main>
<div class="search-container">
  <input type="text" id="myinput" onkeyup="cerca()" placeholder="scrivi qualcosa.." title="Type in a name">
  <a href="#" class="search-btn">
    <i class="fas fa-search"></i>
  </a>
</div>

<input type="text" id="myInput" onkeyup="cerca()" placeholder="scrivi qualcosa.." title="Type in a name">

<ul id="myUL">
  <li><a href="#">sta</a></li>
  <li><a href="#">roba</a></li>

  <li><a href="#">va</a></li>
  <li><a href="#">press</a></li>

  <li><a href="#">dal</a></li>
  <li><a href="#">db</a></li>
  <li><a href="#">cazzo dio</a></li>
</ul>
</main>
<!--FINE CONTENUTO PRINCIPALE-->
<!--INIZIO FOOTER-->
<footer>
</footer>
<!--FINE FOOTER-->
<script>
  function cerca() { //che fantasia mio dio
    var input, filter, ul, li, a, i, txtValue;
    input = document.getElementById("myInput");
    filter = input.value.toUpperCase();
    ul = document.getElementById("myUL");
    li = ul.getElementsByTagName("li");
    for (i = 0; i < li.length; i++) {
      a = li[i].getElementsByTagName("a")[0];
      txtValue = a.textContent || a.innerText;
      if (txtValue.toUpperCase().indexOf(filter) > -1) {
        li[i].style.display = "";
      } else {
        li[i].style.display = "none";
      }
    }
  }
</script>

</body>
</html>