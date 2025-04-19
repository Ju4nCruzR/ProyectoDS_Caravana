package co.edu.javeriana.caravana_medieval_cruz_f_ss.dto;

import java.util.List;

public class CiudadDetalleDTO {
    private CiudadDTO ciudad;
    private List<CiudadProductoDTO> productos;
    private List<CiudadServicioDTO> servicios;
    private List<CiudadRutaDTO> rutas;
    
    public CiudadDetalleDTO() {
    }

    public CiudadDetalleDTO(CiudadDTO ciudad, List<CiudadProductoDTO> productos, List<CiudadServicioDTO> servicios,
            List<CiudadRutaDTO> rutas) {
        this.ciudad = ciudad;
        this.productos = productos;
        this.servicios = servicios;
        this.rutas = rutas;
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

    
}
