<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<body>
    <head>
        <title>Biblioteca Virtual</title>
        <%@include file="/WEB-INF/fragments/header_scripts.jsp" %>
        <link href="/styles/navbarHome.css" rel="stylesheet">
        <link href="/styles/books/librosList.css" rel="stylesheet">
        <link href="/styles/modal.css" rel="stylesheet">
        <link href="/styles/books/librosForm.css" rel="stylesheet">        

        <script src="/js/libros.js"></script>
        <script src="/js/modal.js" type="module"></script>
    </head>
    <body>
        <%@include file="/WEB-INF/fragments/navbarHome.jsp" %>
        <%@include file="/WEB-INF/fragments/modal.jsp" %>
        
        <div class="container">
            <h3>Mantendor de libros</h3>

            <form id="userForm" action="/books/grabar" method="post">
                <input type="hidden" name="id" value="<c:out value='${libro.id}'/>"/>
                <input type="hidden" name="accion" value="crud" />
                <div class="form-group row">
                    <label for="input-isbn" class="col-sm-2 col-form-label">ISBN</label>
                    <div class="col-sm-10">
                    <input 
                        type="text" 
                        class="form-control" 
                        id="input-isbn" 
                        name="isbn" 
                        value="<c:out value='${libro.isbn}'/>"
                        placeholder="Código internacional único"
                        maxlength="13"
                        required
                    />
                    </div>
                </div>
                <div class="form-group row">
                    <label for="input-nombre" class="col-sm-2 col-form-label">Nombre</label>
                    <div class="col-sm-10">
                    <input 
                        type="text" 
                        class="form-control" 
                        id="input-nombre" 
                        name="nombre" 
                        value="<c:out value='${libro.nombre}'/>"
                        placeholder="Nombre del libro"
                        maxlength="255"
                        required
                    />
                    </div>
                </div>
                <div class="form-group row">
                    <label for="input-editorial" class="col-sm-2 col-form-label">Editorial</label>
                    <div class="col-sm-10">
                    <input 
                        type="text" 
                        class="form-control" 
                        id="input-editorial" 
                        name="editorial" 
                        value="<c:out value='${libro.editorial}'/>"
                        placeholder="Nombre de la editorial"
                        maxlength="255"
                        required
                    />
                    </div>
                </div>
                <div class="form-group row">
                    <label for="input-autor" class="col-sm-2 col-form-label">Autor</label>
                    <div class="col-sm-10">
                    <input 
                        type="text" 
                        class="form-control" 
                        id="input-autor" 
                        name="autor" 
                        value="<c:out value='${libro.autor}'/>"
                        placeholder="Nombre del autor"
                        maxlength="255"
                        required
                    />
                    </div>
                </div>
                <div class="form-group row">
                    <label for="input-resumen" class="col-sm-2 col-form-label">Resumen</label>
                    <div class="col-sm-10">
                        <textarea 
                            class="form-control" 
                            id="input-resumen" 
                            name="resumen" 
                            placeholder="Resumen"
                            maxlength="500"
                        >
                            <c:out value='${libro.resumen}'/>
                        </textarea>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="input-fechaPublicacion" class="col-sm-2 col-form-label">Fecha publicación</label>
                    <div class="col-sm-10">
                    <input 
                        type="date" 
                        class="form-control" 
                        id="input-autor" 
                        name="fechaPublicacion" 
                        value="<c:out value='${libro.fechaPublicacion}'/>"
                        placeholder="Fecha publicación"
                    />
                    </div>
                </div>
                <div class="form-group row">
                    <label for="input-idioma" class="col-sm-2 col-form-label">Idioma</label>
                    <div class="col-sm-10">
                    <input 
                        type="text" 
                        class="form-control" 
                        id="input-idioma" 
                        name="idioma" 
                        value="<c:out value='${libro.idioma}'/>"
                        placeholder="idioma"
                        maxlength="15"
                        required
                    />
                    </div>
                </div>
                <div class="form-group row">
                    <label for="input-edicion" class="col-sm-2 col-form-label">N° Edición</label>
                    <div class="col-sm-10">
                    <input 
                        type="text" 
                        class="form-control" 
                        id="input-edicion" 
                        name="edicion" 
                        value="<c:out value='${libro.edicion}'/>"
                        placeholder="N° Edición"
                        maxlength="15"
                        required
                    />
                    </div>
                </div>
            </form>

            <form id="delete-form" action="/books/eliminar" method="post">
                <input type="hidden" name="id" id="delete-id" value="<c:out value='${libro.id}'/>"/>
            </form>

            <div class="btn-container">
                <button 
                    type="button"  
                    class="btn btn-success" 
                    onclick="showModal(
                        'userForm',
                        null,
                        'Nuevo libro',
                        '¿Desea grabar el registro?',
                        'Grabar'
                    )">
                        Grabar
                    </button>

                <button 
                    type="button" 
                    class="btn btn-danger 
                        <c:if test='${libro.id == null}'>
                            btn-disabled
                        </c:if>
                    " 
                    onclick="showModal(
                        'delete-form',
                        '${libro.id}',
                        'Eliminar libro',
                        '¿Desea eliminar el registro?',
                        'Eliminar'
                    );"

                    <c:if test="${libro.id == null}">
                        disabled
                    </c:if>
                >
                    Eliminar
                </button>
                <a class="btn btn-primary" href="/books">Cancelar</a>
            </div>

            
        </div>

    </body>
</body>