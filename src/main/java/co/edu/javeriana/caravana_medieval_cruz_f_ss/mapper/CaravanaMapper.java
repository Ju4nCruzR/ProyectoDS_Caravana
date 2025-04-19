package co.edu.javeriana.caravana_medieval_cruz_f_ss.mapper;

import java.util.List;
import java.util.stream.Collectors;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CaravanaDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CaravanaDetalleDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CaravanaProductoDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CaravanaResumenDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.JugadorDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Caravana;

public class CaravanaMapper {
    public static CaravanaDTO toDTO(Caravana caravana) {
        CaravanaDTO dto = new CaravanaDTO();
        dto.setId(caravana.getId());
        dto.setNombreCaravana(caravana.getNombreCaravana());
        dto.setVelocidadCaravana(caravana.getVelocidadCaravana());
        dto.setCapacidadMaximaCargaCaravana(caravana.getCapacidadMaximaCargaCaravana());
        dto.setDineroDisponibleCaravana(caravana.getDineroDisponibleCaravana());
        dto.setPuntosDeVidaCaravana(caravana.getPuntosDeVidaCaravana());
        if (caravana.getCiudadActual() != null) {
            dto.setNombreCiudadActual(caravana.getCiudadActual().getNombreCiudad());
        }
        return dto;
    }

    public static CaravanaResumenDTO toResumen(Caravana caravana) {
        CaravanaResumenDTO dto = new CaravanaResumenDTO();
        dto.setId(caravana.getId());
        dto.setNombreCaravana(caravana.getNombreCaravana());
        dto.setCiudadActual(caravana.getCiudadActual() != null ? caravana.getCiudadActual().getNombreCiudad() : null);
        return dto;
    }

    public static CaravanaDetalleDTO toDetalle(Caravana caravana) {
        CaravanaDetalleDTO detalle = new CaravanaDetalleDTO();
        detalle.setCaravana(toDTO(caravana));

        List<CaravanaProductoDTO> productos = caravana.getProductos().stream()
            .map(CaravanaProductoMapper::toDTO)
            .collect(Collectors.toList());
        detalle.setProductos(productos);

        List<JugadorDTO> jugadores = caravana.getJugadores().stream()
            .map(JugadorMapper::toDTO)
            .collect(Collectors.toList());
        detalle.setJugadores(jugadores);

        List<String> rutas = caravana.getRutasRecorridas().stream()
            .map(r -> r.getCiudadOrigen().getNombreCiudad() + " -> " + r.getCiudadDestino().getNombreCiudad())
            .collect(Collectors.toList());
        detalle.setRutasRecorridas(rutas);

        return detalle;
    }
}
