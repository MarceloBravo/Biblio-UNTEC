package interfaces.dao;

import dto.BookListDTO;
import entities.Libro;

public interface BookDAOInterface {
    
    public BookListDTO list(Integer desde, Integer filas);
    
    public BookListDTO list(Integer desde, Integer filas, String search);

    public Libro getById(Integer id);

    public Libro create(Libro user);

    public Libro update(Libro user);

    public boolean delete(Libro user);
}
