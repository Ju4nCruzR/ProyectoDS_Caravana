package co.edu.javeriana.caravana_medieval_cruz_f_ss.dto;

public class CiudadServicioDTO {
    private Long id;
    private Long servicioId;
    private String nombreServicio;
    private double precio;
    private boolean adquirido;
    private String nombreCiudad;


    public CiudadServicioDTO() {
    }

    public CiudadServicioDTO(Long id, Long servicioId, String nombreServicio, double precio, boolean adquirido,
            String nombreCiudad) {
        this.id = id;
        this.servicioId = servicioId;
        this.nombreServicio = nombreServicio;
        this.precio = precio;
        this.adquirido = adquirido;
        this.nombreCiudad = nombreCiudad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getServicioId() {
        return servicioId;
    }

    public void setServicioId(Long servicioId) {
        this.servicioId = servicioId;
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

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }
    
}
