package co.edu.javeriana.caravana_medieval_cruz_f_ss.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CiudadProductoDTO;
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

                List<CiudadProductoDTO> productos = ciudadProductoService.listarPorCiudad(ciudad);

                return new ModelAndView("ciudadProductoTemplates/ciudadProducto-list")
                                .addObject("ciudad", ciudad)
                                .addObject("productos", productos);
        }

        // Caso 2: Ver detalle
        @GetMapping("/ciudad/{ciudadId}/producto/{productoId}")
        public ModelAndView verDetalleProductoEnCiudad(@PathVariable Long ciudadId, @PathVariable Long productoId) {
                Ciudad ciudad = ciudadRepository.findById(ciudadId)
                                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));

                Producto producto = productoRepository.findById(productoId)
                                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

                CiudadProductoDTO dto = ciudadProductoService.obtenerPorCiudadYProducto(ciudad, producto)
                                .orElseThrow(() -> new RuntimeException("Producto no registrado en la ciudad"));

                return new ModelAndView("ciudadProductoTemplates/ciudadProducto-detalle")
                                .addObject("ciudadProducto", dto);
        }

        // Caso 3: Mostrar formulario para actualizar stock de un producto en una ciudad
        @GetMapping("/{id}/actualizar")
        public ModelAndView mostrarFormularioActualizar(@PathVariable Long id) {
                CiudadProductoDTO dto = ciudadProductoService.listarTodos().stream()
                                .filter(p -> p.getId().equals(id))
                                .findFirst()
                                .orElseThrow(() -> new RuntimeException("No se encontró el producto"));

                return new ModelAndView("ciudadProductoTemplates/ciudadProducto-actualizar")
                                .addObject("ciudadProducto", dto);
        }

        // Caso 3 (POST): Actualizar stock
        @PostMapping("/{id}/actualizar")
        public String actualizarStock(@PathVariable Long id, @RequestParam int nuevoStock) {
                CiudadProductoDTO actualizado = ciudadProductoService.actualizarStock(id, nuevoStock);
                return "redirect:/ciudad-producto/ciudad/" + actualizado.getId(); // ajusta si tienes acceso a ciudadId
        }

        // Caso 4: Confirmación antes de eliminar producto de una ciudad
        @GetMapping("/ciudad/{ciudadId}/producto/{productoId}/eliminar")
        public ModelAndView mostrarConfirmacionEliminar(@PathVariable Long ciudadId, @PathVariable Long productoId) {
                Ciudad ciudad = ciudadRepository.findById(ciudadId)
                                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));

                Producto producto = productoRepository.findById(productoId)
                                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

                CiudadProductoDTO dto = ciudadProductoService.obtenerPorCiudadYProducto(ciudad, producto)
                                .orElseThrow(() -> new RuntimeException("Producto no registrado en la ciudad"));

                return new ModelAndView("ciudadProductoTemplates/ciudadProducto-eliminar")
                                .addObject("ciudadProducto", dto)
                                .addObject("ciudadId", ciudadId)
                                .addObject("productoId", productoId);
        }

        @PostMapping("/ciudad/{ciudadId}/producto/{productoId}/eliminar")
        public String eliminar(@PathVariable Long ciudadId, @PathVariable Long productoId) {
                Ciudad ciudad = ciudadRepository.findById(ciudadId)
                                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));

                Producto producto = productoRepository.findById(productoId)
                                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

                CiudadProducto cp = ciudadProductoService.obtenerEntidadPorCiudadAndProducto(ciudad, producto)
                                .orElseThrow(() -> new RuntimeException("Asociación no encontrada"));

                ciudadProductoService.eliminarPorId(cp.getId());

                return "redirect:/ciudad-producto/ciudad/" + ciudadId;
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
                return new ModelAndView("ciudadProductoTemplates/ciudadProducto-list-global")
                                .addObject("productos", ciudadProductoService.listarTodos());
        }

}
