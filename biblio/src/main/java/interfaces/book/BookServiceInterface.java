package interfaces.book;

import dto.BookListDTO;
import entities.Libro;

public interface BookServiceInterface {
    
    public BookListDTO list(Integer desde, Integer filas);

    public BookListDTO list(Integer desde, Integer filas, String search);

    public Libro getById(Integer id);

    public Libro save(Libro book) throws Exception;

    public boolean delete(Integer id);

    
}
