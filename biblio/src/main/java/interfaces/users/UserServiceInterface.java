package interfaces.users;

import dto.UserListDTO;
import entities.Usuario;

public interface UserServiceInterface {
    
    public UserListDTO list(Integer desde, Integer filas);

    public UserListDTO list(Integer desde, Integer filas, String search);

    public Usuario getById(Integer id);

    public Usuario save(Usuario user) throws Exception;

    public boolean delete(Integer id);

    
}
