package dto;

import java.util.ArrayList;
import java.util.List;

import entities.Prestamo;

public class PrestamoDTO {
    
    private List<Prestamo> data = new ArrayList<>();
    private PaginationDTO pagination = new PaginationDTO();

    public PrestamoDTO() {
    }

    public PrestamoDTO(List<Prestamo> data) {
        this.data = data;
    }

    public PrestamoDTO(List<Prestamo> data, PaginationDTO pagination) {
        this.data = data;
        this.pagination = pagination;
    }

    public List<Prestamo> getData() {
        return data;
    }

    public void setData(List<Prestamo> data) {
        this.data = data;
    }

    public PaginationDTO getPagination() {
        return this.pagination;
    }

    public void setPagination(PaginationDTO pagination) {
        this.pagination = pagination;
    }

}
