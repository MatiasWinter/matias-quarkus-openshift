package product.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Entity
public class ProductEntity extends PanacheEntity {
    public String name;
    public String detail;
    public BigDecimal price;
}