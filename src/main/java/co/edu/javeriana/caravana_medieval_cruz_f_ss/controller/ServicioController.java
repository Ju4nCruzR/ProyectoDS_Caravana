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
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.ServicioDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.CiudadServicio;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.service.ServicioService;

@RestController
@RequestMapping("/servicio")
public class ServicioController {

@Autowired
    private ServicioService servicioService;

    // Crear servicio
    @PostMapping
    public void crearServicio(@RequestBody ServicioDTO servicioDTO) {
        servicioService.crearServicio(servicioDTO);
    }

    // Ver servicio por ID
    @GetMapping("/{id}")
    public ServicioDTO verServicio(@PathVariable Long id) {
        return servicioService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));
    }

    // Listar todos los servicios
    @GetMapping("/list")
    public List<ServicioDTO> listarServicios() {
        return servicioService.listarTodos();
    }

    // Editar servicio
    @PutMapping("/{id}")
    public void actualizarServicio(@PathVariable Long id, @RequestBody ServicioDTO servicioDTO) {
        servicioService.actualizarServicio(id, servicioDTO);
    }

    // Eliminar servicio
    @DeleteMapping("/{id}")
    public void eliminarServicio(@PathVariable Long id) {
        servicioService.eliminarServicio(id);
    }

    // Ver asociaciones del servicio con ciudades
    @GetMapping("/{id}/asociaciones")
    public List<CiudadServicio> verAsociaciones(@PathVariable Long id) {
        return servicioService.obtenerCiudadesAsociadas(id);
    }
}
