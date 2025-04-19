package co.edu.javeriana.caravana_medieval_cruz_f_ss.service;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Ciudad;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.CiudadServicio;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Servicio;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.CiudadServicioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CiudadServicioService {
    
    @Autowired
    private CiudadServicioRepository ciudadServicioRepository;

    // Caso 1: Listar servicios por ciudad
    public List<CiudadServicio> listarPorCiudad(Ciudad ciudad) {
        return ciudadServicioRepository.findByCiudad(ciudad);
    }

    // Caso 2: Listar todas las asociaciones
    public List<CiudadServicio> listarTodos() {
        return ciudadServicioRepository.findAll();
    }

    // Caso 3: Ver detalle
    public Optional<CiudadServicio> buscarPorId(Long id) {
        return ciudadServicioRepository.findById(id);
    }

    // Caso 4: Asociar servicio a ciudad
    public CiudadServicio asociarServicio(Ciudad ciudad, Servicio servicio) {
        CiudadServicio nueva = new CiudadServicio(ciudad, servicio);
        return ciudadServicioRepository.save(nueva);
    }

    // Caso 5: Marcar servicio como adquirido
    public void marcarAdquirido(Long id) {
        CiudadServicio cs = ciudadServicioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asociación ciudad-servicio no encontrada"));

        cs.setServicioAdquirido(true);
        ciudadServicioRepository.save(cs);
    }

    // Caso 6: Eliminar asociación
    public void eliminarPorId(Long id) {
        ciudadServicioRepository.deleteById(id);
    }
}
