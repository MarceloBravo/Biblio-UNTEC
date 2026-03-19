package services;

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
    public Usuario save(Usuario user) {
        String pwd = user.getPassword();
        String passwordHashed = utils.hashingPassword(pwd);
        user.setPassword(passwordHashed);
        Usuario result = this.dao.create(user);
        return result;
    }
    

}
