package co.edu.javeriana.caravana_medieval_cruz_f_ss.dto;

public class ProductoDTO {
    private Long id;
    private String nombreProducto;
    private double factorDemandaProducto;
    private double factorOfertaProducto;
    private double precioBaseProducto;
    private double pesoProducto;
    
    public ProductoDTO() {
    }

    public ProductoDTO(Long id, String nombreProducto, double factorDemandaProducto, double factorOfertaProducto,
            double precioBaseProducto, double pesoProducto) {
        this.id = id;
        this.nombreProducto = nombreProducto;
        this.factorDemandaProducto = factorDemandaProducto;
        this.factorOfertaProducto = factorOfertaProducto;
        this.precioBaseProducto = precioBaseProducto;
        this.pesoProducto = pesoProducto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public double getFactorDemandaProducto() {
        return factorDemandaProducto;
    }

    public void setFactorDemandaProducto(double factorDemandaProducto) {
        this.factorDemandaProducto = factorDemandaProducto;
    }

    public double getFactorOfertaProducto() {
        return factorOfertaProducto;
    }

    public void setFactorOfertaProducto(double factorOfertaProducto) {
        this.factorOfertaProducto = factorOfertaProducto;
    }

    public double getPrecioBaseProducto() {
        return precioBaseProducto;
    }

    public void setPrecioBaseProducto(double precioBaseProducto) {
        this.precioBaseProducto = precioBaseProducto;
    }

    public double getPesoProducto() {
        return pesoProducto;
    }

    public void setPesoProducto(double pesoProducto) {
        this.pesoProducto = pesoProducto;
    }

    
}
