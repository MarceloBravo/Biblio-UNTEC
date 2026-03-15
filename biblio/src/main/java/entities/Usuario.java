package entities;

public class Usuario {
    private String nombre;
    private String apellidos;
    private String email;
    private String password;

    public Usuario() {
    }

    public Usuario(String nombre, String apellidos, String email, String password) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.password = password;
    }

    // Setters    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAppelidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getAppelidos() {
        return apellidos;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
