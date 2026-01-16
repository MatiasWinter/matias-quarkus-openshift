package contact.controller;

import contact.entity.ContactEntity;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/contact")
public class ContactController {

    @Inject
    EntityManager entityManager;

    @POST
    @Transactional
    public Response createContact(ContactEntity contact) {
        entityManager.persist(contact);
        return Response.status(Response.Status.CREATED).entity(contact).build();
    }

    @GET
    public List<ContactEntity> getAllContacts(){
        List<ContactEntity> fromContact = entityManager.createQuery("from Contact", ContactEntity.class).getResultList();
        return fromContact;
    }

    @GET
    @Path("/{id}")
    public Response getContactById(@PathParam("id") Long id) {
        ContactEntity contact = entityManager.find(ContactEntity.class, id);
        if (contact == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(contact).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateContact(@PathParam("id") Long id, ContactEntity updatedContact) {
        ContactEntity contact = entityManager.find(ContactEntity.class, id);
        if (contact == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        contact.setName(updatedContact.getName());
        contact.setEmail(updatedContact.getEmail());
        entityManager.merge(contact);
        return Response.ok(contact).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteContact(@PathParam("id") Long id) {
        ContactEntity contact = entityManager.find(ContactEntity.class, id);
        if (contact == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        entityManager.remove(contact);
        return Response.noContent().build();
    }

    @PUT
    @Path("/email/{email}")
    @Transactional
    public Response updateContactByEmail(@PathParam("email") String email, ContactEntity updatedContact) {
        ContactEntity contact = entityManager.createQuery("SELECT c FROM Contact c WHERE c.email = :email", ContactEntity.class)
                .setParameter("email", email)
                .getSingleResult();
        if (contact == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        contact.setName(updatedContact.getName());
        contact.setEmail(updatedContact.getEmail());
        entityManager.merge(contact);
        return Response.ok(contact).build();
    }

    @GET
    @Path("/email/{email}")
    public Response getContactByEmail(@PathParam("email") String email) {
        ContactEntity contact = entityManager.createQuery("SELECT c FROM Contact c WHERE c.email = :email", ContactEntity.class)
                .setParameter("email", email)
                .getSingleResult();
        if (contact == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(contact).build();
    }

    @GET
    @Path("/search")
    public Response searchContacts(@QueryParam("email") String email) {
        List<ContactEntity> contacts = entityManager.createQuery("SELECT c FROM Contact c WHERE c.email LIKE :email", ContactEntity.class)
                .setParameter("email", "%" + email + "%")
                .getResultList();
        return Response.ok(contacts).build();
    }
}