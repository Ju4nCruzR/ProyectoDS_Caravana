package co.edu.javeriana.caravana_medieval_cruz_f_ss.service;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Ciudad;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.CiudadProducto;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Producto;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.repository.CiudadProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CiudadProductoService {
    
    @Autowired
    private CiudadProductoRepository ciudadProductoRepository;

    // Caso 1: Listar productos de una ciudad
    public List<CiudadProducto> listarPorCiudad(Ciudad ciudad) {
        return ciudadProductoRepository.findByCiudad(ciudad);
    }

    // Caso 2: Consultar detalle de producto en ciudad
    public Optional<CiudadProducto> obtenerPorCiudadYProducto(Ciudad ciudad, Producto producto) {
        return ciudadProductoRepository.findByCiudadAndProducto(ciudad, producto);
    }

    // Caso 3: Actualizar stock
    public CiudadProducto actualizarStock(CiudadProducto cp, int nuevoStock) {
        cp.setStockProducto(nuevoStock);
        return ciudadProductoRepository.save(cp);
    }

    // Caso 4: Eliminar producto de ciudad
    public void eliminarPorId(Long id) {
        ciudadProductoRepository.deleteById(id);
    }

    // Caso 5: Agregar producto nuevo a ciudad
    public CiudadProducto crear(CiudadProducto nuevo) {
        return ciudadProductoRepository.save(nuevo);
    }

    // Caso 6: Listar todo (opcional)
    public List<CiudadProducto> listarTodos() {
        return ciudadProductoRepository.findAll();
    } 
}
