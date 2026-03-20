package controllers.users;

import java.io.IOException;
import java.util.List;

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
public class UserListServlet extends HttpServlet {
    private UserServiceInterface service;

    @Override
    public void init() throws ServletException {
        ServletConfig sc = this.getServletConfig();
        this.service = (new InjectionBeanCDI()).getInitBeanCDI(sc, UserServiceInterface.class);
    }

    @Override
    public void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException
    { 
        String search = request.getParameter("search");
        String strDesde = request.getParameter("desde");
        String strFilas = request.getParameter("filas");
        Integer desde = Integer.parseInt((strDesde != null ? strDesde : "0"));
        Integer filas = Integer.parseInt(strFilas != null ? strFilas : "10");
        
        List<Usuario> result = null;
        if(search != null && !search.isEmpty()){
            result = this.service.list(desde,filas, search);
        }else{
            result = this.service.list(desde,filas);
        }

        request.setAttribute("data", result);
        request.setAttribute("code", 200);
        request.setAttribute("search", search);
        request.getRequestDispatcher("/usuarios/usuariosList.jsp").forward(request, response);
    }

    @Override
    public void doPost(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException 
    {
        doGet(request, response);
    }
}
