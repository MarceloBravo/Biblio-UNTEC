package controllers.users;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cdi.InjectionBeanCDI;
import interfaces.users.UserServiceInterface;

@WebServlet("/users/eliminar")
public class UserDeleteServlet extends HttpServlet {
    private UserServiceInterface service;

    @Override
    public void init() throws ServletException {
        ServletConfig sc = this.getServletConfig();
        this.service = (new InjectionBeanCDI()).getInitBeanCDI(sc, UserServiceInterface.class);
    }
    
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
