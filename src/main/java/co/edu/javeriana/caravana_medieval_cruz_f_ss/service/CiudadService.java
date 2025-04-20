package co.edu.javeriana.caravana_medieval_cruz_f_ss.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CiudadDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CiudadDetalleDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CiudadFormularioDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CiudadProductoDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CiudadRutaDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CiudadServicioDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.mapper.CiudadMapper;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.mapper.CiudadProductoMapper;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.mapper.CiudadRutaMapper;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.mapper.CiudadServicioMapper;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Ciudad;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.CiudadProducto;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.CiudadRuta;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.CiudadServicio;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Producto;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Ruta;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Servicio;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.CiudadProductoRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.CiudadRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.CiudadRutaRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.CiudadServicioRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.ProductoRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.RutaRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.ServicioRepository;

@Service
public class CiudadService {

    @Autowired
    private CiudadRepository ciudadRepository;

    @Autowired
    private CiudadProductoRepository ciudadProductoRepository;

    @Autowired
    private CiudadServicioRepository ciudadServicioRepository;

    @Autowired
    private CiudadRutaRepository ciudadRutaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ServicioRepository servicioRepository;
    
    @Autowired
    private RutaRepository rutaRepository;


    public CiudadDTO crearCiudad(Ciudad ciudad) {
        return CiudadMapper.toDTO(ciudadRepository.save(ciudad));
    }

    public CiudadDetalleDTO buscarCiudadPorId(Long id) {
        Ciudad ciudad = ciudadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));
        return CiudadMapper.toDetalle(ciudad);
    }

    public List<CiudadDTO> listarCiudades() {
        return ciudadRepository.findAll().stream()
                .map(CiudadMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CiudadDTO actualizarCiudadDesdeFormulario(Long id, CiudadFormularioDTO dto) {
        Ciudad ciudad = ciudadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));

        ciudad.setNombreCiudad(dto.getNombreCiudad());
        ciudad.setImpuestosDeEntradaCiudad(dto.getImpuestosDeEntradaCiudad());

        return CiudadMapper.toDTO(ciudadRepository.save(ciudad));
    }

    @Transactional
    public void actualizarCiudadConAsociaciones(Long id,
            CiudadFormularioDTO dto,
            List<Long> productoIds,
            List<Long> servicioIds,
            List<Long> rutaIds) {

        Ciudad ciudad = ciudadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));

        // Actualizar campos básicos
        ciudad.setNombreCiudad(dto.getNombreCiudad());
        ciudad.setImpuestosDeEntradaCiudad(dto.getImpuestosDeEntradaCiudad());

        // Actualizar productos asociados
        ciudad.getProductosDisponibles().clear();
        if (productoIds != null) {
            List<CiudadProducto> nuevosProductos = productoIds.stream()
                    .map(pid -> {
                        Producto producto = productoRepository.findById(pid)
                                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
                        return new CiudadProducto(ciudad, producto, 0);
                    })
                    .toList();
            ciudad.getProductosDisponibles().addAll(nuevosProductos);
        }

        // Actualizar servicios asociados
        ciudad.getServiciosDisponibles().clear();
        if (servicioIds != null) {
            List<CiudadServicio> nuevosServicios = servicioIds.stream()
                    .map(sid -> {
                        Servicio servicio = servicioRepository.findById(sid)
                                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));
                        return new CiudadServicio(ciudad, servicio);
                    })
                    .toList();
            ciudad.getServiciosDisponibles().addAll(nuevosServicios);
        }

        // Actualizar rutas asociadas
        ciudad.getRutasOrigen().clear();
        if (rutaIds != null) {
            List<CiudadRuta> nuevasRutas = rutaIds.stream()
                    .map(rid -> {
                        Ruta ruta = rutaRepository.findById(rid)
                                .orElseThrow(() -> new RuntimeException("Ruta no encontrada"));
                        return new CiudadRuta(ciudad, ruta);
                    })
                    .toList();
            ciudad.getRutasOrigen().addAll(nuevasRutas);
        }

        ciudadRepository.save(ciudad);
    }

    @Transactional
    public void eliminarCiudad(Long id) {
        Ciudad ciudad = ciudadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));

        ciudadProductoRepository.deleteAll(ciudad.getProductosDisponibles());
        ciudadServicioRepository.deleteAll(ciudad.getServiciosDisponibles());
        ciudadRutaRepository.deleteAll(ciudad.getRutasAsociadas()); // <--- esta es la lista correcta

        ciudadRepository.delete(ciudad);
    }

    public List<CiudadProductoDTO> obtenerProductos(Long ciudadId) {
        Ciudad ciudad = ciudadRepository.findById(ciudadId)
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));

        return ciudad.getProductosDisponibles().stream()
                .map(CiudadProductoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<CiudadServicioDTO> obtenerServicios(Long ciudadId) {
        Ciudad ciudad = ciudadRepository.findById(ciudadId)
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));

        return ciudad.getServiciosDisponibles().stream()
                .map(CiudadServicioMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<CiudadRutaDTO> obtenerRutas(Long ciudadId) {
        Ciudad ciudad = ciudadRepository.findById(ciudadId)
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));

        return ciudad.getRutasAsociadas().stream()
                .map(CiudadRutaMapper::toDTO)
                .collect(Collectors.toList());

    }

}
