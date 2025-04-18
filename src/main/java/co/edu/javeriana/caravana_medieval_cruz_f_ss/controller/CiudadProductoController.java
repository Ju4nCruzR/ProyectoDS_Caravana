package co.edu.javeriana.caravana_medieval_cruz_f_ss.controller;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Ciudad;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.CiudadProducto;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Producto;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.CiudadRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.ProductoRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.service.CiudadProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/ciudad-producto")
public class CiudadProductoController {
    @Autowired 
    private CiudadProductoService ciudadProductoService;

    @Autowired 
    private CiudadRepository ciudadRepository;

    @Autowired 
    private ProductoRepository productoRepository;

    // Caso 1: Listar productos de una ciudad
    @GetMapping("/ciudad/{ciudadId}")
    public ModelAndView listarPorCiudad(@PathVariable Long ciudadId) {
        Ciudad ciudad = ciudadRepository.findById(ciudadId)
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));

        List<CiudadProducto> productos = ciudadProductoService.listarPorCiudad(ciudad);

        return new ModelAndView("ciudadProducto-list")
                .addObject("ciudad", ciudad)
                .addObject("productos", productos);
    }

    // Caso 2: Ver detalle (modo JSON opcional)
    @GetMapping("/detalle")
    @ResponseBody
    public CiudadProducto detalle(@RequestParam Long ciudadId, @RequestParam Long productoId) {
        Ciudad ciudad = ciudadRepository.findById(ciudadId)
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        return ciudadProductoService.obtenerPorCiudadYProducto(ciudad, producto)
                .orElseThrow(() -> new RuntimeException("Producto no registrado en la ciudad"));
    }

    // Caso 3: Actualizar stock
    @PostMapping("/{id}/actualizar")
    public String actualizarStock(@PathVariable Long id, @RequestParam int nuevoStock) {
        CiudadProducto cp = ciudadProductoService.listarTodos().stream()
                .filter(p -> p.getId() == id).findFirst()
                .orElseThrow(() -> new RuntimeException("No se encontró el producto"));
        ciudadProductoService.actualizarStock(cp, nuevoStock);
        return "redirect:/ciudad-producto/ciudad/" + cp.getCiudad().getId();
    }

    // Caso 4: Eliminar
    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id) {
        CiudadProducto cp = ciudadProductoService.listarTodos().stream()
                .filter(p -> p.getId() == id).findFirst()
                .orElseThrow(() -> new RuntimeException("No se encontró el producto"));
        ciudadProductoService.eliminarPorId(id);
        return "redirect:/ciudad-producto/ciudad/" + cp.getCiudad().getId();
    }

    // Caso 5: Crear
    @PostMapping("/crear")
    public String crear(@RequestParam Long ciudadId,
                        @RequestParam Long productoId,
                        @RequestParam int stock) {
        Ciudad ciudad = ciudadRepository.findById(ciudadId)
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        CiudadProducto cp = new CiudadProducto(ciudad, producto, stock);
        ciudadProductoService.crear(cp);

        return "redirect:/ciudad-producto/ciudad/" + ciudadId;
    }

    // Caso 6: Lista global
    @GetMapping("/list")
    public ModelAndView listarTodos() {
        List<CiudadProducto> lista = ciudadProductoService.listarTodos();
        return new ModelAndView("ciudadProducto-list-global")
                .addObject("productos", lista);
    }

    // Mostrar formulario de creación
    @GetMapping("/crear")
    public ModelAndView mostrarFormularioCrear() {
        ModelAndView modelAndView = new ModelAndView("ciudadProducto-crear");
        modelAndView.addObject("ciudades", ciudadRepository.findAll());
        modelAndView.addObject("productos", productoRepository.findAll());
        return modelAndView;
    }
}
