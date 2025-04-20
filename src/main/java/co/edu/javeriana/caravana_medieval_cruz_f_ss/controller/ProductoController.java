package co.edu.javeriana.caravana_medieval_cruz_f_ss.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.CaravanaProducto;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.CiudadProducto;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Producto;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.service.ProductoService;

@Controller
@RequestMapping("/producto")
public class ProductoController {
    
    @Autowired
    private ProductoService productoService;

    // Caso 1: Mostrar formulario para crear
    @GetMapping("/crear")
    public ModelAndView mostrarFormularioCrear() {
        return new ModelAndView("productoTemplates/producto-crear")
                .addObject("producto", new Producto());
    }

    // Caso 1 (POST): Crear producto
    @PostMapping("/crear")
    public String crearProducto(@ModelAttribute Producto producto) {
        productoService.crearProducto(producto);
        return "redirect:/producto/list";
    }

    // Caso 2: Ver producto por ID
    @GetMapping("/{id}")
    public ModelAndView verProducto(@PathVariable Long id) {
        Producto producto = productoService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return new ModelAndView("productoTemplates/producto-detalle")
        .addObject("producto", producto);
    }

    // Caso 3: Listar todos los productos
    @GetMapping("/list")
    public ModelAndView listarProductos() {
        List<Producto> productos = productoService.listarTodos();
        return new ModelAndView("productoTemplates/producto-list")
                .addObject("productos", productos);
    }

    // Caso 4: Mostrar formulario de edición
    @GetMapping("/{id}/editar")
    public ModelAndView mostrarFormularioEditar(@PathVariable Long id) {
        Producto producto = productoService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return new ModelAndView("productoTemplates/producto-editar")
                .addObject("producto", producto);
    }

    // Caso 4 (POST): Actualizar
    @PostMapping("/{id}/editar")
    public String actualizarProducto(@PathVariable Long id, @ModelAttribute Producto producto) {
        productoService.actualizarProducto(id, producto);
        return "redirect:/producto/" + id;
    }

    // Caso 5: Eliminar producto
    @PostMapping("/{id}/eliminar")
    public String eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return "redirect:/producto/list";
    }

    // Caso 6: Ver asociaciones
    @GetMapping("/{id}/asociaciones")
    public ModelAndView verAsociaciones(@PathVariable Long id) {
        Producto producto = productoService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        
        List<CiudadProducto> enCiudades = productoService.obtenerCiudadesAsociadas(id);
        List<CaravanaProducto> enCaravanas = productoService.obtenerCaravanasAsociadas(id);

        return new ModelAndView("productoTemplates/producto-asociaciones")
                .addObject("producto", producto)
                .addObject("enCiudades", enCiudades)
                .addObject("enCaravanas", enCaravanas);
    }
}
