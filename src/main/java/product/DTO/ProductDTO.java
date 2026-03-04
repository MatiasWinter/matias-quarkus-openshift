package product.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    @NotBlank(message = "El nombre no puede estar vacio")
    private String name;
    @NotBlank(message = "El detalle no puede estar vacio")
    private String detail;
    @Min(value = 0, message = "El precio debe ser mayor o igual a 0")
    private BigDecimal price;
}