package entities;

import java.sql.Date;

public class Libro {
    private Integer id;
    private String isbn;
    private String nombre;
    private String editorial;
    private String autor;
    private String resumen;
    private Date fecha_publicacion;
    private String idioma;
    private int edicion;
    private Date created_at;
    private Date updated_at;

    // Constructor
    public Libro(){
    }

    public Libro(Integer id, String isbn, String nombre, String editorial, String autor, String resumen, Date fecha_publicacion, String idioma, int edicion, Date created_at, Date updated_at) {
        this.id = id;
        this.isbn = isbn;
        this.nombre = nombre;
        this.editorial = editorial;
        this.autor = autor;
        this.resumen = resumen;
        this.fecha_publicacion = fecha_publicacion;
        this.idioma = idioma;
        this.edicion = edicion;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    // Getters
    public Integer getId() {
        return id;
    }   

    public String getIsbn(){
        return isbn;
    }

    public String getNombre(){
        return nombre;
    }

    public String getEditorial(){
        return editorial;
    }

    public String getAutor(){
        return autor;
    }

    public String getResumen(){
        return resumen;
    }

    public Date getFechaPublicacion(){
        return fecha_publicacion;
    }

    public String getIdioma(){
        return idioma;
    }

    public int getEdicion(){
        return edicion;
    }

    public Date getCreated_at(){
        return created_at;
    }

    public Date getUpdated_at(){
        return updated_at;
    }


    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public void setFechaPublicacion(Date fecha_publicacion) {
        this.fecha_publicacion = fecha_publicacion;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public void setEdicion(int edicion) {
        this.edicion = edicion;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

}
