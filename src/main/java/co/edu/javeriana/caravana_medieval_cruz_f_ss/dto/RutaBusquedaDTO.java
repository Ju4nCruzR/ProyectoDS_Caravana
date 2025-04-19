package co.edu.javeriana.caravana_medieval_cruz_f_ss.dto;

public class RutaBusquedaDTO {
    private String ciudadOrigen;
    private String ciudadDestino;
    private double distancia;
    private boolean esSegura;
    private int danoRuta;
    
    public RutaBusquedaDTO() {
    }

    public RutaBusquedaDTO(String ciudadOrigen, String ciudadDestino, double distancia, boolean esSegura,
            int danoRuta) {
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
        this.distancia = distancia;
        this.esSegura = esSegura;
        this.danoRuta = danoRuta;
    }

    public String getCiudadOrigen() {
        return ciudadOrigen;
    }

    public void setCiudadOrigen(String ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    public String getCiudadDestino() {
        return ciudadDestino;
    }

    public void setCiudadDestino(String ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public boolean isEsSegura() {
        return esSegura;
    }

    public void setEsSegura(boolean esSegura) {
        this.esSegura = esSegura;
    }

    public int getDanoRuta() {
        return danoRuta;
    }

    public void setDanoRuta(int danoRuta) {
        this.danoRuta = danoRuta;
    }

    
}
