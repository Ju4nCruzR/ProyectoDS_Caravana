package co.edu.javeriana.caravana_medieval_cruz_f_ss.model;

import jakarta.persistence.*;

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
    
}
