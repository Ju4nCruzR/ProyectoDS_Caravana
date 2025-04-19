package co.edu.javeriana.caravana_medieval_cruz_f_ss.mapper;

import java.util.List;
import java.util.stream.Collectors;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CaravanaProductoDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CiudadProductoDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.ProductoAsociacionesDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.ProductoDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Producto;

public class ProductoMapper {

        public static ProductoDTO toDTO(Producto producto) {
        ProductoDTO dto = new ProductoDTO();
        dto.setId(producto.getId());
        dto.setNombreProducto(producto.getNombreProducto());
        dto.setFactorDemandaProducto(producto.getFactorDemandaProducto());
        dto.setFactorOfertaProducto(producto.getFactorOfertaProducto());
        dto.setPrecioBaseProducto(producto.getPrecioBaseProducto());
        dto.setPesoProducto(producto.getPesoProducto());
        return dto;
    }

    public static ProductoAsociacionesDTO toAsociacionesDTO(Producto producto) {
        ProductoAsociacionesDTO dto = new ProductoAsociacionesDTO();
        dto.setProducto(toDTO(producto));

        List<CiudadProductoDTO> enCiudades = producto.getEnCiudades().stream()
            .map(CiudadProductoMapper::toDTO)
            .collect(Collectors.toList());

        List<CaravanaProductoDTO> enCaravanas = producto.getEnCaravanas().stream()
            .map(CaravanaProductoMapper::toDTO)
            .collect(Collectors.toList());

        dto.setEnCiudades(enCiudades);
        dto.setEnCaravanas(enCaravanas);
        return dto;
    }
}
