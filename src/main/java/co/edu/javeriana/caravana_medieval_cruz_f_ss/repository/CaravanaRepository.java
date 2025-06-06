package co.edu.javeriana.caravana_medieval_cruz_f_ss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Caravana;

@Repository
public interface CaravanaRepository extends JpaRepository<Caravana, Long> {
    
}
