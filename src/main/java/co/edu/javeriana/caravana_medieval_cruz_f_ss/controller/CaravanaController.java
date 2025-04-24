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

import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CaravanaDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CaravanaDetalleDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CaravanaFormularioDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CaravanaProductoDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Jugador;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.service.CaravanaProductoService;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.service.CaravanaService;

@RestController
@RequestMapping("/caravana")
public class CaravanaController {
        @Autowired
        private CaravanaService caravanaService;

        @Autowired
        private CaravanaProductoService caravanaProductoService;

        @PostMapping
        public void crearCaravana(@RequestBody CaravanaFormularioDTO dto) {
                caravanaService.crearCaravanaDesdeFormulario(dto);
        }

        @GetMapping("/list")
        public List<CaravanaDTO> listarCaravanas() {
                return caravanaService.listarCaravanas();
        }

        @GetMapping("/{id}")
        public CaravanaDetalleDTO obtenerCaravana(@PathVariable Long id) {
                return caravanaService.buscarCaravanaPorId(id);
        }

        @PutMapping("/{id}/editar")
        public void actualizarCaravana(@PathVariable Long id, @RequestBody CaravanaFormularioDTO dto) {
                caravanaService.actualizarCaravanaDesdeFormulario(id, dto);
        }

        @DeleteMapping("/{id}/eliminar")
        public void eliminarCaravana(@PathVariable Long id) {
                caravanaService.eliminarCaravana(id);
        }

        @PostMapping("/{id}/mover")
        public void moverCaravana(@PathVariable Long id, @RequestParam Long ciudadId) {
                caravanaService.moverCaravana(id, ciudadId);
        }

        @PostMapping("/{id}/comprar")
        public void comprarProducto(@PathVariable Long id,
                        @RequestParam Long productoId,
                        @RequestParam int cantidad) {
                caravanaService.comprarProducto(id, productoId, cantidad);
        }

        @PostMapping("/{id}/vender")
        public void venderProducto(@PathVariable Long id,
                        @RequestParam Long productoId,
                        @RequestParam int cantidad) {
                caravanaService.venderProducto(id, productoId, cantidad);
        }

        @PostMapping("/{id}/servicio")
        public void aplicarServicio(@PathVariable Long id, @RequestParam Long servicioId) {
                caravanaService.aplicarServicio(id, servicioId);
        }

        @GetMapping("/{id}/productos")
        public List<CaravanaProductoDTO> verProductos(@PathVariable Long id) {
                return caravanaService.buscarCaravanaPorId(id).getProductos();
        }

        @DeleteMapping("/{caravanaId}/producto/{productoId}/eliminar")
        public ResponseEntity<Void> eliminarProductoDeCaravana(
                        @PathVariable Long caravanaId,
                        @PathVariable Long productoId) {
                caravanaProductoService.eliminarProductoDeCaravana(caravanaId, productoId);
                return ResponseEntity.noContent().build();
        }

        @PostMapping("/{id}/jugadores")
        public void agregarJugador(@PathVariable Long id,
                        @RequestParam String nombre,
                        @RequestParam Jugador.Rol rol) {
                caravanaService.agregarJugador(id, nombre, rol);
        }

}
