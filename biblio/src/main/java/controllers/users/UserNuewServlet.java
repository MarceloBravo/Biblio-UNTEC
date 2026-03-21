package controllers.users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import entities.Usuario;

/**
 * Servlet para preparar el formulario de creación de un nuevo usuario.
 * Nota: El nombre "Nuew" parece ser un error tipográfico y probablemente debería ser "New".
 * Extiende {@link UserSaveServlet}.
 */
@WebServlet("/users/nuevo")
public class UserNuewServlet extends UserSaveServlet{

    /**
     * Procesa las solicitudes GET para mostrar el formulario de un nuevo usuario.
     * Crea una instancia vacía de {@link Usuario} y la reenvía al formulario
     * para que el usuario pueda introducir los datos.
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
        Usuario user = new Usuario();
        request.setAttribute("usuario", user);
        request.getRequestDispatcher(request.getContextPath() + "/usuarios/usuariosForm.jsp").forward(request, response);
    }

}
