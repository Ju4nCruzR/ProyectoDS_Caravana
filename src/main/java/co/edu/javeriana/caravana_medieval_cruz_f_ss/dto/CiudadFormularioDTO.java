package co.edu.javeriana.caravana_medieval_cruz_f_ss.dto;

import java.util.List;

public class CiudadFormularioDTO {
    private String nombreCiudad;
    private double impuestosDeEntradaCiudad;
    private List<Long> productoIds;
    private List<Long> servicioIds;
    private List<Long> rutaIds;
    
    public CiudadFormularioDTO() {
    }

    public CiudadFormularioDTO(String nombreCiudad, double impuestosDeEntradaCiudad, List<Long> productoIds,
            List<Long> servicioIds, List<Long> rutaIds) {
        this.nombreCiudad = nombreCiudad;
        this.impuestosDeEntradaCiudad = impuestosDeEntradaCiudad;
        this.productoIds = productoIds;
        this.servicioIds = servicioIds;
        this.rutaIds = rutaIds;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    public double getImpuestosDeEntradaCiudad() {
        return impuestosDeEntradaCiudad;
    }

    public void setImpuestosDeEntradaCiudad(double impuestosDeEntradaCiudad) {
        this.impuestosDeEntradaCiudad = impuestosDeEntradaCiudad;
    }

    public List<Long> getProductoIds() {
        return productoIds;
    }

    public void setProductoIds(List<Long> productoIds) {
        this.productoIds = productoIds;
    }

    public List<Long> getServicioIds() {
        return servicioIds;
    }

    public void setServicioIds(List<Long> servicioIds) {
        this.servicioIds = servicioIds;
    }

    public List<Long> getRutaIds() {
        return rutaIds;
    }

    public void setRutaIds(List<Long> rutaIds) {
        this.rutaIds = rutaIds;
    }

    
}
