package contact.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
public class ContactEntity {

    @Id
    @GeneratedValue
    public Long id;
    public String name;
    public String email;
    public boolean dataProtection;

}