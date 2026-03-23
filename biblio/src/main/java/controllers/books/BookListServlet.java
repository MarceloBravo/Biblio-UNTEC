package controllers.books;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cdi.InjectionBeanCDI;
import dto.BookListDTO;
import interfaces.book.BookServiceInterface;

/**
 * Servlet que gestiona la visualización de la lista de libros.
 * Permite listar, buscar y paginar los libros del sistema.
 */
@WebServlet("/books")
public class BookListServlet extends HttpServlet {
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
     * Procesa las solicitudes GET para mostrar la lista de libros.
     * Gestiona la paginación y la búsqueda de libros. Obtiene los datos del servicio
     * y los reenvía a la vista JSP para su visualización.
     *
     * @param request  el objeto {@link HttpServletRequest} que contiene la solicitud del cliente.
     * @param response el objeto {@link HttpServletResponse} que contiene la respuesta del servlet.
     * @throws ServletException si ocurre un error específico del servlet.
     * @throws IOException si ocurre un error de entrada/salida.
     */
    @Override
    public void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException
    { 
        String search = request.getParameter("search");
        String strDesde = request.getParameter("desde");
        String strFilas = request.getParameter("filas");
        Integer desde = Integer.parseInt((strDesde != null ? strDesde : "0"));
        Integer filas = Integer.parseInt(strFilas != null ? strFilas : "10");
        
        BookListDTO result = null;
        if(search != null && !search.isEmpty()){
            result = this.service.list(desde,filas, search);
        }else{
            result = this.service.list(desde,filas);
        }

        result.getPagination().setUrl("books");
        result.getPagination().CalcularPaginas(desde, filas);

        this.borrarMensajeDeSession(request);

        request.setAttribute("data", result.getData());
        request.setAttribute("pagination", result.getPagination());
        request.setAttribute("code", 200);
        request.setAttribute("search", search);
        request.getRequestDispatcher("/libros/librosList.jsp").forward(request, response);
    }

    /**
     * Redirige las solicitudes POST al método doGet.
     * Esto permite que el servlet responda de la misma manera a ambos tipos de solicitudes.
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
    ) throws ServletException, IOException 
    {
        doGet(request, response);
    }

    /**
     * Transfiere los mensajes de notificación desde la sesión a los atributos de la solicitud.
     * Después de transferirlos, los elimina de la sesión para evitar que se muestren en
     * solicitudes posteriores.
     *
     * @param request el objeto {@link HttpServletRequest} donde se transferirán los mensajes.
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
