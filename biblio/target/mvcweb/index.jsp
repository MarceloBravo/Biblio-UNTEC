<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>Biblioteca Virtual</title>
        <%@include file="/fragments/header_scripts.jsp" %>
        <link rel="stylesheet" href="/styles/index.css">
    </head>
    <body>
        <h2>Vienvenido a la biblioteca virtual de UNTEC!</h2>

        <form action="login" method="post">
            <h3>Login</h3>
            <div>
                <label for="txt_email">Email</label>
                <input type="email" id="txt-email" name="email" placeholder="ingresa tu email" maxlength="255"/>
            </div>
            <div>
                <label for="txt_password">Password</label>
                <input type="password" id="txt_password" name="password" placeholder="ingresa tu contraseña" maxlength="30"/>
            </div>
            <div class="btn-container">
                <button class="btn btn-primary">Iniciar Sesión</button>
            </div>
            <p>¿No tienes una cuenta? <a href="/register">Registrate acá</a></p>
        </form>
    </body>
</html>
