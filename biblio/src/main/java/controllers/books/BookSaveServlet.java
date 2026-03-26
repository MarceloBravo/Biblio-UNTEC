package controllers.books;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cdi.InjectionBeanCDI;
import entities.Libro;
import interfaces.Services.BookServiceInterface;

/**
 * Servlet responsable de guardar (crear o actualizar) libros desde el mantenedor de libros.
 * Este servlet es invocado por el formulario de libros para persistir los cambios.
 */
@WebServlet("/books/grabar")
public class BookSaveServlet extends HttpServlet {
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
     * Procesa las solicitudes POST para crear o actualizar un libro.
     * Determina si es una operación de creación o actualización basándose en la presencia del ID de libro.
     * Guarda los datos a través del servicio y redirige a la lista de libros con un mensaje de estado.
     *
     * @param request  el objeto {@link HttpServletRequest} que contiene la solicitud del cliente.
     * @param response el objeto {@link HttpServletResponse} que contiene la respuesta del servlet.
     * @throws ServletException si ocurre un error específico del servlet.
     * @throws IOException si ocurre un error de entrada/salida.
     */
    @Override
    public void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        try{
            Libro book = this.getParams(request);
            String accion = (book.getId() != null ? "actualizado" : "creado");
            
            Libro result = this.service.save(book);
            
            
            request.getSession().setAttribute("message", result != null ? "libro " + accion + " con éxito" : "El libro no pudo ser " + accion);
            request.getSession().setAttribute("code", result != null ? 200 : 500);
            request.getSession().setAttribute("data", result);
            response.sendRedirect(request.getContextPath() + "/books");
        } catch (Exception e) {
            System.out.println(e);
            
            request.setAttribute("message", "Ocurrió un error al registrar el libro");
            request.setAttribute("code", 500);
            request.setAttribute("data", null);
            request.getRequestDispatcher("/WEB-INF/views/libros/librosForm.jsp")
                    .forward(request, response);
        }
    }

    /**
     * Extrae los parámetros de la solicitud HTTP y los mapea a un objeto {@link libro}.
     *
     * @param request el objeto {@link HttpServletRequest} del que se extraerán los parámetros.
     * @return un objeto {@link libro} poblado con los datos de la solicitud.
     */
    private Libro getParams(HttpServletRequest request) {
        Libro book = new Libro();
        String id = request.getParameter("id");
        if(id != null && !id.isEmpty()){
            book.setId(Integer.parseInt(id));
        }
        book.setIsbn(request.getParameter("isbn"));
        book.setNombre(request.getParameter("nombre"));
        book.setAutor(request.getParameter("autor"));
        book.setEditorial(request.getParameter("editorial"));
        book.setResumen(request.getParameter("resumen"));

        String fechaStr = request.getParameter("fechaPublicacion");

        if (fechaStr != null && !fechaStr.isEmpty()) {
            try {
                book.setFechaPublicacion(java.sql.Date.valueOf(fechaStr));
            } catch (IllegalArgumentException e) {
                book.setFechaPublicacion(null); 
            }
        } else {
            // Maneja el caso de fecha nula o vacía
            book.setFechaPublicacion(null); 
        }

        book.setIdioma(request.getParameter("idioma"));
        book.setEdicion(Integer.parseInt(request.getParameter("edicion")));        
        
        return book;
    }

}
