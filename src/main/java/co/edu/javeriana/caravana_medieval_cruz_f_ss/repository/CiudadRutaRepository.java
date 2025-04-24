package co.edu.javeriana.caravana_medieval_cruz_f_ss.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Ciudad;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.CiudadRuta;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Ruta;

@Repository
public interface CiudadRutaRepository extends JpaRepository<CiudadRuta, Long> {
    void deleteByRutaId(Long rutaId);
    List<CiudadRuta> findByCiudad(Ciudad ciudad); // ciudad origen
    List<CiudadRuta> findByCiudadDestino(Ciudad ciudad); // ciudad destino
    List<Ruta> findByCiudadOrigen(Ciudad ciudad);

}
