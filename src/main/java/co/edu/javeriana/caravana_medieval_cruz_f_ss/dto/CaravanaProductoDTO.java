package co.edu.javeriana.caravana_medieval_cruz_f_ss.dto;

public class CaravanaProductoDTO {
    private Long id;
    private Long productoId;
    private String nombreProducto;
    private int stockEnCaravana;
    private double pesoTotal;
    private double precioBaseProducto;
    
    public CaravanaProductoDTO() {
    }

    public CaravanaProductoDTO(Long id, Long productoId, String nombreProducto, int stockEnCaravana, double pesoTotal,
            double precioBaseProducto) {
        this.id = id;
        this.productoId = productoId;
        this.nombreProducto = nombreProducto;
        this.stockEnCaravana = stockEnCaravana;
        this.pesoTotal = pesoTotal;
        this.precioBaseProducto = precioBaseProducto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getStockEnCaravana() {
        return stockEnCaravana;
    }

    public void setStockEnCaravana(int stockEnCaravana) {
        this.stockEnCaravana = stockEnCaravana;
    }

    public double getPesoTotal() {
        return pesoTotal;
    }

    public void setPesoTotal(double pesoTotal) {
        this.pesoTotal = pesoTotal;
    }

    public double getPrecioBaseProducto() {
        return precioBaseProducto;
    }

    public void setPrecioBaseProducto(double precioBaseProducto) {
        this.precioBaseProducto = precioBaseProducto;
    }
    


    
}
