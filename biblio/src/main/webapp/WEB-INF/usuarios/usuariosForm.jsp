<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<body>
    <head>
        <title>Biblioteca Virtual</title>
        <%@include file="/fragments/header_scripts.jsp" %>
        <link href="/styles/navbarHome.css" rel="stylesheet">
        <link href="/styles/users/usuariosList.css" rel="stylesheet">
        <link href="/styles/modal.css" rel="stylesheet">
        <link href="/styles/users/usuariosForm.css" rel="stylesheet">

        <script src="/js/usuarios.js"></script>
        <script src="/js/modal.js" type="module"></script>
    </head>
    <body>
        <%@include file="/fragments/navbarHome.jsp" %>
        <%@include file="/fragments/modal.jsp" %>
        
        <div class="container">
            <h3>Mantendor de usuarios</h3>

            <form id="userForm" action="/users/grabar" method="post">
                <input type="hidden" name="id" value="<c:out value='${usuario.id}'/>"/>
                <input type="hidden" name="accion" value="crud" />
                <div class="form-group row">
                    <label for="input-nombre" class="col-sm-2 col-form-label">Nombre</label>
                    <div class="col-sm-10">
                    <input 
                        type="text" 
                        class="form-control" 
                        id="input-nombre" 
                        name="nombre" 
                        value="<c:out value='${usuario.nombre}'/>"
                        placeholder="Nombre(s)"
                        maxlength="50"
                        required
                    />
                    </div>
                </div>
                <div class="form-group row">
                    <label for="input-apellidos" class="col-sm-2 col-form-label">Apellidos</label>
                    <div class="col-sm-10">
                    <input 
                        type="text" 
                        class="form-control" 
                        id="input-apellidos" 
                        name="apellidos" 
                        value="<c:out value='${usuario.apellidos}'/>"
                        placeholder="Apellidos"
                        maxlength="50"
                        required
                    />
                    </div>
                </div>
                <div class="form-group row">
                    <label for="input-email" class="col-sm-2 col-form-label">Email</label>
                    <div class="col-sm-10">
                    <input 
                        type="email" 
                        class="form-control" 
                        id="input-email" 
                        name="email" 
                        value="<c:out value='${usuario.email}'/>"
                        placeholder="email@ejemplo.cl"
                        maxlength="255"
                        required
                    />
                    </div>
                </div>
                <div class="form-group row">
                    <label for="input-password" class="col-sm-2 col-form-label">Password</label>
                    <div class="col-sm-10">
                    <input 
                        type="password" 
                        class="form-control" 
                        id="input-password" 
                        name="password" 
                        value="<c:out value='${usuario.password}'/>" 
                        placeholder="Contraseña"
                        maxlength="20"
                        />
                    </div>
                </div>
            </form>

            <form id="delete-form" action="/users/eliminar" method="post">
                <input type="hidden" name="id" id="delete-id" value="<c:out value='${usuario.id}'/>"/>
            </form>

            <div class="btn-container">
                <button 
                    type="button"  
                    class="btn btn-success" 
                    onclick="showModal(
                        'userForm',
                        null,
                        'Nuevo usuario',
                        '¿Desea grabar el registro?',
                        'Grabar'
                    )">
                        Grabar
                    </button>

                <button 
                    type="button" 
                    class="btn btn-danger 
                        <c:if test='${usuario.id == null}'>
                            btn-disabled
                        </c:if>
                    " 
                    onclick="showModal(
                        'delete-form',
                        '${usuario.id}',
                        'Eliminar usuario',
                        '¿Desea eliminar el registro?',
                        'Eliminar'
                    );"
                    
                    <c:if test="${usuario.id == null}">
                        disabled
                    </c:if>

                >
                    Eliminar
                </button>
                <a class="btn btn-primary" href="/users">Cancelar</a>
            </div>

            
        </div>

    </body>
</body>