package interfaces.auth;

import javax.servlet.http.HttpSession;

import entities.Usuario;

public interface LoginServiceInterface {
    
    public Usuario login(String email, String password, HttpSession session);
}
