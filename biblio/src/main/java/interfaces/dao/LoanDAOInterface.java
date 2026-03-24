package interfaces.dao;

import dto.PrestamoDTO;
import entities.Prestamo;

/**
 * Interfaz para el Acceso a Datos de Préstamos (DAO).
 */
public interface LoanDAOInterface {
    
    /**
     * Lista los préstamos con paginación.
     * @param desde el inicio de la paginación
     * @param filas el número de filas a devolver
     * @return un objeto PrestamoDTO con la lista de préstamos y la información de paginación
     */
    public PrestamoDTO list(Integer desde, Integer filas);

    /**
     * Lista los préstamos con paginación y búsqueda.
     * @param desde el inicio de la paginación
     * @param filas el número de filas a devolver
     * @param search el término de búsqueda
     * @return un objeto PrestamoDTO con la lista de préstamos y la información de paginación
     */
    public PrestamoDTO list(Integer desde, Integer filas, String search);

    /**
     * Obtiene un préstamo por su ID.
     * @param id el ID del préstamo
     * @return el préstamo encontrado o null si no se encuentra
     */
    public Prestamo getById(Integer id);

    /**
     * Crea un nuevo préstamo.
     * @param prestamo el préstamo a crear
     * @return el préstamo creado
     */
    public Prestamo create(Prestamo prestamo);

    /**
     * Actualiza un préstamo existente.
     * @param prestamo el préstamo a actualizar
     * @return el préstamo actualizado
     */
    public Prestamo update(Prestamo prestamo);

    /**
     * Elimina un préstamo por su ID.
     * @param id el ID del préstamo a eliminar
     * @return true si se eliminó correctamente, false en caso contrario
     */
    public boolean delete(Integer id);

}
