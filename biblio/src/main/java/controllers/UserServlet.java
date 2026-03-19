package controllers;

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

@WebServlet("/users")
public class UserServlet extends HttpServlet {
    private UserServiceInterface service;

    @Override
    public void init() throws ServletException {
        ServletConfig sc = this.getServletConfig();
        this.service = (new InjectionBeanCDI()).getInitBeanCDI(sc, UserServiceInterface.class);
    }

    @Override
    public void doPost(
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        try {
            Usuario user = this.getParams(request);
            this.service.save(user);
            System.out.println(user);

            request.setAttribute("usuario", user);
            request.setAttribute("message",
                    user != null ? "Usuario registrado con éxito" : "El usuario no pudo ser creado");
            request.setAttribute("code", user != null ? 200 : 500);
            request.getRequestDispatcher("index.jsp")
                    .forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
            request.setAttribute("message", "Ocurrió un error al registrar el usuario");
            request.setAttribute("code", 500);
            request.getRequestDispatcher("index.jsp")
                    .forward(request, response);
        }
    }

    private Usuario getParams(HttpServletRequest request) {
        Usuario user = new Usuario();
        user.setNombre(request.getParameter("nombre"));
        user.setAppelidos(request.getParameter("apellidos"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        return user;
    }

}
