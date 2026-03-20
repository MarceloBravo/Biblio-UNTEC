package interfaces.users;

import java.util.List;

import entities.Usuario;

public interface UserServiceInterface {
    
    public List<Usuario> list(Integer desde, Integer filas);

    public List<Usuario> list(Integer desde, Integer filas, String search);

    public Usuario getById(Integer id);

    public Usuario save(Usuario user) throws Exception;

    public boolean delete(Integer id);

    
}
