package listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import config.Wiring;

/**
 * Al arrancar la aplicación, crea el grafo de dependencias (wiring)
 * y registra los servicios en el contexto para que los servlets los usen.
 *
 * <p>La composición real (inyección por constructor) se hace en {@link config.Wiring},
 * de forma análoga a como en una app de consola se hace en Main.</p>
 */
public class AppWiringListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Wiring.wire(sce.getServletContext());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {}
}
