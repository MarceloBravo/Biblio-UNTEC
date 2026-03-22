package utils;

import javax.enterprise.context.ApplicationScoped;

import org.mindrot.jbcrypt.BCrypt;

import interfaces.utils.StringUtilsInterface;


/**
 * Clase que implementa la interfaz StringUtilsInterface y se encarga de las utilidades de cadenas.
 */
@ApplicationScoped
public class StringUtils implements StringUtilsInterface{

    /**
     * Constructor por defecto.
     */
    public StringUtils(){}

    /**
     * Método que encripta una contraseña.
     *
     * @param password la contraseña a encriptar
     * @return la contraseña encriptada
     */
    public String hashingPassword(String password) {
        String passwordHashed = BCrypt.hashpw(password, BCrypt.gensalt());
        return passwordHashed;
    }
}