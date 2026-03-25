<c:if test="${not empty message}">
    <div 
        id="feedback-alert" 
        class="alert 
            <c:if test='${code == 200}'>
                alert-success
            </c:if>
            <c:if test='${code == 400}'>
                alert-danger
            </c:if>
            <c:if test='${code == 500}'>
                alert-danger
            </c:if>
            <c:if test='${code != 200 && code != 400}'>
                alert-info
            </c:if>                        
            alert-dismissible fade show"                         
        role="alert">
        ${message}
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
</c:if>

<div 
    id="feedback-alert-js" 
    class="alert alert-danger"                         
    role="alert"
>
    ${message}
    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
</div>