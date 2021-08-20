<%--
  Created by IntelliJ IDEA.
  User: Lubania
  Date: 8/20/21
  Time: 7:54 a. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>Login</title>
    <link href="${context}/assets/bootstrap/css/bootstrap.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h5>Inicio de sesión</h5>
    <form action="${context}/ServletSession" method="post">
        <label class="form-label mb-3">Correo electrónico:</label>
        <input class="form-control mb-3" type="email" name="email">
        <label class="form-label">Contraseña:</label>
        <input class="form-control mb-3" type="password" name="password">
        <input class="btn btn-success" type="submit" value="Ingresar">
    </form>
</div>
<script src="${context}/assets/bootstrap/js/bootstrap.bundle.js"></script>
</body>
</html>
