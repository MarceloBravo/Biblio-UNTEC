package utils;

import javax.enterprise.context.ApplicationScoped;

import org.mindrot.jbcrypt.BCrypt;

import interfaces.utils.StringUtilsInterface;


@ApplicationScoped
public class StringUtils implements StringUtilsInterface{

    public StringUtils(){}

    public String hashingPassword(String password) {
        String passwordHashed = BCrypt.hashpw(password, BCrypt.gensalt());
        return passwordHashed;
    }
}