package co.edu.javeriana.caravana_medieval_cruz_f_ss.dto;

public class CaravanaDTO {
    private Long id;
    private String nombreCaravana;
    private double velocidadCaravana;
    private double capacidadMaximaCargaCaravana;
    private double dineroDisponibleCaravana;
    private int puntosDeVidaCaravana;
    private String nombreCiudadActual;
    private Long ciudadId;
    
    public CaravanaDTO() {
    }

    public CaravanaDTO(Long id, String nombreCaravana, double velocidadCaravana, double capacidadMaximaCargaCaravana,
            double dineroDisponibleCaravana, int puntosDeVidaCaravana, String nombreCiudadActual, Long ciudadId) {
        this.id = id;
        this.nombreCaravana = nombreCaravana;
        this.velocidadCaravana = velocidadCaravana;
        this.capacidadMaximaCargaCaravana = capacidadMaximaCargaCaravana;
        this.dineroDisponibleCaravana = dineroDisponibleCaravana;
        this.puntosDeVidaCaravana = puntosDeVidaCaravana;
        this.nombreCiudadActual = nombreCiudadActual;
        this.ciudadId = ciudadId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getNombreCiudadActual() {
        return nombreCiudadActual;
    }

    public void setNombreCiudadActual(String nombreCiudadActual) {
        this.nombreCiudadActual = nombreCiudadActual;
    }

    public Long getCiudadId() {
        return ciudadId;
    }

    public void setCiudadId(Long ciudadId) {
        this.ciudadId = ciudadId;
    }

}
