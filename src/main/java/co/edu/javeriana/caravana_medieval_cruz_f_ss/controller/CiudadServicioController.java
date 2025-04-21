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

import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CiudadServicioDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Ciudad;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Servicio;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.CiudadRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.ServicioRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.service.CiudadServicioService;

@RestController
@RequestMapping("/ciudad-servicio")
public class CiudadServicioController {

    @Autowired
    private CiudadServicioService ciudadServicioService;

    @Autowired
    private CiudadRepository ciudadRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    // Listar todos los servicios por ciudad o en general
    @GetMapping("/list")
    public List<CiudadServicioDTO> listarTodos() {
        return ciudadServicioService.listarTodos();
    }

    // Ver detalle de una asociación
    @GetMapping("/{id}")
    public CiudadServicioDTO verDetalle(@PathVariable Long id) {
        return ciudadServicioService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Asociación no encontrada"));
    }

    // Asociar servicio a ciudad
    @PostMapping
    public void asociarServicio(@RequestParam Long ciudadId, @RequestParam Long servicioId) {
        Ciudad ciudad = ciudadRepository.findById(ciudadId)
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));
        Servicio servicio = servicioRepository.findById(servicioId)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

        ciudadServicioService.asociarServicio(ciudad, servicio);
    }

    // Marcar servicio como adquirido
    @PostMapping("/{id}/adquirir")
    public void marcarAdquirido(@PathVariable Long id) {
        ciudadServicioService.marcarAdquirido(id);
    }

    // Eliminar asociación
    @DeleteMapping("/{id}")
    public void eliminarAsociacion(@PathVariable Long id) {
        ciudadServicioService.eliminarPorId(id);
    }
}
