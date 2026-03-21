<c:if test="${not empty message}">
    <div 
        id="feedback-alert" 
        class="alert 
            <c:if test='${code == 200}'>
                toast-success
            </c:if>
            <c:if test='${code == 400}'>
                toast-danger
            </c:if>
            <c:if test='${code == 500}'>
                toast-danger
            </c:if>
            <c:if test='${code != 200 && code != 400 && 500}'>
                toast-info
            </c:if>                        
            alert-dismissible fade show"                         
        role="alert">
        ${message}
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
</c:if>