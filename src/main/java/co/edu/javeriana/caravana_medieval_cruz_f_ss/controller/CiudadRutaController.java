package co.edu.javeriana.caravana_medieval_cruz_f_ss.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CiudadRutaDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.service.CiudadRutaService;


@RestController
@RequestMapping("/ciudad-ruta")
public class CiudadRutaController {

   @Autowired
    private CiudadRutaService ciudadRutaService;

    // Listar todas las asociaciones
    @GetMapping("/list")
    public List<CiudadRutaDTO> listarTodas() {
        return ciudadRutaService.listarTodas();
    }

    // Ver detalle de una asociación
    @GetMapping("/{id}")
    public CiudadRutaDTO verAsociacion(@PathVariable Long id) {
        return ciudadRutaService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Asociación no encontrada"));
    }

    // Crear nueva asociación (POST sin /crear)
    @PostMapping
    public void asociarRuta(@RequestParam Long ciudadId, @RequestParam Long rutaId) {
        ciudadRutaService.asociarRuta(ciudadId, rutaId);
    }

    // Eliminar asociación
    @DeleteMapping("/{id}")
    public void eliminarAsociacion(@PathVariable Long id) {
        ciudadRutaService.eliminarPorId(id);
    }
}
