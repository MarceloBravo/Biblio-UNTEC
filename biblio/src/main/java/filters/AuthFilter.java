package filters;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Filtro de autenticación que intercepta todas las peticiones para verificar si el usuario está autenticado.
 */
@WebFilter("/*")
public class AuthFilter implements Filter {
    
    /**
     * Inicializa el filtro.
     *
     * @param filterConfig la configuración del filtro
     * @throws ServletException si ocurre un error en el servlet
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}
    
    /**
     * Realiza el filtrado de la petición.
     *
     * @param request  la petición
     * @param response la respuesta
     * @param chain    la cadena de filtros
     * @throws IOException      si ocurre un error de entrada/salida
     * @throws ServletException si ocurre un error en el servlet
     */
    @Override
    public void doFilter(
        ServletRequest request, 
        ServletResponse response, 
        FilterChain chain
    ) throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false); // false para no crear una nueva si no existe

        String loginURI = req.getContextPath() + "/index.jsp";

        // 1. Verificar si la sesión existe y tiene el atributo
        boolean loggedIn = (session != null && session.getAttribute("userSession") != null);
        
        // 2. Exclusiones al filtro
        boolean loginRequest = req.getRequestURI().equals(loginURI);
        String path = req.getRequestURI().substring(req.getContextPath().length());        
        boolean isLoginServlet = path.equals("/login");
        boolean isStaticResource = path.startsWith("/styles/") || 
                                    path.startsWith("/js/");

        if (loggedIn || loginRequest || isStaticResource || isLoginServlet) {
            // Si está logueado o va al login, permitimos que siga su camino
            chain.doFilter(request, response);
        } else {
            // Si no está logueado, redirigimos al login
            res.sendRedirect(loginURI);
        }
    }

    /**
     * Destruye el filtro.
     */
    @Override
    public void destroy() {}
}
