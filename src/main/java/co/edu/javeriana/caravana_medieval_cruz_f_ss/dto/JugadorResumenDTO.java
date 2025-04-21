package co.edu.javeriana.caravana_medieval_cruz_f_ss.dto;

public class JugadorResumenDTO {
    private Long id;
    private String nombreJugador;
    private String rolJugador;

    public JugadorResumenDTO() {
    }

    

    public JugadorResumenDTO(Long id, String nombreJugador, String rolJugador) {
        this.id = id;
        this.nombreJugador = nombreJugador;
        this.rolJugador = rolJugador;
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

}
