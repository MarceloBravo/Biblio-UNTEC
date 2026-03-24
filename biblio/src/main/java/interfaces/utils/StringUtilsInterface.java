package interfaces.utils;

/**
 * Interfaz para utilidades de String.
 */
public interface StringUtilsInterface {
    
    /**
     * Aplica hashing a una contraseña.
     * @param password la contraseña a hashear
     * @return la contraseña hasheada
     */
    public String hashingPassword(String password);

}
