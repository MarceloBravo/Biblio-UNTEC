package services;

import javax.servlet.http.HttpSession;

//import org.mindrot.jbcrypt.BCrypt;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import interfaces.Services.LoginServiceInterface;
import interfaces.dao.LoginDAOInterface;
import entities.Usuario;

/**
 * Clase que implementa la interfaz LoginServiceInterface y se encarga de la lógica de negocio para el login de usuarios.
 */
@ApplicationScoped
public class LoginService implements LoginServiceInterface {

    /**
     * Inyección de dependencia para el acceso a datos de login.
     */
    private LoginDAOInterface dao;

    /**
     * Constructor por defecto.
     */
    public LoginService() {
    }

    /**
     * Constructor que recibe el DAO de login.
     *
     * @param dao el DAO de login
     */
    @Inject
    public LoginService(LoginDAOInterface dao) {
        this.dao = dao;
    }

    /**
     * Método que realiza el login de un usuario.
     *
     * @param email    el email del usuario
     * @param password la contraseña del usuario
     * @param session  la sesión HTTP
     * @return el objeto Usuario si el login es correcto, de lo contrario null
     */
    public Usuario login(String email, String password, HttpSession session) {
        Usuario user = dao.login(email, password);
        if (user != null) {
            session.setAttribute("userSession", user);
        }
        return user;
    }
}
