package controllers.loans;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cdi.InjectionBeanCDI;
import dto.LoanListDTO;
import interfaces.Services.LoanServiceInterface;

/**
 * Servlet que atiende la ruta {@code /loans}.
 * 
 * Muestra el listado paginado de préstamos, con soporte opcional de búsqueda
 * por texto. Redirige al formulario de préstamos en caso de error.
 *
 * @author Biblioteca UNTEC
 * @version 1.0
 */
@WebServlet("/loans")
public class LoansListServlet extends HttpServlet {

    /** Servicio de préstamos inyectado mediante CDI. */
    private LoanServiceInterface service;

    /**
     * Inicializa el servlet obteniendo la implementación de
     * {@link LoanServiceInterface}
     * a través del contenedor CDI.
     *
     * @throws ServletException si ocurre un error durante la inicialización
     */
    @Override
    public void init() throws ServletException {
        ServletConfig sc = this.getServletConfig();
        this.service = (new InjectionBeanCDI()).getInitBeanCDI(sc, LoanServiceInterface.class);
    }

    /**
     * Atiende las peticiones GET para listar los préstamos de forma paginada.
     * 
     * Acepta los parámetros opcionales:
     * 
     * {@code search} – texto de búsqueda para filtrar resultados.
     * {@code desde} – índice de inicio de la paginación (por defecto
     * {@code 0})
     * {@code filas} – cantidad de registros por página (por defecto
     * {@code 10})
     * Los resultados se exponen en los atributos {@code data}, {@code pagination},
     * {@code code} y {@code search} del request.
     *
     * @param request  objeto {@link HttpServletRequest} con la petición del cliente
     * @param response objeto {@link HttpServletResponse} con la respuesta al
     *                 cliente
     * @throws ServletException si ocurre un error en el procesamiento del servlet
     * @throws IOException      si ocurre un error de entrada/salida
     */
    @Override
    public void doGet(
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        try {
            String search = request.getParameter("search");
            String strDesde = request.getParameter("desde");
            String strFilas = request.getParameter("filas");
            Integer desde = Integer.parseInt((strDesde != null ? strDesde : "0"));
            Integer filas = Integer.parseInt(strFilas != null ? strFilas : "10");

            LoanListDTO result = null;
            if (search != null && !search.isEmpty()) {
                result = this.service.list(desde, filas, search);
            } else {
                result = this.service.list(desde, filas);
            }
            result.getPagination().CalcularPaginas(desde, filas);

            this.borrarMensajeDeSession(request);

            request.setAttribute("data", result.getData());
            request.setAttribute("pagination", result.getPagination());
            request.setAttribute("code", 200);
            request.setAttribute("search", search);

        } catch (Exception e) {
            System.out.println(e);
            LoanListDTO result = new LoanListDTO();
            request.setAttribute("message", "Ocurrió un error al obtener la lista de libros");
            request.setAttribute("code", 500);
            request.setAttribute("data", result.getData());
            request.setAttribute("pagination", result.getPagination());

        }
        request.getRequestDispatcher("/WEB-INF/views/prestamos/prestamosList.jsp").forward(request, response);
    }

    /**
     * Atiende las peticiones POST delegando el procesamiento en {@link #doGet}.
     *
     * @param request  objeto {@link HttpServletRequest} con la petición del cliente
     * @param response objeto {@link HttpServletResponse} con la respuesta al
     *                 cliente
     * @throws ServletException si ocurre un error en el procesamiento del servlet
     * @throws IOException      si ocurre un error de entrada/salida
     */
    @Override
    public void doPost(
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * Transfiere los atributos {@code message} y {@code code} desde la sesión
     * al request y los elimina de la sesión.
     * 
     * Se utiliza para mostrar mensajes de resultado de operaciones anteriores
     * (creación, actualización o eliminación de préstamos) en la vista de listado.
     *
     * @param request objeto {@link HttpServletRequest} del que se lee y modifica la
     *                sesión
     */
    public void borrarMensajeDeSession(HttpServletRequest request) {
        if (request.getSession().getAttribute("message") != null) {
            request.setAttribute("message", request.getSession().getAttribute("message"));
            request.getSession().removeAttribute("message");
        }
        if (request.getSession().getAttribute("code") != null) {
            request.setAttribute("code", request.getSession().getAttribute("code"));
            request.getSession().removeAttribute("code");
        }
    }
}
