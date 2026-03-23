package services;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import dto.UserListDTO;
import entities.Usuario;
import interfaces.Services.UserServiceInterface;
import interfaces.dao.UserDAOInterface;
import interfaces.utils.StringUtilsInterface;

/**
 * Clase que implementa la interfaz UserServiceInterface y se encarga de la lógica de negocio para los usuarios.
 */
@ApplicationScoped
public class UserService implements UserServiceInterface{

    /**
     * Inyección de dependencia para el acceso a datos de usuarios.
     */
    private UserDAOInterface dao;
    /**
     * Inyección de dependencia para utilidades de cadenas.
     */
    private StringUtilsInterface utils;


    /**
     * Constructor por defecto.
     */
    public UserService(){}

    /**
     * Constructor que recibe el DAO de usuarios y las utilidades de cadenas.
     *
     * @param dao   el DAO de usuarios
     * @param utils las utilidades de cadenas
     */
    @Inject
    public UserService(
        UserDAOInterface dao,
        StringUtilsInterface utils
    ){
        this.dao = dao;
        this.utils = utils;
    }

    /**
     * Obtiene una lista paginada de usuarios.
     *
     * @param desde el número de registro desde el que se empieza a listar
     * @param filas el número de filas a devolver
     * @return un objeto UserListDTO con la lista de usuarios y la información de paginación
     */
    @Override
    public UserListDTO list(Integer desde, Integer filas){
        return this.dao.list(desde, filas);
    }

    /**
     * Obtiene una lista paginada de usuarios que coinciden con un criterio de búsqueda.
     *
     * @param desde  el número de registro desde el que se empieza a listar
     * @param filas  el número de filas a devolver
     * @param search el criterio de búsqueda
     * @return un objeto UserListDTO con la lista de usuarios y la información de paginación
     */
    @Override
    public UserListDTO list(Integer desde, Integer filas, String search){
        return this.dao.list(desde, filas, search);
    }

    /**
     * Obtiene un usuario por su ID.
     *
     * @param id el ID del usuario
     * @return el objeto Usuario si se encuentra, de lo contrario null
     */
    @Override
    public Usuario getById(Integer id){
        Usuario user = this.dao.getById(id);
        user.setPassword(null);
        return user;
        
    }
    
    /**
     * Guarda un usuario en la base de datos. Si el usuario no tiene ID, se crea un nuevo usuario. Si tiene ID, se actualiza el usuario existente.
     *
     * @param user el objeto Usuario a guardar
     * @return el objeto Usuario guardado
     * @throws Exception si el email ya está en uso
     */
    @Override
    public Usuario save(Usuario user) throws Exception {
        Usuario userFound = null;
        String pwd = user.getPassword();
        String passwordHashed = pwd != null ? utils.hashingPassword(pwd) : null;
        user.setPassword(passwordHashed);
        
        if(user.getId() != null){
            userFound = this.dao.getById(user.getId());
        }

        if(user.getEmail() != null && this.emailExists(user)){
            System.out.println("El email ya está en uso");
            throw new Exception("El email ya está en uso");
        }

        if(userFound == null){
            return this.dao.create(user);
        }
        
        if(user.getPassword() == null || user.getPassword().isEmpty()){
            user.setPassword(userFound.getPassword());
        }        
        return this.dao.update(user);
    }

    /**
     * Elimina un usuario de la base de datos.
     *
     * @param id el ID del usuario a eliminar
     * @return true si se eliminó correctamente, de lo contrario false
     */
    @Override
    public boolean delete(Integer id){
        Usuario user = this.getById(id);
        if(user == null){
            return false;
        }
        return this.dao.delete(user);
    }


    /**
     * Comprueba si un email ya existe en la base de datos para un usuario diferente.
     *
     * @param user el objeto Usuario a comprobar
     * @return true si el email ya existe, de lo contrario false
     */
    private boolean emailExists(Usuario user){
        Usuario userFound = this.dao.findByEmail(user.getEmail());
        return userFound != null && !userFound.getId().equals(user.getId());
    }

    
    
    

}
