package interfaces.Services;

import javax.servlet.http.HttpSession;

import entities.Usuario;

/**
 * Interfaz para el Servicio de Login.
 */
public interface LoginServiceInterface {
    
    /**
     * Realiza el login de un usuario y gestiona la sesión.
     * @param email el email del usuario
     * @param password la contraseña del usuario
     * @param session la sesión HTTP
     * @return el usuario si las credenciales son correctas, null en caso contrario
     */
    public Usuario login(String email, String password, HttpSession session);
}
