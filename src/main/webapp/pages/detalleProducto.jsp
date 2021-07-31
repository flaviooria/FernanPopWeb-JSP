<%@ page import="modelos.Usuario" %>
<%@ page import="modelos.Producto" %><%--
  Created by IntelliJ IDEA.
  User: 2097k
  Date: 09/07/2021
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Usuario user = (Usuario) session.getAttribute("user");
    System.out.println("pagina de inicio el user es = " + user);
    if (user == null) {
        session.setAttribute("error","Usuario no identificado.");
        response.sendRedirect("./error.jsp");
    } else {
        Producto p = (Producto) session.getAttribute("producto");
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FernanPop | <%=p.getNombre()%></title>
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
    <link rel="stylesheet" href="../css/detail_product.css">
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
                <li><a class="link" href="../pages/inicio.jsp">Inicio</a></li>
                <li><a class="link" href="../pages/editarPerfil.jsp">Editar Perfíl</a></li>
                <li><a class="link" href="../pages/subirProducto.jsp">Subir Producto</a></li>
                <li><a class="link" href="../pages/tratos.jsp">Ver mis tratos</a></li>
                <li><a class="link" href="./cerrarSesion.jsp">Cerrar Sesión</a></li>
            </ul>
        </nav>
    </header>
    <!-- Back button -->
    <div class="back">
        <form action="${pageContext.request.contextPath}/mostrarProductos?nameProduct=<%=p.getNombre()%>" method="post">
            <button class="btn-back" type="submit"><i class="fas fa-arrow-left"></i>Regresar</button>
        </form>
    </div>
    <!-- Hero  -->
    <section class="hero">
        <div class="product-card">
            <div class="card-img">
                <img src="${pageContext.request.contextPath}/renderImagenProducto?id=<%=p.getId()%>" alt="img-product">
            </div>
            <div class="card-info">
                <h3 class="card-info-title"><%=p.getNombre()%></h3>
                <p class="card-info-description">
                    <%=p.getDescripcion()%>
                </p>
                <p class="card-info-price"><%=p.getPrecio()%> €</p>
                <form action="${pageContext.request.contextPath}/transaccionProducto" class="form-action" method="post">
                    <input type="hidden" name="id" value="<%=p.getId()%>">
                    <button class="btn-request" name="accion" value="solicitud" type="submit">Solicitar</button>
                    <button class="btn-chat" name="accion" value="chat" type="submit">Chat</button>
                </form>
                <%
                    if(session.getAttribute("request") != null) {
                        if (session.getAttribute("request").equals("exito")) {
                %>
                <span class="save-success">Solicitud procesada ✔</span>
                <% } else if (session.getAttribute("request").equals("err1")) { %>
                <p class="alert-failed">Cuidado estás intentando comprar un producto que es tuyo ✘ </p>
                <% } else if (session.getAttribute("request").equals("err2")) { %>
                <p class="alert-failed">No se pudo realizar la solicitud ✘ </p>
                <% } else if (session.getAttribute("request").equals("err3")) { %>
                <p class="alert-failed">No se pudo realizar la solicitud de chat.✘ </p>
                <% }%>
                <% session.removeAttribute("request");
                        } %>
            </div>
        </div>
    </section>
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
    let hamburger = document.querySelector(".hamburger--spin");

    let menu = document.querySelector(".navbar-menu");

    hamburger.addEventListener("click", () => {
        hamburger.classList.toggle("is-active");
        menu.classList.toggle("open");
    })
</script>
<% } %>
</body>
</html>