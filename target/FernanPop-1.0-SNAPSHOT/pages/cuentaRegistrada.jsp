<%-- 
    Document   : cuentaRegistrada
    Created on : Jul 3, 2021, 1:18:38 AM
    Author     : 2097k
--%>

<%@page import="modelos.Usuario" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%
    Usuario user = (Usuario) session.getAttribute("user");
    String token = (String) session.getAttribute("token");
    if (user == null) {
        session.setAttribute("error", "Debes de loguearte.");
        response.sendRedirect("./error.jsp");
    } else {
        String nombre = user.getNombre().substring(0,1).toUpperCase() + user.getNombre().substring(1);
        System.out.println("El token es: " +token);
%>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
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
    <!--Styles css-->
    <link rel="stylesheet" href="../css/email.css"/>
    <!--fonts -->
    <link
            href="https://fonts.googleapis.com/css2?family=Quicksand:wght@500;700&family=Raleway:wght@700;900&display=swap"
            rel="stylesheet"
    />
    <!--fontawesome icons-->
    <link
            rel="stylesheet"
            href="https://use.fontawesome.com/releases/v5.15.1/css/all.css"
            integrity="sha384-vp86vTRFVJgpjF9jiIGPEEqYqlDwgyBgEF109VFjmqGmIY/Y4HV4d3Gp2irVfcrp"
            crossorigin="anonymous"
    />
</head>
<body>
<div class="container">
    <h1 class="title">
        <a href="https://flaviooria.github.io/FernanPop-web/" target="_blank"
        >FernanPop</a>
    </h1>
    <div class="container-home">
        <div class="home-welcome">
            <h3 class="title-home">Bienvenido!!!</h3>
            <p>
                Hola <%=nombre%>,<br/>Para terminar de configurar tu cuenta y empezar a
                usar FernanPop, confirma que te has registrado correctamente.
            </p>
        </div>
        <hr/>
        <div class="token-validation">
            <p>Copia el siguiente token para validarte.</p>
            <div class="token">
                <p>
                    token: <span id="token"><%=token%></span
                ><i class="fas fa-copy" title="copiar"></i>
                </p>
            </div>
        </div>
        <hr/>
        <div class="slogan-web">
            <p>Tus amigos de FernanPop <i class="fas fa-heart"></i></p>
            <div>
                <a class="home" href="./autentificarToken.jsp">Da click aquí para continuar</a>
            </div>
        </div>
    </div>
    <div class="footer">
        <p>© 2021 FernanPop Web - Martos, Jaén, España</p>
        <ul class="about-media">
            <li>
                <a href="" title="facebook/fernanpop.com"
                ><i class="fab fa-facebook-square"></i
                ></a>
            </li>
            <li>
                <a href=""><i class="fab fa-instagram-square"></i></a>
            </li>
            <li>
                <a href=""><i class="fab fa-twitter-square"></i></a>
            </li>
            <li>
                <a href="https://github.com/flaviooria/FernanPop-web" target="_blank"><i class="fab fa-github-square"></i></a>
            </li>
        </ul>
    </div>
</div>
<script>
    const botonCopiar = document.querySelector('i.fa-copy')
    const token = document.getElementById('token')
    let tokenAletorio = "<%=token%>";
    let textoDeToken = token.innerHTML = tokenAletorio;

    botonCopiar.addEventListener('click' , () => {
        token.focus()

        const selection = window.getSelection();

        // Save the current selection
        const currentRange = selection.rangeCount === 0
            ? null : selection.getRangeAt(0);

        // Select the text content of code element
        const range = document.createRange();
        range.selectNodeContents(token);
        selection.removeAllRanges();
        selection.addRange(range);

        // Copy to the clipboard
        try {
            document.execCommand('copy');
            token.innerHTML = 'Copiado';
            console.log('Copiado')
        } catch (err) {
            // Unable to copy
            token.innerHTML = textoDeToken;
            console.error(err)
        } finally {
            // Restore the previous selection
            selection.removeAllRanges();
            currentRange && selection.addRange(currentRange);
        }



        setTimeout(() => token.innerHTML = textoDeToken, 500)
    })
</script>
</body>
<% }%>
</html>

