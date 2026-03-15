package interfaces.utils;

import java.sql.Connection;

public interface ConnectionMySqlInterface {

    public Connection getConnection(String user, String password);

}
