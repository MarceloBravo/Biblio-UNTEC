package interfaces.book;

import dto.BookListDTO;
import entities.Libro;

public interface BookDAOInterface {
    public BookListDTO list(Integer desde, Integer filas);
    
    public BookListDTO list(Integer desde, Integer filas, String search);

    public Libro getById(Integer id);

    public Libro create(Libro book);

    public Libro update(Libro book);

    public boolean delete(Libro book);

}
