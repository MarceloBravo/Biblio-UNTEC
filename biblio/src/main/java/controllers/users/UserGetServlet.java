package controllers.users;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import cdi.InjectionBeanCDI;
import entities.Usuario;
import interfaces.users.UserServiceInterface;

@WebServlet("/users/ver/*")
public class UserGetServlet extends UserSaveServlet{
    private UserServiceInterface service;

    @Override
    public void init() throws ServletException {
        ServletConfig sc = this.getServletConfig();
        this.service = (new InjectionBeanCDI()).getInitBeanCDI(sc, UserServiceInterface.class);
    }


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
            request.getRequestDispatcher("index.jsp");
        }
    }
}
