package co.edu.javeriana.caravana_medieval_cruz_f_ss.dto;

import java.util.List;

public class CiudadDetalleDTO {
    private CiudadDTO ciudad;
    private List<CiudadProductoDTO> productos;
    private List<CiudadServicioDTO> servicios;
    private List<CiudadRutaDTO> rutas;
    private List<ServicioDTO> serviciosDisponibles;

    
    public CiudadDetalleDTO() {
    }

    public CiudadDetalleDTO(CiudadDTO ciudad, List<CiudadProductoDTO> productos, List<CiudadServicioDTO> servicios,
            List<CiudadRutaDTO> rutas, List<ServicioDTO> serviciosDisponibles) {
        this.ciudad = ciudad;
        this.productos = productos;
        this.servicios = servicios;
        this.rutas = rutas;
        this.serviciosDisponibles = serviciosDisponibles;
    }

    public CiudadDTO getCiudad() {
        return ciudad;
    }

    public void setCiudad(CiudadDTO ciudad) {
        this.ciudad = ciudad;
    }

    public List<CiudadProductoDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<CiudadProductoDTO> productos) {
        this.productos = productos;
    }

    public List<CiudadServicioDTO> getServicios() {
        return servicios;
    }

    public void setServicios(List<CiudadServicioDTO> servicios) {
        this.servicios = servicios;
    }

    public List<CiudadRutaDTO> getRutas() {
        return rutas;
    }

    public void setRutas(List<CiudadRutaDTO> rutas) {
        this.rutas = rutas;
    }

    public List<ServicioDTO> getServiciosDisponibles() {
        return serviciosDisponibles;
    }

    public void setServiciosDisponibles(List<ServicioDTO> serviciosDisponibles) {
        this.serviciosDisponibles = serviciosDisponibles;
    }

    
}
