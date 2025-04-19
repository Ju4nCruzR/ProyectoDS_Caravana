package co.edu.javeriana.caravana_medieval_cruz_f_ss.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Ciudad;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.CiudadProducto;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.CiudadRuta;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.CiudadServicio;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.CiudadRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.ProductoRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.RutaRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.ServicioRepository;

@Service
public class CiudadService {

    @Autowired
    private CiudadRepository ciudadRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private RutaRepository rutaRepository;

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
    @Transactional
public Ciudad actualizarCiudadConAsociaciones(Long id, Ciudad ciudadActualizada,
        List<Long> productoIds,
        List<Long> servicioIds,
        List<Long> rutaIds) {

    Ciudad ciudad = ciudadRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));

    ciudad.setNombreCiudad(ciudadActualizada.getNombreCiudad());
    ciudad.setImpuestosDeEntradaCiudad(ciudadActualizada.getImpuestosDeEntradaCiudad());

    // Productos
    if (productoIds != null) {
        ciudad.setProductosDisponibles(new ArrayList<>()); // <- Asegura lista mutable
        ciudad.getProductosDisponibles().addAll(productoIds.stream()
                .map(pid -> new CiudadProducto(
                        ciudad,
                        productoRepository.findById(pid)
                                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + pid)),
                        0))
                .toList());
    }

    // Servicios
    if (servicioIds != null) {
        ciudad.setServiciosDisponibles(new ArrayList<>()); // <- Lista mutable
        ciudad.getServiciosDisponibles().addAll(servicioIds.stream()
                .map(sid -> new CiudadServicio(
                        ciudad,
                        servicioRepository.findById(sid)
                                .orElseThrow(() -> new RuntimeException("Servicio no encontrado: " + sid))))
                .toList());
    }

    // Rutas
    if (rutaIds != null) {
        ciudad.setRutasAsociadas(new ArrayList<>()); // <- Lista mutable
        ciudad.getRutasAsociadas().addAll(rutaIds.stream()
                .map(rid -> new CiudadRuta(
                        ciudad,
                        rutaRepository.findById(rid)
                                .orElseThrow(() -> new RuntimeException("Ruta no encontrada: " + rid))))
                .toList());
    }

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
                .collect(Collectors.toList());
    }
}
