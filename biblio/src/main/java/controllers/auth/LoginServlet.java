package controllers.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.inject.Inject;

import entities.Usuario;
import interfaces.auth.LoginServiceInterface;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Inject
    private LoginServiceInterface service;
    
    @Override
    protected void doPost(
        HttpServletRequest request, 
        HttpServletResponse response
    ) throws ServletException, IOException
    {
        try{
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            HttpSession session = request.getSession(true);
            Usuario user = this.service.login(email, password, session);

            request.setAttribute("message", user != null ? "Autenticación exitosa" : "Usuario o contraseña incorrectos");
            request.setAttribute("code", user != null ? 200 : 403);            
            request.getRequestDispatcher("home.jsp")
            .forward(request, response);
        }catch(Exception e){
            System.out.println(e);
            request.setAttribute("message", "Ocurrió un error al autenticar el usuario");
            request.setAttribute("code", 500);
            request.getRequestDispatcher("login.jsp")
            .forward(request, response);
        }
    } 
    
}
