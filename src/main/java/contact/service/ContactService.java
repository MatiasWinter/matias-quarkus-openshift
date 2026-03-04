package contact.service;

import contact.DTO.ContactDTO;
import jakarta.ws.rs.core.Response;

public interface ContactService {
    Response createContact(ContactDTO contact);

    Response getAllContacts();

    Response getContactById(Long id);

    Response updateContact(Long id, ContactDTO contactDTO);

    Response deleteContact(Long id);

    Response searchContactByEmail(String email);
}
