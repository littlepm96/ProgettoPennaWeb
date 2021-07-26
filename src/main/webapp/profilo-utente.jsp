<%@ page import="com.example.ProgettoPennaWeb.utility.SecurityLayer" %>
<%@ page import="com.example.ProgettoPennaWeb.model.Utente" %>
<%@ page import="com.example.ProgettoPennaWeb.utility.ErrorHandling" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%

    HttpSession sessione = SecurityLayer.checkSession(request); //(HttpSession) request.getAttribute("mySession");
    if(sessione==null){
        response.sendRedirect(application.getContextPath()+"/login.jsp");
        return;
    }
        Utente infoUtente = (Utente) sessione.getAttribute("infoUtente");
%>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profilo-utente.css">
</head>
<body>
<div class="main-content">
    <!-- Navigazione -->
    <nav>
        <div class="topnav">
            <a href="${pageContext.request.contextPath}/index.jsp">Home</a>
        </div>
    </nav>
    <!--<nav class="navbar navbar-top navbar-expand-md navbar-dark" id="navbar-main">
        <a href="${pageContext.request.contextPath}/index.jsp">Home</a>
        <div class="container-fluid">
            <\!-- Brand --\>
            <a class="h4 mb-0 text-white text-uppercase d-none d-lg-inline-block" href="non c'è al momento"
               target="_blank">Mio Account</a>
            <\!-- Utente --\>
            <ul class="navbar-nav align-items-center d-none d-md-flex">
                <li class="nav-item dropdown">
                    <a class="nav-link pr-0" href="#" role="button" data-toggle="dropdown" aria-haspopup="true"
                       aria-expanded="false">
                        <div class="media align-items-center">
                <span class="avatar avatar-sm rounded-circle">
                  <img alt="Image placeholder"
                       src="https://demos.creative-tim.com/argon-dashboard/assets/img/theme/team-4.jpg">
                </span>
                            <div class="media-body ml-2 d-none d-lg-block">
                                <span class="mb-0 text-sm  font-weight-bold"><%=infoUtente.getNome()%></span>
                            </div>
                        </div>
                    </a>
                    <div class="dropdown-menu dropdown-menu-arrow dropdown-menu-right">
                        <div class=" dropdown-header noti-title">
                            <h6 class="text-overflow m-0">Welcome!</h6>
                        </div>
                        <a href="../examples/profile.html" class="dropdown-item">
                            <i class="ni ni-single-02"></i>
                            <span>My profile</span>
                        </a>
                        <a href="../examples/profile.html" class="dropdown-item">
                            <i class="ni ni-settings-gear-65"></i>
                            <span>Settings</span>
                        </a>
                        <a href="../examples/profile.html" class="dropdown-item">
                            <i class="ni ni-calendar-grid-58"></i>
                            <span>Activity</span>
                        </a>
                        <a href="../examples/profile.html" class="dropdown-item">
                            <i class="ni ni-support-16"></i>
                            <span>Support</span>
                        </a>
                        <div class="dropdown-divider"></div>
                        <a href="#!" class="dropdown-item">
                            <i class="ni ni-user-run"></i>
                            <span>Logout</span>
                        </a>
                    </div>
                </li>
            </ul>
        </div>
    </nav>-->
    <!-- Header -->
    <div class="header pb-8 pt-5 pt-lg-8 d-flex align-items-center"
         style="min-height: 600px; background-image: url(https://romeartweek.com/images/artist/CA1545_0.jpg); background-size: cover; background-position: center top;">
        <!-- Mask -->
        <span class="mask bg-gradient-default opacity-8"></span>
        <!-- Header container -->
        <div class="container-fluid d-flex align-items-center">
            <div class="row">
                <div class="col-lg-7 col-md-10">
                    <h1 class="display-2 text-white">Ciao <%=infoUtente.getNome()%>
                    </h1>
                    <a href="#!" class="btn btn-info">modifica</a>
                </div>
            </div>
        </div>
    </div>
    <!-- Pagina -->
    <div class="container-fluid mt--7">
        <div class="row">
            <div class="col-xl-4 order-xl-2 mb-5 mb-xl-0">
                <div class="card card-profile shadow">
                    <div class="row justify-content-center">
                        <div class="col-lg-3 order-lg-2">
                            <div class="card-profile-image">
                                <a href="#">
                                    <img src="https://www.giovaniartisti.it/sites/default/files/styles/artisti_full/public/artisti/ilaria95/img2576.jpg?h=2a58c8d9&itok=ufdp9h63"
                                         class="rounded-circle">
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="card-header text-center border-0 pt-8 pt-md-4 pb-0 pb-md-4">
                        <div class="d-flex justify-content-between"></div>
                    </div>
                    <div class="card-body pt-0 pt-md-4">
                        <div class="row">
                            <div class="col">
                                <div class="card-profile-stats d-flex justify-content-center mt-md-5"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xl-8 order-xl-1">
                <div class="card bg-secondary shadow">
                    <div class="card-header bg-white border-0">
                        <div class="row align-items-center">
                            <div class="col-8">
                                <h3 class="mb-0">Mio Account</h3>
                            </div>
                        </div>
                    </div>
                    <div class="card-body">
                        <form>
                            <h6 class="heading-small text-muted mb-4">Dettagli utente</h6>
                            <div class="pl-lg-4">
                                <div class="row">
                                    <div class="col-lg-6">
                                    </div>
                                    <div class="col-lg-6">
                                        <div class="form-group">
                                            <label class="form-control-label" for="input-email">Email</label>
                                            <input type="email" id="input-email"
                                                   class="form-control form-control-alternative"
                                                   placeholder=<%=infoUtente.getEmail()%>>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-lg-6">
                                        <div class="form-group focused">
                                            <label class="form-control-label" for="input-first-name">Nome</label>
                                            <input type="text" id="input-first-name"
                                                   class="form-control form-control-alternative"
                                                   placeholder="First name" value="<%=infoUtente.getNome()%>">
                                        </div>
                                    </div>
                                    <div class="col-lg-6">
                                        <div class="form-group focused">
                                            <label class="form-control-label" for="input-last-name">Cognome</label>
                                            <input type="text" id="input-last-name"
                                                   class="form-control form-control-alternative" placeholder="Last name"
                                                   value="<%=infoUtente.getCognome()%>">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <hr class="my-4">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>