package config;

/**
 * Claves para atributos en ServletContext.
 * Centralizadas para que servlets y listeners no dependan entre sí por nombre.
 * Al añadir un nuevo servicio: una nueva constante aquí y su registro en {@link config.Wiring}.
 */
public final class WebKeys {

    public static final String LOGIN_SERVICE = "loginService";
    // public static final String LIBRO_SERVICE = "libroService";  // ejemplo

    private WebKeys() {}
}
