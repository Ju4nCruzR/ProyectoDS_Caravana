package co.edu.javeriana.caravana_medieval_cruz_f_ss.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CiudadDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CiudadDetalleDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CiudadFormularioDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CiudadProductoDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.ServicioDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.service.CiudadService;

@RestController
@RequestMapping("/ciudad")
public class CiudadController {

        @Autowired
        private CiudadService ciudadService;

        // Crear ciudad
        @PostMapping
        public void crearCiudad(@RequestBody CiudadFormularioDTO dto) {
                ciudadService.crearCiudad(dto);
        }

        // Ver detalle de ciudad
        @GetMapping("/{id}")
        public CiudadDetalleDTO verCiudad(@PathVariable Long id) {
                return ciudadService.buscarCiudadPorId(id);
        }

        // Listar todas las ciudades
        @GetMapping("/list")
        public List<CiudadDTO> listarCiudades() {
                return ciudadService.listarCiudades();
        }

        // Actualizar ciudad y asociaciones
        @PutMapping("/{id}/asociaciones")
        public ResponseEntity<Void> actualizar(@PathVariable Long id, @RequestBody CiudadFormularioDTO dto) {
                ciudadService.actualizarCiudadConAsociaciones(id, dto);
                return ResponseEntity.noContent().build();
        }

        // Eliminar ciudad
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> eliminar(@PathVariable Long id) {
                ciudadService.eliminarCiudad(id);
                return ResponseEntity.noContent().build();
        }

        @GetMapping("/{ciudadId}/productos")
        public List<CiudadProductoDTO> listarProductosDisponiblesPorCiudad(@PathVariable Long ciudadId) {
                return ciudadService.listarProductosDisponibles(ciudadId);
        }

        @GetMapping("/{id}/destinos")
        public List<CiudadDTO> listarDestinosPorCiudad(@PathVariable Long id) {
                return ciudadService.listarCiudadesDestinoPorOrigen(id);
        }

        @GetMapping("/{ciudadId}/servicios-disponibles")
        public List<ServicioDTO> listarServiciosNoAsociados(@PathVariable Long ciudadId) {
                return ciudadService.listarServiciosNoAsociados(ciudadId);
        }

        @PostMapping("/{ciudadId}/servicio")
        public void agregarServicio(@PathVariable Long ciudadId, @RequestParam Long servicioId) {
                ciudadService.agregarServicio(ciudadId, servicioId);
        }

}