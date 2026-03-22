package services;

import javax.enterprise.context.ApplicationScoped;

import dto.BookListDTO;
import entities.Libro;
import interfaces.book.BookDAOInterface;
import interfaces.user.BookServiceInterface;

@ApplicationScoped
public class BookService implements BookServiceInterface {
    private BookDAOInterface dao;

    public BookService(){}

    public BookService(
        BookDAOInterface dao
    ){
        this.dao = dao;
    }

    
    @Override    
    public BookListDTO list(Integer desde, Integer filas){
        return this.dao.list(desde, filas);
    }
    @Override
    public BookListDTO list(Integer desde, Integer filas, String search){
        return this.dao.list(desde, filas, search);

    }
    @Override
    public Libro getById(Integer id){
        return this.dao.getById(id);
    }
    @Override
    public Libro save(Libro book) throws Exception{
        Libro bookFound = null;
        if(book.getId() != null){
            bookFound = this.dao.getById(book.getId());
        }

        if(bookFound == null){
            return this.dao.create(book);
        }  
        return this.dao.update(book);
    }
    @Override
    public boolean delete(Integer id){
        Libro book = this.getById(id);
        if(book == null){
            return false;
        }
        return this.dao.delete(book);
    }

}
