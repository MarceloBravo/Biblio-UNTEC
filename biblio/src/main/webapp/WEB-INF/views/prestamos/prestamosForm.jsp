<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<body>
    <head>
        <title>Biblioteca Virtual</title>
        <%@include file="/WEB-INF/fragments/header_scripts.jsp" %>
        <link href="/static/styles/navbarHome.css" rel="stylesheet">
        <link href="/static/styles/prestamos/prestamosList.css" rel="stylesheet">
        <link href="/static/styles/modal.css" rel="stylesheet">
        <link href="/static/styles/prestamos/prestamosForm.css" rel="stylesheet">        

        <script src="/static/js/prestamos.js"></script>
        <script src="/static/js/modal.js" type="module"></script>
    </head>
    <body>
        <%@include file="/WEB-INF/fragments/navbarHome.jsp" %>
        <%@include file="/WEB-INF/fragments/modal.jsp" %>
        <%@include file="/WEB-INF/fragments/searchUserModal.jsp" %>
        <%@include file="/WEB-INF/fragments/searchBookModal.jsp" %>
        <%@include file="/WEB-INF/fragments/alert.jsp" %>
        
        <div class="container">
            <h3>Mantendor de prestamos</h3>
            <form id="loanForm" action="/loans/grabar" method="post">
                <input type="hidden" name="id" value="<c:out value='${prestamo.id}'/>"/>
                <input type="hidden" id="input-book_id" name="bookId" value="<c:out value='${prestamo.libro.id}'/>"/>
                <input 
                    type="hidden" 
                    class="form-control" 
                    id="input-user_id" 
                    name="userId" 
                    value="<c:out value='${prestamo.usuario.id}'/>"
                    placeholder="Código del usuario"
                    maxlength="13"
                    required
                />
                <input type="hidden" name="accion" value="crud" />

                <div class="book-container">
                    <div class="form-group row">
                        <label for="input-isbn" class="col-sm-2 col-form-label"><b>ISBN</b></label>
                        <div class="col-sm-10 isbn-container">
                            <input 
                                type="text" 
                                class="form-control" 
                                id="input-isbn" 
                                name="isbn" 
                                value="<c:out value='${prestamo.libro.isbn}'/>"
                                placeholder="Código internacional único"
                                maxlength="13"
                                required
                                <c:if test="${prestamo.id != null}">
                                    disabled
                                </c:if>
                            />
                            <c:if test="${prestamo.id == null}">
                                <button type="button" id="btn_buscar_libro" class="btn btn-primary" onclick="showFindBookModal()">Buscar Libro</button>
                            </c:if>
                        </div>
                    </div>
                    
                    <div class="form-group row">
                        <div class="col-sm-4">
                            <div class="form-group row">
                                <label for="input-book_name" class="col-sm-6 col-form-label"><b>Libro:</b></label>
                                <div class="col-sm-6 book_name-container">
                                    <input type="text" readonly class="form-control-plaintext" id="input-book_name" value="<c:out value='${prestamo.libro.nombre}'/>">
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="form-group row">
                                <label for="input-editorial" class="col-sm-6 col-form-label"><b>Editorial:</b></label>
                                <div class="col-sm-6 editorial-container">
                                    <input type="text" readonly class="form-control-plaintext" id="input-editorial" value="<c:out value='${prestamo.libro.editorial}'/>">
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="form-group row">
                                <label for="input-fechaPublicacion" class="col-sm-6 col-form-label"><b>Pecha publicacion:</b></label>
                                <div class="col-sm-6 fechaPublicacion-container">
                                    <input type="text" readonly class="form-control-plaintext" id="input-fechaPublicacion" value="<c:out value='${prestamo.libro.fechaPublicacion}'/>">
                                </div>
                            </div>
                        </div>
                        
                    </div>
                </div>


                <div class="user-container">
                    <div class="form-group row">
                        <label for="input-user_id" class="col-sm-2 col-form-label"><b>Usuario:</b></label>
                        <div class="col-sm-10 user_id-container">
                            <input type="text" readonly class="form-control-plaintext" id="input-user_name" value="<c:out value='${prestamo.usuario.nombre}'/> <c:out value='${prestamo.usuario.apellidos}'/>">
                            <c:if test="${prestamo.id == null}">
                                <button type="button" id="btn_buscar_usuario" class="btn btn-primary" onclick="showFindUserModal()">Buscar usuario</button>
                            </c:if>
                        </div>
                    </div>
                </div>
                <div class="loan-container row">
                    <div class="col-sm-4">
                        <label for="input-fechaPrestamo" class="col-form-label">Fecha prestamo</label>
                        <div class="col-sm-10">
                            <input 
                            type="date" 
                            class="form-control
                                <c:if test='${prestamo.id != null && prestamo.fechaPrestamo != null}'>
                                    input-disabled
                                </c:if>
                            " 
                            id="input-fechaPrestamo" 
                            name="fechaPrestamo" 
                            value="<c:out value='${prestamo.fechaPrestamo}'/>"
                            maxlength="13"
                            required
                            <c:if test="${prestamo.id != null}">
                                readonly
                            </c:if>
                            />
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <label for="input-fechaDevolución" class="col-form-label">Fecha devolución esperada</label>
                        <div class="col-sm-10">
                            <input 
                            type="date" 
                            class="form-control
                                <c:if test='${prestamo.id != null && prestamo.fechaDevolucion != null}'>
                                    input-disabled
                                </c:if>
                            " 
                            id="input-fechaDevolucion" 
                            name="fechaDevolucion" 
                            value="<c:out value='${prestamo.fechaDevolucion}'/>"
                            maxlength="13"
                            required
                            <c:if test="${prestamo.id != null}">
                                readonly
                            </c:if>
                            />
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <label for="input-fechaRetorno" class="col-form-label">Fecha entrega</label>
                        <div class="col-sm-10">
                            <input 
                            type="date" 
                            class="form-control
                                <c:if test='${prestamo.id != null && prestamo.fechaRetorno != null}'>
                                    input-disabled
                                </c:if>
                            " 
                            id="input-fechaRetorno" 
                            name="fechaRetorno" 
                            value="<c:out value='${prestamo.fechaRetorno}'/>"
                            maxlength="13"     
                            <c:if test="${prestamo.fechaRetorno != null}">
                                readonly
                            </c:if>                       
                            />
                        </div>
                    </div>
                </div>
                
            </form>

            <form id="delete-form" action="/loans/eliminar" method="post">
                <input type="hidden" name="id" id="delete-id" value="<c:out value='${prestamo.id}'/>"/>
            </form>
            <div class="btn-container">
                <button 
                    type="button"  
                    class="btn btn-success
                        <c:if test='${prestamo.id != null && prestamo.fechaRetorno != null}'>
                            btn-disabled
                        </c:if>
                    " 
                    onclick="showModal(
                        'loanForm',
                        null,
                        'Nuevo prestamo',
                        '¿Desea grabar el registro?',
                        'Grabar'
                        )"

                        <c:if test="${prestamo.id != null && prestamo.fechaRetorno != null}">
                            disabled
                        </c:if>
                    >
                        Grabar
                </button>

                <button 
                    type="button" 
                    class="btn btn-danger 
                        <c:if test='${prestamo.id == null || (prestamo.id != null && prestamo.fechaRetorno != null)}'>
                            btn-disabled
                        </c:if>
                    " 
                    onclick="showModal(
                        'delete-form',
                        '${prestamo.id}',
                        'Eliminar prestamo',
                        '¿Desea eliminar el registro?',
                        'Eliminar'
                    );"

                    <c:if test="${prestamo.id == null || (prestamo.id != null && prestamo.fechaRetorno != null)}">
                        disabled
                    </c:if>
                >
                    Eliminar
                </button>
                <a class="btn btn-primary" href="/loans">Cancelar</a>
            </div>

            
        </div>

    </body>
</body>