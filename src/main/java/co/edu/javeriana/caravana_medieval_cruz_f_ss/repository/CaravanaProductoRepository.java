package co.edu.javeriana.caravana_medieval_cruz_f_ss.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Caravana;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.CaravanaProducto;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Producto;

@Repository
public interface CaravanaProductoRepository extends JpaRepository<CaravanaProducto, Long> {
    Optional<CaravanaProducto> findByCaravanaAndProducto(Caravana caravana, Producto producto);
    List<CaravanaProducto> findByProducto(Producto producto);
    List<CaravanaProducto> findByCaravana(Caravana caravana);
    void deleteAllByProducto(Producto producto);

}
