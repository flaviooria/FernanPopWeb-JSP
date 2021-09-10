<%@page import="java.util.ArrayList" %>
<%@ page import="modelos.*" %>
<%@ page import="java.util.Locale" %>
<%@ page import="utilidades.Utils" %>
<%@ page import="static utilidades.Utils.getNombreCompleto" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%
    Usuario user;
    String nombrePefil = null;
    if (session.getAttribute("user") == null) {
        session.setAttribute("error", "Usuario no identificado.");
        response.sendRedirect("./error.jsp");
    } else {
        Gestion gestion = null;
        ArrayList<Producto> listProducts = new ArrayList<>();
        ArrayList<SolicitudProducto> solicitudes = new ArrayList<>();
        ArrayList<Trato> valoracionesPendientes = new ArrayList<>();
        ArrayList<Mensaje> mensajes = new ArrayList<>();
        try {
            gestion = new Gestion();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (gestion != null) {
            user = (Usuario) session.getAttribute("user");
            nombrePefil = getNombreCompleto(user);
            listProducts = Gestion.obtenerProductosByUser(user.getId());
            solicitudes = Gestion.mostrarSolicitudesByUsuario(user);
            valoracionesPendientes = Gestion.getValoracionPendiente(user);
            mensajes = Gestion.mensajesRecibidosSinLeer(user.getId());
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
    <title>FernanPop | Inicio</title>
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
    <link rel="stylesheet" href="../css/home.css">
    <!--fonts -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link
            href="https://fonts.googleapis.com/css2?family=Quicksand:wght@500;700&family=Raleway:wght@700;900&display=swap"
            rel="stylesheet">
    <!--fontawesome icons-->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.1/css/all.css"
          integrity="sha384-vp86vTRFVJgpjF9jiIGPEEqYqlDwgyBgEF109VFjmqGmIY/Y4HV4d3Gp2irVfcrp" crossorigin="anonymous"/>
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
                <li><a class="link" href="../pages/productos.jsp">Ver Productos</a></li>
                <li><a class="link" href="../pages/tratos.jsp">Ver mis tratos</a></li>
                <li><a class="link" href="../pages/msg.jsp">Ver mis mensajes</a></li>
                <li><a class="link" href="./cerrarSesion.jsp">Cerrar Sesión</a></li>
            </ul>
        </nav>
    </header>
    <!-- Hero  -->
    <section class="hero">
        <div class="hero-description">
            <h3 class="hero-title">Bienvenido a FernanPop, <%=nombrePefil%>
            </h3>
            <p class="hero-subtitle">Tus productos:</p>
            <!-- Notifications -->
            <div class="hero-notifications">
                <!-- Notifications valorates -->
                <div class="notification-bell center">
                    <input type="checkbox" class="bell" name="" id="">
                    <div class="num number-bell center"></div>
                    <div class="box">
                        <div class="heading center">
                            <p><span class="number-bell"></span>Notificaciones</p>
                        </div>
                        <div class="notification_box">
                            <% if (!solicitudes.isEmpty()) { %>
                            <%
                                for (SolicitudProducto sp : solicitudes) {
                            %>
                            <form action="./ventaProducto.jsp?idSolicitud=<%=sp.getId()%>"
                                  class="form-notification num-bell" method="post">
                                <p class="subject">Asunto: Solicitud Pendiente</p>
                                <p class="product">Producto: <%=sp.getProducto().getNombre()%>
                                </p>
                                <p class="user">Solicitante: <%=sp.getNombreComprador()%> (<%=sp.getCorreoComprador()%>
                                    )</p>
                                <button type="submit" class="send-valorate">Aceptar</button>
                            </form>
                            <% }%>
                            <% }%>
                            <% if (!valoracionesPendientes.isEmpty()) { %>
                            <%
                                for (Trato t : valoracionesPendientes) {
                                    Usuario userTrato = Gestion.obtenerUsuarioByCorreo(t.getCorreoOtroUsuario());
                            %>
                            <form action="./valorarProducto.jsp?idTrato=<%=t.getId()%>"
                                  class="form-notification num-bell" method="post">
                                <p class="subject">Asunto: Valoración Pendiente</p>
                                <p class="product">Producto: <%=t.getNombreProducto()%>
                                </p>
                                <p class="user">Vendedor: <%=userTrato.getNombre()%> (<%=userTrato.getCorreo()%>)</p>
                                <button type="submit" class="send-valorate">Valorar</button>
                            </form>
                            <% }%>
                            <% }%>
                        </div>
                    </div>
                </div>
                <!-- Notifications messages -->
                <div class="notification-msg center">
                    <input type="checkbox" class="msg">
                    <div class="num number-msg center"></div>
                    <div class="box">
                        <div class="heading center">
                            <p><span class="number-msg"></span>Notificaciones</p>
                        </div>
                        <div class="notification_box">
                            <% if (!mensajes.isEmpty()) {%>
                            <% for (Mensaje m : mensajes) {
                            %>
                            <form action="./msg.jsp" class="form-notification num-msg">
                                <p class="subject">Coversación pendiente</p>
                                <p class="product">Asunto: <%=m.getAsunto()%>
                                </p>
                                <p class="user">Enviado: <%=getNombreCompleto(m.getEmisor())%>
                                </p>
                                <button type="submit" class="send-valorate">Ver</button>
                            </form>
                            <%}%>
                            <%}%>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Nav search -->
            <form action="${pageContext.request.contextPath}/mostrarProductos" class="form-search" method="post" autocomplete="off">
                <div class="header-search">
                    <input type="text" name="nameProduct" id="nameProduct" placeholder="Busca todo lo que quieras">
                    <button type="submit" class="btn-search"><i class="fas fa-search"></i></button>
                </div>
            </form>
        </div>
        <%
            if (listProducts.isEmpty()) {
        %>
        <!-- Alert Products -->
        <div class="alert-products">
            <h1 class="alert-title">Aún no tienes ningún producto</h1>
            <lottie-player class="alert-lottie" src="https://assets4.lottiefiles.com/packages/lf20_b8gboh1d.json"
                           speed="1" loop autoplay>
            </lottie-player>
            <a class="alert-upload" href="./subirProducto.jsp" target="_self" rel="noopener noreferrer">Empieza subiendo
                uno!!!</a>
        </div>
        <% } else { %>
        <!-- Section Products -->
        <div class="products">
            <%
                for (Producto p : listProducts) {
            %>
            <div class="card">
                <img class="card-image" loading="lazy"
                     src="${pageContext.request.contextPath}/renderImagenProducto?id=<%=p.getId()%>" alt="imagen">
                <h3><%=p.getNombre()%>
                </h3>
                <div class="focus-content">
                    <p class="focus-description"><%=p.getDescripcion()%>
                    </p>
                    <p class="focus-price"><%=p.getPrecio()%> €</p>
                    <a href="${pageContext.request.contextPath}/borrarProducto?id=<%=p.getId()%>" class="btn-delete">Borrar
                        Producto</a>
                </div>
            </div>
            <% } %>
        </div>
        <% } %>
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
<script src="https://unpkg.com/@lottiefiles/lottie-player@latest/dist/lottie-player.js"></script>
<script>
    let hamburger = document.querySelector(".hamburger--spin");

    let menu = document.querySelector(".navbar-menu");

    hamburger.addEventListener("click", () => {
        hamburger.classList.toggle("is-active");
        menu.classList.toggle("open");
    })

    const bell = document.querySelectorAll(".notification_box  .num-bell");
    const numberBell = document.querySelectorAll(".number-bell");
    numberBell.forEach(item => item.innerText = bell.length);

    const msg = document.querySelectorAll(".notification_box  .num-msg");
    const numberMsg = document.querySelectorAll(".number-msg");
    numberMsg.forEach(item => item.innerText = msg.length);

    let desc = document.querySelectorAll(".focus-description");

    desc.forEach(item => {
        let abbr = item.innerText;
        if (abbr.length > 115) {
            abbr = abbr.substring(0, 115) + " . . .";
            item.innerText = abbr;
        }
    })

    //Funcionalidad para autocompletado de busqueda
    autocompletar();

    function autocompletar() {
        const inputSearch = document.querySelector('#nameProduct');
        let indexFocus = -1;

        inputSearch.addEventListener('keyup', function () {
            const term = this.value;

            if (!term || term.length < 3 || term === "" ) return;
            cerrarLista();

            //crear la lista de sugerencias
            const divList = document.createElement('div');
            divList.setAttribute('id', this.id + '-lista-autocompletar');
            divList.setAttribute('class', 'lista-autocompletar-items');
            this.parentNode.appendChild(divList);

            // conexión a BD
            httpRequest('${pageContext.request.contextPath}/ajaxBuscador?nameProduct=' + term, function () {

                const arreglo = JSON.parse(this.responseText);

                //validar arreglo vs input
                if (arreglo.length === 0) return false;
                arreglo.forEach(item => {
                    const elementoLista = document.createElement('div');
                    elementoLista.innerHTML = item.nombre;
                    elementoLista.addEventListener('click', function () {
                        inputSearch.value = this.innerText;
                        cerrarLista();
                        return false;
                    });
                    divList.appendChild(elementoLista);

                });
            })
        });

        document.addEventListener('click', function () {
            cerrarLista();
        });
    }

    function cerrarLista() {
        const items = document.querySelectorAll('.lista-autocompletar-items');
        items.forEach(item => {
            item.parentNode.removeChild(item);
        });
        indexFocus = -1;
    }

    function httpRequest(url, callback) {
        const http = new XMLHttpRequest();
        http.open('GET', url);
        http.send();

        http.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                callback.apply(http);
            }
        }
    }
</script>
<% }%>
</body>

</html>