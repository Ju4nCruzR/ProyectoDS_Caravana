package co.edu.javeriana.caravana_medieval_cruz_f_ss.dto;

public class CaravanaFormularioDTO {
    private String nombreCaravana;
    private double velocidadCaravana;
    private double capacidadMaximaCargaCaravana;
    private double dineroDisponibleCaravana;
    private int puntosDeVidaCaravana;
    private Long ciudadId; // ID de la ciudad seleccionada en el formulario

    public CaravanaFormularioDTO() {
    }

    public CaravanaFormularioDTO(double capacidadMaximaCargaCaravana, Long ciudadId, double dineroDisponibleCaravana, String nombreCaravana, int puntosDeVidaCaravana, double velocidadCaravana) {
        this.capacidadMaximaCargaCaravana = capacidadMaximaCargaCaravana;
        this.ciudadId = ciudadId;
        this.dineroDisponibleCaravana = dineroDisponibleCaravana;
        this.nombreCaravana = nombreCaravana;
        this.puntosDeVidaCaravana = puntosDeVidaCaravana;
        this.velocidadCaravana = velocidadCaravana;
    }

    // Getters y Setters

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

    public Long getCiudadId() {
        return ciudadId;
    }

    public void setCiudadId(Long ciudadId) {
        this.ciudadId = ciudadId;
    }
}
