package dao.auth;

import java.sql.Connection;

import entities.Usuario;
import interfaces.dao.LoginDAOInterface;
import interfaces.utils.ConnectionMySqlInterface;
import javax.inject.Inject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LoginDAO implements LoginDAOInterface {
    private ConnectionMySqlInterface connectionMySql;

    public LoginDAO() {}

    @Inject
    public LoginDAO(ConnectionMySqlInterface connectionMySql) {
        this.connectionMySql = connectionMySql;
    }
    
    public Usuario login(String email, String password){
        Usuario user = null;
        String Query = "SELECT * FROM usuarios WHERE email = ? AND password = ?";
        try (Connection cnn = connectionMySql.getConnection();
             PreparedStatement ps = cnn.prepareStatement(Query)) {
            
            ps.setString(1, email);
            ps.setString(2, password);
            
            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next()){
                    user = new Usuario();
                    user.setNombre(rs.getString("nombre"));
                    user.setAppelidos(rs.getString("apellidos"));
                    user.setEmail(rs.getString("email"));
                }
            }
        }catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
        return user;
    }
}
