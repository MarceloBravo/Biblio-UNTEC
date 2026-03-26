package controllers.loans;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import cdi.InjectionBeanCDI;
import entities.Prestamo;
import interfaces.Services.LoanServiceInterface;

@WebServlet("/loans/ver/*")
public class LoanGetServlet extends HttpServlet{
    private LoanServiceInterface service;

    @Override
    public void init() throws ServletException {
        ServletConfig sc = this.getServletConfig();
        this.service = (new InjectionBeanCDI()).getInitBeanCDI(sc, LoanServiceInterface.class);
    }

    public void doGet(
            javax.servlet.http.HttpServletRequest request,
            javax.servlet.http.HttpServletResponse response
    ) throws ServletException, java.io.IOException 
    {
        try{
            String pathInfo = request.getPathInfo();
            String id = pathInfo.substring(1);

            Prestamo loan = id != null ? this.service.getById(Integer.parseInt(id)) : null;
            
            request.setAttribute("prestamo", loan);
            request.setAttribute("code", loan != null ? 200 : 404);
            request.getRequestDispatcher("/WEB-INF/prestamos/prestamosForm.jsp").forward(request, response);
        }catch(Exception e){
            System.out.println(e);
            request.setAttribute("message", "Ocurrió un error al obtener los datos del prestamo");
            request.setAttribute("code", 500);
            request.getRequestDispatcher("/WEB-INF/prestamos/prestamosForm.jsp");
        }
    }
}
