package co.edu.javeriana.caravana_medieval_cruz_f_ss.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.RutaDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.mapper.RutaMapper;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Ciudad;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Ruta;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.CiudadRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.CiudadRutaRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.RutaRepository;

@Service
public class RutaService {

    @Autowired
    private RutaRepository rutaRepository;

    @Autowired
    private CiudadRepository ciudadRepository;

    @Autowired
    private CiudadRutaRepository ciudadRutaRepository;

    // 1. Crear ruta
    public Ruta crearRuta(Ruta ruta) {
        return rutaRepository.save(ruta);
    }

    // 2. Buscar por ID
    public Optional<RutaDTO> buscarPorId(Long id) {
        return rutaRepository.findById(id)
                .map(RutaMapper::toDTO);
    }

    // 3. Listar todas
    public List<RutaDTO> listarTodas() {
        return rutaRepository.findAll()
                .stream()
                .map(RutaMapper::toDTO)
                .collect(Collectors.toList());
    }

    // 4. Editar ruta
    public Ruta actualizarRuta(Long id, Ruta datos) {
        Ruta ruta = rutaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ruta no encontrada"));

        ruta.setCiudadOrigen(datos.getCiudadOrigen());
        ruta.setCiudadDestino(datos.getCiudadDestino());
        ruta.setDistanciaRuta(datos.getDistanciaRuta());
        ruta.setEsSeguraRuta(datos.isEsSeguraRuta());
        ruta.setDanoRuta(datos.getDanoRuta());

        return rutaRepository.save(ruta);
    }

    // 5. Eliminar ruta
    @Transactional
    public void eliminarRuta(Long id) {
        // 1. Eliminar relaciones primero
        ciudadRutaRepository.deleteByRutaId(id);

        // 2. Luego eliminar la ruta
        rutaRepository.deleteById(id);
    }

    // 6. Filtrar por ciudad origen
    public List<RutaDTO> buscarPorCiudadOrigen(Long ciudadId) {
        Ciudad ciudad = ciudadRepository.findById(ciudadId)
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));
        return rutaRepository.findAll().stream()
                .filter(r -> r.getCiudadOrigen().equals(ciudad))
                .map(RutaMapper::toDTO)
                .collect(Collectors.toList());
    }

    // 6.1. Filtrar por ciudad destino
    public List<RutaDTO> buscarPorCiudadDestino(Long ciudadId) {
        return rutaRepository.findAll().stream()
            .filter(r -> r.getCiudadDestino() != null && r.getCiudadDestino().getId() == ciudadId)
            .map(RutaMapper::toDTO)
            .toList();
    }    

    // 7. Filtrar por ciudad seguridad
    public List<RutaDTO> filtrarPorSeguridad(boolean segura) {
        return rutaRepository.findAll().stream()
                .filter(r -> r.isEsSeguraRuta() == segura)
                .map(RutaMapper::toDTO)
                .toList();
    }

    // 8. Rutas entre dos ciudades
    public List<RutaDTO> buscarEntreCiudades(Long idOrigen, Long idDestino) {
        Ciudad origen = ciudadRepository.findById(idOrigen)
                .orElseThrow(() -> new RuntimeException("Ciudad origen no encontrada"));
        Ciudad destino = ciudadRepository.findById(idDestino)
                .orElseThrow(() -> new RuntimeException("Ciudad destino no encontrada"));

        return rutaRepository.findAll().stream()
                .filter(r -> r.getCiudadOrigen().equals(origen) && r.getCiudadDestino().equals(destino))
                .map(RutaMapper::toDTO)
                .collect(Collectors.toList());
    }
}
