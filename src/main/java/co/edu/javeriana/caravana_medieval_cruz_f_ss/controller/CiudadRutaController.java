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

import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CiudadRutaDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.CiudadRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.RutaRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.service.CiudadRutaService;

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
        List<CiudadRutaDTO> asociaciones = ciudadRutaService.listarTodas();
        return new ModelAndView("ciudadRutaTemplates/ciudadRuta-list")
                .addObject("asociaciones", asociaciones);
    }

    // Caso 2: Mostrar formulario de asociación
    @GetMapping("/crear")
    public ModelAndView mostrarFormularioAsociar() {
        return new ModelAndView("ciudadRutaTemplates/ciudadRuta-crear")
                .addObject("ciudades", ciudadRepository.findAll())
                .addObject("rutas", rutaRepository.findAll());
    }

    // Caso 2 (POST): Asociar ruta a ciudad
    @PostMapping("/crear")
    public String asociarRuta(@RequestParam Long ciudadId, @RequestParam Long rutaId) {
        ciudadRutaService.asociarRuta(ciudadId, rutaId);
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
        CiudadRutaDTO dto = ciudadRutaService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Asociación no encontrada"));
        return new ModelAndView("ciudadRutaTemplates/ciudadRuta-detalle")
                .addObject("ciudadruta", dto);
    }

}
