package co.edu.javeriana.caravana_medieval_cruz_f_ss.dto;

import java.util.List;

public class CaravanaDetalleDTO {
    private CaravanaDTO caravana;
    private List<CaravanaProductoDTO> productos;
    private List<JugadorDTO> jugadores;
    private List<String> rutasRecorridas;

    public CaravanaDetalleDTO() {
    }

    public CaravanaDetalleDTO(CaravanaDTO caravana, List<CaravanaProductoDTO> productos, List<JugadorDTO> jugadores,
            List<String> rutasRecorridas) {
        this.caravana = caravana;
        this.productos = productos;
        this.jugadores = jugadores;
        this.rutasRecorridas = rutasRecorridas;
    }

    public CaravanaDTO getCaravana() {
        return caravana;
    }

    public void setCaravana(CaravanaDTO caravana) {
        this.caravana = caravana;
    }

    public List<CaravanaProductoDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<CaravanaProductoDTO> productos) {
        this.productos = productos;
    }

    public List<JugadorDTO> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<JugadorDTO> jugadores) {
        this.jugadores = jugadores;
    }

    public List<String> getRutasRecorridas() {
        return rutasRecorridas;
    }

    public void setRutasRecorridas(List<String> rutasRecorridas) {
        this.rutasRecorridas = rutasRecorridas;
    }

}
