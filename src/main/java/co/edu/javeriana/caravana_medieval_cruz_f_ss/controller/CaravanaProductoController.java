package co.edu.javeriana.caravana_medieval_cruz_f_ss.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CaravanaProductoDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.service.CaravanaProductoService;

@RestController
@RequestMapping("/caravana-producto")
public class CaravanaProductoController {

         @Autowired
    private CaravanaProductoService caravanaProductoService;

    // Listar productos por caravana
    @GetMapping("/caravana/{caravanaId}")
    public List<CaravanaProductoDTO> listarPorCaravana(@PathVariable Long caravanaId) {
        return caravanaProductoService.listarPorCaravana(caravanaId);
    }

    // Ver detalle de producto en caravana
    @GetMapping("/caravana/{caravanaId}/producto/{productoId}")
    public CaravanaProductoDTO detalleProducto(@PathVariable Long caravanaId, @PathVariable Long productoId) {
        return caravanaProductoService.obtenerPorCaravanaYProducto(caravanaId, productoId)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado en la caravana"));
    }

    // Obtener por ID interno
    @GetMapping("/{id}")
    public CaravanaProductoDTO obtenerPorId(@PathVariable Long id) {
        return caravanaProductoService.buscarPorId(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    // Actualizar stock
    @PutMapping("/{id}/actualizar")
    public CaravanaProductoDTO actualizarStock(@PathVariable Long id, @RequestParam int nuevoStock) {
        return caravanaProductoService.actualizarStock(id, nuevoStock);
    }

    // Eliminar producto de la caravana
    @DeleteMapping("/{id}/eliminar")
    public void eliminar(@PathVariable Long id) {
        caravanaProductoService.eliminarPorId(id);
    }

    // Crear nuevo producto en caravana
    @PostMapping("/crear")
    public void crear(@RequestParam Long caravanaId,
                      @RequestParam Long productoId,
                      @RequestParam int stock) {
        caravanaProductoService.crear(caravanaId, productoId, stock);
    }

    // Listar todos los productos de todas las caravanas
    @GetMapping("/list")
    public List<CaravanaProductoDTO> listarTodos() {
        return caravanaProductoService.listarTodos();
    }
}
