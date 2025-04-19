package co.edu.javeriana.caravana_medieval_cruz_f_ss.mapper;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.RutaBusquedaDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.RutaDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Ruta;

public class RutaMapper {
    
    public static RutaDTO toDTO(Ruta ruta) {
        RutaDTO dto = new RutaDTO();
        dto.setId(ruta.getId());
        dto.setOrigen(ruta.getCiudadOrigen() != null ? ruta.getCiudadOrigen().getNombreCiudad() : null);
        dto.setDestino(ruta.getCiudadDestino() != null ? ruta.getCiudadDestino().getNombreCiudad() : null);
        dto.setDistanciaRuta(ruta.getDistanciaRuta());
        dto.setEsSeguraRuta(ruta.isEsSeguraRuta());
        dto.setDanoRuta(ruta.getDanoRuta());
        return dto;
    }

    public static RutaBusquedaDTO toBusquedaDTO(Ruta ruta) {
        RutaBusquedaDTO dto = new RutaBusquedaDTO();
        dto.setCiudadOrigen(ruta.getCiudadOrigen() != null ? ruta.getCiudadOrigen().getNombreCiudad() : null);
        dto.setCiudadDestino(ruta.getCiudadDestino() != null ? ruta.getCiudadDestino().getNombreCiudad() : null);
        dto.setDistancia(ruta.getDistanciaRuta());
        dto.setEsSegura(ruta.isEsSeguraRuta());
        dto.setDanoRuta(ruta.getDanoRuta());
        return dto;
    }
}
