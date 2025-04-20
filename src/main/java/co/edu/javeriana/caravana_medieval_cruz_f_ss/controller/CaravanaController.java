package co.edu.javeriana.caravana_medieval_cruz_f_ss.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CaravanaDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CaravanaDetalleDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CaravanaFormularioDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CaravanaProductoDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Ciudad;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.CiudadProducto;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.CiudadServicio;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Jugador;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.CiudadProductoRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.CiudadRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.CiudadServicioRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.service.CaravanaService;

@Controller
@RequestMapping("/caravana")
public class CaravanaController {
        @Autowired
        private CaravanaService caravanaService;

        @Autowired
        private CiudadRepository ciudadRepository;

        @Autowired
        private CiudadProductoRepository ciudadProductoRepository;

        @Autowired
        private CiudadServicioRepository ciudadServicioRepository;

        @GetMapping("/nueva")
        public ModelAndView mostrarFormularioCrearCaravana() {
                List<Ciudad> ciudades = ciudadRepository.findAll();
                return new ModelAndView("caravanaTemplates/caravana-form")
                                .addObject("caravana", new CaravanaFormularioDTO())
                                .addObject("ciudades", ciudades);
        }

        @PostMapping("/nueva")
        public String crearCaravana(@ModelAttribute CaravanaFormularioDTO dto) {
                caravanaService.crearCaravanaDesdeFormulario(dto);
                return "redirect:/caravana/list";
        }

        @GetMapping("/{id}")
        public ModelAndView verCaravana(@PathVariable Long id) {
                CaravanaDetalleDTO detalle = caravanaService.buscarCaravanaPorId(id);
                return new ModelAndView("caravanaTemplates/caravana-detalle")
                                .addObject("caravana", detalle.getCaravana())
                                .addObject("pesoActualCargado", detalle.getProductos() != null
                                                ? detalle.getProductos().stream().mapToDouble(p -> p.getPesoTotal())
                                                                .sum()
                                                : 0);
        }

        @GetMapping("/list")
        public ModelAndView listarCaravanas() {
                List<CaravanaDTO> caravanas = caravanaService.listarCaravanas();
                return new ModelAndView("caravanaTemplates/caravana-list")
                                .addObject("listaCaravanas", caravanas);
        }

        @PostMapping("/{id}/mover")
        public String moverCaravana(@PathVariable Long id, @RequestParam Long ciudadId) {
                caravanaService.moverCaravana(id, ciudadId);
                return "redirect:/caravana/" + id;
        }

        @GetMapping("/{id}/mover")
        public ModelAndView mostrarFormularioMover(@PathVariable Long id) {
                CaravanaDetalleDTO detalle = caravanaService.buscarCaravanaPorId(id);
                Ciudad ciudadActual = ciudadRepository.findById(detalle.getCaravana().getId()).orElseThrow();

                List<Ciudad> destinosDisponibles = ciudadActual.getRutasOrigen().stream()
                                .map(cr -> cr.getRuta().getCiudadDestino()) 
                                .distinct()
                                .toList();

                return new ModelAndView("caravanaTemplates/caravana-mover")
                                .addObject("caravana", detalle.getCaravana())
                                .addObject("ciudades", destinosDisponibles);
        }

        @PostMapping("/{id}/comprar")
        public ModelAndView comprarProducto(@PathVariable Long id,
                        @RequestParam Long productoId,
                        @RequestParam int cantidad) {
                try {
                        caravanaService.comprarProducto(id, productoId, cantidad);
                        return new ModelAndView("redirect:/caravana/" + id);
                } catch (RuntimeException e) {
                        CaravanaDetalleDTO detalle = caravanaService.buscarCaravanaPorId(id);

                        // Buscar la entidad Ciudad a partir del nombre de la ciudad actual
                        Ciudad ciudad = ciudadRepository
                                        .findByNombreCiudad(detalle.getCaravana().getNombreCiudadActual())
                                        .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));

                        List<CiudadProducto> productosDisponibles = ciudadProductoRepository.findByCiudad(ciudad);

                        double pesoActual = detalle.getProductos().stream()
                                        .mapToDouble(CaravanaProductoDTO::getPesoTotal)
                                        .sum();

