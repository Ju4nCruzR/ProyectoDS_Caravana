package co.edu.javeriana.caravana_medieval_cruz_f_ss.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Ciudad;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Ruta;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.CiudadRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.RutaRepository;

@Service
public class RutaService {
    
    @Autowired
    private RutaRepository rutaRepository;

    @Autowired
    private CiudadRepository ciudadRepository;

    // 1. Crear ruta
    public Ruta crearRuta(Ruta ruta) {
        return rutaRepository.save(ruta);
    }

    // 2. Buscar por ID
    public Optional<Ruta> buscarPorId(Long id) {
        return rutaRepository.findById(id);
    }

    // 3. Listar todas
    public List<Ruta> listarTodas() {
        return rutaRepository.findAll();
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
    public void eliminarRuta(Long id) {
        rutaRepository.deleteById(id);
    }

    // 6. Filtrar por ciudad origen
    public List<Ruta> buscarPorCiudadOrigen(Long ciudadId) {
        Ciudad ciudad = ciudadRepository.findById(ciudadId)
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));
        return rutaRepository.findAll().stream()
                .filter(r -> r.getCiudadOrigen().equals(ciudad))
                .collect(Collectors.toList());
    }

    // 7. Ver rutas seguras o inseguras
    public List<Ruta> filtrarPorSeguridad(boolean segura) {
        return rutaRepository.findAll().stream()
                .filter(r -> r.isEsSeguraRuta() == segura)
                .collect(Collectors.toList());
    }

    // 8. Rutas entre dos ciudades
    public List<Ruta> buscarEntreCiudades(Long idOrigen, Long idDestino) {
        Ciudad origen = ciudadRepository.findById(idOrigen)
                .orElseThrow(() -> new RuntimeException("Ciudad origen no encontrada"));
        Ciudad destino = ciudadRepository.findById(idDestino)
                .orElseThrow(() -> new RuntimeException("Ciudad destino no encontrada"));

        return rutaRepository.findAll().stream()
                .filter(r -> r.getCiudadOrigen().equals(origen) && r.getCiudadDestino().equals(destino))
                .collect(Collectors.toList());
    }
}
