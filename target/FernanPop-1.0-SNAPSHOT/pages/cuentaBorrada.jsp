<%@ page import="modelos.Usuario" %>
<%@ page import="utilidades.Utils" %><%--
  Created by IntelliJ IDEA.
  User: 2097k
  Date: 07/07/2021
  Time: 22:13
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
        String allNombre = Utils.getNombreCompleto(user);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FernanPop | Cuenta Eliminada </title>
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
</head>
<body>
    <h3>Nos lamenta tu perdida.</h3>
    <p>Hola <%=allNombre%>, esperamos verte pronto a FernanPopWeb, gracias por tu preferirnos.</p>
    <a href="../index.jsp">Click aqui para continuar</a>
    <%
        session.removeAttribute("user");
    }%>
</body>
</html>
