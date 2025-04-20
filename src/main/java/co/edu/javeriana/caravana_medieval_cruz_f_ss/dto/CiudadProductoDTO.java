package co.edu.javeriana.caravana_medieval_cruz_f_ss.dto;

public class CiudadProductoDTO {
    private Long id;
    private Long productoId;
    private String nombreProducto;
    private int stockProducto;
    private String nombreCiudad;

    public CiudadProductoDTO() {
    }

    public CiudadProductoDTO(Long id, String nombreCiudad, String nombreProducto, Long productoId, int stockProducto) {
        this.id = id;
        this.nombreCiudad = nombreCiudad;
        this.nombreProducto = nombreProducto;
        this.productoId = productoId;
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

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    
}
