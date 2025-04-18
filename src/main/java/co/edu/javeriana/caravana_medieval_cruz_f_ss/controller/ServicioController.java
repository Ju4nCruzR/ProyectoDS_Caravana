package co.edu.javeriana.caravana_medieval_cruz_f_ss.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.CiudadServicio;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Servicio;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.service.ServicioService;

@Controller
@RequestMapping("/servicio")
public class ServicioController {
    
    @Autowired
    private ServicioService servicioService;

    // Caso 1: Mostrar formulario de creación
    @GetMapping("/crear")
    public ModelAndView mostrarFormularioCrear() {
        return new ModelAndView("servicio-crear")
                .addObject("servicio", new Servicio());
    }

    // Caso 1 (POST): Crear servicio
    @PostMapping("/crear")
    public String crearServicio(@ModelAttribute Servicio servicio) {
        servicioService.crearServicio(servicio);
        return "redirect:/servicio/list";
    }

    // Caso 2: Ver servicio por ID
    @GetMapping("/{id}")
    public ModelAndView verServicio(@PathVariable Long id) {
        Servicio servicio = servicioService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));
        return new ModelAndView("servicio-detalle")
                .addObject("servicio", servicio);
    }

    // Caso 3: Listar todos
    @GetMapping("/list")
    public ModelAndView listarServicios() {
        List<Servicio> servicios = servicioService.listarTodos();
        return new ModelAndView("servicio-list")
                .addObject("servicios", servicios);
    }

    // Caso 4: Mostrar formulario de edición
    @GetMapping("/{id}/editar")
    public ModelAndView mostrarFormularioEditar(@PathVariable Long id) {
        Servicio servicio = servicioService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));
        return new ModelAndView("servicio-editar")
                .addObject("servicio", servicio);
    }

    // Caso 4 (POST): Actualizar servicio
    @PostMapping("/{id}/editar")
    public String actualizarServicio(@PathVariable Long id, @ModelAttribute Servicio servicio) {
        servicioService.actualizarServicio(id, servicio);
        return "redirect:/servicio/" + id;
    }

    // Caso 5: Eliminar servicio
    @PostMapping("/{id}/eliminar")
    public String eliminarServicio(@PathVariable Long id) {
        servicioService.eliminarServicio(id);
        return "redirect:/servicio/list";
    }

    // Caso 6 (extra): Ver asociaciones con ciudades
    @GetMapping("/{id}/asociaciones")
    public ModelAndView verAsociaciones(@PathVariable Long id) {
        Servicio servicio = servicioService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));
        List<CiudadServicio> asociaciones = servicioService.obtenerCiudadesAsociadas(id);

        return new ModelAndView("servicio-asociaciones")
                .addObject("servicio", servicio)
                .addObject("asociaciones", asociaciones);
    }
    
}
