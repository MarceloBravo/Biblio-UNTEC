package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dto.PaginationDTO;
import dto.PrestamoDTO;
import entities.Libro;
import entities.Prestamo;
import entities.Usuario;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import interfaces.dao.LoanDAOInterface;
import interfaces.utils.ConnectionMySqlInterface;

public class PrestamoDAO implements LoanDAOInterface{
    private ConnectionMySqlInterface connectionMySql;
    private static String mainQuery = "SELECT " + 
                        "p.id, p.usuario_id, p.libro_id, p.fecha_prestamo, p.fecha_devolucion, p.fecha_retorno, " + 
                        "l.isbn, l.nombre AS libro_nombre, l.editorial, l.autor, l.resumen, l.fecha_publicacion, l.idioma, l.edicion," +
                        "u.nombre AS usuario_nombre, u.apellidos, u.email " +
                        "FROM prestamos p " +
                        "INNER JOIN libros l ON p.libro_id = l.id " +
                        "INNER JOIN usuarios u ON p.usuario_id = u.id = u.id ";
    
    public PrestamoDAO(){
    }
    
    @Inject
    public PrestamoDAO(ConnectionMySqlInterface connectionMySql) {
        this.connectionMySql = connectionMySql;
    }   

    @Override
    public PrestamoDTO list(Integer desde, Integer filas){
        PrestamoDTO result = new PrestamoDTO();
        PaginationDTO pagDTO = new PaginationDTO();
        List<Prestamo> data = new ArrayList<>();
        Integer totReg = this.getTotReg("", null);  
        String query = mainQuery + "ORDER BY p.id DESC LIMIT ?, ?";
        try{
            Connection cnn = connectionMySql.getConnection();
            PreparedStatement ps = cnn.prepareStatement(query);
            ps.setInt(1, desde);
            ps.setInt(2, filas);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){   
                Libro book = new Libro(rs.getInt("libro_id"), rs.getString("isbn"), rs.getString("nombre"), rs.getString("editorial"), rs.getString("autor"), rs.getString("resumen"), rs.getDate("fecha_publicacion"), rs.getString("idioma"), rs.getInt("edicion"));
                Usuario user = new Usuario(rs.getInt("usuario_id"), rs.getString("usuario_nombre"), rs.getString("apellidos"), rs.getString("email"));
                Prestamo prestamo = new Prestamo(rs.getInt("id"), user, book, rs.getDate("fecha_prestamo"), rs.getDate("fecha_devolucion"), rs.getDate("fecha_retorno"));
                data.add(prestamo);
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
    public PrestamoDTO list(Integer desde, Integer filas, String search){
        PrestamoDTO result = new PrestamoDTO();
        PaginationDTO pagDTO = new PaginationDTO();
        List<Prestamo> data = new ArrayList<>();
        String where = "WHERE p.id LIKE ? OR u.nombre LIKE ? OR u.apellidos LIKE ? OR u.email LIKE ? OR l.isbn LIKE ? OR l.nombre LIKE ? OR l.editorial LIKE ? OR l.autor LIKE ? OR DATE_FORMAT(l.fecha_publicacion, '%Y-%m-%d %H:%i:%s') LIKE ? OR l.idioma LIKE ? ";
        Integer totReg = this.getTotReg("", search);  
        String query = mainQuery + where + "ORDER BY p.id DESC LIMIT ?, ?";
        try{
            Connection cnn = connectionMySql.getConnection();
            PreparedStatement ps = cnn.prepareStatement(query);
            Integer intSearch = search.matches("\\d+") ?  Integer.parseInt(search) : null;
            ps.setInt(1, intSearch);
            ps.setString(2, "%" + search + "%");
            ps.setString(3, "%" + search + "%");
            ps.setString(4, "%" + search + "%");
            ps.setString(5, "%" + search + "%");
            ps.setString(6, "%" + search + "%");
            ps.setString(7, "%" + search + "%");
            ps.setString(8, "%" + search + "%");
            ps.setString(9, "%" + search + "%");
            ps.setString(10, "%" + search + "%");
            ps.setInt(11, desde);
            ps.setInt(12, filas);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){   
                Libro book = new Libro(rs.getInt("libro_id"), rs.getString("isbn"), rs.getString("nombre"), rs.getString("editorial"), rs.getString("autor"), rs.getString("resumen"), rs.getDate("fecha_publicacion"), rs.getString("idioma"), rs.getInt("edicion"));
                Usuario user = new Usuario(rs.getInt("usuario_id"), rs.getString("usuario_nombre"), rs.getString("apellidos"), rs.getString("email"));
                Prestamo prestamo = new Prestamo(rs.getInt("id"), user, book, rs.getDate("fecha_prestamo"), rs.getDate("fecha_devolucion"), rs.getDate("fecha_retorno"));
                data.add(prestamo);
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
    public Prestamo getById(Integer id){
        String query = mainQuery + " WHERE id = ?";
        try {
            Connection cnn = connectionMySql.getConnection();
            PreparedStatement ps = cnn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Libro book = new Libro(rs.getInt("libro_id"), rs.getString("isbn"), rs.getString("nombre"), rs.getString("editorial"), rs.getString("autor"), rs.getString("resumen"), rs.getDate("fecha_publicacion"), rs.getString("idioma"), rs.getInt("edicion"));
                Usuario user = new Usuario(rs.getInt("usuario_id"), rs.getString("usuario_nombre"), rs.getString("apellidos"), rs.getString("email"));
                Prestamo prestamo = new Prestamo(rs.getInt("id"), user, book, rs.getDate("fecha_prestamo"), rs.getDate("fecha_devolucion"), rs.getDate("fecha_retorno"));
                return prestamo;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }


    @Override
    public Prestamo create(Prestamo prestamo){
        String query = "INSERT INTO prestamo (usuario_id, libro_id, fecha_prestamo, fecha_devolucion, fecha_retorno) VALUES (?, ?, ?, ?, ?)";
        try{
            Connection cnn = connectionMySql.getConnection();
            PreparedStatement ps = cnn.prepareStatement(query);
            ps.setInt(1, prestamo.getUsuario().getId());
            ps.setInt(2, prestamo.getLibro().getId());
            ps.setDate(3, prestamo.getFechaPrestamo());
            ps.setDate(4, prestamo.getFechaDevolucion());
            ps.setDate(5, prestamo.getFechaRetorno());
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                prestamo.setId(this.getNewId(ps));
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return prestamo.getId() != null ? prestamo : null;
    }


    @Override
    public Prestamo update(Prestamo prestamo){
        String query = "UPDATE prestamo SET usuario_id = ?, libro_id = ?, fecha_prestamo = ?, fecha_devolucion = ?, fecha_retorno = ? WHERE id = ?";
        try{
            Connection cnn = connectionMySql.getConnection();
            PreparedStatement ps = cnn.prepareStatement(query);
            ps.setInt(1, prestamo.getUsuario().getId());
            ps.setInt(2, prestamo.getLibro().getId());
            ps.setDate(3, prestamo.getFechaPrestamo());
            ps.setDate(4, prestamo.getFechaPrestamo());
            ps.setDate(5, prestamo.getFechaRetorno());
            ps.setInt(6, prestamo.getId());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0 ? prestamo : null;
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    
    
    @Override
    public boolean delete(Integer id){
        String query = "DELETE FROM prestamo WHERE id = ?";
        try{
            Connection cnn = connectionMySql.getConnection();
            PreparedStatement ps = cnn.prepareStatement(query);
            ps.setInt(1, id);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;

        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    /**
     * Obtiene el ID generado para un nuevo registro.
     *
     * @param ps el PreparedStatement que ejecutó la inserción
     * @return el ID generado
     * @throws Exception si ocurre un error
     */
    private Integer getNewId(PreparedStatement ps) throws Exception {
        Integer id = 0;
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            id = rs.getInt(1);
        }
        return id;
    }


    private Integer getTotReg(String where, String search){
        String query = "SELECT COUNT(*) FROM prestamos " + where;
        System.out.println(query);
        try{
            Connection cnn = connectionMySql.getConnection();
            PreparedStatement ps = cnn.prepareStatement(query);
            if(search != null){
                Integer intSearch = search.matches("\\d+") ?  Integer.parseInt(search) : null;
                ps.setInt(1, intSearch);
                ps.setString(2, "%" + search + "%");
                ps.setString(3, "%" + search + "%");
                ps.setString(4, "%" + search + "%");
                ps.setString(5, "%" + search + "%");
                ps.setString(6, "%" + search + "%");
                ps.setString(7, "%" + search + "%");
                ps.setString(8, "%" + search + "%");
                ps.setString(9, "%" + search + "%");
                ps.setString(10, "%" + search + "%");
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
