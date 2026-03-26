package controllers.loans;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import cdi.InjectionBeanCDI;
import entities.Prestamo;
import interfaces.Services.LoanServiceInterface;

/**
 * Servlet que atiende la ruta {@code /loans/ver/*}.
 *
 * Recupera un préstamo por su identificador, extraído del segmento
 * de la URL ({@code pathInfo}), y lo expone al formulario de préstamo
 * ubicado en {@code /WEB-INF/views/prestamos/prestamosForm.jsp}.
 *
 * @author Biblioteca UNTEC
 * @version 1.0
 */
@WebServlet("/loans/ver/*")
public class LoanGetServlet extends HttpServlet {

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
     * Atiende las peticiones GET para mostrar los datos de un préstamo.
     * 
     * El identificador del préstamo se obtiene del segmento {@code pathInfo} de la
     * URL
     * (ej.: {@code /loans/ver/5}). El préstamo recuperado se añade como atributo
     * {@code prestamo} en el request y se reenvía al formulario de préstamo.
     *
     * @param request  objeto {@link javax.servlet.http.HttpServletRequest} con la
     *                 petición del cliente
     * @param response objeto {@link javax.servlet.http.HttpServletResponse} con la
     *                 respuesta al cliente
     * @throws ServletException    si ocurre un error en el procesamiento del
     *                             servlet
     * @throws java.io.IOException si ocurre un error de entrada/salida
     */
    public void doGet(
            javax.servlet.http.HttpServletRequest request,
            javax.servlet.http.HttpServletResponse response) throws ServletException, java.io.IOException {
        try {
            String pathInfo = request.getPathInfo();
            String id = pathInfo.substring(1);

            Prestamo loan = id != null ? this.service.getById(Integer.parseInt(id)) : null;

            
            request.setAttribute("prestamo", loan);
            request.setAttribute("code", loan != null ? 200 : 404);
            request.getRequestDispatcher("/WEB-INF/views/prestamos/prestamosForm.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
            
            request.setAttribute("message", "Ocurrió un error al obtener los datos del prestamo");
            request.setAttribute("code", 500);
            request.getRequestDispatcher("/WEB-INF/views/prestamos/prestamosForm.jsp");
        }
    }
}
