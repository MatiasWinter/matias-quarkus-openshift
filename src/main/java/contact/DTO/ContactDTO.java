package contact.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactDTO {
    @NotBlank(message = "El nombre no puede estar vacio")
    private String name;
    @Email(message = "El formato de email no es valido")
    private String email;
    @NotNull(message = "El campo dataProtection es obligatorio")
    private boolean dataProtection;
}