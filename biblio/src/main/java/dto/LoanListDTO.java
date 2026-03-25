package dto;

import java.util.ArrayList;
import java.util.List;

import entities.Prestamo;

/**
 * Objeto de Transferencia de Datos para Préstamos.
 */
public class LoanListDTO {
    
    private List<Prestamo> data = new ArrayList<>();
    private PaginationDTO pagination = new PaginationDTO();

    /**
     * Constructor por defecto.
     */
    public LoanListDTO() {
    }

    /**
     * Constructor con datos.
     * @param data los datos
     */
    public LoanListDTO(List<Prestamo> data) {
        this.data = data;
    }

    /**
     * Constructor con datos y paginación.
     * @param data los datos
     * @param pagination la paginación
     */
    public LoanListDTO(List<Prestamo> data, PaginationDTO pagination) {
        this.data = data;
        this.pagination = pagination;
    }

    /**
     * Obtiene los datos.
     * @return los datos
     */
    public List<Prestamo> getData() {
        return data;
    }

    /**
     * Establece los datos.
     * @param data los datos
     */
    public void setData(List<Prestamo> data) {
        this.data = data;
    }

    /**
     * Obtiene la paginación.
     * @return la paginación
     */
    public PaginationDTO getPagination() {
        return this.pagination;
    }

    /**
     * Establece la paginación.
     * @param pagination la paginación
     */
    public void setPagination(PaginationDTO pagination) {
        this.pagination = pagination;
    }

}
