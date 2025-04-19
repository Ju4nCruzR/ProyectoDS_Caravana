package co.edu.javeriana.caravana_medieval_cruz_f_ss.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Ciudad;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.CiudadServicio;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Servicio;

@Repository
public interface CiudadServicioRepository extends JpaRepository<CiudadServicio, Long> {
    Optional<CiudadServicio> findByCiudadAndServicio_Id(Ciudad ciudad, Long servicioId);
    List<CiudadServicio> findByCiudad(Ciudad ciudad);
    List<CiudadServicio> findByServicio(Servicio servicio);

}
