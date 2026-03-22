package dao;

import java.sql.Connection;

import entities.Usuario;
import interfaces.dao.LoginDAOInterface;
import interfaces.utils.ConnectionMySqlInterface;
import javax.inject.Inject;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.enterprise.context.ApplicationScoped;

/**
 * Clase que implementa la interfaz LoginDAOInterface y se encarga de la lógica de acceso a datos para el login de usuarios.
 */
@ApplicationScoped
public class LoginDAO implements LoginDAOInterface {

    /**
     * Inyección de dependencia para la conexión a la base de datos.
     */
    private ConnectionMySqlInterface connectionMySql;

    /**
     * Constructor por defecto.
     */
    public LoginDAO() {
    }

    /**
     * Constructor que recibe la conexión a la base de datos.
     *
     * @param connectionMySql la conexión a la base de datos
     */
    @Inject
    public LoginDAO(ConnectionMySqlInterface connectionMySql) {
        this.connectionMySql = connectionMySql;
    }

    /**
     * Método que verifica las credenciales de un usuario en la base de datos.
     *
     * @param email    el email del usuario
     * @param password la contraseña del usuario
     * @return el objeto Usuario si las credenciales son correctas, de lo contrario null
     */
    public Usuario login(String email, String password) {
        Usuario user = null;
        String Query = "SELECT * FROM usuarios WHERE email = ?";
        try (
                Connection cnn = connectionMySql.getConnection();
                PreparedStatement ps = cnn.prepareStatement(Query)) {

            ps.setString(1, email);
            // ps.setString(2, password);

            try (
                    ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String passDesdeBD = rs.getString("password");
                    boolean isLogued = this.CheckPassword(password, passDesdeBD);
                    user = isLogued ? this.getUserData(rs) : null;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Método privado que verifica si la contraseña ingresada por el usuario coincide con la almacenada en la base de datos.
     *
     * @param passwordIngresadaPorUsuario la contraseña ingresada por el usuario
     * @param passDesdeBD                 la contraseña almacenada en la base de datos
     * @return true si las contraseñas coinciden, de lo contrario false
     */
    private boolean CheckPassword(String passwordIngresadaPorUsuario, String passDesdeBD) {
        return BCrypt.checkpw(passwordIngresadaPorUsuario, passDesdeBD);
    }

    /**
     * Método privado que obtiene los datos de un usuario a partir de un ResultSet.
     *
     * @param rs el ResultSet con los datos del usuario
     * @return un objeto Usuario con los datos obtenidos
     * @throws Exception si ocurre un error al obtener los datos
     */
    private Usuario getUserData(ResultSet rs) throws Exception {
        Usuario user = new Usuario();
        user.setNombre(rs.getString("nombre"));
        user.setApellidos(rs.getString("apellidos"));
        user.setEmail(rs.getString("email"));
        return user;
    }
}
