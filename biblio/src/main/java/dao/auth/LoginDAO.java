package dao.auth;

import java.sql.Connection;

import entities.Usuario;
import interfaces.dao.LoginDAOInterface;
import interfaces.utils.ConnectionMySqlInterface;
import jakarta.inject.Inject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LoginDAO implements LoginDAOInterface {
    @Inject
    private ConnectionMySqlInterface connectionMySql;

    private Connection cnn;

    public LoginDAO(){}
    
    public Usuario login(String email, String password){
        Usuario user = null;
        String Query = "SELECT * FROM usuarios WHERE email = ? AND password = ?";
        try{
            this.cnn = connectionMySql.getConnection(email, password);
            PreparedStatement ps = cnn.prepareStatement(Query);
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
