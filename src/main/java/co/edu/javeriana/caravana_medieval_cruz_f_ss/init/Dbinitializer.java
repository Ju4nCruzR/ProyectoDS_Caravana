package co.edu.javeriana.caravana_medieval_cruz_f_ss.init;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.*;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.*;

@Component
public class DbInitializer implements CommandLineRunner {

    @Autowired 
    private CiudadRepository ciudadRepository;

    @Autowired 
    private RutaRepository rutaRepository;

    @Autowired 
    private ProductoRepository productoRepository;
    
    @Autowired 
    private ServicioRepository servicioRepository;
    
    @Autowired 
    private CiudadProductoRepository ciudadProductoRepository;
    
    @Autowired
    private CiudadServicioRepository ciudadServicioRepository;
    
    @Autowired 
    private CiudadRutaRepository ciudadRutaRepository;
    
    @Autowired 
    private CaravanaRepository caravanaRepository;
    
    @Autowired 
    private JugadorRepository jugadorRepository;
    
    @Autowired 
    private CaravanaProductoRepository caravanaProductoRepository;
    
    @Autowired 
    private JuegoRepository juegoRepository;

    @Override
public void run(String... args) throws Exception {

    List<Producto> productos = new ArrayList<>();
    productos.add(productoRepository.save(new Producto(null, null, 1.5, 2.0, "Especias del dragón")));
    productos.add(productoRepository.save(new Producto(null, null, 2.0, 3.5, "Tela")));
    productos.add(productoRepository.save(new Producto(null, null, 0.8, 1.0, "Martillo")));
    for (int i = 4; i <= 50; i++) {
        Producto producto = new Producto(null, null, 1.0 + (i % 3), 1.0 + (i % 2), "Producto" + i);
        productos.add(productoRepository.save(producto));
    }

    List<Servicio> servicios = new ArrayList<>();
    servicios.add(servicioRepository.save(new Servicio(new ArrayList<>(), 300, Servicio.TipoServicio.REPARAR)));
    servicios.add(servicioRepository.save(new Servicio(new ArrayList<>(), 500, Servicio.TipoServicio.MEJORAR_CAPACIDAD)));
    servicios.add(servicioRepository.save(new Servicio(new ArrayList<>(), 450, Servicio.TipoServicio.MEJORAR_VELOCIDAD)));
    servicios.add(servicioRepository.save(new Servicio(new ArrayList<>(), 400, Servicio.TipoServicio.GUARDIAS)));

    List<Ciudad> ciudades = new ArrayList<>();
    ciudades.add(ciudadRepository.save(new Ciudad(2500, "Bogotown", new ArrayList<CiudadProducto>(),
    new ArrayList<Ruta>(), new ArrayList<Ruta>(), new ArrayList<CiudadServicio>(), new ArrayList<CiudadRuta>())));
    ciudades.add(ciudadRepository.save(new Ciudad(1500, "LazyTown", new ArrayList<CiudadProducto>(),
    new ArrayList<Ruta>(), new ArrayList<Ruta>(), new ArrayList<CiudadServicio>(), new ArrayList<CiudadRuta>())));
    for (int i = 0; i < 98; i++) {
        Ciudad ciudad = new Ciudad(1000 + i, "Cali" + i, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        ciudades.add(ciudadRepository.save(ciudad));
    }

    List<Ruta> rutas = new ArrayList<>();
    for (int i = 0; i < ciudades.size() - 2; i++) {
        Ciudad origen = ciudades.get(i);
        Ciudad destino1 = ciudades.get(i + 1);
        Ciudad destino2 = ciudades.get(i + 2);

        Ruta ruta1 = new Ruta(destino1, origen, 5 + (i % 3), 80 + i, i % 2 == 0);
        Ruta ruta2 = new Ruta(destino2, origen, 6 + (i % 4), 100 + i, i % 3 == 0);

        rutas.add(rutaRepository.save(ruta1));
        rutas.add(rutaRepository.save(ruta2));

        ciudadRutaRepository.save(new CiudadRuta(origen, ruta1));
        ciudadRutaRepository.save(new CiudadRuta(origen, ruta2));
    }

    for (Ciudad ciudad : ciudades) {
        for (int i = 0; i < 10; i++) {
            Producto producto = productos.get(i);
            CiudadProducto cp = new CiudadProducto(ciudad, producto, 20 + i);
            ciudadProductoRepository.save(cp);
        }
    }

    for (Ciudad ciudad : ciudades) {
        for (Servicio servicio : servicios) {
            ciudadServicioRepository.save(new CiudadServicio(ciudad, servicio));
        }
    }

    List<Caravana> caravanas = new ArrayList<>();
    Ciudad ciudadInicial = ciudades.get(0);
    caravanas.add(caravanaRepository.save(new Caravana(400, ciudadInicial, 2500, new ArrayList<>(), "La concentida", new ArrayList<>(), 100, new ArrayList<>(), 35)));
    caravanas.add(caravanaRepository.save(new Caravana(350, ciudadInicial, 2000, new ArrayList<>(), "Los piratas", new ArrayList<>(), 95, new ArrayList<>(), 30)));
    for (int i = 3; i <= 10; i++) {
        Caravana caravana = new Caravana(300 + i, ciudadInicial, 2000 + (i * 100), new ArrayList<>(), "Caravana" + i, new ArrayList<>(), 90 + i, new ArrayList<>(), 30 + i);
        caravanas.add(caravanaRepository.save(caravana));
    }

    // 8. Crear dos jugadores por caravana
    int jugadorContador = 1;
    for (Caravana caravana : caravanas) {
        Jugador j1 = new Jugador(caravana, "Jugador" + jugadorContador++, Jugador.Rol.CARAVANERO);
        Jugador j2 = new Jugador(caravana, "Jugador" + jugadorContador++, Jugador.Rol.COMERCIANTE);
        jugadorRepository.save(j1);
        jugadorRepository.save(j2);
    }

    for (Caravana caravana : caravanas) {
        for (int i = 0; i < 5; i++) {
            Producto producto = productos.get(i);
            CaravanaProducto cp = new CaravanaProducto(caravana, producto, 5 + i);
            caravanaProductoRepository.save(cp);
        }
    }

    Juego juego = new Juego(caravanas, jugadorRepository.findAll(), 5000.0, 100, 0);
    juegoRepository.save(juego);
}

}

