<%@ page import="modelos.Usuario" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="modelos.Producto" %>
<%@ page import="modelos.Gestion" %><%--
  Created by IntelliJ IDEA.
  User: 2097k
  Date: 09/07/2021
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Usuario user = (Usuario) session.getAttribute("user");
    if (user == null) {
        session.setAttribute("error", "Usuario no identificado.");
        response.sendRedirect("./error.jsp");
    } else {
        ArrayList<Producto> listProducts;
        if ((ArrayList<Producto>) session.getAttribute("productosByTerm") == null) {
            listProducts = new ArrayList<>();
        } else {
            listProducts = (ArrayList<Producto>) session.getAttribute("productosByTerm");
            session.removeAttribute("productosByTerm");
        }

        ArrayList<Producto> allProducts = new ArrayList<>();
        Gestion gestion = null;

        try {
            gestion = new Gestion();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (gestion != null) {
            allProducts = Gestion.allProductos();
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
    <title>FernanPop | Productos </title>
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
    <link rel="stylesheet" href="../css/searchProducts.css">
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
                <li><a class="link" href="./cerrarSesion.jsp">Cerrar Sesión</a></li>
            </ul>
        </nav>
    </header>
    <!-- Hero  -->
    <section class="hero">
        <div class="hero-description">
            <h3 class="hero-title">Compra lo que quieras en FernanPop</h3>
            <p class="hero-subtitle">Nuestros Productos:</p>
            <!-- Nav search -->
            <form action="${pageContext.request.contextPath}/mostrarProductos" class="form-search" method="post" autocomplete="off">
                <div class="header-search">
                    <input type="text" name="nameProduct" id="nameProduct" placeholder="Busca todo lo que quieras">
                    <button type="submit" class="btn-search"><i class="fas fa-search"></i></button>
                </div>
            </form>

        </div>
        <%
            if (listProducts.isEmpty() && allProducts.isEmpty()) {
        %>
        <!-- Alert Products -->
        <div class="alert-products">
            <h1 class="alert-title">Ups, producto no encontrado</h1>
            <lottie-player class="alert-lottie" src="https://assets4.lottiefiles.com/packages/lf20_b8gboh1d.json"
                           speed="1" loop autoplay>
            </lottie-player>
        </div>
        <% } else {%>
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
                    <a href="${pageContext.request.contextPath}/detalleProducto?id=<%=p.getId()%>" class="btn-delete">Ver
                        Producto</a>
                </div>
            </div>
            <% } %>
            <%
                if (listProducts.isEmpty()) {
                    for (Producto p : allProducts) {
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
                    <a href="${pageContext.request.contextPath}/detalleProducto?id=<%=p.getId()%>" class="btn-delete">Ver
                        Producto</a>
                </div>
            </div>
            <% } %>
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

    let desc = document.querySelectorAll(".focus-description");

    desc.forEach(item => {
        let abbr = item.innerText;
        if (abbr.length > 115) {
            abbr = abbr.substring(0, 115) + " . . .";
            item.innerText = abbr
        }
    })

    //Funcionalidad para autocompletado de busqueda
    autocompletar();

    function autocompletar() {
        const inputSearch = document.querySelector('#nameProduct');
        let indexFocus = -1;

        inputSearch.addEventListener('input', function () {
            const term = this.value;

            if (!term) return false;
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

        inputSearch.addEventListener('keydown', function (e) {
            const divList = document.querySelector('#' + this.id + '-lista-autocompletar');
            let items;

            if (divList) {
                items = divList.querySelectorAll('div');

                switch (e.keyCode) {
                    case 40: //tecla de abajo
                        indexFocus++;
                        if (indexFocus > items.length - 1) indexFocus = items.length - 1;
                        break;

                    case 38: //tecla de arriba
                        indexFocus--;
                        if (indexFocus < 0) indexFocus = 0;
                        break;

                    case 13: // presionas enter
                        e.preventDefault();
                        items[indexFocus].click();
                        indexFocus = -1;
                        break;

                    default:
                        break;
                }

                seleccionar(items, indexFocus);
                return false;
            }
        });

        document.addEventListener('click', function () {
            cerrarLista();
        });
    }

    function seleccionar(items, indexFocus) {
        if (!items || indexFocus === -1) return false;
        items.forEach(x => {
            x.classList.remove('autocompletar-active')
        });
        items[indexFocus].classList.add('autocompletar-active');
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