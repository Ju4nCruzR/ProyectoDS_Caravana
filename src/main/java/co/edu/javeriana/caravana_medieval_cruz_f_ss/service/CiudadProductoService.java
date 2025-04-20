package co.edu.javeriana.caravana_medieval_cruz_f_ss.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CiudadProductoDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.mapper.CiudadProductoMapper;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Ciudad;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.CiudadProducto;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Producto;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.CiudadProductoRepository;

@Service
public class CiudadProductoService {
    
    @Autowired
    private CiudadProductoRepository ciudadProductoRepository;

    public List<CiudadProductoDTO> listarPorCiudad(Ciudad ciudad) {
        return ciudadProductoRepository.findByCiudad(ciudad).stream()
                .map(CiudadProductoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<CiudadProductoDTO> obtenerPorCiudadYProducto(Ciudad ciudad, Producto producto) {
        return ciudadProductoRepository.findByCiudadAndProducto(ciudad, producto)
                .map(CiudadProductoMapper::toDTO);
    }

    public CiudadProductoDTO actualizarStock(Long id, int nuevoStock) {
        CiudadProducto cp = ciudadProductoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asociación no encontrada"));

        cp.setStockProducto(nuevoStock);
        return CiudadProductoMapper.toDTO(ciudadProductoRepository.save(cp));
    }

    public void eliminarPorId(Long id) {
        ciudadProductoRepository.deleteById(id);
    }

    public CiudadProductoDTO crear(CiudadProducto nuevo) {
        return CiudadProductoMapper.toDTO(ciudadProductoRepository.save(nuevo));
    }

    public List<CiudadProductoDTO> listarTodos() {
        return ciudadProductoRepository.findAll().stream()
                .map(CiudadProductoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<CiudadProducto> obtenerEntidadPorId(Long id) {
        return ciudadProductoRepository.findById(id);
    }

    public Optional<CiudadProducto> obtenerEntidadPorCiudadAndProducto(Ciudad ciudad, Producto producto) {
        return ciudadProductoRepository.findByCiudadAndProducto(ciudad, producto);
    }
    
}
