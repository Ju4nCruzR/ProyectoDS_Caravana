package co.edu.javeriana.caravana_medieval_cruz_f_ss.dto;

public class CaravanaResumenDTO {
    private Long id;
    private String nombreCaravana;
    private String ciudadActual;
   
    public CaravanaResumenDTO() {
    }

    public CaravanaResumenDTO(Long id, String nombreCaravana, String ciudadActual) {
        this.id = id;
        this.nombreCaravana = nombreCaravana;
        this.ciudadActual = ciudadActual;
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

    public String getCiudadActual() {
        return ciudadActual;
    }

    public void setCiudadActual(String ciudadActual) {
        this.ciudadActual = ciudadActual;
    }

    
}
