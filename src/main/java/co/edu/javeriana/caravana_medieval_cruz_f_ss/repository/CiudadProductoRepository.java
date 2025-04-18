package co.edu.javeriana.caravana_medieval_cruz_f_ss.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Ciudad;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.CiudadProducto;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Producto;

@Repository
public interface CiudadProductoRepository extends JpaRepository<CiudadProducto, Long>{
    Optional<CiudadProducto> findByCiudadAndProducto(Ciudad ciudad, Producto producto);
    List<CiudadProducto> findByCiudad(Ciudad ciudad);

}
