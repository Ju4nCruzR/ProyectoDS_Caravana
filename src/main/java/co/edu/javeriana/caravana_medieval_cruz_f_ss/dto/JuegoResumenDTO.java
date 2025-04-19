package co.edu.javeriana.caravana_medieval_cruz_f_ss.dto;

public class JuegoResumenDTO {
    private Long id;
    private String descripcion;
    
    public JuegoResumenDTO() {
    }

    public JuegoResumenDTO(Long id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
}
