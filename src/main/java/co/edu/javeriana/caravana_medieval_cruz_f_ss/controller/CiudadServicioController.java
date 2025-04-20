package co.edu.javeriana.caravana_medieval_cruz_f_ss.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CiudadServicioDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Ciudad;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Servicio;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.CiudadRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.ServicioRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.service.CiudadServicioService;

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
        List<CiudadServicioDTO> asociaciones = ciudadServicioService.listarTodos();
        return new ModelAndView("ciudadServicioTemplates/ciudadServicio-list")
                .addObject("asociaciones", asociaciones);
    }

    // Caso 3: Ver detalle
    public ModelAndView verDetalle(@PathVariable Long id) {
        CiudadServicioDTO dto = ciudadServicioService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Asociación no encontrada"));

        return new ModelAndView("ciudadServicioTemplates/ciudadServicio-detalle")
                .addObject("ciudadServicio", dto);
    }

    // Caso 4: Mostrar formulario para crear asociación
    @GetMapping("/crear")
    public ModelAndView mostrarFormularioAsociar() {
        return new ModelAndView("ciudadServicioTemplates/ciudadServicio-crear")
                .addObject("ciudades", ciudadRepository.findAll())
                .addObject("servicios", servicioRepository.findAll());
    }

    // Caso 4 (POST): Asociar servicio a ciudad
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
