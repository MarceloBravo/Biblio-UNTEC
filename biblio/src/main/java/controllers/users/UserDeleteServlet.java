package controllers.users;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cdi.InjectionBeanCDI;
import interfaces.Services.UserServiceInterface;

/**
 * Servlet encargado de gestionar la eliminación de usuarios.
 * Procesa las solicitudes para eliminar un usuario basado en su ID.
 */
@WebServlet("/users/eliminar")
public class UserDeleteServlet extends HttpServlet {
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
     * Procesa las solicitudes POST para eliminar un usuario.
     * Espera un parámetro 'id' en la solicitud, lo utiliza para llamar al servicio de eliminación
     * y redirige al listado de usuarios con un mensaje de éxito o error.
     *
     * @param request  el objeto {@link HttpServletRequest} que contiene la solicitud del cliente.
     * @param response el objeto {@link HttpServletResponse} que contiene la respuesta del servlet.
     * @throws ServletException si ocurre un error específico del servlet.
     * @throws IOException si ocurre un error de entrada/salida.
     */
    public void doPost(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException 
    {
        try{
            Integer id = Integer.parseInt(request.getParameter("id"));
            boolean result = this.service.delete(id);
            
            request.getSession().setAttribute("message", result ? "Usuario eliminado con éxito" : "El usuario no pudo ser eliminado");
            request.getSession().setAttribute("code", result ? 200 : 500);
            response.sendRedirect(request.getContextPath() + "/users");
        }catch(Exception e){
            System.out.println(e);
            
            request.getSession().setAttribute("message", "Ocurrió un error al eliminar el usuario");
            request.getSession().setAttribute("code", 500);
            response.sendRedirect(request.getContextPath() + "/users");
        }

    }

}
