package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import dto.PaginationDTO;
import dto.UserListDTO;
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

    @Override
    public UserListDTO list(Integer desde, Integer filas) {
        UserListDTO result = new UserListDTO();
        PaginationDTO pagDTO = new PaginationDTO();
        List<Usuario> data = new ArrayList<>();
        Integer totReg = this.getTotReg("", null);
        String query = "Select id, nombre, apellidos, email from usuarios ORDER BY id ASC LIMIT ?, ?";
        try{
            Connection cnn = connectionMySql.getConnection();
            PreparedStatement ps = cnn.prepareStatement(query);
            ps.setInt(1, desde);
            ps.setInt(2, filas);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Usuario user = new Usuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellidos"), rs.getString("email"));
                data.add(user);
            }
            result.setData(data);
            pagDTO.setTotReg(totReg);
            result.setPagination(pagDTO);
        }catch(Exception e){
            System.out.print(e);
        }
        return result;
    }
    
    @Override
    public UserListDTO list(Integer desde, Integer filas, String search) {
        UserListDTO result = new UserListDTO();
        PaginationDTO pagDTO = new PaginationDTO();
        List<Usuario> data = new ArrayList<>();
        String where = "WHERE nombre Like ? OR apellidos Like ? OR email Like ?";
        Integer totReg = this.getTotReg(where, search);
        String query = "Select id, nombre, apellidos, email from usuarios " + where + " ORDER BY id ASC LIMIT ?, ?";
        try{
            Connection cnn = connectionMySql.getConnection();
            PreparedStatement ps = cnn.prepareStatement(query);
            ps.setString(1, "%" + search + "%");
            ps.setString(2, "%" + search + "%");
            ps.setString(3, "%" + search + "%");
            ps.setInt(4, desde);
            ps.setInt(5, filas);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Usuario user = new Usuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellidos"), rs.getString("email"));
                data.add(user);
            }
            result.setData(data);
            pagDTO.setTotReg(totReg);
            result.setPagination(pagDTO);
            return result;
        }catch(Exception e){
            System.out.print(e);
        }
        return result;
    }

    @Override
    public Usuario getById(Integer id) {
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
                user.setApellidos(rs.getString("apellidos"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
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
                user.setApellidos(rs.getString("apellidos"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Usuario create(Usuario user) {
        String query = "INSERT INTO usuarios (nombre, apellidos, email, password) VALUES (?, ?, ?, ?)";
        try {
            Connection cnn = connectionMySql.getConnection();
            PreparedStatement ps = cnn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getNombre());
            ps.setString(2, user.getApellidos());
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

    @Override
    public Usuario update(Usuario user) {
        String query = "UPDATE usuarios SET nombre = ?, apellidos = ?, email = ?, password = ? WHERE id = ?;";
        try {           
            Connection cnn = connectionMySql.getConnection();
            PreparedStatement ps = cnn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getNombre());
            ps.setString(2, user.getApellidos());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setInt(5, user.getId());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0 ? user : null;

        } catch (Exception e) {
            System.out.println(e);
        }
        return user;
    }

    @Override
    public boolean delete(Usuario user) {
        String query = "DELETE FROM usuarios WHERE id = ?";
        try {
            Connection cnn = connectionMySql.getConnection();
            PreparedStatement ps = cnn.prepareStatement(query);
            ps.setInt(1, user.getId());
            ps.executeUpdate();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }

    private Integer getTotReg(String where, String search){
        String query = "SELECT COUNT(*) FROM usuarios " + where;
        try{
            Connection cnn = connectionMySql.getConnection();
            PreparedStatement ps = cnn.prepareStatement(query);
            if(search != null){
                ps.setString(1, "%" + search + "%");
                ps.setString(2, "%" + search + "%");
                ps.setString(3, "%" + search + "%");
            }
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return 0;
    }
}
