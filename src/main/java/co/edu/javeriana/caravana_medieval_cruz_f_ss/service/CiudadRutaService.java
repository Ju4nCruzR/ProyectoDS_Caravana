package co.edu.javeriana.caravana_medieval_cruz_f_ss.service;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Ciudad;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.CiudadRuta;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Ruta;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.CiudadRutaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CiudadRutaService {
    @Autowired
    private CiudadRutaRepository ciudadRutaRepository;

    // Caso 1: Listar rutas que salen de una ciudad
    public List<CiudadRuta> listarPorCiudad(Ciudad ciudad) {
        return ciudad.getRutasAsociadas(); // si ya está cargado con fetch
    }

    // Caso 2: Agregar una ruta a una ciudad
    public CiudadRuta asociarRuta(Ciudad ciudad, Ruta ruta) {
        CiudadRuta ciudadRuta = new CiudadRuta(ciudad, ruta);
        return ciudadRutaRepository.save(ciudadRuta);
    }

    // Caso 3: Eliminar una asociación
    public void eliminarPorId(Long id) {
        ciudadRutaRepository.deleteById(id);
    }

    // Caso 4: Ver detalle
    public Optional<CiudadRuta> buscarPorId(Long id) {
        return ciudadRutaRepository.findById(id);
    }

    // Caso 5: Listar todas las asociaciones
    public List<CiudadRuta> listarTodas() {
        return ciudadRutaRepository.findAll();
    } 
}
