package co.edu.javeriana.caravana_medieval_cruz_f_ss.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Juego;
import co.edu.javeriana.caravana_medieval_cruz_f_ss.model.Jugador;

@Repository
public interface JuegoRepository extends JpaRepository<Juego, Long> {
    List<Juego> findByJugadoresContains(Jugador jugador);

    @Query("SELECT j FROM Juego j JOIN j.jugadores jugador WHERE jugador.id = :jugadorId")
    List<Juego> findByJugadorId(@Param("jugadorId") Long jugadorId);

}
