package co.edu.javeriana.caravana_medieval_cruz_f_ss.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Caravana;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.CaravanaProducto;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Producto;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.CaravanaProductoRepository;

@Service
public class CaravanaProductoService {
   
    @Autowired
    private CaravanaProductoRepository caravanaProductoRepository;

    // Caso 1: Listar productos de una caravana
    public List<CaravanaProducto> listarPorCaravana(Caravana caravana) {
        return caravanaProductoRepository.findAll().stream()
                .filter(cp -> cp.getCaravana().equals(caravana))
                .toList();
    }

    // Caso 2: Consultar detalle de producto en caravana
    public Optional<CaravanaProducto> obtenerPorCaravanaYProducto(Caravana caravana, Producto producto) {
        return caravanaProductoRepository.findByCaravanaAndProducto(caravana, producto);
    }

    // Caso 3: Actualizar stock
    public CaravanaProducto actualizarStock(CaravanaProducto cp, int nuevoStock) {
        cp.setStockEnCaravana(nuevoStock);
        return caravanaProductoRepository.save(cp);
    }

    // Caso 4: Eliminar producto de caravana
    public void eliminarPorId(Long id) {
        caravanaProductoRepository.deleteById(id);
    }

    // Caso 5: Agregar producto nuevo a caravana
    public CaravanaProducto crear(CaravanaProducto nuevo) {
        return caravanaProductoRepository.save(nuevo);
    }

    // Caso 6: Listar todos (opcional)
    public List<CaravanaProducto> listarTodos() {
        return caravanaProductoRepository.findAll();
    }
}
