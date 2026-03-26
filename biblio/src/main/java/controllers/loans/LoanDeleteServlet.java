package controllers.loans;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cdi.InjectionBeanCDI;
import interfaces.Services.LoanServiceInterface;

/**
 * Servlet que atiende la ruta {@code /loans/eliminar}.
 * 
 * Procesa la eliminación de un préstamo identificado por su {@code id}.
 * Tras la operación redirige al listado de préstamos ({@code /loans})
 * con un mensaje de éxito o error almacenado en sesión.
 *
 * @author Biblioteca UNTEC
 * @version 1.0
 */
@WebServlet("/loans/eliminar")
public class LoanDeleteServlet extends HttpServlet {

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
     * Atiende las peticiones POST para eliminar un préstamo.
     * 
     * Obtiene el parámetro {@code id} de la petición, invoca el servicio de
     * eliminación
     * y redirige al listado de préstamos con el resultado de la operación en
     * sesión.
     *
     * @param request  objeto {@link HttpServletRequest} con la petición del cliente
     * @param response objeto {@link HttpServletResponse} con la respuesta al
     *                 cliente
     * @throws ServletException si ocurre un error en el procesamiento del servlet
     * @throws IOException      si ocurre un error de entrada/salida
     */
    public void doPost(
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            boolean result = this.service.delete(id);
            request.getSession().setAttribute("message",
                    result ? "Prestamo eliminado con éxito" : "El prestamo no pudo ser eliminado");
            request.getSession().setAttribute("code", result ? 200 : 500);
            
            response.sendRedirect(request.getContextPath() + "/loans");
        } catch (Exception e) {
            System.out.println(e);
            
            request.getSession().setAttribute("message", "Ocurrió un error al eliminar el prestamo");
            request.getSession().setAttribute("code", 500);
            response.sendRedirect(request.getContextPath() + "/loans");
        }

    }

}
