package entities;

/**
 * Clase que representa a un usuario en el sistema.
 */
public class Usuario {
    /**
     * Identificador único del usuario.
     */
    private Integer id;
    /**
     * Nombre del usuario.
     */
    private String nombre;
    /**
     * Apellidos del usuario.
     */
    private String apellidos;
    /**
     * Email del usuario.
     */
    private String email;
    /**
     * Contraseña del usuario.
     */
    private String password;

    /**
     * Constructor por defecto.
     */
    public Usuario() {
    }

    /**
     * Constructor con parámetros para crear un usuario sin ID.
     *
     * @param nombre    el nombre del usuario
     * @param apellidos los apellidos del usuario
     * @param email     el email del usuario
     * @param password  la contraseña del usuario
     */
    public Usuario(String nombre, String apellidos, String email, String password) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.password = password;
    }
    
    /**
     * Constructor con parámetros para crear un usuario con ID pero sin contraseña.
     *
     * @param id        el ID del usuario
     * @param nombre    el nombre del usuario
     * @param apellidos los apellidos del usuario
     * @param email     el email del usuario
     */
    public Usuario(Integer id, String nombre, String apellidos, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.password = null;
    }
    
    /**
     * Constructor con todos los parámetros.
     *
     * @param id        el ID del usuario
     * @param nombre    el nombre del usuario
     * @param apellidos los apellidos del usuario
     * @param email     el email del usuario
     * @param password  la contraseña del usuario
     */
    public Usuario(Integer id, String nombre, String apellidos, String email, String password) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.password = password;
    }

    // Setters    
    /**
     * Establece el ID del usuario.
     *
     * @param id el ID del usuario
     */
    public void setId(Integer id){
        this.id = id;
    }

    /**
     * Establece el nombre del usuario.
     *
     * @param nombre el nombre del usuario
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Establece los apellidos del usuario.
     *
     * @param apellidos los apellidos del usuario
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Establece el email del usuario.
     *
     * @param email el email del usuario
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * Establece la contraseña del usuario.
     *
     * @param password la contraseña del usuario
     */
    public void setPassword(String password) {
        this.password = password;
    }

    // Getters
    /**
     * Obtiene el ID del usuario.
     *
     * @return el ID del usuario
     */
    public Integer getId() {
        return id;
    }
    
    /**
     * Obtiene el nombre del usuario.
     *
     * @return el nombre del usuario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene los apellidos del usuario.
     *
     * @return los apellidos del usuario
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Obtiene el email del usuario.
     *
     * @return el email del usuario
     */
    public String getEmail() {
        return email;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return la contraseña del usuario
     */
    public String getPassword() {
        return password;
    }

}
