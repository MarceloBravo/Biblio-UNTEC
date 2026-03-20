package controllers.users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import entities.Usuario;

@WebServlet("/users/nuevo")
public class UserNuewServlet extends UserSaveServlet{

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
