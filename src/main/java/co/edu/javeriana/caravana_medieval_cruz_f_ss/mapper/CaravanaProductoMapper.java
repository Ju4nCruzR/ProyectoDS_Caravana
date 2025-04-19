package co.edu.javeriana.caravana_medieval_cruz_f_ss.mapper;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CaravanaProductoDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.CaravanaProducto;

public class CaravanaProductoMapper {

    public static CaravanaProductoDTO toDTO(CaravanaProducto cp) {
        CaravanaProductoDTO dto = new CaravanaProductoDTO();
        dto.setId(cp.getId());
        dto.setProductoId(cp.getProducto().getId());
        dto.setNombreProducto(cp.getProducto().getNombreProducto());
        dto.setStockEnCaravana(cp.getStockEnCaravana());
        dto.setPesoTotal(cp.getProducto().getPesoProducto() * cp.getStockEnCaravana());
        dto.setPrecioBaseProducto(cp.getProducto().getPrecioBaseProducto());
        return dto;
    }
}
