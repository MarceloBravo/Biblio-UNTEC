package controllers.books;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import entities.Libro;

/**
 * Servlet para preparar el formulario de creación de un nuevo libro.
 * Nota: El nombre "Nuew" parece ser un error tipográfico y probablemente debería ser "New".
 * Extiende {@link BookSaveServlet}.
 */
@WebServlet("/books/nuevo")
public class BookNuewServlet extends BookSaveServlet{

    /**
     * Procesa las solicitudes GET para mostrar el formulario de un nuevo libro.
     * Crea una instancia vacía de {@link Libro} y la reenvía al formulario
     * para que el libro pueda introducir los datos.
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
        Libro book = new Libro();
        request.setAttribute("libro", book);
        request.getRequestDispatcher(request.getContextPath() + "/WEB-INF/views/libros/librosForm.jsp").forward(request, response);
    }

}
