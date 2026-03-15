package utils;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.enterprise.context.ApplicationScoped;

import interfaces.utils.ConnectionMySqlInterface;

@ApplicationScoped
public class ConnectionMySql implements ConnectionMySqlInterface {
    private Connection cnn; 
    private static String strConnect = "jdbc:mysql://localhost:3306/untec";
    private static String driver = "com.mysql.cj.jdbc.Driver";

    // --- Configurando el patron Singleton ---
    private static ConnectionMySql instance;

    // Constructor privado para prevenior que se cree una instancia desde algún otro lado
    private ConnectionMySql() {}

    // El métodod estático que obtiene la instancia única de esta clase
    public static synchronized ConnectionMySql getInstance() {
        if (instance == null) {
            instance = new ConnectionMySql();
        }
        return instance;
    }
    // --- Fin del patron Singleton ---
    

    // Implementando el método de la interface
    @Override
    public Connection getConnection(String user, String password){
        if(cnn == null){
            try{
                Class.forName(driver);
                cnn = (Connection) DriverManager.getConnection(strConnect, user, password);
            }catch(Exception e){
                System.out.println(e);
                e.printStackTrace();
            }
        }
        return cnn;
    }

}
