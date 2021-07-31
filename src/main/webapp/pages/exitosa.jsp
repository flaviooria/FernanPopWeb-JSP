<%-- 
    Document   : exitosa
    Created on : Jul 7, 2021, 5:14:25 PM
    Author     : 2097k
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FernanPop | Operación Exitosa </title>
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
        <h1>Hello World!</h1>
        <p><%=session.getAttribute("exito")%></p>
        <%
            session.removeAttribute("exito");
        %>
        <a href="./inicio.jsp">para continuar click aquí</a>
    </body>
</html>
