package co.edu.javeriana.caravana_medieval_cruz_f_ss.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CaravanaProductoDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CiudadProductoDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.ProductoAsociacionesDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.ProductoDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.mapper.CaravanaProductoMapper;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.mapper.CiudadProductoMapper;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.mapper.ProductoMapper;
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
    public ProductoDTO crearProducto(ProductoDTO dto) {
        Producto producto = new Producto();
        producto.setNombreProducto(dto.getNombreProducto());
        producto.setFactorDemandaProducto(dto.getFactorDemandaProducto());
        producto.setFactorOfertaProducto(dto.getFactorOfertaProducto());
        producto.setPrecioBaseProducto(dto.getPrecioBaseProducto());
        producto.setPesoProducto(dto.getPesoProducto());
        return ProductoMapper.toDTO(productoRepository.save(producto));
    }

    // Caso 2: Ver por ID
    public Optional<ProductoDTO> buscarPorId(Long id) {
        return productoRepository.findById(id)
                .map(ProductoMapper::toDTO);
    }

    // Caso 3: Listar todos
    public List<ProductoDTO> listarTodos() {
        return productoRepository.findAll().stream()
                .map(ProductoMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Caso 4: Editar producto
    public ProductoDTO actualizarProducto(Long id, ProductoDTO dto) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        producto.setNombreProducto(dto.getNombreProducto());
        producto.setFactorDemandaProducto(dto.getFactorDemandaProducto());
        producto.setFactorOfertaProducto(dto.getFactorOfertaProducto());
        producto.setPrecioBaseProducto(dto.getPrecioBaseProducto());
        producto.setPesoProducto(dto.getPesoProducto());

        return ProductoMapper.toDTO(productoRepository.save(producto));
    }

    // Caso 5: Eliminar producto
    @Transactional
    public void eliminarProducto(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Eliminar asociaciones primero
        caravanaProductoRepository.deleteAllByProducto(producto);
        ciudadProductoRepository.deleteAllByProducto(producto);

        // Ahora sí puedes eliminar el producto
        productoRepository.delete(producto);
    }

    // Caso 6: Ver asociaciones con ciudades y caravanas

    public ProductoAsociacionesDTO obtenerAsociaciones(Long productoId) {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        List<CiudadProductoDTO> enCiudades = obtenerCiudadesAsociadas(productoId);
        List<CaravanaProductoDTO> enCaravanas = obtenerCaravanasAsociadas(productoId);

        return new ProductoAsociacionesDTO(ProductoMapper.toDTO(producto), enCiudades, enCaravanas);
    }

    // Caso 6.1: Ver asociaciones con ciudades
    public List<CiudadProductoDTO> obtenerCiudadesAsociadas(Long productoId) {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        return ciudadProductoRepository.findByProducto(producto).stream()
                .map(CiudadProductoMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Caso 6.2: Ver asociaciones con caravanas
    public List<CaravanaProductoDTO> obtenerCaravanasAsociadas(Long productoId) {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        return caravanaProductoRepository.findByProducto(producto).stream()
                .map(CaravanaProductoMapper::toDTO)
                .collect(Collectors.toList());
    }

}
