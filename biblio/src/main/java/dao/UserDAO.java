package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import entities.Usuario;
import interfaces.dao.UserDAOInterface;
import interfaces.utils.ConnectionMySqlInterface;

@ApplicationScoped
public class UserDAO implements UserDAOInterface {
    private ConnectionMySqlInterface connectionMySql;

    public UserDAO() {
    }

    @Inject
    public UserDAO(ConnectionMySqlInterface connectionMySql) {
        this.connectionMySql = connectionMySql;
    }

    public List<Usuario> list() {
        return null;
    }

    public Usuario find(Integer id) {
        String query = "SELECT * FROM usuarios WHERE id = ?";
        try {
            Connection cnn = connectionMySql.getConnection();
            PreparedStatement ps = cnn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Usuario user = new Usuario();
                user.setId(rs.getInt("id"));
                user.setNombre(rs.getString("nombre"));
                user.setAppelidos(rs.getString("apellidos"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Usuario findByEmail(String email) {
        String query = "SELECT * FROM usuarios WHERE email = ?";
        try {
            Connection cnn = connectionMySql.getConnection();
            PreparedStatement ps = cnn.prepareStatement(query);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Usuario user = new Usuario();
                user.setId(rs.getInt("id"));
                user.setNombre(rs.getString("nombre"));
                user.setAppelidos(rs.getString("apellidos"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Usuario create(Usuario user) {
        String query = "INSERT INTO usuarios (nombre, apellidos, email, password) VALUES (?, ?, ?, ?)";
        try {
            Usuario userFound = this.findByEmail(user.getEmail());
            if (userFound != null) {
                System.out.println("El usuario ya existe");
                throw new Exception("El usuario ya existe");
            }
            Connection cnn = connectionMySql.getConnection();
            PreparedStatement ps = cnn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getNombre());
            ps.setString(2, user.getAppelidos());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                user.setId(this.getNewId(ps));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return user;
    }

    private Integer getNewId(PreparedStatement ps) throws Exception {
        Integer id = 0;
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            id = rs.getInt(1);
        }
        return id;
    }

    public void update(Usuario user) {

    }

    public void delete(Usuario user) {

    }

}
