package interfaces.dao;

import dto.UserListDTO;
import entities.Usuario;

public interface UserDAOInterface {
    
    public UserListDTO list(Integer desde, Integer filas);
    
    public UserListDTO list(Integer desde, Integer filas, String search);

    public Usuario getById(Integer id);

    public Usuario create(Usuario user);

    public Usuario update(Usuario user);

    public boolean delete(Usuario user);

    public Usuario findByEmail(String email);
}
