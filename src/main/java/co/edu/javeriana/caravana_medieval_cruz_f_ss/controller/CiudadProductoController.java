package co.edu.javeriana.caravana_medieval_cruz_f_ss.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Ciudad;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.CiudadProducto;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Producto;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.CiudadRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.ProductoRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.service.CiudadProductoService;

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

                return new ModelAndView("ciudadProductoTemplates/ciudadProducto-list")
                                .addObject("ciudad", ciudad)
                                .addObject("productos", productos);
        }

        // Caso 2: Ver detalle
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

        // Caso 3: Mostrar formulario para actualizar stock de un producto en una ciudad
        @GetMapping("/{id}/actualizar")
        public ModelAndView mostrarFormularioActualizar(@PathVariable Long id) {
                CiudadProducto cp = ciudadProductoService.listarTodos().stream()
                                .filter(p -> p.getId() == id)
                                .findFirst()
                                .orElseThrow(() -> new RuntimeException("No se encontró el producto"));

                return new ModelAndView("ciudadProductoTemplates/ciudadProducto-actualizar")
                                .addObject("ciudadProducto", cp);
        }

        // Caso 3 (POST): Actualizar stock
        @PostMapping("/{id}/actualizar")
        public String actualizarStock(@PathVariable Long id, @RequestParam int nuevoStock) {
                CiudadProducto cp = ciudadProductoService.listarTodos().stream()
                                .filter(p -> p.getId() == id).findFirst()
                                .orElseThrow(() -> new RuntimeException("No se encontró el producto"));
                ciudadProductoService.actualizarStock(cp, nuevoStock);
                return "redirect:/ciudad-producto/ciudad/" + cp.getCiudad().getId();
        }

        // Caso 4: Confirmación antes de eliminar producto de una ciudad
        @GetMapping("/{id}/eliminar")
        public ModelAndView mostrarConfirmacionEliminar(@PathVariable Long id) {
                CiudadProducto cp = ciudadProductoService.listarTodos().stream()
                                .filter(p -> p.getId() == id)
                                .findFirst()
                                .orElseThrow(() -> new RuntimeException("No se encontró el producto"));

                return new ModelAndView("ciudadProductoTemplates/ciudadProducto-eliminar")
                                .addObject("ciudadProducto", cp);
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

        // Caso 5: Mostrar formulario de creación
        @GetMapping("/crear")
        public ModelAndView mostrarFormularioCrear() {
                ModelAndView modelAndView = new ModelAndView("ciudadProductoTemplates/ciudadProducto-crear");
                modelAndView.addObject("ciudades", ciudadRepository.findAll());
                modelAndView.addObject("productos", productoRepository.findAll());
                return modelAndView;
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
                return new ModelAndView("ciudadProductoTemplates/ciudadProducto-list-global")
                                .addObject("productos", lista);
        }

}
