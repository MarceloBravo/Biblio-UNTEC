package interfaces.dao;

import entities.Usuario;

/**
 * Interfaz para el Acceso a Datos de Login (DAO).
 */
public interface LoginDAOInterface {
    
    /**
     * Valida las credenciales de un usuario.
     * @param email el email del usuario
     * @param password la contraseña del usuario
     * @return el usuario si las credenciales son correctas, null en caso contrario
     */
    public Usuario login(String email, String password);
}
