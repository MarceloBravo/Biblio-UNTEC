package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet que gestiona la autenticación de usuarios.
 * Procesa las solicitudes de cierre de sesión, elimina los datos de lasesión
 * del usuario activo.
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    /**
     * Procesa las solicitudes POST para el cierre de sesión de usuarios.
     * Elimina los datos de la sesión del usuario activo y redirige al usuario a la
     * página de inicio.
     *
     * @param request  el objeto {@link HttpServletRequest} que contiene la
     *                 solicitud del cliente.
     * @param response el objeto {@link HttpServletResponse} que contiene la
     *                 respuesta del servlet.
     * @throws ServletException si ocurre un error específico del servlet.
     * @throws IOException      si ocurre un error de entrada/salida.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(true);
            session.invalidate();
            response.sendRedirect(request.getContextPath() + "/index");
        } catch (Exception e) {
            System.out.println(e);
            request.setAttribute("message", "Ocurrió un error al cerrar la sesión");
            request.setAttribute("code", 500);
            request.getRequestDispatcher("index.jsp")
                    .forward(request, response);
        }
    }
}
