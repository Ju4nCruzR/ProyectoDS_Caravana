package co.edu.javeriana.caravana_medieval_cruz_f_ss.model;

import jakarta.persistence.*;

@Entity
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Enumerated(EnumType.STRING)
    private TipoServicio tipo;

    private double precioServicio;

    public enum TipoServicio {
        REPARAR,
        MEJORAR_CAPACIDAD,
        MEJORAR_VELOCIDAD,
        GUARDIAS
    }
    
    
}
