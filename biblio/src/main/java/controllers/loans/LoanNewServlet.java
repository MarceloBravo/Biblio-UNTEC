package controllers.loans;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import controllers.books.BookSaveServlet;
import entities.Prestamo;

/**
 * Servlet que atiende la ruta {@code /loans/nuevo}.
 *
 * Prepara un objeto {@link Prestamo} vacío y lo expone al formulario
 * de creación de préstamos ubicado en
 * {@code /WEB-INF/views/prestamos/prestamosForm.jsp}.
 *
 * @author Biblioteca UNTEC
 * @version 1.0
 */
@WebServlet("/loans/nuevo")
public class LoanNewServlet extends BookSaveServlet {

    /**
     * Atiende las peticiones GET inicializando un préstamo vacío
     * y reenviando al formulario de nuevo préstamo.
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
        Prestamo loan = new Prestamo();
        request.setAttribute("prestamo", loan);
        request.getRequestDispatcher(request.getContextPath() + "/WEB-INF/views/prestamos/prestamosForm.jsp")
                .forward(request, response);
    }
}
