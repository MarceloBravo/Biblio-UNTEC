package interfaces.Services;

import java.sql.Date;

import dto.PrestamoDTO;
import entities.Prestamo;

public interface LoanServiceInterface {
    
    public PrestamoDTO list(Integer desde, Integer filas);

    public PrestamoDTO list(Integer desde, Integer filas, String search);

    public Prestamo getById(Integer id);

    public Prestamo create(Integer userId, Integer bookId, Date fechaPrestamo, Date fechaDevolucion) throws Exception;

    public Prestamo update(Prestamo prestamo) throws Exception;

    public boolean delete(Integer id);
    
}
