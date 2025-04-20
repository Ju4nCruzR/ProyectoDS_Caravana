package co.edu.javeriana.caravana_medieval_cruz_f_ss.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CiudadRutaDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.mapper.CiudadRutaMapper;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Ciudad;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.CiudadRuta;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Ruta;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.CiudadRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.CiudadRutaRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.RutaRepository;

@Service
public class CiudadRutaService {
    @Autowired
    private CiudadRutaRepository ciudadRutaRepository;

     @Autowired
    private CiudadRepository ciudadRepository;

    @Autowired
    private RutaRepository rutaRepository;

    // Caso 1: Listar rutas asociadas a una ciudad
    public List<CiudadRutaDTO> listarPorCiudad(Long ciudadId) {
        Ciudad ciudad = ciudadRepository.findById(ciudadId)
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));
        return ciudad.getRutasAsociadas().stream()
                .map(CiudadRutaMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Caso 2: Asociar una ruta a una ciudad
    public CiudadRutaDTO asociarRuta(Long ciudadId, Long rutaId) {
        Ciudad ciudad = ciudadRepository.findById(ciudadId)
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));
        Ruta ruta = rutaRepository.findById(rutaId)
                .orElseThrow(() -> new RuntimeException("Ruta no encontrada"));

        CiudadRuta ciudadRuta = new CiudadRuta(ciudad, ruta.getCiudadDestino(), ruta.getCiudadOrigen(), ruta);
        return CiudadRutaMapper.toDTO(ciudadRutaRepository.save(ciudadRuta));
    }

    // Caso 3: Eliminar una asociación por ID
    public void eliminarPorId(Long id) {
        ciudadRutaRepository.deleteById(id);
    }

    // Caso 4: Ver detalle por ID
    public Optional<CiudadRutaDTO> buscarPorId(Long id) {
        return ciudadRutaRepository.findById(id)
                .map(CiudadRutaMapper::toDTO);
    }

    // Caso 5: Listar todas las asociaciones
    public List<CiudadRutaDTO> listarTodas() {
        return ciudadRutaRepository.findAll().stream()
                .map(CiudadRutaMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Caso 6 (opcional): Listar rutas entre dos ciudades
    public List<CiudadRutaDTO> listarEntreCiudades(Long origenId, Long destinoId) {
        Ciudad origen = ciudadRepository.findById(origenId)
                .orElseThrow(() -> new RuntimeException("Ciudad origen no encontrada"));
        Ciudad destino = ciudadRepository.findById(destinoId)
                .orElseThrow(() -> new RuntimeException("Ciudad destino no encontrada"));

        return ciudadRutaRepository.findAll().stream()
                .filter(r -> r.getCiudadOrigen().equals(origen) && r.getCiudadDestino().equals(destino))
                .map(CiudadRutaMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Caso 7 (opcional): Listar solo rutas seguras desde una ciudad
    public List<CiudadRutaDTO> listarRutasSegurasDesdeCiudad(Long ciudadId) {
        Ciudad ciudad = ciudadRepository.findById(ciudadId)
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));

        return ciudad.getRutasAsociadas().stream()
                .filter(r -> r.getRuta().isEsSeguraRuta())
                .map(CiudadRutaMapper::toDTO)
                .collect(Collectors.toList());
    }
}
