package contact.controller;

import contact.entity.ContactEntity;
import contact.mapper.ContactMapper;
import contact.repository.ContactRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/contact")
public class ContactController {

    @Inject
    ContactRepository contactRepository;

    @Inject
    ContactMapper contactMapper;

    @POST
    @Transactional
    public Response createContact(ContactEntity contact) {
        contactRepository.persist(contact);
        return Response.status(Response.Status.CREATED).entity(contact).build();
    }

    @GET
    public List<ContactEntity> getAllContacts(){
        return contactRepository.findAll().list();
    }

    @GET
    @Path("/{id}")
    public Response getContactById(@PathParam("id") Long id) {
        ContactEntity contact = contactRepository.findById(id);
        if (contact == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(contact).build();
    }

    @PATCH
    @Path("/{id}")
    @Transactional
    public ContactEntity updateContact(@PathParam("id") Long id, ContactEntity contactEntity) {
        ContactEntity newContactEntity = contactRepository.findById(id);
        contactMapper.updateEntityFromDto(contactEntity, newContactEntity);
        contactRepository.persist(newContactEntity);
        return newContactEntity;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteContact(@PathParam("id") Long id) {
        ContactEntity contact = contactRepository.findById(id);
        if (contact == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        contactRepository.delete(contact);
        return Response.ok().build();
    }

    @GET
    @Path("/search")
    public Response searchContactByEmail(@QueryParam("email") String email) {
        ContactEntity contact = contactRepository.find("email",email).firstResult();
        return Response.ok(contact).build();
    }
}