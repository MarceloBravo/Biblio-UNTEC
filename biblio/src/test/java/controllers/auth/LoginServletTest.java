package controllers.auth;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;

import config.LazyHolder;
import config.WebKeys;
import entities.Usuario;
import interfaces.auth.LoginServiceInterface;

/**
 * Test del servlet completamente aislado: el servlet no importa LoginService ni LoginDAO.
 * El servicio se "inyecta" vía ServletContext (mock). No se usa ninguna implementación real.
 */
public class LoginServletTest {

    private LoginServlet servlet;
    private ServletContext mockContext;
    private HttpServletRequest mockRequest;
    private HttpServletResponse mockResponse;
    private HttpSession mockSession;
    private RequestDispatcher mockDispatcher;
    private LoginServiceInterface mockService;

    @Before
    public void setUp() throws ServletException {
        servlet = new LoginServlet();
        mockService = mock(LoginServiceInterface.class);
        mockContext = mock(ServletContext.class);
        when(mockContext.getAttribute(WebKeys.LOGIN_SERVICE)).thenReturn(new LazyHolder<>(() -> mockService));

        ServletConfig mockConfig = mock(ServletConfig.class);
        when(mockConfig.getServletContext()).thenReturn(mockContext);

        servlet.init(mockConfig);

        mockRequest = mock(HttpServletRequest.class);
        mockResponse = mock(HttpServletResponse.class);
        mockSession = mock(HttpSession.class);
        mockDispatcher = mock(RequestDispatcher.class);

        when(mockRequest.getSession(true)).thenReturn(mockSession);
        when(mockRequest.getRequestDispatcher(eq("home.jsp"))).thenReturn(mockDispatcher);
        when(mockRequest.getRequestDispatcher(eq("login.jsp"))).thenReturn(mockDispatcher);
    }

    @Test
    public void loginExitoso_seteaCode200YMessageExitoso() throws ServletException, IOException {
        when(mockRequest.getParameter("email")).thenReturn("ok@test.com");
        when(mockRequest.getParameter("password")).thenReturn("1234");

        Usuario user = new Usuario();
        user.setEmail("ok@test.com");
        user.setNombre("Test");
        when(mockService.login(eq("ok@test.com"), eq("1234"), eq(mockSession))).thenReturn(user);

        servlet.doPost(mockRequest, mockResponse);

        verify(mockRequest).setAttribute("message", "Autenticación exitosa");
        verify(mockRequest).setAttribute("code", 200);
        verify(mockRequest).getRequestDispatcher("home.jsp");
    }

    @Test
    public void loginFallido_seteaCode403YMessageIncorrecto() throws ServletException, IOException {
        when(mockRequest.getParameter("email")).thenReturn("bad@test.com");
        when(mockRequest.getParameter("password")).thenReturn("wrong");
        when(mockService.login(eq("bad@test.com"), eq("wrong"), eq(mockSession))).thenReturn(null);

        servlet.doPost(mockRequest, mockResponse);

        verify(mockRequest).setAttribute("message", "Usuario o contraseña incorrectos");
        verify(mockRequest).setAttribute("code", 403);
    }
}
