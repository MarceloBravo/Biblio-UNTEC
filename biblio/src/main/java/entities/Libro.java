package entities;

import java.sql.Date;

/**
 * Representa un libro en la biblioteca.
 */
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

    /**
     * Constructor por defecto.
     */
    public Libro(){
    }

    /**
     * Constructor con datos esenciales del libro.
     * @param id el ID del libro
     * @param isbn el ISBN del libro
     * @param nombre el título del libro
     * @param editorial la editorial del libro
     * @param autor el autor del libro
     * @param resumen un resumen del libro
     * @param fecha_publicacion la fecha de publicación del libro
     * @param idioma el idioma del libro
     * @param edicion la edición del libro
     */
    public Libro(Integer id, String isbn, String nombre, String editorial, String autor, String resumen, Date fecha_publicacion, String idioma, int edicion) {
        this.id = id;
        this.isbn = isbn;
        this.nombre = nombre;
        this.editorial = editorial;
        this.autor = autor;
        this.resumen = resumen;
        this.fecha_publicacion = fecha_publicacion;
        this.idioma = idioma;
        this.edicion = edicion;
    }
    
    /**
     * Constructor con todos los datos del libro, incluyendo marcas de tiempo.
     * @param id el ID del libro
     * @param isbn el ISBN del libro
     * @param nombre el título del libro
     * @param editorial la editorial del libro
     * @param autor el autor del libro
     * @param resumen un resumen del libro
     * @param fecha_publicacion la fecha de publicación del libro
     * @param idioma el idioma del libro
     * @param edicion la edición del libro
     * @param created_at la marca de tiempo de creación
     * @param updated_at la marca de tiempo de la última actualización
     */
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

    /**
     * Obtiene el ID del libro.
     * @return el ID
     */
    public Integer getId() {
        return id;
    }   

    /**
     * Obtiene el ISBN del libro.
     * @return el ISBN
     */
    public String getIsbn(){
        return isbn;
    }

    /**
     * Obtiene el título del libro.
     * @return el título
     */
    public String getNombre(){
        return nombre;
    }

    /**
     * Obtiene la editorial del libro.
     * @return la editorial
     */
    public String getEditorial(){
        return editorial;
    }

    /**
     * Obtiene el autor del libro.
     * @return el autor
     */
    public String getAutor(){
        return autor;
    }

    /**
     * Obtiene el resumen del libro.
     * @return el resumen
     */
    public String getResumen(){
        return resumen;
    }

    /**
     * Obtiene la fecha de publicación del libro.
     * @return la fecha de publicación
     */
    public Date getFechaPublicacion(){
        return fecha_publicacion;
    }

    /**
     * Obtiene el idioma del libro.
     * @return el idioma
     */
    public String getIdioma(){
        return idioma;
    }

    /**
     * Obtiene la edición del libro.
     * @return la edición
     */
    public int getEdicion(){
        return edicion;
    }

    /**
     * Obtiene la marca de tiempo de creación.
     * @return la marca de tiempo de creación
     */
    public Date getCreated_at(){
        return created_at;
    }

    /**
     * Obtiene la marca de tiempo de la última actualización.
     * @return la marca de tiempo de la última actualización
     */
    public Date getUpdated_at(){
        return updated_at;
    }


    /**
     * Establece el ID del libro.
     * @param id el ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Establece el ISBN del libro.
     * @param isbn el ISBN
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Establece el título del libro.
     * @param nombre el título
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Establece la editorial del libro.
     * @param editorial la editorial
     */
    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    /**
     * Establece el autor del libro.
     * @param autor el autor
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * Establece el resumen del libro.
     * @param resumen el resumen
     */
    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    /**
     * Establece la fecha de publicación del libro.
     * @param fecha_publicacion la fecha de publicación
     */
    public void setFechaPublicacion(Date fecha_publicacion) {
        this.fecha_publicacion = fecha_publicacion;
    }

    /**
     * Establece el idioma del libro.
     * @param idioma el idioma
     */
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    /**
     * Establece la edición del libro.
     * @param edicion la edición
     */
    public void setEdicion(int edicion) {
        this.edicion = edicion;
    }

    /**
     * Establece la marca de tiempo de creación.
     * @param created_at la marca de tiempo de creación
     */
    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    /**
     * Establece la marca de tiempo de la última actualización.
     * @param updated_at la marca de tiempo de la última actualización
     */
    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

}
