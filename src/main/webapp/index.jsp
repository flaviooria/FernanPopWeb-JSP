<%--
  Created by IntelliJ IDEA.
  User: flaviooria
  Date: 31/07/2021
  Time: 21:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>FernanPop | Logeo</title>
  <link rel="apple-touch-icon" sizes="57x57" href="./assets/favicons/apple-icon-57x57.png">
  <link rel="apple-touch-icon" sizes="60x60" href="./assets/favicons/apple-icon-60x60.png">
  <link rel="apple-touch-icon" sizes="72x72" href="./assets/favicons/apple-icon-72x72.png">
  <link rel="apple-touch-icon" sizes="76x76" href="./assets/favicons/apple-icon-76x76.png">
  <link rel="apple-touch-icon" sizes="114x114" href="./assets/favicons/apple-icon-114x114.png">
  <link rel="apple-touch-icon" sizes="120x120" href="./assets/favicons/apple-icon-120x120.png">
  <link rel="apple-touch-icon" sizes="144x144" href="./assets/favicons/apple-icon-144x144.png">
  <link rel="apple-touch-icon" sizes="152x152" href="./assets/favicons/apple-icon-152x152.png">
  <link rel="apple-touch-icon" sizes="180x180" href="./assets/favicons/apple-icon-180x180.png">
  <link rel="icon" type="image/png" sizes="192x192" href="./assets/favicons/android-icon-192x192.png">
  <link rel="icon" type="image/png" sizes="32x32" href="./assets/favicons/favicon-32x32.png">
  <link rel="icon" type="image/png" sizes="96x96" href="./assets/favicons/favicon-96x96.png">
  <link rel="icon" type="image/png" sizes="16x16" href="./assets/favicons/favicon-16x16.png">
  <link rel="manifest" href="./assets/favicons/manifest.json">
  <meta name="msapplication-TileColor" content="#3D5A80">
  <meta name="msapplication-TileImage" content="./assets/favicons/ms-icon-144x144.png">
  <meta name="theme-color" content="#3D5A80">
  <!--styles css-->
  <link rel="stylesheet" href="./css/main.css" />
  <!--fonts -->
  <link rel="preconnect" href="https://fonts.gstatic.com">
  <link
          href="https://fonts.googleapis.com/css2?family=Quicksand:wght@500;700&family=Raleway:wght@700;900&display=swap"
          rel="stylesheet">
  <!--fontawesome icons-->
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.1/css/all.css"
        integrity="sha384-vp86vTRFVJgpjF9jiIGPEEqYqlDwgyBgEF109VFjmqGmIY/Y4HV4d3Gp2irVfcrp" crossorigin="anonymous" />
  <!--Animations CDN-->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css" />
  <!--Animation AOS Scroll-->
  <link rel="stylesheet" href="https://unpkg.com/aos@next/dist/aos.css" />
</head>

<body>
<header id="inicio">
  <div class="hero">
    <div class="header animate__animated animate__fadeInLeftBig animate__fast">
      <div class="header-navbar">
        <ul class="navbar-links">
          <li><a href="#caracteristicas">Caracteristicas</a></li>
          <li><a href="#faqs">Faqs</a></li>
          <li><a href="#contacto">Contacto</a></li>
        </ul>
      </div>
      <form action="" autocomplete="off">
        <div class="header-search">
          <input type="text" name="nameProduct" id="nameProduct" placeholder="Busca todo lo que quieras">
          <button type="submit" class="btn-search"><i class="fas fa-search"></i></button>
        </div>
      </form>
    </div>
    <div class="hero-title animate__animated animate__fadeIn animate__slower animate-delay-3s">
      <h1>Fernan<span class="orange">Pop</span></h1>
      <p>Vende y compra lo que quieras en cualquier momento y lugar.</p>
    </div>
    <div class="hero-register animate__animated animate__fadeIn animate__slower animate-delay-3s">
      <form id="form-login" method="get" action="./pages/verificarLogin.jsp">
        <h3>Inciar Sesión</h3>
        <label for="correo">Correo electronico:</label><br />
        <input type="email" name="email" id="correo" /><br />
        <label for="password">Contraseña:</label><br />
        <input type="password" name="pass" id="password">
        <div class="password">
          <input type="checkbox" class="checkbox">Mostrar contraseña
        </div>
        <p class="form-register">Aun no estas registrado,hazlo aquí: <a class="form-register-link" href="./pages/registro.jsp">Registrarse</a></p>
        <div class="spinner">
          <div class="double-bounce1"></div>
          <div class="double-bounce2"></div>
        </div>
        <%
          String failed = (String) session.getAttribute("failed");
          System.out.println(failed);
          if (failed != null) {
            if(failed.equals("true")) {
              session.removeAttribute("failed");
        %>
        <p>No se pudo iniciar sesión, intentalo de nuevo.</p>
        <% }
        }%>
        <button class="send" type="submit">Entrar <i class="fas fa-chevron-right"></i></button>
      </form>
    </div>
    <div class="wave-bottom" style="height: 150px; overflow: hidden;"><svg viewBox="0 0 500 150"
                                                                           preserveAspectRatio="none" style="height: 100%; width: 100%;">
      <path d="M0.00,49.98 C323.64,137.66 206.83,-33.05 500.00,49.98 L504.79,112.00 L-3.10,107.06 Z"
            style="stroke: none; "></path>
    </svg></div>
  </div>
