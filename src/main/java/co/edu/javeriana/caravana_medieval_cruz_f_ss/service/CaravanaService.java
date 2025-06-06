package co.edu.javeriana.caravana_medieval_cruz_f_ss.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CaravanaDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CaravanaDetalleDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CaravanaFormularioDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CaravanaProductoDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.mapper.CaravanaMapper;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.mapper.CaravanaProductoMapper;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Caravana;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.CaravanaProducto;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Ciudad;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.CiudadProducto;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.CiudadServicio;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Juego;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Jugador;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Producto;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Ruta;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Servicio;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.CaravanaProductoRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.CaravanaRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.CiudadProductoRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.CiudadRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.CiudadServicioRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.JuegoRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.JugadorRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.ProductoRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.RutaRepository;

@Service
public class CaravanaService {
    @Autowired
    private CaravanaRepository caravanaRepository;

    @Autowired
    private CiudadRepository ciudadRepository;

    @Autowired
    private CiudadProductoRepository ciudadProductoRepository;

    @Autowired
    private RutaRepository rutaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CaravanaProductoRepository caravanaProductoRepository;

    @Autowired
    private CiudadServicioRepository ciudadServicioRepository;

    @Autowired
    private JugadorRepository jugadorRepository;

    @Autowired
    private JuegoRepository juegoRepository;

