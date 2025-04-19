package co.edu.javeriana.caravana_medieval_cruz_f_ss.controller;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Ciudad;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.CiudadRuta;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Ruta;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.CiudadRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.RutaRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.service.CiudadRutaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/ciudad-ruta")
public class CiudadRutaController {
    
    @Autowired
    private CiudadRutaService ciudadRutaService;

    @Autowired
    private CiudadRepository ciudadRepository;

    @Autowired
    private RutaRepository rutaRepository;

    // Caso 1 y 5: Listar todas las asociaciones
    @GetMapping("/list")
    public ModelAndView listarTodas() {
        List<CiudadRuta> asociaciones = ciudadRutaService.listarTodas();
        return new ModelAndView("ciudadruta-list")
                .addObject("asociaciones", asociaciones);
    }

    // Caso 2: Mostrar formulario de asociación
    @GetMapping("/crear")
    public ModelAndView mostrarFormularioAsociar() {
        return new ModelAndView("ciudadruta-crear")
                .addObject("ciudades", ciudadRepository.findAll())
                .addObject("rutas", rutaRepository.findAll());
    }

    // Caso 2 (POST): Asociar ruta a ciudad
    @PostMapping("/crear")
    public String asociarRuta(@RequestParam Long ciudadId, @RequestParam Long rutaId) {
        Ciudad ciudad = ciudadRepository.findById(ciudadId)
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));
        Ruta ruta = rutaRepository.findById(rutaId)
                .orElseThrow(() -> new RuntimeException("Ruta no encontrada"));

        ciudadRutaService.asociarRuta(ciudad, ruta);
        return "redirect:/ciudad-ruta/list";
    }

    // Caso 3: Eliminar asociación
    @PostMapping("/{id}/eliminar")
    public String eliminarAsociacion(@PathVariable Long id) {
        ciudadRutaService.eliminarPorId(id);
        return "redirect:/ciudad-ruta/list";
    }

    // Caso 4: Ver detalle
    @GetMapping("/{id}")
    public ModelAndView verAsociacion(@PathVariable Long id) {
        CiudadRuta cr = ciudadRutaService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Asociación no encontrada"));
        return new ModelAndView("ciudadruta-detalle").addObject("ciudadruta", cr);
    }

}
