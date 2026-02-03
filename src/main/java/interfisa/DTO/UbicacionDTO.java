package interfisa.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UbicacionDTO {
    private String titulo;
    private String nombre;
    private String descripcion;
    private String direccion;
    private String ciudad;
    private String departamento;
    private String barrio;
    private Double latitud;
    private Double longitud;
    private String tipo;
}