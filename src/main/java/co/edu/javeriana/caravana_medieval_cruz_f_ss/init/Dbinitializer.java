package co.edu.javeriana.caravana_medieval_cruz_f_ss.init;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.*;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Dbinitializer implements CommandLineRunner {
    
    @Autowired
    private CaravanaProductoRepository caravanaProductoRepository;

    @Autowired
    private CaravanaRepository caravanaRepository;

    @Autowired
    private CiudadProductoRepository ciudadProductoRepository;

    @Autowired
    private CiudadRepository ciudadRepository;

    @Autowired
    private CiudadServicioRepository ciudadServicioRepository;

    @Autowired
    private JuegoRepository juegoRepository;

    @Autowired
    private JugadorRepository jugadorRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private RutaRepository rutaRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    @Override
    public void run(String... args) throws Exception {
        Ciudad ciudad = new Ciudad("1234", "Bogotown", "2.500");

        ciudadRepository.save(ciudad);

        ciudadRepository.save(new Ciudad("1235", "Medellin", "1000"));

        for (int i = 0; i < 100; i++){
            ciudadRepository.save(new Ciudad("1236" + i, "Cali" + i, "1000" + i));
        }
    }
}
