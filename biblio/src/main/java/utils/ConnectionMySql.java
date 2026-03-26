package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import javax.enterprise.context.ApplicationScoped;

import interfaces.utils.ConnectionMySqlInterface;

/**
 * Clase que implementa la interfaz ConnectionMySqlInterface y se encarga de la lógica para la conexión a la base de datos MySQL.
 */
@ApplicationScoped
public class ConnectionMySql implements ConnectionMySqlInterface {
    /**
     * Objeto de conexión a la base de datos.
     */
    private Connection cnn;
    /**
     * Cadena de conexión a la base de datos.
     */
    private static String strConnect = "jdbc:mysql://localhost:3306/untec";
    /**
     * Driver de la base de datos.
     */
    private static String driver = "com.mysql.cj.jdbc.Driver";
    /**
     * Usuario de la base de datos.
     */
    private static String user = "root";    
    /**
     * Contraseña del usuario de la base de datos.
     */
    private static String password = "admin123";
    /**
     * Constructor por defecto.
     */
    public ConnectionMySql() {}

    /**
     * Obtiene una conexión a la base de datos.
     *
     * @return un objeto Connection
     */
    @Override
    public Connection getConnection() {
        try {
            if (cnn == null || cnn.isClosed()) {
                Class.forName(driver);
                Properties props = new Properties();
                props.setProperty("user", user);
                props.setProperty("password", password);
                cnn = DriverManager.getConnection(strConnect, props);
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return cnn;
    }
}
