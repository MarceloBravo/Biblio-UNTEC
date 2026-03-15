package interfaces.dao;

import entities.Usuario;

public interface LoginDAOInterface {
    
    public Usuario login(String email, String password);
}
