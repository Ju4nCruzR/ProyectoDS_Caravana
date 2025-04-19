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

import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Caravana;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.CaravanaProducto;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Producto;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.CaravanaRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.ProductoRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.service.CaravanaProductoService;

@Controller
@RequestMapping("/caravana-producto")
public class CaravanaProductoController {

        @Autowired
        private CaravanaProductoService caravanaProductoService;

        @Autowired
        private CaravanaRepository caravanaRepository;

        @Autowired
        private ProductoRepository productoRepository;

        // Caso 1: Listar productos de una caravana
        @GetMapping("/caravana/{caravanaId}")
        public ModelAndView listarPorCaravana(@PathVariable Long caravanaId) {
                Caravana caravana = caravanaRepository.findById(caravanaId)
                                .orElseThrow(() -> new RuntimeException("Caravana no encontrada"));

                List<CaravanaProducto> productos = caravanaProductoService.listarPorCaravana(caravana);

                return new ModelAndView("caravanaProducto-list")
                                .addObject("caravana", caravana)
                                .addObject("productos", productos);
        }

        // Caso 2: Formulario para ver detalle de producto en caravana
        @GetMapping("/detalle")
        @ResponseBody
        public CaravanaProducto detalle(@RequestParam Long caravanaId, @RequestParam Long productoId) {
                Caravana caravana = caravanaRepository.findById(caravanaId)
                                .orElseThrow(() -> new RuntimeException("Caravana no encontrada"));
                Producto producto = productoRepository.findById(productoId)
                                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

                return caravanaProductoService.obtenerPorCaravanaYProducto(caravana, producto)
                                .orElseThrow(() -> new RuntimeException("No encontrado"));
        }

        // Caso 2: Ver detalle de producto en caravana
        @PostMapping("/detalle")
        public ModelAndView detallePost(@RequestParam Long caravanaId, @RequestParam Long productoId) {
                Caravana caravana = caravanaRepository.findById(caravanaId)
                                .orElseThrow(() -> new RuntimeException("Caravana no encontrada"));
                Producto producto = productoRepository.findById(productoId)
                                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

                CaravanaProducto cp = caravanaProductoService.obtenerPorCaravanaYProducto(caravana, producto)
                                .orElseThrow(() -> new RuntimeException("No encontrado"));

                return new ModelAndView("caravanaProducto-detalle")
                                .addObject("caravanaProducto", cp);
        }

        // Caso 3: Formulario para actualizar stock
        @GetMapping("/{id}/actualizar")
        public ModelAndView mostrarFormularioActualizar(@PathVariable Long id) {
                CaravanaProducto cp = caravanaProductoService.buscarPorId(id)
                                .orElseThrow(() -> new RuntimeException("CaravanaProducto no encontrado"));

                return new ModelAndView("caravanaProducto-actualizar")
                                .addObject("caravanaProducto", cp);
        }

         // Caso 3: Actualizar stock
        @PostMapping("/{id}/actualizar")
        public String actualizarStock(@PathVariable Long id, @RequestParam int nuevoStock) {
                CaravanaProducto cp = caravanaProductoService.buscarPorId(id)
                                .orElseThrow(() -> new RuntimeException("CaravanaProducto no encontrado"));

                cp.setStockEnCaravana(nuevoStock);
                caravanaProductoService.actualizarStock(cp, nuevoStock);

                return "redirect:/caravana/" + cp.getCaravana().getId(); // o redirige a donde prefieras
        }

        // Caso 4: Formulario para eliminar
        @GetMapping("/{id}/eliminar")
        public ModelAndView confirmarEliminacion(@PathVariable Long id) {
                CaravanaProducto cp = caravanaProductoService.buscarPorId(id)
                                .orElseThrow(() -> new RuntimeException("CaravanaProducto no encontrado"));

                return new ModelAndView("caravanaProducto-eliminar")
                                .addObject("caravanaProducto", cp);
        }

         // Caso 4: Eliminar
        @PostMapping("/{id}/eliminar")
        public String eliminar(@PathVariable Long id) {
                caravanaProductoService.eliminarPorId(id);
                return "redirect:/caravana-producto/list";
        }

        // Caso 5: Formulario para crear nuevo
        @GetMapping("/crear")
        public ModelAndView mostrarFormularioCrear() {
                ModelAndView modelAndView = new ModelAndView("caravanaProducto-crear");
                modelAndView.addObject("caravanas", caravanaRepository.findAll());
                modelAndView.addObject("productos", productoRepository.findAll());
                return modelAndView;
        }

        // Caso 5: Crear nuevo
        @PostMapping("/crear")
        public String crear(@RequestParam Long caravanaId,
                        @RequestParam Long productoId,
                        @RequestParam int stock) {
                Caravana caravana = caravanaRepository.findById(caravanaId)
                                .orElseThrow(() -> new RuntimeException("Caravana no encontrada"));
                Producto producto = productoRepository.findById(productoId)
                                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

                CaravanaProducto cp = new CaravanaProducto(caravana, producto, stock);
                caravanaProductoService.crear(cp);

                return "redirect:/caravana-producto/caravana/" + caravanaId;
        }

        // Caso 6: Listar todo
        @GetMapping("/list")
        public ModelAndView listarTodos() {
                List<CaravanaProducto> lista = caravanaProductoService.listarTodos();
                return new ModelAndView("caravanaProducto-list-global")
                                .addObject("productos", lista);
        }
}
