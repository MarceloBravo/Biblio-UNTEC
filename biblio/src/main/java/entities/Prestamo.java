package entities;

import java.sql.Date;

public class Prestamo {
    private Integer id;
    private Usuario usuario;
    private Libro libro;
    private Date fecha_prestamo;
    private Date fecha_devolucion;
    private Date fecha_retorno;

    public Prestamo() {
    }

    public Prestamo(Integer id, Usuario usuario, Libro libro, Date fecha_prestamo, Date fecha_devolucion, Date fecha_retorno) {
        this.id = id;
        this.usuario = usuario;
        this.libro = libro;
        this.fecha_prestamo = fecha_prestamo;
        this.fecha_devolucion = fecha_devolucion;
        this.fecha_retorno = fecha_retorno;
    }

    // Getters
    public Integer getId() {
        return id;
    }
    
    public Usuario getUsuario() {
        return this.usuario;
    }

    public Libro getLibro() {
        return this.libro;
    }

    public Date getFechaPrestamo() {
        return fecha_prestamo;
    }   

    public Date getFechaDevolucion() {
        return fecha_devolucion;
    }
    
    public Date getFechaRetorno() {
        return fecha_retorno;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }   

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }   

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public void setFechaPrestamo(Date fecha_prestamo) {
        this.fecha_prestamo = fecha_prestamo;
    }

    public void setFechaDevolucion(Date fecha_devolucion) {
        this.fecha_devolucion = fecha_devolucion;
    }

    public void setFechaRetorno(Date fecha_retorno) {
        this.fecha_retorno = fecha_retorno;
    }
}
