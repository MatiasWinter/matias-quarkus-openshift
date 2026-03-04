package contact.service;

import contact.DTO.ContactDTO;
import contact.entity.ContactEntity;
import contact.mapper.ContactMapper;
import contact.repository.ContactRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@ApplicationScoped
public class ContactServiceImpl implements ContactService{

    private static final Logger log = LoggerFactory.getLogger(ContactServiceImpl.class);

    @Inject
    ContactRepository contactRepository;

    @Inject
    ContactMapper contactMapper;

    @Override
    @Transactional
    public Response createContact(ContactDTO contact) {
            log.info("Procedemos a crear el contacto {}",contact.getName());
            contactRepository.persist(contactMapper.toEntity(contact));
            log.info("El contacto {} fue creado con exito", contact.getName());

            return Response.status(Response.Status.CREATED).entity(contact).build();
    }

    @Override
    public Response getAllContacts() {
        log.info("Procedemos a obtener a todos los contactos");
        List<ContactDTO> contactDTOList = contactMapper.toDTOList(contactRepository.findAll().list());
        log.info("Lista de contactos obtenida correctamente");
        if (contactDTOList.isEmpty()){
            throw new RuntimeException("Aún no existe contactos en la base de datos");
        }
        return Response.status(Response.Status.OK).entity(contactDTOList).build();
    }

    @Override
    public Response getContactById(Long id) {
        log.info("Procedemos a buscar el contacto con ID = {}",id);
        ContactEntity contact = contactRepository.findById(id);
        validarPresencia(id, contact);
        log.info("Contacto con ID = {} encontrado con exito", id);
        return Response.ok(contactMapper.toDTO(contact)).build();
    }

    @Override
    @Transactional
    public Response updateContact(Long id, ContactDTO contactDTO) {
        log.info("Procedemos a actualizar el contacto con ID = {}",id);
        ContactEntity newContactEntity = contactRepository.findById(id);
        validarPresencia(id,newContactEntity);
        contactMapper.updateEntityFromDto(contactMapper.toEntity(contactDTO), newContactEntity);
        contactRepository.persist(newContactEntity);
        log.info("Actualizacion del contacto con ID = {} exitosa",id);
        return Response.status(Response.Status.ACCEPTED).entity(contactMapper.toDTO(newContactEntity)).build();
    }

    @Override
    @Transactional
    public Response deleteContact(Long id) {
        log.info("Procedemos a borrar el contacto con ID = {}",id);
        ContactEntity contact = contactRepository.findById(id);
        validarPresencia(id,contact);
        contactRepository.delete(contact);
        log.info("Borrado del contacto con ID = {} exitoso",id);
        return Response.ok().build();
    }

    @Override
    public Response searchContactByEmail(String email) {
        log.info("Procedemos a buscar el contacto con email = {}",email);
        ContactEntity contact = contactRepository.find("email",email).firstResult();
        if (contact == null) {
            log.info("No se encontro un cliente con el email = {} ",email);
            throw new RuntimeException("Contacto no encontrado en la base de datos");
        }

        log.info("Cliente con email = {} encontrado con exito", email);
        return Response.ok(contactMapper.toDTO(contact)).build();
    }

    private static void validarPresencia(Long id, ContactEntity contact) {
        if (contact == null) {
            log.info("No se encontro un contacto con el ID = {} ", id);
            throw new RuntimeException("Contacto no encontrado en la base de datos");
        }
    }
}
