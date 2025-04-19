package co.edu.javeriana.caravana_medieval_cruz_f_ss.dto;

public class CiudadDTO {
    private Long id;
    private String nombreCiudad;
    private double impuestosDeEntradaCiudad;
    
    public CiudadDTO() {
    }

    public CiudadDTO(Long id, String nombreCiudad, double impuestosDeEntradaCiudad) {
        this.id = id;
        this.nombreCiudad = nombreCiudad;
        this.impuestosDeEntradaCiudad = impuestosDeEntradaCiudad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    public double getImpuestosDeEntradaCiudad() {
        return impuestosDeEntradaCiudad;
    }

    public void setImpuestosDeEntradaCiudad(double impuestosDeEntradaCiudad) {
        this.impuestosDeEntradaCiudad = impuestosDeEntradaCiudad;
    }

    
}
