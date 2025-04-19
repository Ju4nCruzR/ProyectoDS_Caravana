package co.edu.javeriana.caravana_medieval_cruz_f_ss.mapper;

import java.util.List;
import java.util.stream.Collectors;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CiudadDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CiudadDetalleDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CiudadProductoDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CiudadRutaDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CiudadServicioDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Ciudad;

public class CiudadMapper {
    public static CiudadDTO toDTO(Ciudad ciudad) {
        CiudadDTO dto = new CiudadDTO();
        dto.setId(ciudad.getId());
        dto.setNombreCiudad(ciudad.getNombreCiudad());
        dto.setImpuestosDeEntradaCiudad(ciudad.getImpuestosDeEntradaCiudad());
        return dto;
    }

    public static CiudadDetalleDTO toDetalle(Ciudad ciudad) {
        CiudadDetalleDTO detalle = new CiudadDetalleDTO();
        detalle.setCiudad(toDTO(ciudad));

        List<CiudadProductoDTO> productos = ciudad.getProductosDisponibles().stream()
            .map(CiudadProductoMapper::toDTO)
            .collect(Collectors.toList());
        detalle.setProductos(productos);

        List<CiudadServicioDTO> servicios = ciudad.getServiciosDisponibles().stream()
            .map(CiudadServicioMapper::toDTO)
            .collect(Collectors.toList());
        detalle.setServicios(servicios);

        List<CiudadRutaDTO> rutas = ciudad.getRutasAsociadas().stream()
            .map(CiudadRutaMapper::toDTO)
            .collect(Collectors.toList());
        detalle.setRutas(rutas);

        return detalle;
    }
}
