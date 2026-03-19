package interfaces.dao;

import java.util.List;

import entities.Usuario;

public interface UserDAOInterface {
    
    public List<Usuario> list();

    public Usuario find(Integer id);

    public Usuario create(Usuario user);

    public void update(Usuario user);

    public void delete(Usuario user);

}
