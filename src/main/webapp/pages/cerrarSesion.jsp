<%--
  Created by IntelliJ IDEA.
  User: 2097k
  Date: 04/07/2021
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    session.removeAttribute("user");
    session.removeAttribute("token");
    response.sendRedirect("../index.jsp");
%>