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
 * Servlet que gestiona el registro de nuevos usuarios desde el formulario de registro público.
 */
@WebServlet("/register")
public class UserRegisterServlet extends HttpServlet {private UserServiceInterface service;

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
     * Procesa las solicitudes POST para registrar un nuevo usuario.
     * Recoge los datos del formulario, los guarda a través del servicio y redirige
     * a la página de inicio con un mensaje de confirmación.
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
            String accion = request.getParameter("accion");
            Usuario user = this.getParams(request);
            this.service.save(user);
            
            if(accion == null){
                accion = "registrar";
            }

            request.setAttribute("data", user);
            request.setAttribute("message", user != null ? "Usuario registrado con éxito" : "El usuario no pudo ser creado");
            request.setAttribute("code", user != null ? 200 : 500);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
            request.setAttribute("message", "Ocurrió un error al registrar el usuario");
            request.setAttribute("code", 500);
            request.setAttribute("data", null);
            request.getRequestDispatcher("/WEB-INF/views/usuarios/usuariosList.jsp")
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
