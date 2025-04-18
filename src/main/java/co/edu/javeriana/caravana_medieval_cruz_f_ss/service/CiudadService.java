package co.edu.javeriana.caravana_medieval_cruz_f_ss.service;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Ciudad;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.CiudadProducto;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.CiudadRuta;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.CiudadServicio;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.CiudadRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CiudadService {

    @Autowired
    private CiudadRepository ciudadRepository;

    // Caso 1: Crear ciudad
    public Ciudad crearCiudad(Ciudad ciudad) {
        return ciudadRepository.save(ciudad);
    }

    // Caso 2: Buscar por ID
    public Optional<Ciudad> buscarPorId(Long id) {
        return ciudadRepository.findById(id);
    }

    // Caso 3: Listar todas
    public List<Ciudad> listarTodas() {
        return ciudadRepository.findAll();
    }

    // Caso 4: Editar
    public Ciudad actualizarCiudad(Long id, Ciudad ciudadActualizada) {
        Ciudad ciudad = ciudadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));

        ciudad.setNombreCiudad(ciudadActualizada.getNombreCiudad());
        ciudad.setImpuestosDeEntradaCiudad(ciudadActualizada.getImpuestosDeEntradaCiudad());
        return ciudadRepository.save(ciudad);
    }

    // Caso 5: Eliminar
    public void eliminarCiudad(Long id) {
        ciudadRepository.deleteById(id);
    }

    // Caso 6: Obtener productos disponibles
    public List<CiudadProducto> obtenerProductos(Ciudad ciudad) {
        return ciudad.getProductosDisponibles();
    }

    // Caso 7: Obtener servicios disponibles
    public List<CiudadServicio> obtenerServicios(Ciudad ciudad) {
        return ciudad.getServiciosDisponibles();
    }

    // Caso 8: Rutas que salen de la ciudad
    public List<CiudadRuta> obtenerRutasDesdeCiudad(Ciudad ciudad) {
        return ciudad.getRutasAsociadas();
    }

    // Caso 9: Rutas que llegan a la ciudad
    public List<Ciudad> obtenerCiudadesQueConectanA(Ciudad ciudadDestino) {
        return ciudadRepository.findAll().stream()
                .filter(c -> c.getRutasOrigen().stream()
                        .anyMatch(r -> r.getCiudadDestino().equals(ciudadDestino)))
                .toList();
    }
}
