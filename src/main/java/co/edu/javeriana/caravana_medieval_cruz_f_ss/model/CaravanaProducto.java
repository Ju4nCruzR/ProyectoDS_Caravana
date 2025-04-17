package co.edu.javeriana.caravana_medieval_cruz_f_ss.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CaravanaProducto {
   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int stockEnCaravana;

    public CaravanaProducto() {
    }

    public CaravanaProducto(long id, int stockEnCaravana) {
        this.id = id;
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

}
