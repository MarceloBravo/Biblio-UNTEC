package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet que atiende la ruta {@code /registerForm}.
 *
 * Redirige al formulario de registro de usuario ubicado en
 * {@code /WEB-INF/views/registerUser.jsp}.
 *
 * @author Biblioteca UNTEC
 * @version 1.0
 */
@WebServlet("/registerForm")
public class RegisterForm extends HttpServlet {

    /**
     * Atiende las peticiones GET y reenvía al formulario de registro de usuario.
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
        
        request.getRequestDispatcher("/WEB-INF/views/registerUser.jsp").forward(request, response);
    }

}
