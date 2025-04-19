package co.edu.javeriana.caravana_medieval_cruz_f_ss.dto;

public class JugadorDTO {
    private Long id;
    private String nombreJugador;
    private String rolJugador;
    private Long caravanaId;
    private String nombreCaravana;
    
    public JugadorDTO() {
    }

    public JugadorDTO(Long id, String nombreJugador, String rolJugador, Long caravanaId, String nombreCaravana) {
        this.id = id;
        this.nombreJugador = nombreJugador;
        this.rolJugador = rolJugador;
        this.caravanaId = caravanaId;
        this.nombreCaravana = nombreCaravana;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public String getRolJugador() {
        return rolJugador;
    }

    public void setRolJugador(String rolJugador) {
        this.rolJugador = rolJugador;
    }

    public Long getCaravanaId() {
        return caravanaId;
    }

    public void setCaravanaId(Long caravanaId) {
        this.caravanaId = caravanaId;
    }

    public String getNombreCaravana() {
        return nombreCaravana;
    }

    public void setNombreCaravana(String nombreCaravana) {
        this.nombreCaravana = nombreCaravana;
    }

    
}
