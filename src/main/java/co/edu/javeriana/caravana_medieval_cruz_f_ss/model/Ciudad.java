package co.edu.javeriana.caravana_medieval_cruz_f_ss.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Ciudad {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String nombreCiudad;
    private double impuestosDeEntradaCiudad;

    @OneToMany(mappedBy = "ciudad")
    private List<CiudadProducto> productosDisponibles = new ArrayList<>();

    @OneToMany(mappedBy = "ciudad")
    private List<CiudadServicio> serviciosDisponibles = new ArrayList<>();

    @OneToMany(mappedBy = "ciudadOrigen", fetch = FetchType.EAGER)
    private List<Ruta> rutasOrigen = new ArrayList<>();

    @OneToMany(mappedBy = "ciudadDestino")
    private List<Ruta> rutasDestino = new ArrayList<>();

    @OneToMany(mappedBy = "ciudad")
    private List<CiudadRuta> rutasAsociadas = new ArrayList<>();

    public Ciudad() {
        // listas ya inicializadas arriba
    }

    public Ciudad(double impuestosDeEntradaCiudad, String nombreCiudad,
            List<CiudadProducto> productosDisponibles,
            List<Ruta> rutasDestino,
            List<Ruta> rutasOrigen,
            List<CiudadServicio> serviciosDisponibles,
            List<CiudadRuta> rutasAsociadas) {
        this.impuestosDeEntradaCiudad = impuestosDeEntradaCiudad;
        this.nombreCiudad = nombreCiudad;
        this.productosDisponibles = new ArrayList<>(productosDisponibles);
        this.rutasDestino = new ArrayList<>(rutasDestino);
        this.rutasOrigen = new ArrayList<>(rutasOrigen);
        this.serviciosDisponibles = new ArrayList<>(serviciosDisponibles);
        this.rutasAsociadas = new ArrayList<>(rutasAsociadas);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public List<CiudadProducto> getProductosDisponibles() {
        return productosDisponibles;
    }

    public void setProductosDisponibles(List<CiudadProducto> productosDisponibles) {
        this.productosDisponibles = productosDisponibles;
    }

    public List<CiudadServicio> getServiciosDisponibles() {
        return serviciosDisponibles;
    }

    public void setServiciosDisponibles(List<CiudadServicio> serviciosDisponibles) {
        this.serviciosDisponibles = serviciosDisponibles;
    }

    public List<Ruta> getRutasOrigen() {
        return rutasOrigen;
    }

    public void setRutasOrigen(List<Ruta> rutasOrigen) {
        this.rutasOrigen = rutasOrigen;
    }

    public List<Ruta> getRutasDestino() {
        return rutasDestino;
    }

    public void setRutasDestino(List<Ruta> rutasDestino) {
        this.rutasDestino = rutasDestino;
    }

    public List<CiudadRuta> getRutasAsociadas() {
        return rutasAsociadas;
    }

    public void setRutasAsociadas(List<CiudadRuta> rutasAsociadas) {
        this.rutasAsociadas = rutasAsociadas;
    }

}
