package interfaces.Services;

import java.sql.Date;

import dto.LoanListDTO;
import entities.Prestamo;

/**
 * Interfaz para el Servicio de Préstamos.
 */
public interface LoanServiceInterface {

    /**
     * Lista los préstamos con paginación.
     * 
     * @param desde el inicio de la paginación
     * @param filas el número de filas a devolver
     * @return un objeto PrestamoDTO con la lista de préstamos y la información de
     *         paginación
     */
    public LoanListDTO list(Integer desde, Integer filas);

    /**
     * Lista los préstamos con paginación y búsqueda.
     * 
     * @param desde  el inicio de la paginación
     * @param filas  el número de filas a devolver
     * @param search el término de búsqueda
     * @return un objeto PrestamoDTO con la lista de préstamos y la información de
     *         paginación
     */
    public LoanListDTO list(Integer desde, Integer filas, String search);

    /**
     * Obtiene un préstamo por su ID.
     * 
     * @param id el ID del préstamo
     * @return el préstamo encontrado o null si no se encuentra
     */
    public Prestamo getById(Integer id);

    /**
     * Crea un nuevo préstamo.
     * 
     * @param userId          el ID del usuario
     * @param bookId          el ID del libro
     * @param fechaPrestamo   la fecha del préstamo
     * @param fechaDevolucion la fecha de devolución
     * @return el préstamo creado
     * @throws Exception si ocurre un error durante la operación
     */
    public Prestamo create(Integer userId, Integer bookId, Date fechaPrestamo, Date fechaDevolucion) throws Exception;

    /**
     * Actualiza un préstamo existente.
     * 
     * @param id              el ID del préstamo
     * @param userId          el ID del usuario
     * @param bookId          el ID del libro
     * @param fechaPrestamo   la fecha del préstamo
     * @param fechaDevolucion la fecha de devolución
     * @param fechaRetorno    la fecha de retorno
     * @return el préstamo actualizado
     * @throws Exception si ocurre un error durante la operación
     */
    public Prestamo update(Integer id, Integer userId, Integer bookId, Date fechaPrestamo, Date fechaDevolucion,
            Date fechaRetorno) throws Exception;

    /**
     * Elimina un préstamo por su ID.
     * 
     * @param id el ID del préstamo a eliminar
     * @return true si se eliminó correctamente, false en caso contrario
     */
    public boolean delete(Integer id);

}
