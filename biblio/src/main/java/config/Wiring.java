package config;

import javax.servlet.ServletContext;

import dao.auth.LoginDAO;
import interfaces.auth.LoginServiceInterface;
import interfaces.dao.LoginDAOInterface;
import interfaces.utils.ConnectionMySqlInterface;
import services.auth.LoginService;
import utils.ConnectionMySql;

/**
 * Punto único de composición (composition root). Los servicios se crean
 * <b>solo la primera vez que se usan</b> (lazy), no al arranque: así no
 * se consumen recursos de módulos que nunca se acceden.
 *
 * <p>Al añadir otro servlet/servicio/DAO: registrar aquí un {@link LazyHolder}
 * y un getter en Wiring; añadir constante en {@link WebKeys}. Un solo listener.</p>
 */
public final class Wiring {

    private Wiring() {}

    /**
     * Registra en el contexto los holders que crearán cada servicio en su primer uso.
     */
    public static void wire(ServletContext ctx) {
        ctx.setAttribute(WebKeys.LOGIN_SERVICE, new LazyHolder<>(Wiring::createLoginService));
        // Ejemplo al añadir otro módulo:
        // ctx.setAttribute(WebKeys.LIBRO_SERVICE, new LazyHolder<>(Wiring::createLibroService));
    }

    private static LoginServiceInterface createLoginService() {
        ConnectionMySqlInterface connection = ConnectionMySql.getInstance();
        LoginDAOInterface loginDAO = new LoginDAO(connection);
        return new LoginService(loginDAO);
    }

    /**
     * Obtiene el servicio de login (se crea en la primera llamada).
     */
    public static LoginServiceInterface getLoginService(ServletContext ctx) {
        return get(ctx, WebKeys.LOGIN_SERVICE);
    }

    @SuppressWarnings("unchecked")
    private static <T> T get(ServletContext ctx, String key) {
        return (T) ((LazyHolder<?>) ctx.getAttribute(key)).get();
    }
}
