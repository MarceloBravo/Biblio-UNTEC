package cdi;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.context.spi.CreationalContext;
import javax.naming.InitialContext;

public final class InjectionBeanCDI extends HttpServlet {
    
    public <T> T getInitBeanCDI(Class<T> serviceClass, ServletConfig sc) throws ServletException {
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
