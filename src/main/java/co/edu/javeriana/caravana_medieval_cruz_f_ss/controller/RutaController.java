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

import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.RutaDTO;
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
        return new ModelAndView("rutaTemplates/ruta-crear")
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
        RutaDTO ruta = rutaService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Ruta no encontrada"));
        return new ModelAndView("rutaTemplates/ruta-detalle")
                .addObject("ruta", ruta);
    }

    // Caso 3: Listar todas las rutas
    @GetMapping("/list")
    public ModelAndView listarRutas() {
        List<RutaDTO> rutas = rutaService.listarTodas();
        return new ModelAndView("rutaTemplates/ruta-list")
                .addObject("rutas", rutas);
    }

    // Caso 4: Mostrar formulario de edición
    @GetMapping("/{id}/editar")
    public ModelAndView mostrarFormularioEditar(@PathVariable Long id) {
        Ruta ruta = rutaService.buscarPorId(id)
                .map(dto -> {
                    Ruta r = new Ruta();
                    r.setId(dto.getId());
                    r.setDistanciaRuta(dto.getDistanciaRuta());
                    r.setEsSeguraRuta(dto.isEsSeguraRuta());
                    r.setDanoRuta(dto.getDanoRuta());
                    return r;
                })
                .orElseThrow(() -> new RuntimeException("Ruta no encontrada"));

        return new ModelAndView("rutaTemplates/ruta-editar")
                .addObject("ruta", ruta)
                .addObject("ciudades", ciudadRepository.findAll());
    }

    // Caso 4 (POST): Editar ruta
    @PostMapping("/{id}/editar")
    public String editarRuta(@PathVariable Long id, @ModelAttribute Ruta ruta) {
        rutaService.actualizarRuta(id, ruta);
        return "redirect:/ruta/" + id;
    }

    // Caso 5: Mostrar pantalla de confirmación para eliminar ruta
    @GetMapping("/{id}/eliminar")
    public ModelAndView confirmarEliminarRuta(@PathVariable Long id) {
        RutaDTO ruta = rutaService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Ruta no encontrada"));
        return new ModelAndView("rutaTemplates/ruta-eliminar")
                .addObject("ruta", ruta);
    }

    // Caso 5: Eliminar ruta
    @PostMapping("/{id}/eliminar")
    public String eliminarRuta(@PathVariable Long id) {
        rutaService.eliminarRuta(id);
        return "redirect:/ruta/list";
    }

    // FORMULARIOS DE FILTRO

    @GetMapping("/origen")
    public ModelAndView mostrarFormularioFiltrarPorOrigen() {
        return new ModelAndView("rutaTemplates/ruta-filtrar-origen")
                .addObject("ciudades", ciudadRepository.findAll());
    }

    @GetMapping("/destino")
    public ModelAndView mostrarFormularioFiltrarPorDestino() {
        return new ModelAndView("rutaTemplates/ruta-filtrar-destino")
                .addObject("ciudades", ciudadRepository.findAll());
    }

    @GetMapping("/seguridad")
    public ModelAndView mostrarFormularioFiltrarPorSeguridad() {
        return new ModelAndView("rutaTemplates/ruta-filtrar-seguridad");
    }

    @GetMapping("/entre-ciudades-form")
    public ModelAndView mostrarFormularioFiltrarEntreCiudades() {
        return new ModelAndView("rutaTemplates/ruta-entre-ciudades")
                .addObject("ciudades", ciudadRepository.findAll());
    }

    // RESULTADOS DE FILTRO

    @GetMapping("/filtrar-origen")
    public ModelAndView filtrarPorCiudadOrigen(@RequestParam Long ciudadId) {
        List<RutaDTO> rutas = rutaService.buscarPorCiudadOrigen(ciudadId);
        if (rutas.isEmpty()) {
            return new ModelAndView("rutaTemplates/ruta-no-encontrada");
        }
        return new ModelAndView("rutaTemplates/ruta-filtrada")
                .addObject("rutas", rutas);
    }

    @GetMapping("/filtrar-destino")
    public ModelAndView filtrarPorCiudadDestino(@RequestParam Long ciudadId) {
        List<RutaDTO> rutas = rutaService.buscarPorCiudadDestino(ciudadId);
        if (rutas.isEmpty()) {
            return new ModelAndView("rutaTemplates/ruta-no-encontrada");
        }
        return new ModelAndView("rutaTemplates/ruta-filtrada")
                .addObject("rutas", rutas);
    }

    @GetMapping("/filtrar-seguridad")
    public ModelAndView filtrarPorSeguridad(@RequestParam boolean segura) {
        List<RutaDTO> rutas = rutaService.filtrarPorSeguridad(segura);
        if (rutas.isEmpty()) {
            return new ModelAndView("rutaTemplates/ruta-no-encontrada");
        }
        return new ModelAndView("rutaTemplates/ruta-filtrada")
                .addObject("rutas", rutas);
    }

    @GetMapping("/entre-ciudades")
    public ModelAndView buscarEntreCiudades(@RequestParam Long origenId, @RequestParam Long destinoId) {
        List<RutaDTO> rutas = rutaService.buscarEntreCiudades(origenId, destinoId);
        if (rutas.isEmpty()) {
            return new ModelAndView("rutaTemplates/ruta-no-encontrada");
        }
        return new ModelAndView("rutaTemplates/ruta-filtrada")
                .addObject("rutas", rutas);
    }

}
