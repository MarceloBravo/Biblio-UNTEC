package dao.auth;

import java.sql.Connection;

import entities.Usuario;
import interfaces.dao.LoginDAOInterface;
import interfaces.utils.ConnectionMySqlInterface;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LoginDAO implements LoginDAOInterface {
    private final ConnectionMySqlInterface connectionMySql;
    private Connection cnn;

    public LoginDAO() {
        this.connectionMySql = null;
    }

    public LoginDAO(ConnectionMySqlInterface connectionMySql) {
        this.connectionMySql = connectionMySql;
    }
    
    public Usuario login(String email, String password){
        Usuario user = null;
        String Query = "SELECT * FROM usuarios WHERE email = ? AND password = ?";
        try{
            if (connectionMySql == null) return null;
            this.cnn = connectionMySql.getConnection();
            if (this.cnn == null) return null;
            PreparedStatement ps = cnn.prepareStatement(Query);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                user = new Usuario();
                user.setNombre(rs.getString("nombre"));
                user.setAppelidos(rs.getString("apellidos"));
                user.setEmail(rs.getString("email"));
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return user;
    }

}
