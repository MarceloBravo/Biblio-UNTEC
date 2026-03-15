package services.auth;

import javax.servlet.http.HttpSession;
import javax.enterprise.context.ApplicationScoped;

import interfaces.auth.LoginServiceInterface;
import interfaces.dao.LoginDAOInterface;
import entities.Usuario;

@ApplicationScoped
public class LoginService implements LoginServiceInterface {
    private final LoginDAOInterface dao;

    public LoginService() {
        this.dao = null;
    }

    public LoginService(LoginDAOInterface dao) {
        this.dao = dao;
    }

    public Usuario login(String email, String password, HttpSession session){
        if (dao == null) return null;
        Usuario user = dao.login(email, password);
        if(user != null){
            session.setAttribute("userSession", user);
        }
        return user;
    }
}
