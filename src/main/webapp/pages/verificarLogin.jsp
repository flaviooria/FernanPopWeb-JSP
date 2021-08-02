<%-- 
    Document   : verificarLogin
    Created on : Jul 3, 2021, 8:22:21 PM
    Author     : 2097k
--%>

<%@page import="modelos.Gestion"%>
<%@page import="modelos.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String email = request.getParameter("email");
    String pass = request.getParameter("pass");
    System.out.println("email, " + email);
    System.out.println("pass, " + pass);

    Usuario user;
    Gestion gestion = null;
            
        try {
            gestion = new Gestion();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if ((email.isEmpty() || pass.isEmpty())) {
            session.setAttribute("failed","true");
            response.sendRedirect("../index.jsp");
        } else {
            if (gestion != null) {
                user = Gestion.login(email,pass);
                if (user != null) {
                    //Si no esta autentificado el usuario le hacemos el auth y lo rederijimos a la página de cuenta registrada
                    if(!Gestion.isAutentificado(user)) {
                        Gestion.setAutentificado(user);
                        String token = Gestion.obtenerTokenUsuario(user);
                        session.setAttribute("user", user);
                        session.setAttribute("token", token);
                        response.sendRedirect("./cuentaRegistrada.jsp");
                    } else {
                        session.setAttribute("user", user);
                        response.sendRedirect("./inicio.jsp");
                    }
                } else {
                    session.setAttribute("failed","true");
                    response.sendRedirect("../index.jsp");
                }
            } else {
                session.setAttribute("error","No se pudo conectar a la base de datoas.");
                response.sendRedirect("./error.jsp");
            }
        }
%>
