package co.edu.javeriana.caravana_medieval_cruz_f_ss.mapper;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CiudadProductoDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.CiudadProducto;

public class CiudadProductoMapper {
    
    public static CiudadProductoDTO toDTO(CiudadProducto cp) {
        CiudadProductoDTO dto = new CiudadProductoDTO();
        dto.setId(cp.getId());
        dto.setProductoId(cp.getProducto().getId());
        dto.setNombreProducto(cp.getProducto().getNombreProducto());
        dto.setStockProducto(cp.getStockProducto());
        dto.setNombreCiudad(cp.getCiudad().getNombreCiudad()); // ← añadimos esto
        return dto;
    }
    
}
