<%@ page import="modelos.Mensaje" %>
<%@ page import="modelos.Gestion" %>
<%@ page import="modelos.Usuario" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="utilidades.FicherosConverter" %>
<%@ page import="utilidades.Utils" %><%--
  Created by IntelliJ IDEA.
  User: flaviooria
  Date: 30/07/2021
  Time: 16:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Usuario user = (Usuario) session.getAttribute("user");
    ArrayList<Mensaje> mensajesEnviados = new ArrayList<>();
    ArrayList<Mensaje> mensajesRecibidos = new ArrayList<>();
    System.out.println("pagina de inicio el user es = " + user);
    if (user == null) {
        session.setAttribute("error", "Usuario no identificado.");
        response.sendRedirect("./error.jsp");
    } else {
        Gestion gestion = null;
        try {
            gestion = new Gestion();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (gestion != null) {
            mensajesEnviados = Gestion.mensajesEnviados(user.getId());
            mensajesRecibidos = Gestion.mensajesRecibidos(user.getId());
        } else {
            session.setAttribute("error", "No se pudo conectar a la base de datos.");
            response.sendRedirect("./error.jsp");
        }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FernanPop | Chat</title>
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
    <!-- Styles -->
    <link rel="stylesheet" href="../css/chat.css">
    <!--fonts -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link
            href="https://fonts.googleapis.com/css2?family=Quicksand:wght@500;700&family=Raleway:wght@700;900&display=swap"
            rel="stylesheet">
    <!--fontawesome icons-->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.1/css/all.css"
          integrity="sha384-vp86vTRFVJgpjF9jiIGPEEqYqlDwgyBgEF109VFjmqGmIY/Y4HV4d3Gp2irVfcrp" crossorigin="anonymous" />
    <!-- Style of hamburger_menu -->
    <link href="../css/hamburger-menu.css" rel="stylesheet">

<body>
<div class="container">
    <!-- Header -->
    <header id="inicio">
        <div class="hamburger hamburger--spin">
            <div class="hamburger-box">
                <div class="hamburger-inner">
                </div>
            </div>
        </div>
        <div class="logo">
            <h1>Fernan<span class="orange">Pop</span></h1>
        </div>
        <nav class="navbar-menu">
            <ul>
                <li><a class="link" href="../pages/inicio.jsp">Inicio</a></li>
                <li><a class="link" href="../pages/editarPerfil.jsp">Editar Perfíl</a></li>
                <li><a class="link" href="../pages/subirProducto.jsp">Subir Producto</a></li>
                <li><a class="link" href="../pages/productos.jsp">Ver Productos</a></li>
                <li><a class="link" href="../pages/tratos.jsp">Ver mis tratos</a></li>
                <li><a class="link" href="./cerrarSesion.jsp">Cerrar Sesión</a></li>
            </ul>
        </nav>
    </header>
    <!-- Back button -->
    <div class="back">
        <a href="./inicio.jsp" class="btn-back"><i class="fas fa-arrow-left"></i>Regresar</a>
    </div>
    <!-- Chats Notifications -->
    <div class="hero">
        <p class="hero-title">Mensajes</p>
        <div class="chats">
            <input type="radio"  id="tab1" name="tab" checked>
            <label for="tab1" onclick="getListOfMessages('recibido')" class="tab1"><i class="fas fa-envelope-open"></i> Recibidos</label>
            <input type="radio" id="tab2" name="tab">
            <label for="tab2" onclick="getListOfMessages('enviado')" class="tab2"><i class="fas fa-paper-plane"></i> Enviados</label>
            <div class="line"></div>
            <div class="content-chats">
                <div class="content"></div>
                <div class="chat-item"></div>
            </div>
        </div>
        <div class="new-message">
            <a href="./nuevoMensaje.jsp" class="go-to-message none">Nuevo Mensaje</a>
            <img src="../assets/icons/edit.svg" alt="" class="icon-edit" title="Nuevo Mensaje">
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
<script src="https://unpkg.com/@lottiefiles/lottie-player@latest/dist/lottie-player.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.js"></script>
<script>
    let contenListMessages = document.querySelector(".content")

    function getListOfMessages(accion) {
        chat.innerHTML = ""
        fetch("${pageContext.request.contextPath}/pages/listOfMessage.jsp?accion="+accion)
        .then(response => response.text())
        .then(data => {
            console.log(data)
            contenListMessages.innerHTML = data
        })
    }

    function getContentMessage(id, accion) {
        contenListMessages.innerHTML = ""
        fetch("${pageContext.request.contextPath}/pages/chat.jsp?idChat="+id+"&accion="+accion)
        .then(response => response.text())
        .then(data => {
            console.log(data)
            chat.innerHTML = data
        })
    }
</script>
<script src="../js/chat.js"></script>
</body>
<%}%>
</html>