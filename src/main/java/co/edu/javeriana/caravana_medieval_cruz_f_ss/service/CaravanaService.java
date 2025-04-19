package co.edu.javeriana.caravana_medieval_cruz_f_ss.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Caravana;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.CaravanaProducto;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Ciudad;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.CiudadProducto;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.CiudadServicio;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Jugador;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Producto;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Servicio;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.CaravanaProductoRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.CaravanaRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.CiudadProductoRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.CiudadRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.CiudadServicioRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.JugadorRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.ProductoRepository;

@Service
public class CaravanaService {
    @Autowired
    private CaravanaRepository caravanaRepository;

    @Autowired
    private CiudadRepository ciudadRepository;

    @Autowired
    private CiudadProductoRepository ciudadProductoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CaravanaProductoRepository caravanaProductoRepository;

    @Autowired
    private CiudadServicioRepository ciudadServicioRepository;

    @Autowired
    private JugadorRepository jugadorRepository;

    public Caravana crearCaravana(Caravana caravana) {
        return caravanaRepository.save(caravana);
    }

    public Optional<Caravana> buscarCaravanaPorId(Long id) {
        return caravanaRepository.findById(id);
    }

    public List<Caravana> listarCaravanas() {
        return caravanaRepository.findAll();
    }

    public Caravana moverCaravana(Long caravanaId, Long ciudadId) {
        Caravana caravana = caravanaRepository.findById(caravanaId)
                .orElseThrow(() -> new RuntimeException("Caravana no encontrada"));
        Ciudad nuevaCiudad = ciudadRepository.findById(ciudadId)
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));

        caravana.setCiudadActual(nuevaCiudad);
        caravana.getRutasRecorridas().addAll(nuevaCiudad.getRutasOrigen());
        return caravanaRepository.save(caravana);
    }

    public void comprarProducto(Long caravanaId, Long productoId, int cantidad) {
        Caravana caravana = caravanaRepository.findById(caravanaId)
                .orElseThrow(() -> new RuntimeException("Caravana no encontrada"));
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        Ciudad ciudad = caravana.getCiudadActual();
    
        CiudadProducto cp = ciudadProductoRepository.findByCiudadAndProducto(ciudad, producto)
                .orElseThrow(() -> new RuntimeException("Producto no disponible en esta ciudad"));
    
        if (cp.getStockProducto() < cantidad)
            throw new RuntimeException("Stock insuficiente");
    
        double precioTotal = producto.getPrecioBaseProducto() * cantidad;
    
        if (caravana.getDineroDisponibleCaravana() < precioTotal)
            throw new RuntimeException("Dinero insuficiente");
    
        CaravanaProducto existente = caravanaProductoRepository.findByCaravanaAndProducto(caravana, producto)
                .orElse(null);
    
        if (existente == null) {
            existente = new CaravanaProducto(caravana, producto, cantidad);
        } else {
            existente.setStockEnCaravana(existente.getStockEnCaravana() + cantidad);
        }
    
        cp.setStockProducto(cp.getStockProducto() - cantidad);
        caravana.setDineroDisponibleCaravana(caravana.getDineroDisponibleCaravana() - precioTotal);
    
        ciudadProductoRepository.save(cp);
        caravanaProductoRepository.save(existente);
        caravanaRepository.save(caravana); // guardar los cambios de dinero
    }

    public void venderProducto(Long caravanaId, Long productoId, int cantidad) {
        Caravana caravana = caravanaRepository.findById(caravanaId)
                .orElseThrow(() -> new RuntimeException("Caravana no encontrada"));
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        Ciudad ciudad = caravana.getCiudadActual();
    
        CaravanaProducto cp = caravanaProductoRepository.findByCaravanaAndProducto(caravana, producto)
                .orElseThrow(() -> new RuntimeException("Producto no está en la caravana"));
    
        if (cp.getStockEnCaravana() < cantidad) {
            throw new IllegalArgumentException("Stock insuficiente en la caravana para vender.");
        }
        
        double precioTotal = producto.getPrecioBaseProducto() * cantidad;

        // Aumentar el dinero de la caravana
        caravana.setDineroDisponibleCaravana(caravana.getDineroDisponibleCaravana() + precioTotal);
    
        // Reducir stock en caravana
        cp.setStockEnCaravana(cp.getStockEnCaravana() - cantidad);
    
        // Aumentar stock en ciudad
        CiudadProducto ciudadProducto = ciudadProductoRepository.findByCiudadAndProducto(ciudad, producto)
                .orElse(null);
    
        if (ciudadProducto == null) {
            ciudadProducto = new CiudadProducto(ciudad, producto, cantidad);
        } else {
            ciudadProducto.setStockProducto(ciudadProducto.getStockProducto() + cantidad);
        }
    
        ciudadProductoRepository.save(ciudadProducto);
        caravanaProductoRepository.save(cp);
        caravanaRepository.save(caravana);
    }
    
    public void aplicarServicio(Long caravanaId, Long servicioId) {
        Caravana caravana = caravanaRepository.findById(caravanaId)
                .orElseThrow(() -> new RuntimeException("Caravana no encontrada"));
        Ciudad ciudad = caravana.getCiudadActual();

        CiudadServicio cs = ciudadServicioRepository.findByCiudadAndServicio_Id(ciudad, servicioId)
                .orElseThrow(() -> new RuntimeException("Servicio no disponible en esta ciudad"));

        Servicio servicio = cs.getServicio();

        switch (servicio.getTipo()) {
            case REPARAR -> caravana.setPuntosDeVidaCaravana(100);
            case MEJORAR_CAPACIDAD -> caravana.setCapacidadMaximaCargaCaravana(caravana.getCapacidadMaximaCargaCaravana() + 50);
            case MEJORAR_VELOCIDAD -> caravana.setVelocidadCaravana(caravana.getVelocidadCaravana() + 10);
            case GUARDIAS -> {
                int nuevosPuntos = Math.min(100, caravana.getPuntosDeVidaCaravana() + 20);
                caravana.setPuntosDeVidaCaravana(nuevosPuntos);
            }
        }        

        caravanaRepository.save(caravana);
    }

    public List<CaravanaProducto> obtenerProductos(Long caravanaId) {
        Caravana caravana = caravanaRepository.findById(caravanaId)
                .orElseThrow(() -> new RuntimeException("Caravana no encontrada"));
        return caravana.getProductos();
    }

    public void eliminarCaravana(Long id) {
        caravanaRepository.deleteById(id);
    }

    public Jugador agregarJugador(Long caravanaId, String nombre, Jugador.Rol rol) {
        Caravana caravana = caravanaRepository.findById(caravanaId)
                .orElseThrow(() -> new RuntimeException("Caravana no encontrada"));

        Jugador jugador = new Jugador(caravana, nombre, rol);
        return jugadorRepository.save(jugador);
    }

    public Caravana actualizarCaravana(Long id, Caravana datos) {
        Caravana caravana = caravanaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Caravana no encontrada"));

        caravana.setNombreCaravana(datos.getNombreCaravana());
        caravana.setVelocidadCaravana(datos.getVelocidadCaravana());
        caravana.setCapacidadMaximaCargaCaravana(datos.getCapacidadMaximaCargaCaravana());
        caravana.setDineroDisponibleCaravana(datos.getDineroDisponibleCaravana());
        caravana.setPuntosDeVidaCaravana(datos.getPuntosDeVidaCaravana());
        caravana.setCiudadActual(datos.getCiudadActual());

        return caravanaRepository.save(caravana);
    }

}
