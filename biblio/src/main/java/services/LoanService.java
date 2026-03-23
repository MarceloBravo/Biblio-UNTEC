package services;

import java.sql.Date;

import javax.inject.Inject;

import dto.PrestamoDTO;
import entities.Libro;
import entities.Prestamo;
import entities.Usuario;
import interfaces.Services.LoanServiceInterface;
import interfaces.dao.LoanDAOInterface;

public class LoanService implements LoanServiceInterface {
    private LoanDAOInterface dao;
    private UserService userService;
    private BookService bookService;


    public LoanService(){}

    @Inject
    public LoanService(
        LoanDAOInterface dao,
        UserService userService,
        BookService bookService
    ){
        this.dao = dao;
        this.userService = userService;
        this.bookService = bookService;
    }


    @Override
    public PrestamoDTO list(Integer desde, Integer filas) {
        return this.dao.list(desde, filas);
    }

    @Override
    public PrestamoDTO list(Integer desde, Integer filas, String search) {
        return this.dao.list(desde, filas, search);
    }

    @Override
    public Prestamo getById(Integer id) {
        return this.dao.getById(id);
    }

    @Override
    public Prestamo create(Integer userId, Integer bookId, Date fechaPrestamo, Date fechaDevolucion) throws Exception {
        Libro book = this.bookService.getById(bookId);
        if(book == null){
            throw new RuntimeException("El libro no existe");
        }
        Usuario user = this.userService.getById(userId);
        if(user == null){
            throw new RuntimeException("El usuario no existe");
        }
        Prestamo prestamo = new Prestamo();
        prestamo.setLibro(book);
        prestamo.setUsuario(user);
        prestamo.setFechaPrestamo(fechaPrestamo);
        prestamo.setFechaDevolucion(fechaDevolucion);
        return this.dao.create(prestamo);
    }


    @Override
    public Prestamo update(Prestamo prestamo) throws Exception {
        Prestamo prestamoFound = this.dao.getById(prestamo.getId());
        if(prestamoFound == null){
            throw new RuntimeException("El prestamo no existe");
        }
        Libro book = this.bookService.getById(prestamo.getLibro().getId());
        if(book == null){
            throw new RuntimeException("El libro no existe");
        }
        Usuario user = this.userService.getById(prestamo.getUsuario().getId());
        if(user == null){
            throw new RuntimeException("El usuario no existe");
        }
        return this.dao.update(prestamo);
    }

    @Override
    public boolean delete(Integer id) {
        return this.dao.delete(id);
    }
    
}
