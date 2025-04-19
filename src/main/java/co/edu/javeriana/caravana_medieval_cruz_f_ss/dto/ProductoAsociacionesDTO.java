package co.edu.javeriana.caravana_medieval_cruz_f_ss.dto;

import java.util.List;

public class ProductoAsociacionesDTO {
    private ProductoDTO producto;
    private List<CiudadProductoDTO> enCiudades;
    private List<CaravanaProductoDTO> enCaravanas;
    
    public ProductoAsociacionesDTO() {
    }

    public ProductoAsociacionesDTO(ProductoDTO producto, List<CiudadProductoDTO> enCiudades,
            List<CaravanaProductoDTO> enCaravanas) {
        this.producto = producto;
        this.enCiudades = enCiudades;
        this.enCaravanas = enCaravanas;
    }

    public ProductoDTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoDTO producto) {
        this.producto = producto;
    }

    public List<CiudadProductoDTO> getEnCiudades() {
        return enCiudades;
    }

    public void setEnCiudades(List<CiudadProductoDTO> enCiudades) {
        this.enCiudades = enCiudades;
    }

    public List<CaravanaProductoDTO> getEnCaravanas() {
        return enCaravanas;
    }

    public void setEnCaravanas(List<CaravanaProductoDTO> enCaravanas) {
        this.enCaravanas = enCaravanas;
    }

    
}
