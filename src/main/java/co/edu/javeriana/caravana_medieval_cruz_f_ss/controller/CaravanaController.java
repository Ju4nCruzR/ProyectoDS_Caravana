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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Caravana;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.CaravanaProducto;
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

    // Caso 1: crear caravana
    @GetMapping("/nueva")
    public ModelAndView mostrarFormularioCrearCaravana() {
        return new ModelAndView("caravana-form").addObject("caravana", new Caravana());
    }

    // Caso 1 (POST): procesar creación de caravana
    @PostMapping("/nueva")
    public String crearCaravana(@ModelAttribute Caravana caravana) {
        caravanaService.crearCaravana(caravana);
        return "redirect:/caravana/list";
    }

    // Caso 2: consultar caravana por ID
    @GetMapping("/{id}")
    public ModelAndView verCaravana(@PathVariable Long id) {
        Caravana caravana = caravanaService.buscarCaravanaPorId(id)
                .orElseThrow(() -> new RuntimeException("Caravana no encontrada"));
        return new ModelAndView("caravana-detalle").addObject("caravana", caravana);
    }

    // Caso 3: listar todas las caravanas
    @GetMapping("/list")
    public ModelAndView listarCaravanas() {
        List<Caravana> caravanas = caravanaService.listarCaravanas();
        ModelAndView modelAndView = new ModelAndView("caravana-list");
        modelAndView.addObject("listaCaravanas", caravanas);
        return modelAndView;
    }

    // Caso 4: mover caravana a otra ciudad
    @PostMapping("/{id}/mover")
    public String moverCaravana(@PathVariable Long id, @RequestParam Long ciudadId) {
        caravanaService.moverCaravana(id, ciudadId);
        return "redirect:/caravana/" + id;
    }

    @GetMapping("/{id}/mover")
    public ModelAndView mostrarFormularioMover(@PathVariable Long id) {
        Caravana caravana = caravanaService.buscarCaravanaPorId(id)
                .orElseThrow(() -> new RuntimeException("Caravana no encontrada"));

        List<Ciudad> ciudades = ciudadRepository.findAll();

        ModelAndView modelAndView = new ModelAndView("caravana-mover");
        modelAndView.addObject("caravana", caravana);
        modelAndView.addObject("ciudades", ciudades);

        return modelAndView;
    }

    // Caso 5: comprar producto
    @PostMapping("/{id}/comprar")
    public String comprarProducto(@PathVariable Long id,
            @RequestParam Long productoId,
            @RequestParam int cantidad) {
        caravanaService.comprarProducto(id, productoId, cantidad);
        return "redirect:/caravana/" + id;
    }

    @GetMapping("/{id}/comprar")
    public ModelAndView mostrarFormularioComprar(@PathVariable Long id) {
        Caravana caravana = caravanaService.buscarCaravanaPorId(id)
                .orElseThrow(() -> new RuntimeException("Caravana no encontrada"));

        List<CiudadProducto> productosDisponibles = ciudadProductoRepository
                .findByCiudad(caravana.getCiudadActual());

        ModelAndView modelAndView = new ModelAndView("caravana-comprar");
        modelAndView.addObject("caravana", caravana);
        modelAndView.addObject("productosDisponibles", productosDisponibles);

        return modelAndView;
    }

    // Caso 6: vender producto
    @GetMapping("/{id}/vender")
public ModelAndView mostrarFormularioVender(@PathVariable Long id,
                                            @RequestParam(value = "error", required = false) String error) {
    Caravana caravana = caravanaService.buscarCaravanaPorId(id)
            .orElseThrow(() -> new RuntimeException("Caravana no encontrada"));

    List<CaravanaProducto> productosEnCaravana = caravana.getProductos();

    ModelAndView modelAndView = new ModelAndView("caravana-vender");
    modelAndView.addObject("caravana", caravana);
    modelAndView.addObject("productosEnCaravana", productosEnCaravana);
    modelAndView.addObject("error", error);

    return modelAndView;
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

    // Caso 7: aplicar servicio
    @PostMapping("/{id}/servicio")
    public String aplicarServicio(@PathVariable Long id,
            @RequestParam Long servicioId) {
        caravanaService.aplicarServicio(id, servicioId);
        return "redirect:/caravana/" + id;
    }

    @GetMapping("/{id}/servicio")
    public ModelAndView mostrarFormularioServicio(@PathVariable Long id) {
        Caravana caravana = caravanaService.buscarCaravanaPorId(id)
                .orElseThrow(() -> new RuntimeException("Caravana no encontrada"));

        List<CiudadServicio> serviciosDisponibles = ciudadServicioRepository
                .findByCiudad(caravana.getCiudadActual());

        ModelAndView modelAndView = new ModelAndView("caravana-servicio");
        modelAndView.addObject("caravana", caravana);
        modelAndView.addObject("serviciosDisponibles", serviciosDisponibles);

        return modelAndView;
    }

    // Caso 8: obtener productos
    @GetMapping("/{id}/productos")
    @ResponseBody
    public List<CaravanaProducto> obtenerProductos(@PathVariable Long id) {
        return caravanaService.obtenerProductos(id);
    }

    // Caso 9: eliminar caravana
    @PostMapping("/{id}/eliminar")
    public String eliminarCaravana(@PathVariable Long id) {
        caravanaService.eliminarCaravana(id);
        return "redirect:/caravana/list";
    }

    // Confirmación antes de eliminar
    @GetMapping("/{id}/eliminar")
    public ModelAndView mostrarConfirmacionEliminar(@PathVariable Long id) {
        Caravana caravana = caravanaService.buscarCaravanaPorId(id)
                .orElseThrow(() -> new RuntimeException("Caravana no encontrada"));

        return new ModelAndView("caravana-eliminar")
                .addObject("caravana", caravana);
    }

    // Caso 10: agregar jugador
    @PostMapping("/{id}/jugadores")
    public String agregarJugador(@PathVariable Long id,
            @RequestParam String nombre,
            @RequestParam Jugador.Rol rol) {
        caravanaService.agregarJugador(id, nombre, rol);
        return "redirect:/caravana/" + id;
    }

    @GetMapping("/{id}/jugadores")
    public ModelAndView mostrarFormularioJugador(@PathVariable Long id) {
        Caravana caravana = caravanaService.buscarCaravanaPorId(id)
                .orElseThrow(() -> new RuntimeException("Caravana no encontrada"));

        return new ModelAndView("caravana-jugador")
                .addObject("caravana", caravana);
    }

    // Caso 11: Mostrar formulario de edición
    @GetMapping("/{id}/editar")
    public ModelAndView mostrarFormularioEditar(@PathVariable Long id) {
        Caravana caravana = caravanaService.buscarCaravanaPorId(id)
                .orElseThrow(() -> new RuntimeException("Caravana no encontrada"));

        List<Ciudad> ciudades = ciudadRepository.findAll();

        return new ModelAndView("caravana-editar")
                .addObject("caravana", caravana)
                .addObject("ciudades", ciudades);
    }

    // Caso 11 (POST): Actualizar caravana
    @PostMapping("/{id}/editar")
    public String actualizarCaravana(@PathVariable Long id, @ModelAttribute Caravana caravana) {
        caravanaService.actualizarCaravana(id, caravana);
        return "redirect:/caravana/" + id;
    }

}
