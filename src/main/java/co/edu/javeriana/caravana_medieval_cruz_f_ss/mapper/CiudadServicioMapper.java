package co.edu.javeriana.caravana_medieval_cruz_f_ss.mapper;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CiudadServicioDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.CiudadServicio;

public class CiudadServicioMapper {
    
    public static CiudadServicioDTO toDTO(CiudadServicio cs) {
        CiudadServicioDTO dto = new CiudadServicioDTO();
        dto.setId(cs.getId());
        dto.setNombreCiudad(cs.getCiudad().getNombreCiudad()); // ← NUEVO
        dto.setNombreServicio(cs.getServicio().getTipo().name());
        dto.setPrecio(cs.getServicio().getPrecioServicio());
        dto.setAdquirido(cs.isServicioAdquirido());
        return dto;
    }
    
}
