package interfaces.dao;

import dto.BookListDTO;
import entities.Libro;

/**
 * Interfaz para el Acceso a Datos de Libros (DAO).
 */
public interface BookDAOInterface {
    
    /**
     * Lista los libros con paginación.
     * @param desde el inicio de la paginación
     * @param filas el número de filas a devolver
     * @return un objeto BookListDTO con la lista de libros y la información de paginación
     */
    public BookListDTO list(Integer desde, Integer filas);
    
    /**
     * Lista los libros con paginación y búsqueda.
     * @param desde el inicio de la paginación
     * @param filas el número de filas a devolver
     * @param search el término de búsqueda
     * @return un objeto BookListDTO con la lista de libros y la información de paginación
     */
    public BookListDTO list(Integer desde, Integer filas, String search);

    /**
     * Obtiene un libro por su ID.
     * @param id el ID del libro
     * @return el libro encontrado o null si no se encuentra
     */
    public Libro getById(Integer id);

    /**
     * Crea un nuevo libro.
     * @param user el libro a crear
     * @return el libro creado
     */
    public Libro create(Libro user);

    /**
     * Actualiza un libro existente.
     * @param user el libro a actualizar
     * @return el libro actualizado
     */
    public Libro update(Libro user);

    /**
     * Elimina un libro.
     * @param user el libro a eliminar
     * @return true si se eliminó correctamente, false en caso contrario
     */
    public boolean delete(Libro user);
}
