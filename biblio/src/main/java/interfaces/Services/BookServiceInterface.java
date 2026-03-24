package interfaces.Services;

import dto.BookListDTO;
import entities.Libro;

/**
 * Interfaz para el Servicio de Libros.
 */
public interface BookServiceInterface {
    
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
     * Guarda un libro (crea o actualiza).
     * @param book el libro a guardar
     * @return el libro guardado
     * @throws Exception si ocurre un error durante la operación
     */
    public Libro save(Libro book) throws Exception;

    /**
     * Elimina un libro por su ID.
     * @param id el ID del libro a eliminar
     * @return true si se eliminó correctamente, false en caso contrario
     */
    public boolean delete(Integer id);

    
}
