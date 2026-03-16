package services.auth;

import javax.servlet.http.HttpSession;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import interfaces.auth.LoginServiceInterface;
import interfaces.dao.LoginDAOInterface;
import entities.Usuario;

@ApplicationScoped
public class LoginService implements LoginServiceInterface {
    private LoginDAOInterface dao;

    public LoginService() {
    }

    @Inject
    public LoginService(LoginDAOInterface dao) {
        this.dao = dao;
    }

    public Usuario login(String email, String password, HttpSession session) {
        Usuario user = dao.login(email, password);
        if (user != null) {
            session.setAttribute("userSession", user);
        }
        return user;
    }
}
