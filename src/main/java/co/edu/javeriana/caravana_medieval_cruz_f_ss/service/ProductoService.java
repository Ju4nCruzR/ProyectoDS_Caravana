package co.edu.javeriana.caravana_medieval_cruz_f_ss.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.CaravanaProducto;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.CiudadProducto;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Producto;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.CaravanaProductoRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.CiudadProductoRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.ProductoRepository;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CaravanaProductoRepository caravanaProductoRepository;

    @Autowired
    private CiudadProductoRepository ciudadProductoRepository;

    // Caso 1: Crear producto
    public Producto crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    // Caso 2: Ver por ID
    public Optional<Producto> buscarPorId(Long id) {
        return productoRepository.findById(id);
    }

    // Caso 3: Listar todos
    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    // Caso 4: Editar producto
    public Producto actualizarProducto(Long id, Producto datos) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        producto.setNombreProducto(datos.getNombreProducto());
        producto.setFactorDemandaProducto(datos.getFactorDemandaProducto());
        producto.setFactorOfertaProducto(datos.getFactorOfertaProducto());

        return productoRepository.save(producto);
    }

    // Caso 5: Eliminar producto
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }

    // Caso 6 (extra): Ver asociaciones con ciudades y caravanas
    public List<CiudadProducto> obtenerCiudadesAsociadas(Long productoId) {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return ciudadProductoRepository.findByProducto(producto);
    }

    public List<CaravanaProducto> obtenerCaravanasAsociadas(Long productoId) {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    
        return caravanaProductoRepository.findByProducto(producto);
    }
    
}
