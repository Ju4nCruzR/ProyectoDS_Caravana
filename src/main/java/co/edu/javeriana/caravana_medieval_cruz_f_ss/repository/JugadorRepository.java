package co.edu.javeriana.caravana_medieval_cruz_f_ss.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Caravana;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Jugador;

@Repository
public interface JugadorRepository extends  JpaRepository<Jugador, Long>{
  List<Jugador> findByCaravana(Caravana caravana);
  
}
