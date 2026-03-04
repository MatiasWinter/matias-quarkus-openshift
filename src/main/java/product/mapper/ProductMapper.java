package product.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.MappingConstants;
import product.DTO.ProductDTO;
import product.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.JAKARTA, // Usa la constante para mayor seguridad
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface ProductMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(ProductEntity dto, @MappingTarget ProductEntity entity);

    ProductDTO toDTO(ProductEntity entity);

    ProductEntity toEntity(ProductDTO product);

    List<ProductDTO> toDTOList(List<ProductEntity> entities);
}