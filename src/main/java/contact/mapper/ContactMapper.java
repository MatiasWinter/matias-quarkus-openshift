package contact.mapper;

import contact.DTO.ContactDTO;
import contact.entity.ContactEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.JAKARTA, // Usa la constante para mayor seguridad
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface ContactMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(ContactEntity dto, @MappingTarget ContactEntity entity);

    ContactDTO toDTO(ContactEntity entity);

    ContactEntity toEntity(ContactDTO contact);

    List<ContactDTO> toDTOList(List<ContactEntity> entities);
}