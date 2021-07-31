<%--
  Created by IntelliJ IDEA.
  User: 2097k
  Date: 05/07/2021
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Buscador</h1>
<form  class="form-search" method="post">
    <div class="header-search">
        <input type="text" name="nameProduct" id="nameProduct" placeholder="Busca todo lo que quieras">
        <button type="submit" class="btn-search"><i class="fas fa-search"></i></button>
    </div>
</form>
</body>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"
        integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script>
    document.querySelector(".btn-search").addEventListener("click",buscar);
    function buscar() {
        $.ajax({
            url: "${pageContext.request.contextPath}/ajaxBuscador",
            data: {
                name: nameProduct
            },
            success: function( result ) {
                $( ".result" ).html(result);
            }
        });
    }

</script>
</html>
