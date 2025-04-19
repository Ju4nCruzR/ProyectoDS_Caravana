package co.edu.javeriana.caravana_medieval_cruz_f_ss.dto;

public class ServicioAplicadoDTO {
    private Long caravanaId;
    private String tipoServicio;
    private String efectoAplicado;
    
    public ServicioAplicadoDTO() {
    }

    public ServicioAplicadoDTO(Long caravanaId, String tipoServicio, String efectoAplicado) {
        this.caravanaId = caravanaId;
        this.tipoServicio = tipoServicio;
        this.efectoAplicado = efectoAplicado;
    }

    public Long getCaravanaId() {
        return caravanaId;
    }

    public void setCaravanaId(Long caravanaId) {
        this.caravanaId = caravanaId;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public String getEfectoAplicado() {
        return efectoAplicado;
    }

    public void setEfectoAplicado(String efectoAplicado) {
        this.efectoAplicado = efectoAplicado;
    }

    
}
