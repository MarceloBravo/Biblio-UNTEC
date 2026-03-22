package cdi;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.context.spi.CreationalContext;
import javax.naming.InitialContext;

/**
 * Clase de utilidad para la inyección de dependencias con CDI en Servlets.
 * Permite implementar la inversión de dependencias.
 */
public final class InjectionBeanCDI extends HttpServlet {
    
    /**
     * Obtiene una instancia de un bean CDI.
     *
     * @param sc           la configuración del servlet
     * @param serviceClass la clase del servicio a instanciar
     * @param <T>          el tipo del servicio
     * @return una instancia del servicio
     * @throws ServletException si la inicialización de CDI falla
     */
    public <T> T getInitBeanCDI(ServletConfig sc, Class<T> serviceClass) throws ServletException {
        try {
           BeanManager bm = null;
            Object bmObj = sc.getServletContext()
                    .getAttribute("org.jboss.weld.environment.servlet.javax.enterprise.inject.spi.BeanManager");
            if (bmObj == null) {
                bmObj = sc.getServletContext().getAttribute("javax.enterprise.inject.spi.BeanManager");
            }

            if (bmObj instanceof BeanManager) {
                bm = (BeanManager) bmObj;
            } else {
                InitialContext ic = new javax.naming.InitialContext();
                bm = (BeanManager) ic.lookup("java:comp/env/BeanManager");
            }

            if (bm == null) {
                throw new ServletException("BeanManager could not be found in ServletContext or JNDI");
            }

            Bean<?> bean = bm.getBeans(serviceClass).iterator().next();
            CreationalContext<?> ctx = bm.createCreationalContext(bean);
            return serviceClass.cast(bm.getReference(bean, serviceClass, ctx));
        } catch (Exception e) {
            throw new ServletException("CDI Initialization failed", e);
        }
    }
}
