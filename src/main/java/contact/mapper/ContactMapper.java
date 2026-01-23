package contact.mapper;

import contact.entity.ContactEntity;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.JAKARTA, // Usa la constante para mayor seguridad
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface ContactMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(ContactEntity dto, @MappingTarget ContactEntity entity);
}