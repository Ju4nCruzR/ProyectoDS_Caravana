package co.edu.javeriana.caravana_medieval_cruz_f_ss.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Jugador {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    private String nombreJugador;

    @ManyToOne
    private Caravana caravana;

    @Enumerated(EnumType.STRING)
    private Rol rolJugador;

    public Jugador() {
    }

    public Jugador(Caravana caravana, String nombreJugador, Rol rolJugador) {
        this.caravana = caravana;
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

    public Caravana getCaravana() {
        return caravana;
    }

    public void setCaravana(Caravana caravana) {
        this.caravana = caravana;
    }

    public Rol getRolJugador() {
        return rolJugador;
    }

    public void setRolJugador(Rol rolJugador) {
        this.rolJugador = rolJugador;
    }

    public enum Rol {
        COMERCIANTE,
        CARAVANERO
    }    

}
