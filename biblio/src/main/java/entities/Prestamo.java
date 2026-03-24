package entities;

import java.sql.Date;

/**
 * Representa un préstamo de libro en la biblioteca.
 */
public class Prestamo {
    private Integer id;
    private Usuario usuario;
    private Libro libro;
    private Date fecha_prestamo;
    private Date fecha_devolucion;
    private Date fecha_retorno;

    /**
     * Constructor por defecto.
     */
    public Prestamo() {
    }

    /**
     * Constructor con todos los datos del préstamo.
     * @param id el ID del préstamo
     * @param usuario el usuario que realiza el préstamo
     * @param libro el libro prestado
     * @param fecha_prestamo la fecha en que se realiza el préstamo
     * @param fecha_devolucion la fecha en que se debe devolver el libro
     * @param fecha_retorno la fecha en que se retornó el libro
     */
    public Prestamo(Integer id, Usuario usuario, Libro libro, Date fecha_prestamo, Date fecha_devolucion, Date fecha_retorno) {
        this.id = id;
        this.usuario = usuario;
        this.libro = libro;
        this.fecha_prestamo = fecha_prestamo;
        this.fecha_devolucion = fecha_devolucion;
        this.fecha_retorno = fecha_retorno;
    }

    /**
     * Obtiene el ID del préstamo.
     * @return el ID del préstamo
     */
    public Integer getId() {
        return id;
    }
    
    /**
     * Obtiene el usuario que realiza el préstamo.
     * @return el usuario
     */
    public Usuario getUsuario() {
        return this.usuario;
    }

    /**
     * Obtiene el libro prestado.
     * @return el libro
     */
    public Libro getLibro() {
        return this.libro;
    }

    /**
     * Obtiene la fecha en que se realiza el préstamo.
     * @return la fecha del préstamo
     */
    public Date getFechaPrestamo() {
        return fecha_prestamo;
    }   

    /**
     * Obtiene la fecha en que se debe devolver el libro.
     * @return la fecha de devolución
     */
    public Date getFechaDevolucion() {
        return fecha_devolucion;
    }
    
    /**
     * Obtiene la fecha en que se retornó el libro.
     * @return la fecha de retorno
     */
    public Date getFechaRetorno() {
        return fecha_retorno;
    }

    /**
     * Establece el ID del préstamo.
     * @param id el ID del préstamo
     */
    public void setId(Integer id) {
        this.id = id;
    }   

    /**
     * Establece el usuario que realiza el préstamo.
     * @param usuario el usuario
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }   

    /**
     * Establece el libro prestado.
     * @param libro el libro
     */
    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    /**
     * Establece la fecha en que se realiza el préstamo.
     * @param fecha_prestamo la fecha del préstamo
     */
    public void setFechaPrestamo(Date fecha_prestamo) {
        this.fecha_prestamo = fecha_prestamo;
    }

    /**
     * Establece la fecha en que se debe devolver el libro.
     * @param fecha_devolucion la fecha de devolución
     */
    public void setFechaDevolucion(Date fecha_devolucion) {
        this.fecha_devolucion = fecha_devolucion;
    }

    /**
     * Establece la fecha en que se retornó el libro.
     * @param fecha_retorno la fecha de retorno
     */
    public void setFechaRetorno(Date fecha_retorno) {
        this.fecha_retorno = fecha_retorno;
    }
}
