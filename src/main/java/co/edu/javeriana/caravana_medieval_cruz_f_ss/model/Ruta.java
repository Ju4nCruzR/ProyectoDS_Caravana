package co.edu.javeriana.caravana_medieval_cruz_f_ss.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.*;

@Entity
public class Ruta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private double distanciaRuta;
    private boolean esSeguraRuta;
    private int danoRuta;

    
}
