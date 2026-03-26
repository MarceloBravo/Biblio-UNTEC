package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/registerForm")
public class RegisterForm extends HttpServlet {

    @Override
    public void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException 
    {
        request.getRequestDispatcher("/WEB-INF/views/registerUser.jsp").forward(request, response);
    }
    
}
