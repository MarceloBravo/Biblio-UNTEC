<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>Biblioteca Virtual</title>
        <%@include file="/fragments/header_scripts.jsp" %>
        <link rel="stylesheet" href="/styles/index.css">
    </head>
    <body>
        <h2>Biblioteca virtual de UNTEC!</h2>

        <form action="login" method="post">
            <h3>Login</h3>
            <div class="form-group row">
                <input type="text" class="form-control" id="id-email" name="email" placeholder="ingresa tu email" maxlength="255" required>
            </div>
            <div class="form-group row">
                <input type="password" class="form-control" id="id-password" name="password" placeholder="ingresa tu contraseña" maxlength="30" required>
            </div>

            <div class="btn-container">
                <button class="btn btn-primary">Iniciar Sesión</button>
            </div>
            <p>¿No tienes una cuenta? <a href="registerUser.jsp">Registrate acá</a></p>
        </form>
    </body>
</html>
