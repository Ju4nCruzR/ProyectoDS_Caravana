package co.edu.javeriana.caravana_medieval_cruz_f_ss.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Ciudad;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Ruta;

@Repository
public interface RutaRepository extends JpaRepository<Ruta, Long>{
    List<Ruta> findByCiudadOrigen(Ciudad ciudad);
    List<Ruta> findByCiudadDestino(Ciudad ciudad);

}
