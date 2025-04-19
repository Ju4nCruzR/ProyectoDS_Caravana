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

import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Ciudad;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Producto;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Ruta;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Servicio;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.ProductoRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.RutaRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.ServicioRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.service.CiudadService;

@Controller
@RequestMapping("/ciudad")
public class CiudadController {

    @Autowired
    private CiudadService ciudadService;

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private RutaRepository rutaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    // Caso 1: Mostrar formulario de creación
    @GetMapping("/crear")
    public ModelAndView mostrarFormularioCrear() {
        return new ModelAndView("ciudad-crear")
                .addObject("ciudad", new Ciudad());
    }

    // Caso 1 (POST): Crear ciudad
    @PostMapping("/crear")
    public String crearCiudad(@ModelAttribute Ciudad ciudad) {
        ciudadService.crearCiudad(ciudad);
        return "redirect:/ciudad/list";
    }

    // Caso 2: Ver ciudad por ID
    @GetMapping("/{id}")
public ModelAndView verCiudad(@PathVariable Long id) {
    Ciudad ciudad = ciudadService.buscarPorId(id)
            .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));

    return new ModelAndView("ciudad-detalle")
            .addObject("ciudad", ciudad)
            .addObject("productos", ciudad.getProductosDisponibles())
            .addObject("servicios", ciudad.getServiciosDisponibles())
            .addObject("rutas", ciudad.getRutasAsociadas());
}


    // Caso 3: Listar ciudades
    @GetMapping("/list")
    public ModelAndView listarCiudades() {
        List<Ciudad> ciudades = ciudadService.listarTodas();
        return new ModelAndView("ciudad-list").addObject("ciudades", ciudades);
    }

    // Caso 4: Mostrar formulario de edición
    @GetMapping("/{id}/editar")
    public ModelAndView mostrarFormularioEditar(@PathVariable Long id) {
        Ciudad ciudad = ciudadService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));

        // Primero declara todas las listas
        List<Producto> todosLosProductos = productoRepository.findAll();
        List<Servicio> todosLosServicios = servicioRepository.findAll();
        List<Ruta> todasLasRutas = rutaRepository.findAll();

        // Luego genera las listas de IDs seleccionados
        List<Long> productoIdsSeleccionados = ciudad.getProductosDisponibles().stream()
                .map(cp -> cp.getProducto().getId())
                .toList();

        List<Long> servicioIdsSeleccionados = ciudad.getServiciosDisponibles().stream()
                .map(cs -> cs.getServicio().getId())
                .toList();

        List<Long> rutaIdsSeleccionadas = ciudad.getRutasAsociadas().stream()
                .map(cr -> cr.getRuta().getId())
                .toList();

        // Finalmente agrega todo al modelo
        return new ModelAndView("ciudad-editar")
                .addObject("ciudad", ciudad)
                .addObject("todosLosProductos", todosLosProductos)
                .addObject("todosLosServicios", todosLosServicios)
                .addObject("todasLasRutas", todasLasRutas)
                .addObject("productoIdsSeleccionados", productoIdsSeleccionados)
                .addObject("servicioIdsSeleccionados", servicioIdsSeleccionados)
                .addObject("rutaIdsSeleccionadas", rutaIdsSeleccionadas);
    }

    // Caso 4 (POST): Actualizar ciudad
    @PostMapping("/{id}/editar")
    public String actualizarCiudad(@PathVariable Long id,
            @ModelAttribute Ciudad ciudad,
            @RequestParam(required = false) List<Long> productoIds,
            @RequestParam(required = false) List<Long> servicioIds,
            @RequestParam(required = false) List<Long> rutaIds) {
        ciudadService.actualizarCiudadConAsociaciones(id, ciudad, productoIds, servicioIds, rutaIds);
        return "redirect:/ciudad/" + id;
    }

    // Caso 5: Confirmación antes de eliminar ciudad
    @GetMapping("/{id}/eliminar")
    public ModelAndView mostrarConfirmacionEliminar(@PathVariable Long id) {
        Ciudad ciudad = ciudadService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));
        return new ModelAndView("ciudad-eliminar")
                .addObject("ciudad", ciudad);
    }

    // Caso 5 (POST): Eliminar ciudad
    @PostMapping("/{id}/eliminar")
    public String eliminarCiudad(@PathVariable Long id) {
        ciudadService.eliminarCiudad(id);
        return "redirect:/ciudad/list";
    }
}
