package co.edu.javeriana.caravana_medieval_cruz_f_ss.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Jugador {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    private String nombreJugador;

    @Enumerated(EnumType.STRING)
    private Rol rolJugador;

    public enum Rol {
        COMERCIANTE,
        CARAVANERO
    }

    public Jugador() {
    }

    public Jugador(long id, String nombreJugador, Rol rolJugador) {
        this.id = id;
        this.nombreJugador = nombreJugador;
        this.rolJugador = rolJugador;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public Rol getRolJugador() {
        return rolJugador;
    }

    public void setRolJugador(Rol rolJugador) {
        this.rolJugador = rolJugador;
    }
    
    
}