                        return new ModelAndView("caravana-comprar")
                                        .addObject("caravana", detalle.getCaravana())
                                        .addObject("productosDisponibles", productosDisponibles)
                                        .addObject("pesoActual", pesoActual)
                                        .addObject("error", e.getMessage());
                }
        }

        @GetMapping("/{id}/comprar")
        public ModelAndView mostrarFormularioComprar(@PathVariable Long id) {
                CaravanaDetalleDTO detalle = caravanaService.buscarCaravanaPorId(id);

                // Buscar la entidad Ciudad a partir del nombre
                Ciudad ciudad = ciudadRepository.findByNombreCiudad(detalle.getCaravana().getNombreCiudadActual())
                                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));

                List<CiudadProducto> productosDisponibles = ciudadProductoRepository.findByCiudad(ciudad);
                double pesoActual = detalle.getProductos().stream()
                                .mapToDouble(CaravanaProductoDTO::getPesoTotal)
                                .sum();

                return new ModelAndView("caravanaTemplates/caravana-comprar")
                                .addObject("caravana", detalle.getCaravana())
                                .addObject("productosDisponibles", productosDisponibles)
                                .addObject("pesoActual", pesoActual);
        }

        @GetMapping("/{id}/vender")
        public ModelAndView mostrarFormularioVender(@PathVariable Long id,
                        @RequestParam(value = "error", required = false) String error) {
                CaravanaDetalleDTO detalle = caravanaService.buscarCaravanaPorId(id);

                double pesoActual = detalle.getProductos().stream()
                                .mapToDouble(CaravanaProductoDTO::getPesoTotal)
                                .sum();

                return new ModelAndView("caravanaTemplates/caravana-vender")
                                .addObject("caravana", detalle.getCaravana())
                                .addObject("productosEnCaravana", detalle.getProductos())
                                .addObject("error", error)
                                .addObject("pesoActual", pesoActual);
        }

        @PostMapping("/{id}/vender")
        public String venderProducto(@PathVariable Long id,
                        @RequestParam Long productoId,
                        @RequestParam int cantidad) {
                try {
                        caravanaService.venderProducto(id, productoId, cantidad);
                } catch (IllegalArgumentException e) {
                        return "redirect:/caravana/" + id + "/vender?error=" + e.getMessage().replace(" ", "+");
                }
                return "redirect:/caravana/" + id;
        }

        @PostMapping("/{id}/servicio")
        public ModelAndView aplicarServicio(@PathVariable Long id, @RequestParam Long servicioId) {
                try {
                        caravanaService.aplicarServicio(id, servicioId);
                        return new ModelAndView("redirect:/caravana/" + id);
                } catch (RuntimeException e) {
                        CaravanaDetalleDTO detalle = caravanaService.buscarCaravanaPorId(id);

                        // Convertir nombre de ciudad a entidad Ciudad
                        Ciudad ciudad = ciudadRepository
                                        .findByNombreCiudad(detalle.getCaravana().getNombreCiudadActual())
                                        .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));

                        List<CiudadServicio> serviciosDisponibles = ciudadServicioRepository.findByCiudad(ciudad);

                        return new ModelAndView("caravanaTemplates/caravana-servicio")
                                        .addObject("caravana", detalle.getCaravana())
                                        .addObject("serviciosDisponibles", serviciosDisponibles)
                                        .addObject("error", e.getMessage());
                }
        }

        @GetMapping("/{id}/servicio")
        public ModelAndView mostrarFormularioServicio(@PathVariable Long id) {
                CaravanaDetalleDTO detalle = caravanaService.buscarCaravanaPorId(id);

                // Convertir nombre de ciudad a entidad Ciudad
                Ciudad ciudad = ciudadRepository.findByNombreCiudad(detalle.getCaravana().getNombreCiudadActual())
                                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));

                List<CiudadServicio> serviciosDisponibles = ciudadServicioRepository.findByCiudad(ciudad);

                return new ModelAndView("caravanaTemplates/caravana-servicio")
                                .addObject("caravana", detalle.getCaravana())
                                .addObject("serviciosDisponibles", serviciosDisponibles);
        }

        @GetMapping("/{id}/productos")
        public ModelAndView verProductos(@PathVariable Long id) {
                CaravanaDetalleDTO detalle = caravanaService.buscarCaravanaPorId(id);
                return new ModelAndView("caravanaTemplates/caravana-productos")
                                .addObject("caravana", detalle.getCaravana())
                                .addObject("productos", detalle.getProductos());
        }

        @PostMapping("/{id}/eliminar")
        public String eliminarCaravana(@PathVariable Long id) {
                caravanaService.eliminarCaravana(id);
                return "redirect:/caravana/list";
        }

        @GetMapping("/{id}/eliminar")
        public ModelAndView mostrarConfirmacionEliminar(@PathVariable Long id) {
                CaravanaDetalleDTO detalle = caravanaService.buscarCaravanaPorId(id);
                return new ModelAndView("caravanaTemplates/caravana-eliminar")
                                .addObject("caravana", detalle.getCaravana());
        }

        @PostMapping("/{id}/jugadores")
        public String agregarJugador(@PathVariable Long id,
                        @RequestParam String nombre,
                        @RequestParam Jugador.Rol rol) {
                caravanaService.agregarJugador(id, nombre, rol);
                return "redirect:/caravana/" + id;
        }

        @GetMapping("/{id}/jugadores")
        public ModelAndView mostrarFormularioJugador(@PathVariable Long id) {
                CaravanaDetalleDTO detalle = caravanaService.buscarCaravanaPorId(id);
                return new ModelAndView("caravanaTemplates/caravana-jugador")
                                .addObject("caravana", detalle.getCaravana());
        }

        @GetMapping("/{id}/editar")
        public ModelAndView mostrarFormularioEditar(@PathVariable Long id) {
                CaravanaDetalleDTO detalle = caravanaService.buscarCaravanaPorId(id);
                List<Ciudad> ciudades = ciudadRepository.findAll();

                CaravanaFormularioDTO formulario = new CaravanaFormularioDTO();
                formulario.setNombreCaravana(detalle.getCaravana().getNombreCaravana());
                formulario.setVelocidadCaravana(detalle.getCaravana().getVelocidadCaravana());
                formulario.setCapacidadMaximaCargaCaravana(detalle.getCaravana().getCapacidadMaximaCargaCaravana());
                formulario.setDineroDisponibleCaravana(detalle.getCaravana().getDineroDisponibleCaravana());
                formulario.setPuntosDeVidaCaravana(detalle.getCaravana().getPuntosDeVidaCaravana());

                Ciudad ciudad = ciudadRepository.findByNombreCiudad(detalle.getCaravana().getNombreCiudadActual())
                                .orElse(null);
                if (ciudad != null)
                        formulario.setCiudadId(ciudad.getId());

                return new ModelAndView("caravanaTemplates/caravana-editar")
                                .addObject("caravana", formulario)
                                .addObject("ciudades", ciudades)
                                .addObject("caravanaId", id);

        }

        @PostMapping("/{id}/editar")
        public String actualizarCaravana(@PathVariable Long id, @ModelAttribute CaravanaFormularioDTO dto) {
                caravanaService.actualizarCaravanaDesdeFormulario(id, dto);
                return "redirect:/caravana/" + id;
        }

}
