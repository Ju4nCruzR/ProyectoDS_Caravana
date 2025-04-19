package co.edu.javeriana.caravana_medieval_cruz_f_ss.dto;

public class CaravanaVentaDTO {
    private Long caravanaId;
    private Long productoId;
    private int cantidad;
    
    public CaravanaVentaDTO() {
    }

    public CaravanaVentaDTO(Long caravanaId, Long productoId, int cantidad) {
        this.caravanaId = caravanaId;
        this.productoId = productoId;
        this.cantidad = cantidad;
    }

    public Long getCaravanaId() {
        return caravanaId;
    }
    public void setCaravanaId(Long caravanaId) {
        this.caravanaId = caravanaId;
    }
    public Long getProductoId() {
        return productoId;
    }
    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    
}
