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
import interfaces.users.UserServiceInterface;

@WebServlet("/users/grabar")
public class UserSaveServlet extends HttpServlet {
    private UserServiceInterface service;

    @Override
    public void init() throws ServletException {
        ServletConfig sc = this.getServletConfig();
        this.service = (new InjectionBeanCDI()).getInitBeanCDI(sc, UserServiceInterface.class);
    }

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
            request.getRequestDispatcher("index.jsp")
                    .forward(request, response);
        }
    }


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
