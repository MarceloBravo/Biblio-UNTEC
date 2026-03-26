package controllers.books;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cdi.InjectionBeanCDI;
import interfaces.Services.BookServiceInterface;

/**
 * Servlet encargado de gestionar la eliminación de libros.
 * Procesa las solicitudes para eliminar un libros basado en su ID.
 */
@WebServlet("/books/eliminar")
public class BookDeleteServlet extends HttpServlet {
    private BookServiceInterface service;

    /**
     * Inicializa el servlet y sus dependencias.
     * Inyecta la dependencia del servicio de libros.
     *
     * @throws ServletException si ocurre un error durante la inicialización.
     */
    @Override
    public void init() throws ServletException {
        ServletConfig sc = this.getServletConfig();
        this.service = (new InjectionBeanCDI()).getInitBeanCDI(sc, BookServiceInterface.class);
    }
    
    /**
     * Procesa las solicitudes POST para eliminar un libro.
     * Espera un parámetro 'id' en la solicitud, lo utiliza para llamar al servicio de eliminación
     * y redirige al listado de libros con un mensaje de éxito o error.
     *
     * @param request  el objeto {@link HttpServletRequest} que contiene la solicitud del cliente.
     * @param response el objeto {@link HttpServletResponse} que contiene la respuesta del servlet.
     * @throws ServletException si ocurre un error específico del servlet.
     * @throws IOException si ocurre un error de entrada/salida.
     */
    public void doPost(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException 
    {
        try{
            Integer id = Integer.parseInt(request.getParameter("id"));
            boolean result = this.service.delete(id);
            request.getSession().setAttribute("message", result ? "Libro eliminado con éxito" : "El libro no pudo ser eliminado");
            request.getSession().setAttribute("code", result ? 200 : 500);
            
            response.sendRedirect(request.getContextPath() + "/books");
        }catch(Exception e){
            System.out.println(e);
            request.getSession().setAttribute("message", "Ocurrió un error al eliminar el libro");
            request.getSession().setAttribute("code", 500);
            
            response.sendRedirect(request.getContextPath() + "/books");
        }

    }

}
