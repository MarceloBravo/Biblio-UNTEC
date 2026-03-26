package services;

import java.sql.Date;

import javax.inject.Inject;

import dto.LoanListDTO;
import entities.Libro;
import entities.Prestamo;
import entities.Usuario;
import interfaces.Services.LoanServiceInterface;
import interfaces.dao.LoanDAOInterface;

/**
 * Servicio para la gestión de préstamos.
 */
public class LoanService implements LoanServiceInterface {
    private LoanDAOInterface dao;
    private UserService userService;
    private BookService bookService;

    /**
     * Constructor por defecto.
     */
    public LoanService() {
    }

    /**
     * Constructor con inyección de dependencias.
     * 
     * @param dao         el DAO de préstamos
     * @param userService el servicio de usuarios
     * @param bookService el servicio de libros
     */
    @Inject
    public LoanService(
            LoanDAOInterface dao,
            UserService userService,
            BookService bookService) {
        this.dao = dao;
        this.userService = userService;
        this.bookService = bookService;
    }

    /**
     * Obtiene una lista paginada de prestamos.
     * 
     * @param desde el índice del primer elemento a devolver
     * @param filas el número de elementos a devolver
     * @return el DTO de la lista de prestamos
     */
    @Override
    public LoanListDTO list(Integer desde, Integer filas) {
        return this.dao.list(desde, filas);
    }

    /**
     * Obtiene una lista paginada de prestamos que coinciden con un criterio de
     * búsqueda.
     * 
     * @param desde  el índice del primer elemento a devolver
     * @param filas  el número de elementos a devolver
     * @param search el criterio de búsqueda
     * @return el DTO de la lista de prestamos
     */
    @Override
    public LoanListDTO list(Integer desde, Integer filas, String search) {
        return this.dao.list(desde, filas, search);
    }

    /**
     * Obtiene un prestamo por su ID.
     * 
     * @param id el ID del prestamo
     * @return el prestamo correspondiente al ID proporcionado
     */
    @Override
    public Prestamo getById(Integer id) {
        return this.dao.getById(id);
    }

    /**
     * Crea un nuevo prestamo.
     * 
     * @param userId          el ID del usuario
     * @param bookId          el ID del libro
     * @param fechaPrestamo   la fecha de préstamo
     * @param fechaDevolucion la fecha de devolución
     * @return el prestamo creado
     */
    @Override
    public Prestamo create(Integer userId, Integer bookId, Date fechaPrestamo, Date fechaDevolucion) throws Exception {
        Libro book = this.bookService.getById(bookId);
        if (book == null) {
            throw new RuntimeException("El libro no existe");
        }
        Usuario user = this.userService.getById(userId);
        if (user == null) {
            throw new RuntimeException("El usuario no existe");
        }
        Prestamo prestamo = new Prestamo();

        prestamo.setLibro(book);
        prestamo.setUsuario(user);
        prestamo.setFechaPrestamo(fechaPrestamo);
        prestamo.setFechaDevolucion(fechaDevolucion);

        return this.dao.create(prestamo);
    }

    /**
     * Actualiza un prestamo existente.
     * 
     * @param id              el ID del prestamo
     * @param userId          el ID del usuario
     * @param bookId          el ID del libro
     * @param fechaPrestamo   la fecha de préstamo
     * @param fechaDevolucion la fecha de devolución
     * @param fechaRetorno    la fecha de retorno
     * @return el prestamo actualizado
     */
    @Override
    public Prestamo update(Integer id, Integer userId, Integer bookId, Date fechaPrestamo, Date fechaDevolucion,
            Date fechaRetorno) throws Exception {
        Prestamo prestamoFound = this.dao.getById(id);
        if (prestamoFound == null) {
            throw new RuntimeException("El prestamo no existe");
        }
        Libro book = this.bookService.getById(bookId);
        if (book == null) {
            throw new RuntimeException("El libro no existe");
        }
        Usuario user = this.userService.getById(userId);
        if (user == null) {
            throw new RuntimeException("El usuario no existe");
        }
        Prestamo prestamo = new Prestamo();
        prestamo.setId(id);
        prestamo.setLibro(book);
        prestamo.setUsuario(user);
        prestamo.setFechaPrestamo(fechaPrestamo);
        prestamo.setFechaDevolucion(fechaDevolucion);
        prestamo.setFechaRetorno(fechaRetorno);
        return this.dao.update(prestamo);
    }

    /**
     * Elimina un prestamo por su ID.
     * 
     * @param id el ID del prestamo a eliminar
     * @return true si se eliminó correctamente, false en caso contrario
     */
    @Override
    public boolean delete(Integer id) {
        return this.dao.delete(id);
    }

}
