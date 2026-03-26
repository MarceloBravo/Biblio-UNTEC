package controllers.loans;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cdi.InjectionBeanCDI;
import entities.Prestamo;
import interfaces.Services.LoanServiceInterface;

@WebServlet("/loans/grabar")
public class LoanSaveServlet extends HttpServlet {
    private LoanServiceInterface service;
    
    @Override
    public void init() throws ServletException {
        ServletConfig sc = this.getServletConfig();
        this.service = (new InjectionBeanCDI()).getInitBeanCDI(sc, LoanServiceInterface.class);
    }


    @Override
    public void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        try{
            String id = request.getParameter("id");
            Integer userId = Integer.parseInt(request.getParameter("userId"));
            Integer bookId = Integer.parseInt(request.getParameter("bookId"));
            String strFechaPrestamo = request.getParameter("fechaPrestamo");
            String strFechaDevolucion = request.getParameter("fechaDevolucion");
            String strFechaRetorno = request.getParameter("fechaRetorno");
            Date fechaPrestamo = (strFechaPrestamo != null && !strFechaPrestamo.isEmpty()) ? Date.valueOf(strFechaPrestamo) : null;
            Date fechaDevolucion = (strFechaDevolucion != null && !strFechaDevolucion.isEmpty()) ? Date.valueOf(strFechaDevolucion) : null;
            Date fechaRetorno = (strFechaRetorno != null && !strFechaRetorno.isEmpty()) ? Date.valueOf(strFechaRetorno) : null;
            String accion = ((id != null && !id.isEmpty()) ? "actualizado" : "registrado");
            
            Prestamo result = null;            
            if(id != null && !id.isEmpty()){
                result = this.service.update(Integer.parseInt(id), userId, bookId, fechaPrestamo, fechaDevolucion, fechaRetorno);
            }else{
                result = this.service.create(userId, bookId, fechaPrestamo, fechaDevolucion);
            }
            
            
            request.getSession().setAttribute("message", result != null ? "prestamo " + accion + " con éxito" : "El libro no pudo ser " + accion);
            request.getSession().setAttribute("code", result != null ? 200 : 500);
            request.getSession().setAttribute("data", result);
            response.sendRedirect(request.getContextPath() + "/loans");
        } catch (Exception e) {
            System.out.println(e);
            request.setAttribute("message", "Ocurrió un error al registrar el prestamo");
            request.setAttribute("code", 500);
            request.setAttribute("data", null);
            request.getRequestDispatcher("/WEB-INF/prestamos/prestamosForm.jsp")
                    .forward(request, response);
        }
    }

}
