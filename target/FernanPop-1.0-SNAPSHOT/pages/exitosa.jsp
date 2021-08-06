<%-- 
    Document   : exitosa
    Created on : Jul 7, 2021, 5:14:25 PM
    Author     : 2097k
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FernanPop | Operación Exitosa </title>
        <link rel="apple-touch-icon" sizes="57x57" href="../assets/favicons/apple-icon-57x57.png">
        <link rel="apple-touch-icon" sizes="60x60" href="../assets/favicons/apple-icon-60x60.png">
        <link rel="apple-touch-icon" sizes="72x72" href="../assets/favicons/apple-icon-72x72.png">
        <link rel="apple-touch-icon" sizes="76x76" href="../assets/favicons/apple-icon-76x76.png">
        <link rel="apple-touch-icon" sizes="114x114" href="../assets/favicons/apple-icon-114x114.png">
        <link rel="apple-touch-icon" sizes="120x120" href="../assets/favicons/apple-icon-120x120.png">
        <link rel="apple-touch-icon" sizes="144x144" href="../assets/favicons/apple-icon-144x144.png">
        <link rel="apple-touch-icon" sizes="152x152" href="../assets/favicons/apple-icon-152x152.png">
        <link rel="apple-touch-icon" sizes="180x180" href="../assets/favicons/apple-icon-180x180.png">
        <link rel="icon" type="image/png" sizes="192x192" href="../assets/favicons/android-icon-192x192.png">
        <link rel="icon" type="image/png" sizes="32x32" href="../assets/favicons/favicon-32x32.png">
        <link rel="icon" type="image/png" sizes="96x96" href="../assets/favicons/favicon-96x96.png">
        <link rel="icon" type="image/png" sizes="16x16" href="../assets/favicons/favicon-16x16.png">
        <link rel="manifest" href="../assets/favicons/manifest.json">
        <meta name="msapplication-TileColor" content="#3D5A80">
        <meta name="msapplication-TileImage" content="../assets/favicons/ms-icon-144x144.png">
        <meta name="theme-color" content="#3D5A80">
        <!-- Styles  -->
        <link rel="stylesheet" href="../css/errGeneral.css">
        <!--fonts -->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link
                href="https://fonts.googleapis.com/css2?family=Quicksand:wght@500;700&family=Raleway:wght@700;900&display=swap"
                rel="stylesheet">
        <!--fontawesome icons-->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.1/css/all.css"
              integrity="sha384-vp86vTRFVJgpjF9jiIGPEEqYqlDwgyBgEF109VFjmqGmIY/Y4HV4d3Gp2irVfcrp" crossorigin="anonymous" />
    </head>
    <body>
    <div class="container">
        <!-- Header -->
        <header id="inicio">
            <div class="logo">
                <h1>Fernan<span class="orange">Pop</span></h1>
            </div>
        </header>
        <!-- Hero -->
        <div class="hero">
            <div class="hero__alert">
                <h3 class="hero__alert-info"><%=session.getAttribute("exito")%></h3>
                <a href="./inicio.jsp" class="hero__alert-link">Haz click aquí.</a>
            </div>
        </div>
        <!-- Footer -->
        <footer>
            <p class="footer-rigth">® Derechos Reservados</p>
            <a class="footer-designed" href="">Designed by @flaviooria</a>
            <div class="about-media">
                <ul>
                    <li><a href="" title="facebook/fernanpop.com"><i class="fab fa-facebook-square"></i></a></li>
                    <li><a href=""><i class="fab fa-instagram-square"></i></a></li>
                    <li><a href=""><i class="fab fa-twitter-square"></i></a></li>
                    <li><a class="inicio" href="#inicio" title="Inicio"><i class="fas fa-arrow-circle-up"></i></a></li>
                </ul>
            </div>
        </footer>
    </div>
        <%
            session.removeAttribute("exito");
        %>
    </body>
</html>
