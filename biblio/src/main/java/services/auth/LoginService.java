package services.auth;

import javax.servlet.http.HttpSession;
import javax.enterprise.context.ApplicationScoped;

import interfaces.auth.LoginServiceInterface;
import interfaces.dao.LoginDAOInterface;
import jakarta.inject.Inject;
import entities.Usuario;

@ApplicationScoped
public class LoginService implements LoginServiceInterface {
    @Inject
    private LoginDAOInterface dao;
    
    public LoginService(){
    }

    public Usuario login(String email, String password, HttpSession session){
        Usuario user = null;
        user = dao.login(email, password);
        if(user != null){
            session.setAttribute("userSession", user);
        }
        return user;
    }
}
