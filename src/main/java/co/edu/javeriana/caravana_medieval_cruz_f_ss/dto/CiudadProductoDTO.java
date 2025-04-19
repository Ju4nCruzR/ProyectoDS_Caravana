package co.edu.javeriana.caravana_medieval_cruz_f_ss.dto;

public class CiudadProductoDTO {
    private Long id;
    private Long productoId;
    private String nombreProducto;
    private int stockProducto;

    public CiudadProductoDTO() {
    }

    public CiudadProductoDTO(Long id, Long productoId, String nombreProducto, int stockProducto) {
        this.id = id;
        this.productoId = productoId;
        this.nombreProducto = nombreProducto;
        this.stockProducto = stockProducto;
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
    public int getStockProducto() {
        return stockProducto;
    }
    public void setStockProducto(int stockProducto) {
        this.stockProducto = stockProducto;
    }

    
}
