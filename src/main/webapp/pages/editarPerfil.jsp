<%@ page import="modelos.Usuario" %>
<%@ page import="modelos.Gestion" %><%--
  Created by IntelliJ IDEA.
  User: flavio-dev
  Date: 2/7/21
  Time: 9:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setCharacterEncoding("UTF-8");
    Usuario user = (Usuario) session.getAttribute("user");
    System.out.println("pagina de inicio el user es = " + user);
    if (user == null) {
        session.setAttribute("error","Usuario no identificado.");
        response.sendRedirect("./error.jsp");
    } else {
        Usuario userPerfil = new Usuario();
        Gestion gestion = null;
        try {
            gestion = new Gestion();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (gestion != null) {
            userPerfil = Gestion.login(user.getCorreo(),user.getContrasenia());
            //Machaco al usuario anterior ya que obtengo un usuario  con los nuevos datos!!!
            session.setAttribute("user",userPerfil);
        } else {
            session.setAttribute("error","No se pudo conectar a la base de datos.");
            response.sendRedirect("./error.jsp");
        }
        String nombreCompleto = userPerfil.getNombre() + " " + userPerfil.getApellido();
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FernanPop | Perfíl</title>
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
    <!-- style -->
    <link rel="stylesheet" href="../css/edit.css">
    <!--fonts -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link
            href="https://fonts.googleapis.com/css2?family=Quicksand:wght@500;700&family=Raleway:wght@700;900&display=swap"
            rel="stylesheet">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css"
          integrity="sha512-5A8nwdMOWrSz20fDsjczgUidUBR8liPYU+WymTZP1lmY9G6Oc7HlZv156XqnsgNUzTyMefFTcsFH/tnJE/+xBg=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
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
                <li><a class="link" href="../pages/productos.jsp">Ver Productos</a></li>
                <li><a class="link" href="../pages/tratos.jsp">Ver mis tratos</a></li>
                <li><a class="link" href="./cerrarSesion.jsp">Cerrar Sesión</a></li>
            </ul>
        </nav>
    </header>
    <!-- Hero -->
    <div class="hero">
        <!-- Card Profile -->
        <div class="hero-card-profile">
            <div class="card">
                <div class="card-header">
                    <div class="waves-container">
                        <div class="wave wave1"></div>
                        <div class="wave wave2"></div>
                        <div class="wave wave3"></div>
                    </div>
                    <%

                        if(userPerfil.getFotoPerfil() != null && userPerfil.getFotoPerfil().available() > 0) {
                            System.out.println(userPerfil.getFotoPerfil().available());
                    %>
                    <img class="img-profile" src="${pageContext.request.contextPath}/renderAvatar?id=<%=userPerfil.getId()%>"
                         alt="img_profile">
                    <% } else { %>
                    <img class="img-profile" src="../assets/icons/user.svg" alt="img_profile">
                    <% } %>
                    <div class="load-avatar-profile">
                        <label for="avatar" class="label-avatar"></label>
                    </div>
                </div>
                <div class="card-body">
                    <h2><%=nombreCompleto%></h2>
                    <div class="profile_ranking">
                        <p class="ranking"></p>
                        <div class="stars-outer">
                            <div class="stars-inner"></div>
                        </div>
                    </div>
                    <p class="member">Miembro desde el <%=userPerfil.getFechaCreacion()%></p>
                </div>
            </div>
        </div>
        <!-- Edit Profile -->
        <div class="hero-edit-profile">
            <h3 class="edit-title">Tus Datos</h3>
            <form action="${pageContext.request.contextPath}/perfilUsuario" class="form" enctype="multipart/form-data" method="post">
                <div class="form-group">
                    <label class="form-label" for="name">Nombre</label>
                    <input id="name" name="name" class="form-input" type="text" value="<%=userPerfil.getNombre()%>" />
                </div>
                <div class="form-group">
                    <label class="form-label" for="lastname">Apellido</label>
                    <input id="lastname" name="lastname" class="form-input" type="text" value="<%=userPerfil.getApellido()%>"/>
                </div>
                <div class="form-group">
                    <label class="form-label" for="email">Correo Electronico</label>
                    <input id="email" name="email" class="form-input" type="text" value="<%=userPerfil.getCorreo()%>"/>
                </div>
                <div class="form-group">
                    <label class="form-label" for="phone">Móvil</label>
                    <input id="phone" name="phone" class="form-input" type="tel" value="<%=userPerfil.getMovil()%>"/>
                </div>
                <div class="form-group">
                    <label class="form-label" for="age">Edad</label>
                    <input id="age" name="age" class="form-input" type="text" value="<%=userPerfil.getEdad()%>"/>
                </div>
                <div class="form-group">
                    <label class="form-label" for="pass">Contraseña</label>
                    <input id="pass" name="pass" class="form-input" type="text" value="<%=userPerfil.getContrasenia()%>"/>
                </div>
                <input type="hidden" class="imgSrc" name="imgSrc" value="">
                <input type="hidden" class="extImg" name="extImg" value="">
                <input type="file" id="avatar" name="avatar" accept="image/*">
                <button class="btn-delete" name="accion" value="borrar">Borrar Cuenta</button>
                <button class="btn-save" name="accion" value="guardar">Guardar Datos</button>
            </form>
            <%
                if(session.getAttribute("editada") != null) {
                    System.out.println("Valor de editado es: "+ session.getAttribute("editada"));
                    if (session.getAttribute("editada").equals("true")) {
            %>
            <span class="save-success">Datos guardados correctamente</span>
            <% } else { %>
            <p class="alert-failed">No se pudo guardar los datos, debes de llenar los campos correctamente.</p>
            <% } %>
            <% session.removeAttribute("editada");
                } %>
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
<!-- cdn de jquery -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js"
        integrity="sha512-qzrZqY/kMVCEYeu/gCm8U2800Wz++LTGK4pitW/iswpCbjwxhsmUwleL1YXaHImptCHG0vJwU7Ly7ROw3ZQoww=="
        crossorigin="anonymous" referrerpolicy="no-referrer">
</script>
<script src="../js/editProfile.js"></script>
<script>
    //Esta es la media que tiene el usuario
    const rating = <%=userPerfil.getNotaMedia()%>
    // total number of stars
    const starTotal = 5;

    const starPercentage = ((<%=userPerfil.getNotaMedia()%>) / starTotal) * 100;

    const starPercentageRounded = (Math.floor(starPercentage / 10 )) * 10;

    document.querySelector(".profile_ranking .ranking").innerHTML = <%=userPerfil.getNotaMedia()%> + " de valoración"

    document.querySelector(`.profile_ranking .stars-inner`).style.width = starPercentageRounded + "%";
</script>
<% }%>
</body>
</html>