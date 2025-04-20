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

import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CiudadDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CiudadDetalleDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CiudadFormularioDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CiudadProductoDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CiudadRutaDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CiudadServicioDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Producto;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Ruta;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Servicio;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.ProductoRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.RutaRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.ServicioRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.service.CiudadService;

@Controller
@RequestMapping("/ciudad")
public class CiudadController {

        @Autowired
        private CiudadService ciudadService;

        @Autowired
        private ServicioRepository servicioRepository;

        @Autowired
        private RutaRepository rutaRepository;

        @Autowired
        private ProductoRepository productoRepository;

        // Caso 1: Mostrar formulario de creación
        @GetMapping("/crear")
        public ModelAndView mostrarFormularioCrear() {
                return new ModelAndView("ciudadTemplates/ciudad-crear")
                                .addObject("ciudad", new CiudadFormularioDTO());
        }

        // Caso 1 (POST): Crear ciudad
        @PostMapping("/crear")
        public String crearCiudad(@ModelAttribute CiudadFormularioDTO dto) {
                ciudadService.crearCiudad(dto);
                return "redirect:/ciudad/list";
        }

        // Caso 2: Ver ciudad por ID
        @GetMapping("/{id}")
        public ModelAndView verCiudad(@PathVariable Long id) {
                CiudadDetalleDTO detalle = ciudadService.buscarCiudadPorId(id);
                return new ModelAndView("ciudadTemplates/ciudad-detalle")
                                .addObject("ciudad", detalle.getCiudad())
                                .addObject("productos", detalle.getProductos())
                                .addObject("servicios", detalle.getServicios())
                                .addObject("rutas", detalle.getRutas());
        }

        // Caso 3: Listar ciudades
        @GetMapping("/list")
        public ModelAndView listarCiudades() {
                List<CiudadDTO> ciudades = ciudadService.listarCiudades();
                return new ModelAndView("ciudadTemplates/ciudad-list")
                                .addObject("ciudades", ciudades);
        }

        // Caso 4: Mostrar formulario de edición
        @GetMapping("/{id}/editar")
        public ModelAndView mostrarFormularioEditar(@PathVariable Long id) {
                CiudadDetalleDTO detalle = ciudadService.buscarCiudadPorId(id);
                CiudadDTO ciudad = detalle.getCiudad();

                List<Producto> todosLosProductos = productoRepository.findAll();
                List<Servicio> todosLosServicios = servicioRepository.findAll();
                List<Ruta> todasLasRutas = rutaRepository.findAll();

                List<Long> productoIdsSeleccionados = detalle.getProductos().stream()
                                .map(CiudadProductoDTO::getProductoId)
                                .toList();

                List<Long> servicioIdsSeleccionados = detalle.getServicios().stream()
                                .map(CiudadServicioDTO::getServicioId)
                                .toList();

                List<Long> rutaIdsSeleccionadas = detalle.getRutas().stream()
                                .map(CiudadRutaDTO::getRutaId)
                                .toList();

                return new ModelAndView("ciudadTemplates/ciudad-editar")
                                .addObject("ciudad", ciudad)
                                .addObject("todosLosProductos", todosLosProductos)
                                .addObject("todosLosServicios", todosLosServicios)
                                .addObject("todasLasRutas", todasLasRutas)
                                .addObject("productoIdsSeleccionados", productoIdsSeleccionados)
                                .addObject("servicioIdsSeleccionados", servicioIdsSeleccionados)
                                .addObject("rutaIdsSeleccionadas", rutaIdsSeleccionadas);
        }

        // Caso 4 (POST): Actualizar ciudad
        @PostMapping("/{id}/editar")
        public String actualizarCiudad(@PathVariable Long id, @ModelAttribute CiudadFormularioDTO dto) {
                ciudadService.actualizarCiudadConAsociaciones(id, dto);
                return "redirect:/ciudad/" + id;
        }

        // Caso 5: Confirmación antes de eliminar ciudad
        @GetMapping("/{id}/eliminar")
        public ModelAndView mostrarConfirmacionEliminar(@PathVariable Long id) {
                CiudadDetalleDTO detalle = ciudadService.buscarCiudadPorId(id);
                return new ModelAndView("ciudadTemplates/ciudad-eliminar")
                                .addObject("ciudad", detalle.getCiudad());
        }

        // Caso 5 (POST): Eliminar ciudad
        @PostMapping("/{id}/eliminar")
        public String eliminarCiudad(@PathVariable Long id) {
                ciudadService.eliminarCiudad(id);
                return "redirect:/ciudad/list";
        }
}