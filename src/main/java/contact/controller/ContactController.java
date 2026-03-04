package contact.controller;

import contact.DTO.ContactDTO;
import contact.service.ContactService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/api/contact")
public class ContactController {

    @Inject
    ContactService contactService;

    @POST
    public Response createContact(@Valid ContactDTO contact) {
        return contactService.createContact(contact);
    }

    @GET
    public Response getAllContacts(){
        return contactService.getAllContacts();
    }

    @GET
    @Path("/{id}")
    public Response getContactById(@PathParam("id") Long id) {
        return contactService.getContactById(id);
    }

    @PATCH
    @Path("/{id}")
    public Response updateContact(@PathParam("id") Long id, ContactDTO contactDTO) {
        return contactService.updateContact(id,contactDTO);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteContact(@PathParam("id") Long id) {
        return contactService.deleteContact(id);
    }

    @GET
    @Path("/search")
    public Response searchContactByEmail(@QueryParam("email")
    @NotEmpty(message = "El email no puede estar vacío")
    @Email(message = "Debe proporcionar un formato de email válido")
                                             String email) {
        return contactService.searchContactByEmail(email);
    }
}