    public CaravanaDTO crearCaravanaDesdeFormulario(CaravanaFormularioDTO dto) {
        Ciudad ciudad = ciudadRepository.findById(dto.getCiudadId())
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));

        Caravana caravana = new Caravana();
        aplicarDatosDesdeFormulario(caravana, dto, ciudad);

        return CaravanaMapper.toDTO(caravanaRepository.save(caravana));
    }

    public CaravanaDetalleDTO buscarCaravanaPorId(Long id) {
        Caravana caravana = caravanaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Caravana no encontrada"));
        return CaravanaMapper.toDetalle(caravana);
    }

    public List<CaravanaDTO> listarCaravanas() {
        return caravanaRepository.findAll().stream()
                .map(CaravanaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CaravanaDTO moverCaravana(Long caravanaId, Long ciudadId) {
        Caravana caravana = caravanaRepository.findById(caravanaId)
                .orElseThrow(() -> new RuntimeException("Caravana no encontrada"));
        Ciudad nuevaCiudad = ciudadRepository.findById(ciudadId)
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));

        List<Ruta> rutas = rutaRepository.findAll();
        Optional<Ruta> rutaOpt = rutas.stream()
                .filter(r -> r.getCiudadOrigen().equals(caravana.getCiudadActual()) &&
                        r.getCiudadDestino().equals(nuevaCiudad))
                .findFirst();

        if (rutaOpt.isEmpty()) {
            throw new RuntimeException("No existe una ruta directa desde " +
                    caravana.getCiudadActual().getNombreCiudad() + " hasta " +
                    nuevaCiudad.getNombreCiudad());
        }

        Ruta rutaUsada = rutaOpt.get();

        if (!rutaUsada.isEsSeguraRuta()) {
            int nuevoPV = caravana.getPuntosDeVidaCaravana() - rutaUsada.getDanoRuta();
            caravana.setPuntosDeVidaCaravana(Math.max(0, nuevoPV));
        }

        caravana.setCiudadActual(nuevaCiudad);
        caravana.getRutasRecorridas().add(rutaUsada);

        return CaravanaMapper.toDTO(caravanaRepository.save(caravana));
    }

    public CaravanaDTO actualizarCaravanaDesdeFormulario(Long id, CaravanaFormularioDTO dto) {
        Caravana caravana = caravanaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Caravana no encontrada"));

        Ciudad ciudad = ciudadRepository.findById(dto.getCiudadId())
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));

        aplicarDatosDesdeFormulario(caravana, dto, ciudad);

        return CaravanaMapper.toDTO(caravanaRepository.save(caravana));
    }

    private void aplicarDatosDesdeFormulario(Caravana caravana, CaravanaFormularioDTO dto, Ciudad ciudad) {
        caravana.setNombreCaravana(dto.getNombreCaravana());
        caravana.setVelocidadCaravana(dto.getVelocidadCaravana());
        caravana.setCapacidadMaximaCargaCaravana(dto.getCapacidadMaximaCargaCaravana());
        caravana.setDineroDisponibleCaravana(dto.getDineroDisponibleCaravana());
        caravana.setPuntosDeVidaCaravana(dto.getPuntosDeVidaCaravana());
        caravana.setCiudadActual(ciudad);
    }

    public List<CaravanaProductoDTO> obtenerProductos(Long caravanaId) {
        Caravana caravana = caravanaRepository.findById(caravanaId)
                .orElseThrow(() -> new RuntimeException("Caravana no encontrada"));
        return caravana.getProductos().stream()
                .map(CaravanaProductoMapper::toDTO)
                .collect(Collectors.toList());
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

        double pesoActual = caravana.getProductos().stream()
                .mapToDouble(p -> p.getProducto().getPesoProducto() * p.getStockEnCaravana())
                .sum();

        double pesoProducto = producto.getPesoProducto() * cantidad;
        double nuevaCarga = pesoActual + pesoProducto;

        if (nuevaCarga > caravana.getCapacidadMaximaCargaCaravana())
            throw new RuntimeException("La caravana no puede cargar más peso");

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
        caravanaRepository.save(caravana);
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

        caravana.setDineroDisponibleCaravana(
                caravana.getDineroDisponibleCaravana() + precioTotal);

        cp.setStockEnCaravana(cp.getStockEnCaravana() - cantidad);

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
        double precio = servicio.getPrecioServicio();
    
        if (caravana.getServiciosAplicados().contains(servicio)) {
            throw new RuntimeException("Este servicio ya fue aplicado a esta caravana.");
        }
    
        if (servicio.getTipo() == Servicio.TipoServicio.REPARAR && caravana.getPuntosDeVidaCaravana() >= 100) {
            throw new RuntimeException("La caravana ya tiene la vida al máximo. No se puede reparar más.");
        }
    
        if (caravana.getDineroDisponibleCaravana() < precio) {
            throw new RuntimeException("La caravana no tiene suficiente dinero para adquirir este servicio.");
        }
    
        caravana.setDineroDisponibleCaravana(caravana.getDineroDisponibleCaravana() - precio);
    
        switch (servicio.getTipo()) {
            case REPARAR -> caravana.setPuntosDeVidaCaravana(100);
            case MEJORAR_CAPACIDAD -> caravana.setCapacidadMaximaCargaCaravana(
                    caravana.getCapacidadMaximaCargaCaravana() + 50);
            case MEJORAR_VELOCIDAD -> caravana.setVelocidadCaravana(
                    caravana.getVelocidadCaravana() + 10);
            case GUARDIAS -> {
                int nuevosPuntos = Math.min(100, caravana.getPuntosDeVidaCaravana() + 20);
                caravana.setPuntosDeVidaCaravana(nuevosPuntos);
            }
        }
    
        // 🔐 Registrar que el servicio ya fue aplicado
        caravana.getServiciosAplicados().add(servicio);
    
        caravanaRepository.save(caravana);
    }
    

    public List<CaravanaProducto> obtenerProductosEntidad(Long caravanaId) {
        Caravana caravana = caravanaRepository.findById(caravanaId)
                .orElseThrow(() -> new RuntimeException("Caravana no encontrada"));
        return caravana.getProductos();
    }

    @Transactional
    public void eliminarCaravana(Long id) {
        Caravana caravana = caravanaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Caravana no encontrada"));

        caravanaProductoRepository.deleteAll(caravana.getProductos());

        List<Juego> juegosConCaravana = juegoRepository.findAll().stream()
                .filter(j -> j.getCaravanas().contains(caravana))
                .toList();
        for (Juego juego : juegosConCaravana) {
            juego.getCaravanas().remove(caravana);
            juegoRepository.save(juego);
        }

        List<Jugador> jugadores = jugadorRepository.findByCaravana(caravana);
        for (Jugador jugador : jugadores) {
            List<Juego> juegosDelJugador = juegoRepository.findByJugadoresContains(jugador);
            for (Juego juego : juegosDelJugador) {
                juego.getJugadores().remove(jugador);
                juegoRepository.save(juego);
            }
            jugadorRepository.delete(jugador);
        }

        caravanaRepository.delete(caravana);
    }

    public Jugador agregarJugador(Long caravanaId, String nombre, Jugador.Rol rol) {
        Caravana caravana = caravanaRepository.findById(caravanaId)
                .orElseThrow(() -> new RuntimeException("Caravana no encontrada"));

        Jugador jugador = new Jugador(caravana, nombre, rol);
        return jugadorRepository.save(jugador);
    }

}
