package co.edu.javeriana.caravana_medieval_cruz_f_ss.controller;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Ciudad;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.service.CiudadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/ciudad")
public class CiudadController {

    @Autowired
    private CiudadService ciudadService;

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
        return new ModelAndView("ciudad-detalle").addObject("ciudad", ciudad);
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
        return new ModelAndView("ciudad-editar").addObject("ciudad", ciudad);
    }

    // Caso 4 (POST): Actualizar ciudad
    @PostMapping("/{id}/editar")
    public String actualizarCiudad(@PathVariable Long id, @ModelAttribute Ciudad ciudad) {
        ciudadService.actualizarCiudad(id, ciudad);
        return "redirect:/ciudad/" + id;
    }

    // Caso 5: Eliminar ciudad
    @PostMapping("/{id}/eliminar")
    public String eliminarCiudad(@PathVariable Long id) {
        ciudadService.eliminarCiudad(id);
        return "redirect:/ciudad/list";
    }
}
