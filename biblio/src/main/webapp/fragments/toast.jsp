<div class="toast-container position-absolute top-0 end-0 p-3">

    <c:if test="${not empty message}">
        <div id="feedback-toast" class="toast 
            <c:if test='${code == 200}'>
                toast-success
            </c:if>
            <c:if test='${code == 400}'>
                toast-danger
            </c:if>
            <c:if test='${code == 500}'>
                toast-danger
            </c:if>
            <c:if test='${code != 200 && code != 400}'>
                toast-info
            </c:if>
        " role="alert" aria-live="assertive" aria-atomic="true">
            <div class="toast-header">
                <img src="..." class="rounded me-2" alt="...">
                <strong class="me-auto">
                    <c:if test="${code == 200}">
                        Operación exitosa
                    </c:if>
                    <c:if test="${code == 400}">
                        Operación fallida
                    </c:if>
                    <c:if test="${code == 500}">
                        Error interno del servidor
                    </c:if>
                    <c:if test="${code != 200 && code != 400}">
                        Información                        
                    </c:if>
                </strong>
                <!-- <small class="text-muted">just now</small> -->
                <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
            </div>
            <div class="toast-body">
                ${message}
            </div>
        </div>
    </c:if>

    <div class="toast bg-primary" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="toast-header">
            <img src="..." class="rounded me-2" alt="...">
            <strong class="me-auto">Bootstrap</strong>
            <small class="text-muted">2 seconds ago</small>
            <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
        <div class="toast-body">
            Atención, los toasts se apilarán automáticamente
        </div>
    </div>

</div>