</header>
<section id="caracteristicas">
  <div class="features" data-aos="zoom-in" data-aos-easing="ease">
    <h3>Caracteristicas</h3>
    <div class="features-items">
      <div class="item-badge">
        <img src="./assets/img/soFast-trust.png" width="250" alt="rapido-confiable" />
        <p>Es rapido y confiable.</p>
      </div>
      <div class="item-badge">
        <img src="./assets/img/pay-secure.png" width="250" alt="pagoSeguro" />
        <p>Pagos seguros siempre.</p>
      </div>
      <div class="item-badge">
        <img src="./assets/img/succes.png" width="250" alt="acuerdo-mutuo" />
        <p>
          Acuerdo mutuo entre<br>comprador
          y vendedor.
        </p>
      </div>
    </div>
  </div>
</section>
<section id="faqs">
  <div class="faqs">
    <div class="faqs-wave-top" style="height: 130px; overflow: hidden;"><svg viewBox="0 0 500 150"
                                                                             preserveAspectRatio="none" style="height: 100%; width: 100%;">
      <path d="M0.00,49.98 C149.99,150.00 271.49,-49.98 500.00,49.98 L500.00,0.00 L0.00,0.00 Z"
            style="stroke: none;"></path>
    </svg></div>
    <div class="faqs-logo" data-aos="fade-in" data-aos-easing="ease">
      <img src="./assets/img/faq.jpg" width="500" alt="">
    </div>
    <div class="faqs-list" data-aos="fade-in" data-aos-easing="ease">
      <h1>Preguntas Frecuentes</h1>
      <p class="question">¿Puedó pagar con distintas entidades, incluyendo PayPal?</p>
      <p class="response"> - Claro sí puedes, nuestra app tiene trato directo con entidades financieras para
        hacer tus pagos seguros.</p>
      <p class="question">¿Las compras y ventas se puede hacer en toda españa?</p>
      <p class="response"> - Nuestros servicios de compra y venta funciona alreddor de toda la Union Europea.
      </p>
      <p class="question">¿Es fernanpop la mejor app de venta y compra?</p>
      <p class="response"> - Definitivamente Sí !!!</p>
    </div>
    <div class="faqs-wave-bottom" style="height: 130px; overflow: hidden;"><svg viewBox="0 0 500 150"
                                                                                preserveAspectRatio="none" style="height: 100%; width: 100%;">
      <path d="M-21.72,16.28 C192.71,110.03 294.86,103.13 518.90,14.30 L500.00,150.00 L0.00,150.00 Z"
            style="stroke: none;"></path>
    </svg></div>
  </div>
</section>
<section id="contacto">
  <div class="contact" data-aos="fade-in" data-aos-easing="ease">
    <h1>¿Necesitas ayuda? <br> <span>Contactanos</span> </h1>

    <div class="contact-info">
      <form action="">
        <label for="txtEmail">Correo:</label>
        <input type="email" name="" id="txtEmail">
        <label for="txtAreaConsulta">Indica cual es tu problema o consulta:</label>
        <textarea name="" id="txtAreaConsulta" cols="30" rows="10"></textarea>
        <button>Enviar <i class="fas fa-envelope-open-text"></i></button>
      </form>
    </div>
  </div>
</section>
<footer id="Acerca">
  <div class="about-wave-top" style="height: 100px; overflow: hidden;"><svg viewBox="0 0 500 150"
                                                                            preserveAspectRatio="none" style="height: 100%; width: 100%;">
    <path d="M-0.84,36.02 C207.38,99.17 272.85,100.16 503.10,28.13 L500.00,0.00 L0.00,0.00 Z"
          style="stroke: none;"></path>
  </svg></div>
  <div class="about" ata-aos="fade-in" data-aos-easing="ease">
    <p>Derechos Reservados ©</p>
    <p>Diseñado por <span class="orange">@FlavioOria</span></p>
    <ul class="about-media">
      <li><a href="" title="facebook/fernanpop.com"><i class="fab fa-facebook-square"></i></a></li>
      <li><a href=""><i class="fab fa-instagram-square"></i></a></li>
      <li><a href=""><i class="fab fa-twitter-square"></i></a></li>
      <li><a class="inicio" href="#inicio" title="Inicio"><i class="fas fa-arrow-circle-up"></i></a></li>
    </ul>
  </div>
</footer>
</body>
<!--Script de animation scroll-->
<script src="https://unpkg.com/aos@next/dist/aos.js"></script>
<script src="./js/loger.js"></script>
</html>