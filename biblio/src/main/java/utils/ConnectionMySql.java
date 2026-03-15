package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import javax.enterprise.context.ApplicationScoped;

import interfaces.utils.ConnectionMySqlInterface;

@ApplicationScoped
public class ConnectionMySql implements ConnectionMySqlInterface {
    private Connection cnn;
    private static String strConnect = "jdbc:mysql://localhost:3306/untec";
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String user = "root";
    /** Contraseña del usuario MySQL. Si 'root' tiene contraseña, cámbiala aquí. */
    private static String password = "admin123";

    private static ConnectionMySql instance;

    private ConnectionMySql() {}

    public static synchronized ConnectionMySql getInstance() {
        if (instance == null) {
            instance = new ConnectionMySql();
        }
        return instance;
    }

    @Override
    public Connection getConnection() {
        if (cnn == null) {
            try {
                Class.forName(driver);
                Properties props = new Properties();
                props.setProperty("user", user);
                props.setProperty("password", password);
                cnn = DriverManager.getConnection(strConnect, props);
            } catch (Exception e) {
                System.out.println(e);
                e.printStackTrace();
            }
        }
        return cnn;
    }
}
