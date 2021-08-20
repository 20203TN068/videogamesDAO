<%--
  Created by IntelliJ IDEA.
  User: Lubania
  Date: 8/20/21
  Time: 7:20 a. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"></c:set>
<html>

<head>
    <title>Games</title>
    <link href="${context}/assets/bootstrap/css/bootstrap.css" rel="stylesheet">
</head>

<body onload="loadContent()">
<div class="container mt-5">
    <button class="btn btn-primary" onclick="loadContent()">Recargar</button>
    <button class="btn btn-success" data-bs-toggle="modal" data-bs-target="#modalNuevoJuego">Agregar juego</button>
    <h3 class="mt-3">Videojuegos</h3>
    <div id="content">

    </div>
</div>


<div class="modal fade" id="modalNuevoJuego" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Nuevo juego</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="row gy-5">
                    <div class="col-12">
                        <label class="form-label">Nombre:</label>
                        <input type="text" class="form-control" required name="create_nameInput" id="create_nameInput">
                    </div>
                    <div class="col-12">
                        <label class="form-label">Fecha de lanzamiento</label>
                        <input type="date" class="form-control" required name="create_dateInput" id="create_dateInput">
                    </div>
                    <div class="col-12">
                        <label class="form-label">Categoría:</label>
                        <select class="form-select" required name="create_categoryInput" id="create_categoryInput">
                            <option value="" selected>Selecciona una cateogría...</option>
                            <c:forEach items="${categoryList}" var="category">
                                <option value="${category.idCategory}">${category.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-12">
                        <label class="form-label">Imagen:</label>
                        <input type="file" class="form-control" required name="create_imageInput" id="create_imageInput">
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                <button type="button" class="btn btn-success" data-bs-dismiss="modal"
                        onclick="createGame()">Registrar</button>
            </div>
        </div>
    </div>
</div>


<script src="${context}/assets/bootstrap/js/bootstrap.bundle.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
        crossorigin="anonymous"></script>
<script src="${context}/assets/dist/main.js"></script>
</body>

</html>
