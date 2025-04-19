package co.edu.javeriana.caravana_medieval_cruz_f_ss.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.CiudadServicio;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Servicio;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.CiudadServicioRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.ServicioRepository;

@Service
public class ServicioService {
    
    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private CiudadServicioRepository ciudadServicioRepository;

    // Caso 1: Crear servicio
    public Servicio crearServicio(Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    // Caso 2: Ver detalle
    public Optional<Servicio> buscarPorId(Long id) {
        return servicioRepository.findById(id);
    }

    // Caso 3: Listar todos
    public List<Servicio> listarTodos() {
        return servicioRepository.findAll();
    }

    // Caso 4: Editar servicio
    public Servicio actualizarServicio(Long id, Servicio datos) {
        Servicio servicio = servicioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

        servicio.setTipo(datos.getTipo());
        servicio.setPrecioServicio(datos.getPrecioServicio());

        return servicioRepository.save(servicio);
    }

    // Caso 5: Eliminar servicio
    public void eliminarServicio(Long id) {
        servicioRepository.deleteById(id);
    }

    // Caso 6: Obtener ciudades donde se ofrece este servicio
    public List<CiudadServicio> obtenerCiudadesAsociadas(Long servicioId) {
        Servicio servicio = servicioRepository.findById(servicioId)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));
    
        return ciudadServicioRepository.findByServicio(servicio);
    }    
}
