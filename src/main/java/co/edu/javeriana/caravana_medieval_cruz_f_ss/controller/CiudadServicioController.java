package co.edu.javeriana.caravana_medieval_cruz_f_ss.controller;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Ciudad;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.CiudadServicio;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Servicio;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.CiudadRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.ServicioRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.service.CiudadServicioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/ciudad-servicio")
public class CiudadServicioController {

    @Autowired
    private CiudadServicioService ciudadServicioService;

    @Autowired
    private CiudadRepository ciudadRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    // Caso 1 y 2: Listar todos los servicios por ciudad o general
    @GetMapping("/list")
    public ModelAndView listarTodos() {
        List<CiudadServicio> asociaciones = ciudadServicioService.listarTodos();
        return new ModelAndView("ciudadServicio-list")
                .addObject("asociaciones", asociaciones);
    }

    // Caso 3: Ver detalle
    @GetMapping("/{id}")
    public ModelAndView verDetalle(@PathVariable Long id) {
        CiudadServicio cs = ciudadServicioService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Asociación no encontrada"));
        return new ModelAndView("ciudadServicio-detalle").addObject("ciudadServicio", cs);
    }

    // Caso 4: Mostrar formulario para crear asociación
    @GetMapping("/crear")
    public ModelAndView mostrarFormularioAsociar() {
        return new ModelAndView("ciudadServicio-crear")
                .addObject("ciudades", ciudadRepository.findAll())
                .addObject("servicios", servicioRepository.findAll());
    }

    // ✅ Caso 4 (POST): Asociar servicio a ciudad
    @PostMapping("/crear")
    public String asociarServicio(@RequestParam Long ciudadId, @RequestParam Long servicioId) {
        Ciudad ciudad = ciudadRepository.findById(ciudadId)
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));
        Servicio servicio = servicioRepository.findById(servicioId)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

        ciudadServicioService.asociarServicio(ciudad, servicio);
        return "redirect:/ciudad-servicio/list";
    }

    // Caso 5: Marcar como adquirido
    @PostMapping("/{id}/adquirir")
    public String marcarAdquirido(@PathVariable Long id) {
        ciudadServicioService.marcarAdquirido(id);
        return "redirect:/ciudad-servicio/" + id;
    }

    // Caso 6: Eliminar asociación
    @PostMapping("/{id}/eliminar")
    public String eliminarAsociacion(@PathVariable Long id) {
        ciudadServicioService.eliminarPorId(id);
        return "redirect:/ciudad-servicio/list";
    }
}
