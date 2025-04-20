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

import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CaravanaProductoDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Caravana;
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

                List<CaravanaProductoDTO> productos = caravanaProductoService.listarPorCaravana(caravanaId);

                return new ModelAndView("caravanaProductoTemplates/caravanaProducto-list")
                                .addObject("caravana", caravana)
                                .addObject("productos", productos);
        }

        // Caso 2 (GET): Mostrar detalle de producto en la caravana
        @GetMapping("/caravana/{caravanaId}/producto/{productoId}")
        public ModelAndView detalleProducto(@PathVariable Long caravanaId, @PathVariable Long productoId) {
                CaravanaProductoDTO dto = caravanaProductoService.obtenerPorCaravanaYProducto(caravanaId, productoId)
                                .orElseThrow(() -> new RuntimeException("Producto no encontrado en la caravana"));

                return new ModelAndView("caravanaProductoTemplates/caravanaProducto-detalle")
                                .addObject("caravanaProducto", dto);
        }

        // Caso 2 (POST): Redirigir al GET con los parámetros correctos
        @PostMapping("/caravana/{caravanaId}/producto/{productoId}")
        public String detallePost(@RequestParam Long caravanaId, @RequestParam Long productoId) {
                return "redirect:/caravana-producto/caravana/" + caravanaId + "/producto/" + productoId;
        }

        // Caso 3: Formulario para actualizar stock
        @GetMapping("/{id}/actualizar")
        public ModelAndView mostrarFormularioActualizar(@PathVariable Long id) {
                CaravanaProductoDTO dto = caravanaProductoService.buscarPorId(id)
                                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
                return new ModelAndView("caravanaProductoTemplates/caravanaProducto-actualizar")
                                .addObject("caravanaProducto", dto);
        }

        // Caso 3: Actualizar stock
        @PostMapping("/{id}/actualizar")
        public String actualizarStock(@PathVariable Long id, @RequestParam int nuevoStock) {
                CaravanaProductoDTO actualizado = caravanaProductoService.actualizarStock(id, nuevoStock);
                return "redirect:/caravana-producto/caravana/" + actualizado.getCaravanaId();
        }

        // Caso 4: Formulario para eliminar
        @GetMapping("/{id}/eliminar")
        public ModelAndView confirmarEliminacion(@PathVariable Long id) {
                CaravanaProductoDTO dto = caravanaProductoService.buscarPorId(id)
                                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
                return new ModelAndView("caravanaProductoTemplates/caravanaProducto-eliminar")
                                .addObject("caravanaProducto", dto);
        }

        // Caso 4: Eliminar
        @PostMapping("/{id}/eliminar")
        public String eliminar(@PathVariable Long id) {
                Long caravanaId = caravanaProductoService.buscarPorId(id)
                                .map(CaravanaProductoDTO::getCaravanaId)
                                .orElse(0L);
                caravanaProductoService.eliminarPorId(id);
                return "redirect:/caravana-producto/caravana/" + caravanaId;
        }

        // Caso 5: Formulario para crear nuevo
        @GetMapping("/crear")
        public ModelAndView mostrarFormularioCrear() {
                ModelAndView mav = new ModelAndView("caravanaProductoTemplates/caravanaProducto-crear");
                mav.addObject("caravanas", caravanaRepository.findAll());
                mav.addObject("productos", productoRepository.findAll());
                return mav;
        }

        // Caso 5: Crear nuevo
        @PostMapping("/crear")
        public String crear(@RequestParam Long caravanaId,
                        @RequestParam Long productoId,
                        @RequestParam int stock) {
                caravanaProductoService.crear(caravanaId, productoId, stock);
                return "redirect:/caravana-producto/caravana/" + caravanaId;
        }

        // Caso 6: Listar todo
        @GetMapping("/list")
        public ModelAndView listarTodos() {
                return new ModelAndView("caravanaProductoTemplates/caravanaProducto-list-global")
                                .addObject("productos", caravanaProductoService.listarTodos());
        }
}
