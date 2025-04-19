package co.edu.javeriana.caravana_medieval_cruz_f_ss.dto;

public class RutaDTO {
    private Long id;
    private String origen;
    private String destino;
    private double distanciaRuta;
    private boolean esSeguraRuta;
    private int danoRuta;
    
    public RutaDTO() {
    }

    public RutaDTO(Long id, String origen, String destino, double distanciaRuta, boolean esSeguraRuta, int danoRuta) {
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.distanciaRuta = distanciaRuta;
        this.esSeguraRuta = esSeguraRuta;
        this.danoRuta = danoRuta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public double getDistanciaRuta() {
        return distanciaRuta;
    }

    public void setDistanciaRuta(double distanciaRuta) {
        this.distanciaRuta = distanciaRuta;
    }

    public boolean isEsSeguraRuta() {
        return esSeguraRuta;
    }

    public void setEsSeguraRuta(boolean esSeguraRuta) {
        this.esSeguraRuta = esSeguraRuta;
    }

    public int getDanoRuta() {
        return danoRuta;
    }

    public void setDanoRuta(int danoRuta) {
        this.danoRuta = danoRuta;
    }

    
}
