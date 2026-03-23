package interfaces.dao;

import dto.PrestamoDTO;
import entities.Prestamo;

public interface LoanDAOInterface {
    
    public PrestamoDTO list(Integer desde, Integer filas);

    public PrestamoDTO list(Integer desde, Integer filas, String search);

    public Prestamo getById(Integer id);

    public Prestamo create(Prestamo prestamo);

    public Prestamo update(Prestamo prestamo);

    public boolean delete(Integer id);

}
