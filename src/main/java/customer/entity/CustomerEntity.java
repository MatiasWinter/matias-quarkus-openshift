package customer.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class CustomerEntity extends PanacheEntity {
    public String nombre;
    public String apellido;
    public Integer edad;
    public String email;
}