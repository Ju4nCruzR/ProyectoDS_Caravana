package co.edu.javeriana.caravana_medieval_cruz_f_ss.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.RutaDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Ruta;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.service.RutaService;

@RestController
@RequestMapping("/ruta")
public class RutaController {

    @Autowired
    private RutaService rutaService;

    // Crear nueva ruta
    @PostMapping
    public void crearRuta(@RequestBody Ruta ruta) {
        rutaService.crearRuta(ruta);
    }

    // Ver detalle
    @GetMapping("/{id}")
    public RutaDTO verRuta(@PathVariable Long id) {
        return rutaService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Ruta no encontrada"));
    }

    // Listar todas las rutas
    @GetMapping("/list")
    public List<RutaDTO> listarRutas() {
        return rutaService.listarTodas();
    }

    // Editar ruta
    @PutMapping("/{id}")
    public void editarRuta(@PathVariable Long id, @RequestBody Ruta ruta) {
        rutaService.actualizarRuta(id, ruta);
    }

    // Eliminar ruta
    @DeleteMapping("/{id}")
    public void eliminarRuta(@PathVariable Long id) {
        rutaService.eliminarRuta(id);
    }

    // Filtros

    @GetMapping("/filtrar-origen")
    public List<RutaDTO> filtrarPorCiudadOrigen(@RequestParam Long ciudadId) {
        return rutaService.buscarPorCiudadOrigen(ciudadId);
    }

    @GetMapping("/filtrar-destino")
    public List<RutaDTO> filtrarPorCiudadDestino(@RequestParam Long ciudadId) {
        return rutaService.buscarPorCiudadDestino(ciudadId);
    }

    @GetMapping("/filtrar-seguridad")
    public List<RutaDTO> filtrarPorSeguridad(@RequestParam boolean segura) {
        return rutaService.filtrarPorSeguridad(segura);
    }

    @GetMapping("/entre-ciudades")
    public List<RutaDTO> buscarEntreCiudades(@RequestParam Long origenId, @RequestParam Long destinoId) {
        return rutaService.buscarEntreCiudades(origenId, destinoId);
    }
}
