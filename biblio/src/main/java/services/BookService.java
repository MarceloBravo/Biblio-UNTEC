package services;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import dto.BookListDTO;
import entities.Libro;
import interfaces.Services.BookServiceInterface;
import interfaces.dao.BookDAOInterface;

/**
 * Servicio para la gestión de libros.
 */
@ApplicationScoped
public class BookService implements BookServiceInterface {
    private BookDAOInterface dao;

    /**
     * Constructor por defecto.
     */
    public BookService(){}

    /**
     * Constructor con inyección de dependencias.
     * @param dao el DAO de libros
     */
    @Inject
    public BookService(
        BookDAOInterface dao
    ){
        this.dao = dao;
    }

    
    /**
     * Obtiene una lista paginada de libros.
     * @param desde el índice del primer elemento a devolver
     * @param filas el número de elementos a devolver
     * @return el DTO de la lista de libros
     */
    @Override    
    public BookListDTO list(Integer desde, Integer filas){
        return this.dao.list(desde, filas);
    }
    /**
     * Obtiene una lista paginada de libros que coinciden con un criterio de búsqueda.
     * @param desde el índice del primer elemento a devolver
     * @param filas el número de elementos a devolver
     * @param search el criterio de búsqueda
     * @return el DTO de la lista de libros
     */
    @Override
    public BookListDTO list(Integer desde, Integer filas, String search){
        return this.dao.list(desde, filas, search);

    }
    /**
     * Obtiene un libro por su ID.
     * @param id el ID del libro
     * @return el libro correspondiente al ID proporcionado
     */
    @Override
    public Libro getById(Integer id){
        return this.dao.getById(id);
    }
    /**
     * Crea o actualiza un libro.
     * @param book el libro a crear o actualizar
     * @return el libro creado o actualizado
     */
    @Override
    public Libro save(Libro book) throws Exception{
        Libro bookFound = null;
        if(book.getId() != null){
            bookFound = this.dao.getById(book.getId());
        }

        if(bookFound == null){
            return this.dao.create(book);
        }  
        return this.dao.update(book);
    }


    /**
     * Elimina un libro por su ID.
     * @param id el ID del libro a eliminar
     * @return true si se eliminó correctamente
     */
    @Override
    public boolean delete(Integer id){
        Libro book = this.getById(id);
        if(book == null){
            return false;
        }
        return this.dao.delete(book);
    }

}
