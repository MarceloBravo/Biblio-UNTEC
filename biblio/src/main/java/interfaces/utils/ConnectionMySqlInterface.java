package interfaces.utils;

import java.sql.Connection;

/**
 * Interfaz para la conexión a la base de datos MySQL.
 */
public interface ConnectionMySqlInterface {

    /**
     * Obtiene una conexión a la base de datos.
     * @return un objeto Connection
     */
    public Connection getConnection();

}
