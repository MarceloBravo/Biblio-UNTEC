<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <html>

        <head>
            <title>Biblioteca Virtual</title>
            <%@include file="/fragments/header_scripts.jsp" %>

                <link rel="stylesheet" href="/styles/register_user.css">
        </head>

        <body>
            <%@include file="/fragments/navbar.jsp" %>

                <h3>Registro de usuarios</h3>
                <form action="${pageContext.request.contextPath}/register" method="post">
                    <input type="hidden" name="accion" value="registrar" />
                    <div class="form-group row">
                        <label for="id-nombre" class="col-sm-2 col-form-label">Nombre</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="id-nombre" name="nombre" placeholder="Nombre(s)"
                                maxlength="50" required>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="id-apellidos" class="col-sm-2 col-form-label">Apellidos</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="id-apellidos" name="apellidos"
                                placeholder="Apellido(s)" maxlength="50" required>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="id-email" class="col-sm-2 col-form-label">Email</label>
                        <div class="col-sm-10">
                            <input type="email" class="form-control" id="id-email" name="email"
                                placeholder="Ej.: mi.email@ejemplo.cl" maxlength="255" required>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="id-password" class="col-sm-2 col-form-label">Password</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" id="id-assword" name="password"
                                placeholder="Password" maxlength="20" required>
                        </div>
                    </div>

                    <div class="btn-container">
                        <button class="btn btn-primary">Registrar</button>
                        <a class="btn btn-danger" href="index.jsp">Cancelar</a>
                    </div>
                </form>

        </body>

        </html>