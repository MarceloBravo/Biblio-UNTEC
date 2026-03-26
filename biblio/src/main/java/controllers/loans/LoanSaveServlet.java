package controllers.loans;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cdi.InjectionBeanCDI;
import entities.Prestamo;
import interfaces.Services.LoanServiceInterface;

/**
 * Servlet que atiende la ruta {@code /loans/grabar}.
 *
 * Procesa el guardado (creación o actualización) de un préstamo a partir
 * de los datos enviados por el formulario. Determina si es una operación
 * de creación o actualización según la presencia del parámetro {@code id}.
 * Tras la operación redirige al listado de préstamos ({@code /loans}).
 *
 * @author Biblioteca UNTEC
 * @version 1.0
 */
@WebServlet("/loans/grabar")
public class LoanSaveServlet extends HttpServlet {

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
     * Atiende las peticiones POST para crear o actualizar un préstamo.
     * 
     * Lee los parámetros {@code id}, {@code userId}, {@code bookId},
     * {@code fechaPrestamo}, {@code fechaDevolucion} y {@code fechaRetorno}.
     * Si {@code id} está presente y no es vacío, actualiza el préstamo existente;
     * en caso contrario, crea uno nuevo.
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
        try {
            String id = request.getParameter("id");
            Integer userId = Integer.parseInt(request.getParameter("userId"));
            Integer bookId = Integer.parseInt(request.getParameter("bookId"));
            String strFechaPrestamo = request.getParameter("fechaPrestamo");
            String strFechaDevolucion = request.getParameter("fechaDevolucion");
            String strFechaRetorno = request.getParameter("fechaRetorno");
            Date fechaPrestamo = (strFechaPrestamo != null && !strFechaPrestamo.isEmpty())
                    ? Date.valueOf(strFechaPrestamo)
                    : null;
            Date fechaDevolucion = (strFechaDevolucion != null && !strFechaDevolucion.isEmpty())
                    ? Date.valueOf(strFechaDevolucion)
                    : null;
            Date fechaRetorno = (strFechaRetorno != null && !strFechaRetorno.isEmpty()) ? Date.valueOf(strFechaRetorno)
                    : null;
            String accion = ((id != null && !id.isEmpty()) ? "actualizado" : "registrado");

            Prestamo result = null;
            if (id != null && !id.isEmpty()) {
                result = this.service.update(Integer.parseInt(id), userId, bookId, fechaPrestamo, fechaDevolucion,
                        fechaRetorno);
            } else {
                result = this.service.create(userId, bookId, fechaPrestamo, fechaDevolucion);
            }

            request.getSession().setAttribute("message",
                    result != null ? "prestamo " + accion + " con éxito" : "El libro no pudo ser " + accion);
            request.getSession().setAttribute("code", result != null ? 200 : 500);
            request.getSession().setAttribute("data", result);
            
            response.sendRedirect(request.getContextPath() + "/loans");
        } catch (Exception e) {
            System.out.println(e);
            
            request.setAttribute("message", "Ocurrió un error al registrar el prestamo");
            request.setAttribute("code", 500);
            request.setAttribute("data", null);
            request.getRequestDispatcher("/WEB-INF/views/prestamos/prestamosForm.jsp")
                    .forward(request, response);
        }
    }

}
