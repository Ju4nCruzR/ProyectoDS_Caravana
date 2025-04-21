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

import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CiudadProductoDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Ciudad;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.CiudadProducto;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Producto;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.CiudadRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.ProductoRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.service.CiudadProductoService;

@RestController
@RequestMapping("/ciudad-producto")
public class CiudadProductoController {
        @Autowired
    private CiudadProductoService ciudadProductoService;

    @Autowired
    private CiudadRepository ciudadRepository;

    @Autowired
    private ProductoRepository productoRepository;

    // Listar productos de una ciudad
    @GetMapping("/ciudad/{ciudadId}")
    public List<CiudadProductoDTO> listarPorCiudad(@PathVariable Long ciudadId) {
        Ciudad ciudad = ciudadRepository.findById(ciudadId)
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));
        return ciudadProductoService.listarPorCiudad(ciudad);
    }

    // Ver detalle de producto en ciudad
    @GetMapping("/ciudad/{ciudadId}/producto/{productoId}")
    public CiudadProductoDTO verDetalle(@PathVariable Long ciudadId, @PathVariable Long productoId) {
        Ciudad ciudad = ciudadRepository.findById(ciudadId)
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return ciudadProductoService.obtenerPorCiudadYProducto(ciudad, producto)
                .orElseThrow(() -> new RuntimeException("Producto no registrado en la ciudad"));
    }

    // Obtener por ID interno
    @GetMapping("/{id}")
    public CiudadProductoDTO obtenerPorId(@PathVariable Long id) {
        return ciudadProductoService.listarTodos().stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No se encontró el producto"));
    }

    // Actualizar stock
    @PutMapping("/{id}")
    public CiudadProductoDTO actualizarStock(@PathVariable Long id, @RequestParam int nuevoStock) {
        return ciudadProductoService.actualizarStock(id, nuevoStock);
    }

    // Eliminar asociación ciudad-producto
    @DeleteMapping("/ciudad/{ciudadId}/producto/{productoId}")
    public void eliminar(@PathVariable Long ciudadId, @PathVariable Long productoId) {
        Ciudad ciudad = ciudadRepository.findById(ciudadId)
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        CiudadProducto cp = ciudadProductoService.obtenerEntidadPorCiudadAndProducto(ciudad, producto)
                .orElseThrow(() -> new RuntimeException("Asociación no encontrada"));
        ciudadProductoService.eliminarPorId(cp.getId());
    }

    // Crear nueva asociación ciudad-producto
    @PostMapping
    public void crear(@RequestParam Long ciudadId,
                      @RequestParam Long productoId,
                      @RequestParam int stock) {
        Ciudad ciudad = ciudadRepository.findById(ciudadId)
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        CiudadProducto cp = new CiudadProducto(ciudad, producto, stock);
        ciudadProductoService.crear(cp);
    }

    // Listar todas las asociaciones
    @GetMapping("/list")
    public List<CiudadProductoDTO> listarTodos() {
        return ciudadProductoService.listarTodos();
    }

}
