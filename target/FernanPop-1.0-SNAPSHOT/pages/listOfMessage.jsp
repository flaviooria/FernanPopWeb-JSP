<%@ page import="modelos.Gestion" %>
<%@ page import="modelos.Usuario" %>
<%@ page import="modelos.Mensaje" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="utilidades.Utils" %><%--
  Created by IntelliJ IDEA.
  User: flaviooria
  Date: 30/08/2021
  Time: 20:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  Usuario user = (Usuario) session.getAttribute("user");
  ArrayList<Mensaje> listaMensajes = new ArrayList<>();
  String accion = request.getParameter("accion");
  Gestion gestion = null;
  try {
    gestion = new Gestion();
  } catch (Exception e) {
    e.printStackTrace();
  }
  if (gestion != null) {
    listaMensajes = (accion.equalsIgnoreCase("recibido"))
            ? Gestion.mensajesRecibidos(user.getId())
            : Gestion.mensajesEnviados(user.getId());
  } else {
    session.setAttribute("error", "No se pudo conectar a la base de datos.");
    response.sendRedirect("./error.jsp");
  }
%>
<%
  if (!listaMensajes.isEmpty()) {
  for (Mensaje m: listaMensajes) {
    String allNombre = Utils.getNombreCompleto(m.getEmisor());
    String accionUser = accion.equalsIgnoreCase("recibido")
    ? "Recibido por: "
    : "Enviado por: ";
%>
<form  class="form">
  <p class="user"><%=accionUser + allNombre%></p>
  <p class="subject">Asunto: <%=m.getAsunto()%></p>
  <button type="button" onclick="getContentMessage(<%=m.getId()%>, <%="'"+accion+"'"%>)">Ver chat</button>
</form>
<% }%>
<% } else {%>
<div class="alert-products">
  <h1 class="alert-title">Aun no tienes ningún mensaje.</h1>
  <lottie-player class="alert-lottie"
                 src="https://assets4.lottiefiles.com/packages/lf20_b8gboh1d.json" speed="1" loop
                 autoplay>
  </lottie-player>
</div>
<%}%>
