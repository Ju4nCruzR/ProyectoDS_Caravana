package co.edu.javeriana.caravana_medieval_cruz_f_ss.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.ServicioDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.mapper.ServicioMapper;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.CiudadServicio;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Servicio;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Servicio.TipoServicio;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.CiudadServicioRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.ServicioRepository;

@Service
public class ServicioService {

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private CiudadServicioRepository ciudadServicioRepository;

    // Caso 1: Crear servicio
    public ServicioDTO crearServicio(ServicioDTO servicioDTO) {
        Servicio servicio = new Servicio();
        servicio.setTipo(TipoServicio.valueOf(servicioDTO.getTipoServicio()));
        servicio.setPrecioServicio(servicioDTO.getPrecioServicio());

        return ServicioMapper.toDTO(servicioRepository.save(servicio));
    }

    // Caso 2: Ver detalle
    public Optional<ServicioDTO> buscarPorId(Long id) {
        return servicioRepository.findById(id)
                .map(ServicioMapper::toDTO);
    }

    // Caso 3: Listar todos
    public List<ServicioDTO> listarTodos() {
        return servicioRepository.findAll()
                .stream()
                .map(ServicioMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Caso 4: Editar servicio
    public ServicioDTO actualizarServicio(Long id, ServicioDTO datos) {
        Servicio servicio = servicioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

        servicio.setTipo(TipoServicio.valueOf(datos.getTipoServicio()));
        servicio.setPrecioServicio(datos.getPrecioServicio());

        return ServicioMapper.toDTO(servicioRepository.save(servicio));
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
