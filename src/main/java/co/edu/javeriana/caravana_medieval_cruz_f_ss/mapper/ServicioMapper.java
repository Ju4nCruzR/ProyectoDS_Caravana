package co.edu.javeriana.caravana_medieval_cruz_f_ss.mapper;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.ServicioAplicadoDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.ServicioDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Servicio;

public class ServicioMapper {
    
    public static ServicioDTO toDTO(Servicio servicio) {
        ServicioDTO dto = new ServicioDTO();
        dto.setId(servicio.getId());
        dto.setTipoServicio(servicio.getTipo().name());
        dto.setPrecioServicio(servicio.getPrecioServicio());
        return dto;
    }

    public static ServicioAplicadoDTO toAplicadoDTO(Long caravanaId, Servicio servicio, String efectoAplicado) {
        ServicioAplicadoDTO dto = new ServicioAplicadoDTO();
        dto.setCaravanaId(caravanaId);
        dto.setTipoServicio(servicio.getTipo().name());
        dto.setEfectoAplicado(efectoAplicado);
        return dto;
    }
}
