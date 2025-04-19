package co.edu.javeriana.caravana_medieval_cruz_f_ss.dto;

public class ServicioDTO {
    private Long id;
    private String tipoServicio;
    private double precioServicio;
    
    public ServicioDTO() {
    }

    public ServicioDTO(Long id, String tipoServicio, double precioServicio) {
        this.id = id;
        this.tipoServicio = tipoServicio;
        this.precioServicio = precioServicio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public double getPrecioServicio() {
        return precioServicio;
    }

    public void setPrecioServicio(double precioServicio) {
        this.precioServicio = precioServicio;
    }

    
}
