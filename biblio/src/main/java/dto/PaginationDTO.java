package dto;

public class PaginationDTO {
    private int cantEnlaces = 5;
    private int inicio = 1;
    private int fin = 1;
    private Integer totReg;
    private Integer totPag;
    private int paginaActual = 1;
    private Integer filas = 10;
    private String url = "";
    

    public PaginationDTO() {
    }

    public Integer getTotReg() {
        return totReg;
    }

    public void setTotReg(Integer totReg) {
        this.totReg = totReg;
    }

    public Integer getTotPag() {
        return totPag;
    }

    public void setTotPag(Integer totPag) {
        this.totPag = totPag;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCantEnlaces(){
        return this.cantEnlaces;
    }

    
    public Integer getFilas(){
        return this.filas;
    }


    public int getInicio(){
        return this.inicio;
    }

    public int getFin(){
        return this.fin;
    }

    public int getPaginaActual(){
        return this.paginaActual;
    }
    
    public void CalcularPaginas(Integer desde, Integer filas){
        int radio = 2;
        this.filas = filas;
        totPag = (totReg / filas) % 2 == 0 ? (totReg / filas) + 1 : (totReg / filas) + 2;
        this.paginaActual = (desde / filas) + 1;
        this.inicio = Math.max(1, paginaActual - radio);
        this.fin = Math.min(totPag, paginaActual + radio);

        // Ajuste para mostrar siempre 5 si hay suficientes páginas
        if (fin - inicio + 1 < cantEnlaces) {
            if (paginaActual <= radio) {
                // Estamos cerca del comienzo
                fin = Math.min(totPag, cantEnlaces);
            } else {
                // Estamos cerca del final
                inicio = Math.max(1, totPag - cantEnlaces + 1);
            }
        }

    }




}
