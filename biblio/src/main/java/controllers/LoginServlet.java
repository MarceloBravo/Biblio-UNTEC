package controllers;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Usuario;
import interfaces.auth.LoginServiceInterface;
import cdi.InjectionBeanCDI;

/**
 * Servlet que gestiona la autenticación de usuarios.
 * Procesa las solicitudes de inicio de sesión, valida las credenciales y gestiona la sesión del usuario.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private LoginServiceInterface service;

    /**
     * Inicializa el servlet y sus dependencias.
     * Este método es llamado por el contenedor de servlets una sola vez durante el ciclo de vida del servlet.
     * Inyecta la dependencia del servicio de login.
     * 
     * @throws ServletException si ocurre un error durante la inicialización.
     */
    @Override
    public void init() throws ServletException {
        ServletConfig sc = this.getServletConfig();
        this.service = (new InjectionBeanCDI()).getInitBeanCDI(sc, LoginServiceInterface.class);
    }

    /**
     * Procesa las solicitudes POST para la autenticación de usuarios.
     * Extrae el email y la contraseña de la solicitud, los valida utilizando el servicio de login
     * y redirige al usuario a la página de inicio si la autenticación es exitosa, o de vuelta al login
     * en caso de fallo.
     *
     * @param request  el objeto {@link HttpServletRequest} que contiene la solicitud del cliente.
     * @param response el objeto {@link HttpServletResponse} que contiene la respuesta del servlet.
     * @throws ServletException si ocurre un error específico del servlet.
     * @throws IOException si ocurre un error de entrada/salida.
     */
    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            HttpSession session = request.getSession(true);
            Usuario user = this.service.login(email, password, session);

            request.setAttribute("message",
                    user != null ? "Autenticación exitosa" : "Usuario o contraseña incorrectos");
            request.setAttribute("code", user != null ? 200 : 403);
            request.getRequestDispatcher(user != null ? "home.jsp" : "index.jsp")
                    .forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
            request.setAttribute("message", "Ocurrió un error al autenticar el usuario");
            request.setAttribute("code", 500);
            request.getRequestDispatcher("login.jsp")
                    .forward(request, response);
        }
    }

}
