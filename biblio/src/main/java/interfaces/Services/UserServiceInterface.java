package interfaces.Services;

import dto.UserListDTO;
import entities.Usuario;

/**
 * Interfaz para el Servicio de Usuarios.
 */
public interface UserServiceInterface {
    
    /**
     * Lista los usuarios con paginación.
     * @param desde el inicio de la paginación
     * @param filas el número de filas a devolver
     * @return un objeto UserListDTO con la lista de usuarios y la información de paginación
     */
    public UserListDTO list(Integer desde, Integer filas);

    /**
     * Lista los usuarios con paginación y búsqueda.
     * @param desde el inicio de la paginación
     * @param filas el número de filas a devolver
     * @param search el término de búsqueda
     * @return un objeto UserListDTO con la lista de usuarios y la información de paginación
     */
    public UserListDTO list(Integer desde, Integer filas, String search);

    /**
     * Obtiene un usuario por su ID.
     * @param id el ID del usuario
     * @return el usuario encontrado o null si no se encuentra
     */
    public Usuario getById(Integer id);

    /**
     * Guarda un usuario (crea o actualiza).
     * @param user el usuario a guardar
     * @return el usuario guardado
     * @throws Exception si ocurre un error durante la operación
     */
    public Usuario save(Usuario user) throws Exception;

    /**
     * Elimina un usuario por su ID.
     * @param id el ID del usuario a eliminar
     * @return true si se eliminó correctamente, false en caso contrario
     */
    public boolean delete(Integer id);

    
}
