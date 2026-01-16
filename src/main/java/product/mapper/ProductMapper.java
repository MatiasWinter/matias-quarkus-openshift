package product.mapper;

import product.entity.ProductEntity;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.JAKARTA, // Usa la constante para mayor seguridad
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface ProductMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(ProductEntity dto, @MappingTarget ProductEntity entity);
}