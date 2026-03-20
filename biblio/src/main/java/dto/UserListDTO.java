package dto;

import java.util.ArrayList;
import java.util.List;

import entities.Usuario;

public class UserListDTO {
    private List<Usuario> data = new ArrayList<>();
    private PaginationDTO pagination = new PaginationDTO();

    public UserListDTO() {
    }

    public UserListDTO(List<Usuario> data) {
        this.data = data;
    }
    
    public UserListDTO(List<Usuario> data, PaginationDTO pagination) {
        this.data = data;
        this.pagination = pagination;
    }

    public List<Usuario> getData() {
        return data;
    }

    public void setData(List<Usuario> data) {
        this.data = data;
    }

    public PaginationDTO getPagination() {
        return pagination;
    }

    public void setPagination(PaginationDTO pagination) {
        this.pagination = pagination;
    }
   
}
