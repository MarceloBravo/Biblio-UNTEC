package controllers.books;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import cdi.InjectionBeanCDI;
import entities.Libro;
import interfaces.Services.BookServiceInterface;

/**
 * Servlet para obtener los datos de un libro específico.
 * Se utiliza para mostrar los detalles de un libro en un formulario para su visualización o edición.
 * Extiende {@link BookSaveServlet} para reutilizar parte de su funcionalidad.
 */
@WebServlet("/books/ver/*")
public class BookGetServlet extends BookSaveServlet{
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
     * Procesa las solicitudes GET para obtener un libros por su ID.
     * El ID del libro se extrae de la ruta de la URL. Busca al libro a través del servicio
     * y lo adjunta como un atributo en la solicitud antes de reenviarla al formulario de libros.
     *
     * @param request  el objeto {@link javax.servlet.http.HttpServletRequest} que contiene la solicitud del cliente.
     * @param response el objeto {@link javax.servlet.http.HttpServletResponse} que contiene la respuesta del servlet.
     * @throws ServletException si ocurre un error específico del servlet.
     * @throws java.io.IOException si ocurre un error de entrada/salida.
     */
    public void doGet(
            javax.servlet.http.HttpServletRequest request,
            javax.servlet.http.HttpServletResponse response
    ) throws ServletException, java.io.IOException 
    {
        try{
            String pathInfo = request.getPathInfo();
            String id = pathInfo.substring(1);

            Libro user = id != null ? this.service.getById(Integer.parseInt(id)) : null;

            request.setAttribute("libro", user);
            request.setAttribute("code", user != null ? 200 : 404);
            request.getRequestDispatcher("/libros/librosForm.jsp").forward(request, response);
        }catch(Exception e){
            System.out.println(e);
            request.setAttribute("message", "Ocurrió un error al obtener el libro");
            request.setAttribute("code", 500);
            request.getRequestDispatcher("/libros/librosForm.jsp");
        }
    }
}
