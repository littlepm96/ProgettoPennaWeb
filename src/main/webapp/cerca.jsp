<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cerca.css">
<head>
  <link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css2?family=Lato&display=swap" rel="stylesheet">
</head>

<body>
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
  <li><a href="#">presa</a></li>

  <li><a href="#">dal</a></li>
  <li><a href="#">db</a></li>
  <li><a href="#">cazzo dio</a></li>
</ul>

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
