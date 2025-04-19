package co.edu.javeriana.caravana_medieval_cruz_f_ss.mapper;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CiudadRutaDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.CiudadRuta;

public class CiudadRutaMapper {
    public static CiudadRutaDTO toDTO(CiudadRuta cr) {
        CiudadRutaDTO dto = new CiudadRutaDTO();
        dto.setId(cr.getId());
        dto.setCiudadNombre(cr.getCiudad().getNombreCiudad());
        dto.setDestinoNombre(cr.getRuta().getCiudadDestino().getNombreCiudad());
        dto.setDistancia(cr.getRuta().getDistanciaRuta());
        dto.setEsSegura(cr.getRuta().isEsSeguraRuta());
        return dto;
    }
}
