package controllers.loans;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cdi.InjectionBeanCDI;
import interfaces.Services.LoanServiceInterface;

@WebServlet("/loans/eliminar")
public class LoanDeleteServlet extends HttpServlet {
    private LoanServiceInterface service;

    @Override
    public void init() throws ServletException {
        ServletConfig sc = this.getServletConfig();
        this.service = (new InjectionBeanCDI()).getInitBeanCDI(sc, LoanServiceInterface.class);
    }


    public void doPost(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException 
    {
        try{
            Integer id = Integer.parseInt(request.getParameter("id"));
            boolean result = this.service.delete(id);
            request.getSession().setAttribute("message", result ? "Prestamo eliminado con éxito" : "El prestamo no pudo ser eliminado");
            request.getSession().setAttribute("code", result ? 200 : 500);
            response.sendRedirect(request.getContextPath() + "/loans");
        }catch(Exception e){
            System.out.println(e);
            request.getSession().setAttribute("message", "Ocurrió un error al eliminar el prestamo");
            request.getSession().setAttribute("code", 500);
            response.sendRedirect(request.getContextPath() + "/loans");
        }

    }

}
