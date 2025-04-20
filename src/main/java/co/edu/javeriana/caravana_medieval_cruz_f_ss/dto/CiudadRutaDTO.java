package co.edu.javeriana.caravana_medieval_cruz_f_ss.dto;

public class CiudadRutaDTO {
    private Long id;
    private Long rutaId;
    private String ciudadNombre;
    private String destinoNombre;
    private double distancia;
    private boolean esSegura;
    
    public CiudadRutaDTO() {
    }

    public CiudadRutaDTO(Long id, Long rutaId, String ciudadNombre, String destinoNombre, double distancia, boolean esSegura) {
        this.id = id;
        this.rutaId = rutaId;
        this.ciudadNombre = ciudadNombre;
        this.destinoNombre = destinoNombre;
        this.distancia = distancia;
        this.esSegura = esSegura;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRutaId() {
        return rutaId;
    }

    public void setRutaId(Long rutaId) {
        this.rutaId = rutaId;
    }

    public String getCiudadNombre() {
        return ciudadNombre;
    }

    public void setCiudadNombre(String ciudadNombre) {
        this.ciudadNombre = ciudadNombre;
    }

    public String getDestinoNombre() {
        return destinoNombre;
    }

    public void setDestinoNombre(String destinoNombre) {
        this.destinoNombre = destinoNombre;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public boolean isEsSegura() {
        return esSegura;
    }

    public void setEsSegura(boolean esSegura) {
        this.esSegura = esSegura;
    }

}
