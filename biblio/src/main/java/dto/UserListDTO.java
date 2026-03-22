package dto;

import java.util.ArrayList;
import java.util.List;

import entities.Usuario;

/**
 * Clase que representa un objeto de transferencia de datos para una lista de usuarios.
 */
public class UserListDTO {
    /**
     * Lista de usuarios.
     */
    private List<Usuario> data = new ArrayList<>();
    /**
     * Objeto de paginación.
     */
    private PaginationDTO pagination = new PaginationDTO();

    /**
     * Constructor por defecto.
     */
    public UserListDTO() {
    }

    /**
     * Constructor que recibe la lista de usuarios.
     *
     * @param data la lista de usuarios
     */
    public UserListDTO(List<Usuario> data) {
        this.data = data;
    }
    
    /**
     * Constructor que recibe la lista de usuarios y el objeto de paginación.
     *
     * @param data       la lista de usuarios
     * @param pagination el objeto de paginación
     */
    public UserListDTO(List<Usuario> data, PaginationDTO pagination) {
        this.data = data;
        this.pagination = pagination;
    }

    /**
     * Obtiene la lista de usuarios.
     *
     * @return la lista de usuarios
     */
    public List<Usuario> getData() {
        return data;
    }

    /**
     * Establece la lista de usuarios.
     *
     * @param data la lista de usuarios
     */
    public void setData(List<Usuario> data) {
        this.data = data;
    }

    /**
     * Obtiene el objeto de paginación.
     *
     * @return el objeto de paginación
     */
    public PaginationDTO getPagination() {
        return pagination;
    }

    /**
     * Establece el objeto de paginación.
     *
     * @param pagination el objeto de paginación
     */
    public void setPagination(PaginationDTO pagination) {
        this.pagination = pagination;
    }
   
}
