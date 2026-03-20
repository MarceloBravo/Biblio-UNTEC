<nav aria-label="...">
  <ul class="pagination">
    <li class="page-item">

    <c:if test="${pagination.paginaActual > 1}">
      <li class="page-item">
        <a class="page-link" href="users?desde=0&filas=${pagination.filas}" title="Ir a la primera página">
          <span aria-hidden="true">&laquo;&laquo;</span>
        </a>
      </li>
    </c:if>

    <c:forEach var="i" begin="${pagination.inicio}" end="${pagination.fin}">
      <li class="page-item ${i == pagination.paginaActual ? 'active' : ''}">
        <%-- El nuevo 'desde' para el SQL será: (página - 1) * filas --%>
        <a class="page-link" href="users?desde=${(i - 1) * pagination.filas}&filas=${pagination.filas}">
          ${i}
        </a>
      </li>
    </c:forEach>

    <c:if test="${pagination.paginaActual < pagination.totPag}">
      <li class="page-item">
        <a class="page-link" href="${pagination.url}?desde=${(pagination.totPag - 1) * pagination.filas}&filas=${pagination.filas}" title="Ir a la última página">
          &raquo;&raquo;
        </a>
      </li>
    </c:if>
  </ul>
</nav>