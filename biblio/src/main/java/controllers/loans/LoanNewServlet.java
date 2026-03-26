package controllers.loans;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import controllers.books.BookSaveServlet;
import entities.Prestamo;

@WebServlet("/loans/nuevo")
public class LoanNewServlet extends BookSaveServlet {
    

    public void doGet(
            javax.servlet.http.HttpServletRequest request,
            javax.servlet.http.HttpServletResponse response
    ) throws ServletException, java.io.IOException 
    {
        Prestamo loan = new Prestamo();
        request.setAttribute("prestamo", loan);
        request.getRequestDispatcher(request.getContextPath() + "/WEB-INF/prestamos/prestamosForm.jsp").forward(request, response);
    }
}
