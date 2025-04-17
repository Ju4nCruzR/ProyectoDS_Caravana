package co.edu.javeriana.caravana_medieval_cruz_f_ss.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CiudadProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int stockProducto;

    public CiudadProducto() {
    }

    public CiudadProducto(long id, int stockProducto) {
        this.id = id;
        this.stockProducto = stockProducto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getStockProducto() {
        return stockProducto;
    }

    public void setStockProducto(int stockProducto) {
        this.stockProducto = stockProducto;
    }


}
