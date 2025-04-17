package co.edu.javeriana.caravana_medieval_cruz_f_ss.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Caravana {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String nombreCaravana;
    private double velocidadCaravana;
    private double capacidadMaximaCargaCaravana;
    private double dineroDisponibleCaravana;
    private int puntosDeVidaCaravana;

    public Caravana() {
    }

    public Caravana(long id, String nombreCaravana, double velocidadCaravana, double capacidadMaximaCargaCaravana, double dineroDisponibleCaravana, int puntosDeVidaCaravana) {
        this.id = id;
        this.nombreCaravana = nombreCaravana;
        this.velocidadCaravana = velocidadCaravana;
        this.capacidadMaximaCargaCaravana = capacidadMaximaCargaCaravana;
        this.dineroDisponibleCaravana = dineroDisponibleCaravana;
        this.puntosDeVidaCaravana = puntosDeVidaCaravana;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombreCaravana() {
        return nombreCaravana;
    }

    public void setNombreCaravana(String nombreCaravana) {
        this.nombreCaravana = nombreCaravana;
    }

    public double getVelocidadCaravana() {
        return velocidadCaravana;
    }

    public void setVelocidadCaravana(double velocidadCaravana) {
        this.velocidadCaravana = velocidadCaravana;
    }

    public double getCapacidadMaximaCargaCaravana() {
        return capacidadMaximaCargaCaravana;
    }

    public void setCapacidadMaximaCargaCaravana(double capacidadMaximaCargaCaravana) {
        this.capacidadMaximaCargaCaravana = capacidadMaximaCargaCaravana;
    }

    public double getDineroDisponibleCaravana() {
        return dineroDisponibleCaravana;
    }

    public void setDineroDisponibleCaravana(double dineroDisponibleCaravana) {
        this.dineroDisponibleCaravana = dineroDisponibleCaravana;
    }

    public int getPuntosDeVidaCaravana() {
        return puntosDeVidaCaravana;
    }

    public void setPuntosDeVidaCaravana(int puntosDeVidaCaravana) {
        this.puntosDeVidaCaravana = puntosDeVidaCaravana;
    }

    
}
