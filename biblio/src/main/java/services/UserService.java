package services;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import entities.Usuario;
import interfaces.dao.UserDAOInterface;
import interfaces.users.UserServiceInterface;
import interfaces.utils.StringUtilsInterface;

@ApplicationScoped
public class UserService implements UserServiceInterface{
    private UserDAOInterface dao;
    private StringUtilsInterface utils;


    public UserService(){}

    @Inject
    public UserService(
        UserDAOInterface dao,
        StringUtilsInterface utils
    ){
        this.dao = dao;
        this.utils = utils;
    }

    @Override
    public List<Usuario> list(Integer desde, Integer filas){
        return this.dao.list(desde, filas);
    }

    @Override
    public List<Usuario> list(Integer desde, Integer filas, String search){
        return this.dao.list(desde, filas, search);
    }

    @Override
    public Usuario getById(Integer id){
        Usuario user = this.dao.getById(id);
        user.setPassword(null);
        return user;
        
    }
    
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

    @Override
    public boolean delete(Integer id){
        Usuario user = this.getById(id);
        if(user == null){
            return false;
        }
        return this.dao.delete(user);
    }


    private boolean emailExists(Usuario user){
        Usuario userFound = this.dao.findByEmail(user.getEmail());
        return userFound != null && !userFound.getId().equals(user.getId());
    }

    
    
    

}
