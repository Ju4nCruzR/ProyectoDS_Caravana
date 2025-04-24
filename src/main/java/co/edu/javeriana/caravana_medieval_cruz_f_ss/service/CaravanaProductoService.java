package co.edu.javeriana.caravana_medieval_cruz_f_ss.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.dto.CaravanaProductoDTO;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.mapper.CaravanaProductoMapper;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Caravana;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.CaravanaProducto;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Producto;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.CaravanaProductoRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.CaravanaRepository;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.ProductoRepository;

@Service
public class CaravanaProductoService {

    @Autowired
    private CaravanaProductoRepository caravanaProductoRepository;

    @Autowired
    private CaravanaRepository caravanaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    // Caso 1: Listar productos de una caravana
    public List<CaravanaProductoDTO> listarPorCaravana(Long caravanaId) {
        Caravana caravana = caravanaRepository.findById(caravanaId)
                .orElseThrow(() -> new RuntimeException("Caravana no encontrada"));
        return caravanaProductoRepository.findByCaravana(caravana).stream()
                .map(CaravanaProductoMapper::toDTO)

                .toList();
    }

    // Caso 2: Consultar detalle de producto en caravana
    public Optional<CaravanaProductoDTO> obtenerPorCaravanaYProducto(Long caravanaId, Long productoId) {
        Caravana caravana = caravanaRepository.findById(caravanaId)
                .orElseThrow(() -> new RuntimeException("Caravana no encontrada"));
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return caravanaProductoRepository.findByCaravanaAndProducto(caravana, producto)
                .map(CaravanaProductoMapper::toDTO);
    }

    // Caso 3: Actualizar stock
    public CaravanaProductoDTO actualizarStock(Long id, int nuevoStock) {
        CaravanaProducto cp = caravanaProductoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        
        cp.setStockEnCaravana(nuevoStock);
        
        return CaravanaProductoMapper.toDTO(caravanaProductoRepository.save(cp));
    }    

    // Caso 4: Eliminar producto de caravana
    public void eliminarPorId(Long id) {
        caravanaProductoRepository.deleteById(id);
    }

    // Caso 5: Agregar producto nuevo a caravana
    public CaravanaProductoDTO crear(Long caravanaId, Long productoId, int stock) {
        Caravana caravana = caravanaRepository.findById(caravanaId)
                .orElseThrow(() -> new RuntimeException("Caravana no encontrada"));
    
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    
        // Verificamos si ya existe la relación
        Optional<CaravanaProducto> existenteOpt = caravanaProductoRepository.findByCaravanaAndProducto(caravana, producto);
    
        CaravanaProducto cp;
    
        if (existenteOpt.isPresent()) {
            // Si ya existe, se suma al stock
            cp = existenteOpt.get();
            cp.setStockEnCaravana(cp.getStockEnCaravana() + stock);
        } else {
            // Si no existe, se crea uno nuevo
            cp = new CaravanaProducto(caravana, producto, stock);
        }
    
        return CaravanaProductoMapper.toDTO(caravanaProductoRepository.save(cp));
    }
     

    // Caso 6: Listar todos
    public List<CaravanaProductoDTO> listarTodos() {
        return caravanaProductoRepository.findAll().stream()
                .map(CaravanaProductoMapper::toDTO)

                .toList();
    }

    // caso 7: Buscar por Id
    public Optional<CaravanaProductoDTO> buscarPorId(Long id) {
        return caravanaProductoRepository.findById(id)
                .map(CaravanaProductoMapper::toDTO);
    }

    public Optional<CaravanaProducto> obtenerEntidadPorId(Long id) {
        return caravanaProductoRepository.findById(id);
    }

}
