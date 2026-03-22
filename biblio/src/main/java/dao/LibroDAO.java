package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

import javax.enterprise.context.ApplicationScoped;

import dto.BookListDTO;
import dto.PaginationDTO;
import entities.Libro;
import interfaces.book.BookDAOInterface;
import interfaces.utils.ConnectionMySqlInterface;

@ApplicationScoped
public class LibroDAO implements BookDAOInterface{
    private ConnectionMySqlInterface connectionMySql;

    public LibroDAO(ConnectionMySqlInterface connectionMySql) {
        this.connectionMySql = connectionMySql;
    }

    public LibroDAO(){
    }

    @Override
    public BookListDTO list(Integer desde, Integer filas){
        BookListDTO result = new BookListDTO();
        PaginationDTO pagDTO = new PaginationDTO();
        List<Libro> data = new ArrayList<>();
        Integer totReg = this.getTotReg("", null);
        String query = "SELECT id, isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion, created_at, updated_at FROM libros ORDER BY id DESC LIMIT ?, ?";
        try{
            Connection cnn = connectionMySql.getConnection();
            PreparedStatement ps = cnn.prepareStatement(query);
            ps.setInt(1, desde);
            ps.setInt(2, filas);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Libro book = new Libro(rs.getInt("id"), rs.getString("isbn"), rs.getString("nombre"), rs.getString("editorial"), rs.getString("autor"), rs.getString("resumen"), rs.getDate("fecha_publicacion"), rs.getString("idioma"), rs.getInt("edicion"), rs.getDate("created_at"), rs.getDate("updated_at"));
                data.add(book);
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
    public BookListDTO list(Integer desde, Integer filas, String search){
        BookListDTO result = new BookListDTO();
        PaginationDTO pagDTO = new PaginationDTO();
        List<Libro> data = new ArrayList<>();
        String where = "WHERE nombre Like ? OR apellidos Like ? OR email Like ?";
        Integer totReg = this.getTotReg(where, search);
        String query = "Select id, nombre, apellidos, email from usuarios " + where + " ORDER BY id DESC LIMIT ?, ?";
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
                Libro book = new Libro(rs.getInt("id"), rs.getString("isbn"), rs.getString("nombre"), rs.getString("editorial"), rs.getString("autor"), rs.getString("resumen"), rs.getDate("fecha_publicacion"), rs.getString("idioma"), rs.getInt("edicion"), rs.getDate("created_at"), rs.getDate("updated_at"));
                data.add(book);
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
    public Libro getById(Integer id){
        String query = "SELECT * FROM libros WHERE id = ?";
        try {
            Connection cnn = connectionMySql.getConnection();
            PreparedStatement ps = cnn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Libro libro = new Libro();
                libro.setId(rs.getInt("id"));
                libro.setIsbn(rs.getString("isbn"));
                libro.setNombre(rs.getString("nombre"));
                libro.setEditorial(rs.getString("editorial"));
                libro.setResumen(rs.getString("resumen"));
                libro.setAutor(rs.getString("autor"));
                libro.setFechaPublicacion(rs.getDate("fecha_publicacion"));
                libro.setIdioma(rs.getString("idioma"));
                libro.setEdicion(rs.getInt("edicion"));
                libro.setCreated_at(rs.getDate("created_at"));
                libro.setUpdated_at(rs.getDate("updated_at"));
                return libro;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Libro create(Libro book){
       String query = "INSERT INTO libros (isbn, nombre, editorial, autor, resumen, fecha_publicacion, idioma, edicion) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection cnn = connectionMySql.getConnection();
            PreparedStatement ps = cnn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, book.getIsbn());
            ps.setString(2, book.getNombre());
            ps.setString(3, book.getEditorial());
            ps.setString(4, book.getAutor());
            ps.setString(5, book.getResumen());
            ps.setDate(6, book.getFechaPublicacion());
            ps.setString(7, book.getIdioma());
            ps.setInt(8, book.getEdicion());
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                book.setId(this.getNewId(ps));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return book.getId() != null ? book : null;
   }

    @Override
    public Libro update(Libro book){
        String query = "UPDATE libros SET isbn = ?, nombre = ?, editorial = ?, autor = ?, resumen = ?, fecha_publicacion = ?, idioma = ?, edicion = ? WHERE id = ?;";
        try {           
            Connection cnn = connectionMySql.getConnection();
            PreparedStatement ps = cnn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, book.getNombre());
            ps.setString(2, book.getEditorial());
            ps.setString(3, book.getAutor());
            ps.setString(4, book.getResumen());
            ps.setDate(5, book.getFechaPublicacion());
            ps.setString(6, book.getIdioma());
            ps.setInt(7, book.getEdicion());
            ps.setInt(8, book.getId());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0 ? book : null;

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public boolean delete(Libro book){
        String query = "DELETE FROM libros WHERE id = ?";
        try {
            Connection cnn = connectionMySql.getConnection();
            PreparedStatement ps = cnn.prepareStatement(query);
            ps.setInt(1, book.getId());
            ps.executeUpdate();
            return true;
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


    /**
     * Obtiene el número total de registros en la tabla de usuarios, opcionalmente filtrando por un criterio de búsqueda.
     *
     * @param where el criterio de búsqueda WHERE
     * @param search el término de búsqueda
     * @return el número total de registros
     */
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
