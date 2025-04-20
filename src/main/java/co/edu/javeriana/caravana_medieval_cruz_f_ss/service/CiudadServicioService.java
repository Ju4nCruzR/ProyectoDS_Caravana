package co.edu.javeriana.caravana_medieval_cruz_f_ss.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CiudadServicioDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.mapper.CiudadServicioMapper;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Ciudad;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.CiudadServicio;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Servicio;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.CiudadServicioRepository;

@Service
public class CiudadServicioService {
    
    @Autowired
    private CiudadServicioRepository ciudadServicioRepository;

    public List<CiudadServicioDTO> listarPorCiudad(Ciudad ciudad) {
        return ciudadServicioRepository.findByCiudad(ciudad).stream()
                .map(CiudadServicioMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<CiudadServicioDTO> listarTodos() {
        return ciudadServicioRepository.findAll().stream()
                .map(CiudadServicioMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<CiudadServicioDTO> buscarPorId(Long id) {
        return ciudadServicioRepository.findById(id)
                .map(CiudadServicioMapper::toDTO);
    }

    public CiudadServicioDTO asociarServicio(Ciudad ciudad, Servicio servicio) {
        CiudadServicio nueva = new CiudadServicio(ciudad, servicio);
        return CiudadServicioMapper.toDTO(ciudadServicioRepository.save(nueva));
    }

    public void marcarAdquirido(Long id) {
        CiudadServicio cs = ciudadServicioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asociación ciudad-servicio no encontrada"));

        cs.setServicioAdquirido(true);
        ciudadServicioRepository.save(cs);
    }

    public void eliminarPorId(Long id) {
        ciudadServicioRepository.deleteById(id);
    }
}
