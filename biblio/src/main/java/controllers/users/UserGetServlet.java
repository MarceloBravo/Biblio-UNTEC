package controllers.users;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import cdi.InjectionBeanCDI;
import entities.Usuario;
import interfaces.Services.UserServiceInterface;

/**
 * Servlet para obtener los datos de un usuario específico.
 * Se utiliza para mostrar los detalles de un usuario en un formulario para su visualización o edición.
 * Extiende {@link UserSaveServlet} para reutilizar parte de su funcionalidad.
 */
@WebServlet("/users/ver/*")
public class UserGetServlet extends UserSaveServlet{
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
     * Procesa las solicitudes GET para obtener un usuario por su ID.
     * El ID del usuario se extrae de la ruta de la URL. Busca al usuario a través del servicio
     * y lo adjunta como un atributo en la solicitud antes de reenviarla al formulario de usuarios.
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

            Usuario user = id != null ? this.service.getById(Integer.parseInt(id)) : null;

            request.setAttribute("usuario", user);
            request.setAttribute("code", user != null ? 200 : 404);
            request.getRequestDispatcher("/usuarios/usuariosForm.jsp").forward(request, response);
        }catch(Exception e){
            System.out.println(e);
            request.setAttribute("message", "Ocurrió un error al obtener el usuario");
            request.setAttribute("code", 500);
            request.getRequestDispatcher("/usuarios/usuariosList.jsp");
        }
    }
}
