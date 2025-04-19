package co.edu.javeriana.caravana_medieval_cruz_f_ss.dto;

public class CiudadServicioDTO {
    private Long id;
    private String nombreServicio;
    private double precio;
    private boolean adquirido;

    public CiudadServicioDTO() {
    }

    public CiudadServicioDTO(Long id, String nombreServicio, double precio, boolean adquirido) {
        this.id = id;
        this.nombreServicio = nombreServicio;
        this.precio = precio;
        this.adquirido = adquirido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isAdquirido() {
        return adquirido;
    }

    public void setAdquirido(boolean adquirido) {
        this.adquirido = adquirido;
    }


}
