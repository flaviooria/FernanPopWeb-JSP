<%@ page import="modelos.Gestion" %>
<%@ page import="modelos.Mensaje" %><%--
  Created by IntelliJ IDEA.
  User: flaviooria
  Date: 30/07/2021
  Time: 09:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    int idChat = Integer.parseInt(request.getParameter("idChat"));
    String accion = request.getParameter("accion");
    Mensaje msg = new Mensaje();
    Gestion gestion = null;
    String fecha = " ";
    try {
        gestion = new Gestion();
    } catch (Exception e) {
        e.printStackTrace();
    }
    if (gestion != null) {
        msg = Gestion.obtenerMensje(idChat);
        fecha = ((accion.equals("enviado") ? msg.getFechaLectura() : msg.getFechaEnvio()));
    } else {
        session.setAttribute("error", "No se pudo conectar a la base de datos.");
        response.sendRedirect("./error.jsp");
    }
%>
<div class="chat-message">
    <p class="message-content"><%=msg.getContenido()%></p>
    <div class="message-about">
        <span class="about-date"><%=fecha%></span>
        <span class="about-check">
            <%
                if (msg.isEstaLeido()) {
            %>
            <img src="../assets/icons/double-check-skyblue.svg" alt="check">
            <%} else {%>
            <img src="../assets/icons/double-check-gray.svg" alt="check">
            <%}%>
            <a href="" class="btn-delete" title="eliminar"><i class="fas fa-trash-alt"></i></a>
        </span>
    </div>
</div>