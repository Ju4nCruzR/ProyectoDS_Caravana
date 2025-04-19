package co.edu.javeriana.caravana_medieval_cruz_f_ss.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Ciudad;

@Repository
public interface CiudadRepository extends JpaRepository<Ciudad, Long> {
    
    @Override
    @EntityGraph(attributePaths = {
        "productosDisponibles.producto",
        "serviciosDisponibles.servicio",
        "rutasAsociadas.ruta.ciudadDestino"
    })
    Optional<Ciudad> findById(Long id);
}
