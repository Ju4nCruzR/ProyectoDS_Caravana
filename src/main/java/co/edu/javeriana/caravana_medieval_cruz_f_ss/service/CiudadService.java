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
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.ServicioDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.mapper.CiudadMapper;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.mapper.CiudadProductoMapper;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.mapper.CiudadRutaMapper;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.mapper.CiudadServicioMapper;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.mapper.ServicioMapper;
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

        public CiudadDTO crearCiudad(CiudadFormularioDTO dto) {
                Ciudad ciudad = new Ciudad();
                ciudad.setNombreCiudad(dto.getNombreCiudad());
                ciudad.setImpuestosDeEntradaCiudad(dto.getImpuestosDeEntradaCiudad());
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

        @Transactional
        public CiudadDTO actualizarCiudadDesdeFormulario(Long id, CiudadFormularioDTO dto) {
                Ciudad ciudad = ciudadRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));

                ciudad.setNombreCiudad(dto.getNombreCiudad());
                ciudad.setImpuestosDeEntradaCiudad(dto.getImpuestosDeEntradaCiudad());

                return CiudadMapper.toDTO(ciudadRepository.save(ciudad));
        }

        @Transactional
        public void actualizarCiudadConAsociaciones(Long id, CiudadFormularioDTO dto) {
                System.out.println("🔧 Actualizando ciudad con ID: " + id);
                System.out.println("✅ Recibido PUT /ciudad/" + id + "/asociaciones");
                System.out.println("📥 DTO recibido: " + dto);

                Ciudad ciudad = ciudadRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));

                System.out.println("📋 Nuevos datos: nombre = " + dto.getNombreCiudad() + ", impuestos = "
                                + dto.getImpuestosDeEntradaCiudad());

                ciudad.setNombreCiudad(dto.getNombreCiudad());
                ciudad.setImpuestosDeEntradaCiudad(dto.getImpuestosDeEntradaCiudad());

                // Vaciar asociaciones previas
                ciudadProductoRepository.deleteAll(ciudad.getProductosDisponibles());
                ciudadServicioRepository.deleteAll(ciudad.getServiciosDisponibles());
                ciudadRutaRepository.deleteAll(ciudad.getRutasAsociadas());

                System.out.println("🧹 Asociaciones eliminadas");

                // Agregar productos
                if (dto.getProductoIds() != null) {
                        List<CiudadProducto> nuevos = dto.getProductoIds().stream()
                                        .map(pid -> new CiudadProducto(
                                                        ciudad,
                                                        productoRepository.findById(pid)
                                                                        .orElseThrow(() -> new RuntimeException(
                                                                                        "Producto no encontrado")),
                                                        0))
                                        .collect(Collectors.toList());
                        ciudadProductoRepository.saveAll(nuevos);
                        System.out.println("📦 Nuevos productos asociados: " + nuevos.size());
                }

                // Agregar servicios
                if (dto.getServicioIds() != null) {
                        List<CiudadServicio> nuevos = dto.getServicioIds().stream()
                                        .map(sid -> new CiudadServicio(
                                                        ciudad,
                                                        servicioRepository.findById(sid)
                                                                        .orElseThrow(() -> new RuntimeException(
                                                                                        "Servicio no encontrado"))))
                                        .collect(Collectors.toList());
                        ciudadServicioRepository.saveAll(nuevos);
                        System.out.println("🧰 Nuevos servicios asociados: " + nuevos.size());
                }

                // Agregar rutas
                if (dto.getRutaIds() != null) {
                        List<CiudadRuta> nuevas = dto.getRutaIds().stream()
                                        .map(rid -> {
                                                Ruta ruta = rutaRepository.findById(rid).orElseThrow(
                                                                () -> new RuntimeException("Ruta no encontrada"));
                                                return new CiudadRuta(ciudad, ruta.getCiudadDestino(),
                                                                ruta.getCiudadOrigen(), ruta);
                                        })
                                        .collect(Collectors.toList());
                        ciudadRutaRepository.saveAll(nuevas);
                        System.out.println("🛣️ Nuevas rutas asociadas: " + nuevas.size());
                }

                System.out.println("✅ Ciudad actualizada correctamente");
        }

        @Transactional
        public void eliminarCiudad(Long id) {
                Ciudad ciudad = ciudadRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));

                // 1. Eliminar rutas donde esta ciudad es origen o destino
                List<Ruta> rutasOrigen = rutaRepository.findByCiudadOrigen(ciudad);
                List<Ruta> rutasDestino = rutaRepository.findByCiudadDestino(ciudad);

                rutaRepository.deleteAll(rutasOrigen);
                rutaRepository.deleteAll(rutasDestino);

                // 2. Eliminar CiudadRuta donde esta ciudad es origen, destino o principal
                List<CiudadRuta> rutasDesde = ciudadRutaRepository.findByCiudad(ciudad);
                List<CiudadRuta> rutasConDestino = ciudadRutaRepository.findByCiudadDestino(ciudad);

                ciudadRutaRepository.deleteAll(rutasDesde);
                ciudadRutaRepository.deleteAll(rutasConDestino);

                // 3. Eliminar asociaciones de productos y servicios
                ciudadProductoRepository.deleteAll(ciudadProductoRepository.findByCiudad(ciudad));
                ciudadServicioRepository.deleteAll(ciudadServicioRepository.findByCiudad(ciudad));

                // 4. Eliminar finalmente la ciudad
                ciudadRepository.delete(ciudad);

                System.out.println("✅ Ciudad eliminada correctamente.");
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

        public List<CiudadProductoDTO> listarProductosDisponibles(Long ciudadId) {
                Ciudad ciudad = ciudadRepository.findById(ciudadId)
                                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));

                return ciudad.getProductosDisponibles().stream()
                                .map(CiudadProductoMapper::toDTO)
                                .collect(Collectors.toList());
        }

        public List<CiudadDTO> listarCiudadesDestinoPorOrigen(Long ciudadOrigenId) {
                Ciudad ciudadOrigen = ciudadRepository.findById(ciudadOrigenId)
                                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));

                return ciudadOrigen.getRutasOrigen().stream()
                                .map(CiudadRuta::getCiudadDestino) // ✅ CiudadRuta, no Ruta
                                .map(CiudadMapper::toDTO)
                                .toList();
        }

        public List<ServicioDTO> listarServiciosDisponiblesParaAgregar(Long ciudadId) {
                Ciudad ciudad = ciudadRepository.findById(ciudadId)
                                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));

                List<Long> serviciosYaAgregados = ciudad.getServiciosDisponibles().stream()
                                .map(cs -> cs.getServicio().getId())
                                .collect(Collectors.toList());

                return servicioRepository.findAll().stream()
                                .filter(servicio -> !serviciosYaAgregados.contains(servicio.getId()))
                                .map(ServicioMapper::toDTO)
                                .collect(Collectors.toList());
        }

        public List<ServicioDTO> listarServiciosNoAsociados(Long ciudadId) {
                Ciudad ciudad = ciudadRepository.findById(ciudadId)
                                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));

                // Obtener los IDs de los servicios ya asociados
                List<Long> idsAsociados = ciudad.getServiciosDisponibles().stream()
                                .map(cs -> cs.getServicio().getId())
                                .collect(Collectors.toList());

                // Filtrar todos los servicios que NO están asociados
                List<Servicio> serviciosNoAsociados = servicioRepository.findAll().stream()
                                .filter(servicio -> !idsAsociados.contains(servicio.getId()))
                                .collect(Collectors.toList());

                return serviciosNoAsociados.stream()
                                .map(ServicioMapper::toDTO)
                                .collect(Collectors.toList());
        }

        @Transactional
        public void agregarServicio(Long ciudadId, Long servicioId) {
                Ciudad ciudad = ciudadRepository.findById(ciudadId)
                                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));
                Servicio servicio = servicioRepository.findById(servicioId)
                                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

                CiudadServicio nuevaAsociacion = new CiudadServicio(ciudad, servicio);
                ciudadServicioRepository.save(nuevaAsociacion);
        }

        @Transactional
        public void agregarProducto(Long ciudadId, Long productoId, int stock) {
                Ciudad ciudad = ciudadRepository.findById(ciudadId)
                                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));
                Producto producto = productoRepository.findById(productoId)
                                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

                CiudadProducto nuevaAsociacion = new CiudadProducto(ciudad, producto, stock);
                ciudadProductoRepository.save(nuevaAsociacion);
        }

        @Transactional
        public void eliminarProducto(Long ciudadId, Long productoId) {
                Ciudad ciudad = ciudadRepository.findById(ciudadId)
                                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));
                List<CiudadProducto> asociaciones = ciudadProductoRepository.findByCiudad(ciudad);

                asociaciones.stream()
                                .filter(cp -> cp.getProducto().getId() == productoId)
                                .forEach(cp -> ciudadProductoRepository.delete(cp));
        }

        @Transactional
        public void agregarRuta(Long ciudadId, Long rutaId) {
                Ciudad ciudad = ciudadRepository.findById(ciudadId)
                                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));
                Ruta ruta = rutaRepository.findById(rutaId)
                                .orElseThrow(() -> new RuntimeException("Ruta no encontrada"));

                CiudadRuta nuevaAsociacion = new CiudadRuta(ciudad, ruta.getCiudadDestino(), ruta.getCiudadOrigen(),
                                ruta);
                ciudadRutaRepository.save(nuevaAsociacion);
        }

        @Transactional
        public void eliminarRuta(Long ciudadId, Long rutaId) {
                Ciudad ciudad = ciudadRepository.findById(ciudadId)
                                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));
                List<CiudadRuta> rutas = ciudadRutaRepository.findByCiudad(ciudad);

                rutas.stream()
                                .filter(cr -> cr.getRuta().getId() == rutaId)
                                .forEach(cr -> ciudadRutaRepository.delete(cr));
        }

}
