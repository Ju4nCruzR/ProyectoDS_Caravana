package co.edu.javeriana.caravana_medieval_cruz_f_ss.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class CaravanaProducto {
   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int stockEnCaravana;

    @ManyToOne
    private Caravana caravana;

    @ManyToOne
    private Producto producto;

    public CaravanaProducto() {
    }

    public CaravanaProducto(Caravana caravana, Producto producto, int stockEnCaravana) {
        this.caravana = caravana;
        this.producto = producto;
        this.stockEnCaravana = stockEnCaravana;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getStockEnCaravana() {
        return stockEnCaravana;
    }

    public void setStockEnCaravana(int stockEnCaravana) {
        this.stockEnCaravana = stockEnCaravana;
    }

    public Caravana getCaravana() {
        return caravana;
    }

    public void setCaravana(Caravana caravana) {
        this.caravana = caravana;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

}
