<%@ page import="modelos.Usuario" %>
<%@ page import="modelos.Gestion" %>
<%@ page import="modelos.SolicitudProducto" %><%--
  Created by IntelliJ IDEA.
  User: 2097k
  Date: 12/07/2021
  Time: 21:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setCharacterEncoding("UTF-8");
    Usuario user = (Usuario) session.getAttribute("user");
    SolicitudProducto sp = null;
    System.out.println("pagina de inicio el user es = " + user);
    if (user == null) {
        session.setAttribute("error","Usuario no identificado.");
        response.sendRedirect("./error.jsp");
    } else {
        Gestion gestion = null;
        try {
            gestion = new Gestion();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (gestion !=  null) {
            int idSolicitud = Integer.parseInt(request.getParameter("idSolicitud"));
            sp  = Gestion.obtenerSolicitudById(idSolicitud);

        } else {
            request.getSession().setAttribute("error","No se pudo conectar a la base de datos.");
            response.sendRedirect("./pages/error.jsp");
        }
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FernanPop | Venta Producto</title>
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
    <link rel="stylesheet" href="../css/sailProduct.css">
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
                <li><a class="link" href="../pages/productos.jsp">Ver Producto</a></li>
                <li><a class="link" href="../pages/tratos.jsp">Ver mis tratos</a></li>
                <li><a class="link" href="./cerrarSesion.jsp">Cerrar Sesión</a></li>
            </ul>
        </nav>
    </header>
    <!-- Back button -->
    <div class="back">
        <a href="./inicio.jsp" class="btn-back"><i class="fas fa-arrow-left"></i>Regresar</a>
    </div>
    <!-- Hero -->
    <section class="hero">
        <div class="card">
            <div class="card-img">
                <img src="${pageContext.request.contextPath}/renderImagenProducto?id=<%=sp.getProducto().getId()%>" alt="img-product">
            </div>
            <div class="card-content">
                <h2 class="content-title"><%=sp.getProducto().getNombre()%></h2>
                <p class="content-request"> <span>Solicitado por:</span> <%=sp.getNombreComprador()%></p>
                <p class="content-date"> <span>Fecha de Solicitud: </span> <%=sp.getFechaSolicitud()%></p>
                <p class="content-price">
                    <span>Precio Final:</span> <span class="price-custom"><%=sp.getProducto().getPrecio()%> €</span></p>
            </div>
        </div>
        <div class="card-info">
            <form action="${pageContext.request.contextPath}/ventaProducto?idProducto=<%=sp.getProducto().getId()%>&correoComprador=<%=sp.getCorreoComprador()%>"
                  method="POST" class="form">
                <div class="form-group">
                    <label class="form-label" for="price">Precio Final</label>
                    <input id="price" class="form-input" name="precioFinal" type="text">
                </div>
                <div class="form-group">
                    <label class="form-label" for="punctuation">Puntuación</label>
                    <input id="punctuation" class="form-input" name="puntuacion" type="text">
                </div>
                <div class="form-group">
                    <label class="form-label" for="commentary">Comentario </label>
                    <textarea name="comentario" id="commentary" class="form-input" cols="30" rows="5"></textarea>
                </div>
                <button type="submit" name="accion" value="vender" class="btn-save">Realizar Venta</button>
                <button type="submit" name="accion" value="borrar" class="btn-delete">Eliminar Solicitud</button>
                <%
                    if(session.getAttribute("failed") != null) {
                        if (session.getAttribute("failed").equals("true")) {
                %>
                <p class="alert-failed">No se pudo realizar la solicitud,verifica que los campos esten rellenandos
                    correctamente e intentalo de nuevo. ✘ </p>
                <% }%>
                <% session.removeAttribute("failed");
                    }%>
            </form>
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
<script src="../js/sail.js"></script>
</body>
<% } %>
</html>