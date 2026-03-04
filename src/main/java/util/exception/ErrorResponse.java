package util.exception;


import lombok.*;
import util.response.DefaultResponseDTO;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponse extends DefaultResponseDTO {
    public String fecha;

    public ErrorResponse(String mensaje, String codigo) {
        super(mensaje, codigo);
        this.fecha = java.time.LocalDateTime.now().toString();
    }
}