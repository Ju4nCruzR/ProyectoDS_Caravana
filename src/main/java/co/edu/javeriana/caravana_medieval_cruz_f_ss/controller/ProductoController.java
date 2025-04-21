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

import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.ProductoAsociacionesDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.ProductoDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.service.ProductoService;

@RestController
@RequestMapping("/producto")
public class ProductoController {

@Autowired
    private ProductoService productoService;

    // Crear producto
    @PostMapping
    public void crearProducto(@RequestBody ProductoDTO productoDTO) {
        productoService.crearProducto(productoDTO);
    }

    // Ver producto por ID
    @GetMapping("/{id}")
    public ProductoDTO verProducto(@PathVariable Long id) {
        return productoService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    // Listar todos los productos
    @GetMapping("/list")
    public List<ProductoDTO> listarProductos() {
        return productoService.listarTodos();
    }

    // Editar producto
    @PutMapping("/{id}")
    public void actualizarProducto(@PathVariable Long id, @RequestBody ProductoDTO productoDTO) {
        productoService.actualizarProducto(id, productoDTO);
    }

    // Eliminar producto
    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
    }

    // Ver asociaciones del producto con caravanas y ciudades
    @GetMapping("/{id}/asociaciones")
    public ProductoAsociacionesDTO verAsociaciones(@PathVariable Long id) {
        return productoService.obtenerAsociaciones(id);
    }
}
