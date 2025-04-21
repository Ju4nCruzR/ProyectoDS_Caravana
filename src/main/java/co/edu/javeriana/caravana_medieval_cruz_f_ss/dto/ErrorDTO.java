package co.edu.javeriana.caravana_medieval_cruz_f_ss.dto;

import java.time.LocalDateTime;

public class ErrorDTO {

    private final String mensaje;
    private final String detalle;
    private final int codigo;
    private final LocalDateTime timestamp;

    public ErrorDTO(String mensaje, String detalle, int codigo) {
        this.mensaje = mensaje;
        this.detalle = detalle;
        this.codigo = codigo;
        this.timestamp = LocalDateTime.now();
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getDetalle() {
        return detalle;
    }

    public int getCodigo() {
        return codigo;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
