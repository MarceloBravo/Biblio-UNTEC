package interfaces.dao;

import dto.UserListDTO;
import entities.Usuario;

/**
 * Interfaz para el Acceso a Datos de Usuarios (DAO).
 */
public interface UserDAOInterface {
    
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
     * Crea un nuevo usuario.
     * @param user el usuario a crear
     * @return el usuario creado
     */
    public Usuario create(Usuario user);

    /**
     * Actualiza un usuario existente.
     * @param user el usuario a actualizar
     * @return el usuario actualizado
     */
    public Usuario update(Usuario user);

    /**
     * Elimina un usuario.
     * @param user el usuario a eliminar
     * @return true si se eliminó correctamente, false en caso contrario
     */
    public boolean delete(Usuario user);

    /**
     * Busca un usuario por su email.
     * @param email el email del usuario a buscar
     * @return el usuario encontrado o null si no se encuentra
     */
    public Usuario findByEmail(String email);
}
