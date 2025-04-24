package co.edu.javeriana.caravana_medieval_cruz_f_ss.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

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

    @ManyToOne
    private Ciudad ciudadActual;

    @OneToMany(mappedBy = "caravana")
    private List<CaravanaProducto> productos;

    @OneToMany(mappedBy = "caravana")
    private List<Jugador> jugadores;

    @ManyToMany
    private List<Servicio> serviciosAplicados = new ArrayList<>();

    @ManyToMany
    private List<Ruta> rutasRecorridas;

    public Caravana() {
    }

    public Caravana(double capacidadMaximaCargaCaravana, Ciudad ciudadActual, double dineroDisponibleCaravana,
            List<Jugador> jugadores, String nombreCaravana, List<CaravanaProducto> productos, int puntosDeVidaCaravana,
            List<Ruta> rutasRecorridas, double velocidadCaravana, List<Servicio> serviciosAplicados) {
        this.capacidadMaximaCargaCaravana = capacidadMaximaCargaCaravana;
        this.ciudadActual = ciudadActual;
        this.dineroDisponibleCaravana = dineroDisponibleCaravana;
        this.jugadores = jugadores;
        this.nombreCaravana = nombreCaravana;
        this.productos = productos;
        this.puntosDeVidaCaravana = puntosDeVidaCaravana;
        this.rutasRecorridas = rutasRecorridas;
        this.velocidadCaravana = velocidadCaravana;
        this.serviciosAplicados = serviciosAplicados;
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

    public Ciudad getCiudadActual() {
        return ciudadActual;
    }

    public void setCiudadActual(Ciudad ciudadActual) {
        this.ciudadActual = ciudadActual;
    }

    public List<CaravanaProducto> getProductos() {
        return productos;
    }

    public void setProductos(List<CaravanaProducto> productos) {
        this.productos = productos;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public List<Ruta> getRutasRecorridas() {
        return rutasRecorridas;
    }

    public void setRutasRecorridas(List<Ruta> rutasRecorridas) {
        this.rutasRecorridas = rutasRecorridas;
    }

    public double calcularPesoActual(Caravana caravana) {
        return caravana.getProductos().stream()
                .mapToDouble(p -> p.getProducto().getPesoProducto() * p.getStockEnCaravana())
                .sum();
    }

    public List<Servicio> getServiciosAplicados() {
        return serviciosAplicados;
    }

    public void setServiciosAplicados(List<Servicio> serviciosAplicados) {
        this.serviciosAplicados = serviciosAplicados;
    }

    
}
