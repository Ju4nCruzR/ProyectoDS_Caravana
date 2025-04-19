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

import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Ruta;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.CiudadRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.service.RutaService;

@Controller
@RequestMapping("/ruta")
public class RutaController {
    
    @Autowired
    private RutaService rutaService;

    @Autowired
    private CiudadRepository ciudadRepository;

    // Caso 1: Mostrar formulario de creación
    @GetMapping("/crear")
    public ModelAndView mostrarFormularioCrear() {
        return new ModelAndView("ruta-crear")
                .addObject("ruta", new Ruta())
                .addObject("ciudades", ciudadRepository.findAll());
    }

    // Caso 1 (POST): Crear ruta
    @PostMapping("/crear")
    public String crearRuta(@ModelAttribute Ruta ruta) {
        rutaService.crearRuta(ruta);
        return "redirect:/ruta/list";
    }

    // Caso 2: Ver detalle de una ruta
    @GetMapping("/{id}")
    public ModelAndView verRuta(@PathVariable Long id) {
        Ruta ruta = rutaService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Ruta no encontrada"));
        return new ModelAndView("ruta-detalle").addObject("ruta", ruta);
    }

    // Caso 3: Listar todas las rutas
    @GetMapping("/list")
    public ModelAndView listarRutas() {
        List<Ruta> rutas = rutaService.listarTodas();
        return new ModelAndView("ruta-list").addObject("rutas", rutas);
    }

    // Caso 4: Mostrar formulario de edición
    @GetMapping("/{id}/editar")
    public ModelAndView mostrarFormularioEditar(@PathVariable Long id) {
        Ruta ruta = rutaService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Ruta no encontrada"));
        return new ModelAndView("ruta-editar")
                .addObject("ruta", ruta)
                .addObject("ciudades", ciudadRepository.findAll());
    }

    // Caso 4 (POST): Editar ruta
    @PostMapping("/{id}/editar")
    public String editarRuta(@PathVariable Long id, @ModelAttribute Ruta ruta) {
        rutaService.actualizarRuta(id, ruta);
        return "redirect:/ruta/" + id;
    }

    // Caso 5: Eliminar ruta
    @PostMapping("/{id}/eliminar")
    public String eliminarRuta(@PathVariable Long id) {
        rutaService.eliminarRuta(id);
        return "redirect:/ruta/list";
    }

    // Caso 6: Filtrar por ciudad origen
    @GetMapping("/origen")
    public ModelAndView filtrarPorCiudadOrigen(@RequestParam Long ciudadId) {
        List<Ruta> rutas = rutaService.buscarPorCiudadOrigen(ciudadId);
        return new ModelAndView("ruta-filtrada").addObject("rutas", rutas);
    }

    // Caso 7: Filtrar por seguridad
    @GetMapping("/seguridad")
    public ModelAndView filtrarPorSeguridad(@RequestParam boolean segura) {
        List<Ruta> rutas = rutaService.filtrarPorSeguridad(segura);
        return new ModelAndView("ruta-filtrada").addObject("rutas", rutas);
    }

    // Caso 8: Buscar entre dos ciudades
    @GetMapping("/entre-ciudades")
    public ModelAndView buscarEntreCiudades(@RequestParam Long origenId, @RequestParam Long destinoId) {
        List<Ruta> rutas = rutaService.buscarEntreCiudades(origenId, destinoId);
        return new ModelAndView("ruta-filtrada").addObject("rutas", rutas);
    }
}
