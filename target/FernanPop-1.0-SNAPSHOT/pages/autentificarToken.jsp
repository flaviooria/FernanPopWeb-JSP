<%@ page import="modelos.Usuario" %><%--
  Created by IntelliJ IDEA.
  User: flaviooria
  Date: 04/08/2021
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  Usuario user = (Usuario) session.getAttribute("user");
  String token = (String) session.getAttribute("token");
  if (user == null) {
    session.setAttribute("error", "Debes de loguearte.");
    response.sendRedirect("./error.jsp");
  } else {
    System.out.println("el tokeAuth es: "+ token);
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>FernanPop | Token </title>
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
  <link rel="stylesheet" href="../css/autenticateToken.css">
  <!--fonts -->
  <link rel="preconnect" href="https://fonts.gstatic.com">
  <link
          href="https://fonts.googleapis.com/css2?family=Quicksand:wght@500;700&family=Raleway:wght@700;900&display=swap"
          rel="stylesheet">
  <!--fontawesome icons-->
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.1/css/all.css"
        integrity="sha384-vp86vTRFVJgpjF9jiIGPEEqYqlDwgyBgEF109VFjmqGmIY/Y4HV4d3Gp2irVfcrp" crossorigin="anonymous" />
  <!-- Style of hamburger_menu -->
  <link href="../dist/hamburgers.css" rel="stylesheet">
</head>
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
        <li><a class="link" href="../index.jsp">Cerrar Sesión</a></li>
      </ul>
    </nav>
  </header>
  <!-- Hero -->
  <div class="hero">
    <form action="${pageContext.request.contextPath}/authToken" class="hero__form" method="post">
      <label class="hero__form-label" for="token">Ingresa tu token:</label>
      <div class="form__token">
        <input class="form__token-textToken" type="text" maxlength="4" name="token" id="token" required>
        <i class="form__token-paste fas fa-paste"></i>
      </div>
      <%
        if (session.getAttribute("failed") != null) {
          if (session.getAttribute("failed").equals("true")) {
      %>
      <span class="hero__form-response">Token invalido; Intentalo de nuevo.</span>
      <%} session.removeAttribute("failed");%>
      <%}%>
      <button class="hero__form-submit" type="submit">Validar</button>
    </form>
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
<script>
  let token_area = document.querySelector(".form__token-textToken");

  //Menu hamburguesa
  loadHamburger();

  function loadHamburger() {
    let hamburger = document.querySelector(".hamburger--spin");

    let menu = document.querySelector(".navbar-menu");

    hamburger.addEventListener("click", () => {
      hamburger.classList.toggle("is-active");
      menu.classList.toggle("open");
    })
  }

  let paste = document.querySelector(".form__token-paste");

  //Evento para funcionalidad de boton pegar
  paste.addEventListener("click", () => {


    token_area.removeAttribute("value");
    //Luego le agregamo el valor del token
    token_area.setAttribute("value", "<%=token%>");
  })

  token_area.addEventListener("click", (e) => {
    const msg = "Ingresar Token";
    let item = e.target;
    let val = item.value;

    if (val === "") {
      item.classList.remove("filled");
      item.classList.add("filled-danger");
      item.setAttribute("placeholder", msg);
      setTimeout(() => {
        item.classList.remove("filled-danger");
        item.removeAttribute("placeholder");
      }, 1000);
    } else {
      item.classList.remove("filled-danger");
      item.removeAttribute("placeholder");
      item.classList.add("filled");
    }

  })
</script>
</body>
<%}%>
</html>