package controllers.users;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cdi.InjectionBeanCDI;
import entities.Usuario;
import interfaces.Services.UserServiceInterface;

/**
 * Servlet responsable de guardar (crear o actualizar) usuarios desde el mantenedor de usuarios.
 * Este servlet es invocado por el formulario de usuarios para persistir los cambios.
 */
@WebServlet("/users/grabar")
public class UserSaveServlet extends HttpServlet {
    private UserServiceInterface service;

    /**
     * Inicializa el servlet y sus dependencias.
     * Inyecta la dependencia del servicio de usuarios.
     *
     * @throws ServletException si ocurre un error durante la inicialización.
     */
    @Override
    public void init() throws ServletException {
        ServletConfig sc = this.getServletConfig();
        this.service = (new InjectionBeanCDI()).getInitBeanCDI(sc, UserServiceInterface.class);
    }

    /**
     * Procesa las solicitudes POST para crear o actualizar un usuario.
     * Determina si es una operación de creación o actualización basándose en la presencia del ID de usuario.
     * Guarda los datos a través del servicio y redirige a la lista de usuarios con un mensaje de estado.
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
            Usuario user = this.getParams(request);
            String accion = (user.getId() != null ? "actualizado" : "creado");

            Usuario result = this.service.save(user);
            
            request.getSession().setAttribute("message", result != null ? "Usuario " + accion + " con éxito" : "El usuario no pudo ser " + accion);
            request.getSession().setAttribute("code", result != null ? 200 : 500);
            request.getSession().setAttribute("data", result);
            response.sendRedirect(request.getContextPath() + "/users");
        } catch (Exception e) {
            System.out.println(e);
            request.setAttribute("message", "Ocurrió un error al registrar el usuario");
            request.setAttribute("code", 500);
            request.setAttribute("data", null);
            request.getRequestDispatcher("/users")
                    .forward(request, response);
        }
    }

    /**
     * Extrae los parámetros de la solicitud HTTP y los mapea a un objeto {@link Usuario}.
     *
     * @param request el objeto {@link HttpServletRequest} del que se extraerán los parámetros.
     * @return un objeto {@link Usuario} poblado con los datos de la solicitud.
     */
    private Usuario getParams(HttpServletRequest request) {
        Usuario user = new Usuario();
        String id = request.getParameter("id");
        if(id != null && !id.isEmpty()){
            user.setId(Integer.parseInt(id));
        }
        user.setNombre(request.getParameter("nombre"));
        user.setApellidos(request.getParameter("apellidos"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        return user;
    }

}
