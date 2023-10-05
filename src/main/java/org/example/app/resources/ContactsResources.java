package org.example.app.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.app.entity.Contacts;
import org.example.app.repository.ContactsRepository;

import java.util.List;

@Path("/api/v.1/contacts")
@Produces({MediaType.APPLICATION_JSON})
public class ContactsResources {
    ContactsRepository repository  = new ContactsRepository();
    @GET
    public Response getContacts() {
        List<Contacts> list = repository.fetchAll();
        if (!list.isEmpty()) {
            return Response.ok(list).status(Response.Status.OK).build();
        } else {
            return Response.noContent().status(Response.Status.NO_CONTENT).build();
        }
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createContact(Contacts contacts){
        repository.create(contacts);
        return Response.ok().status(Response.Status.CREATED).build();

    }

    @GET
    @Path("{id: [0-9]+}")
    public Response getContactByID(@PathParam("id") int id) {
        if (id >= 0) {
            Contacts contacts = repository.fetchById(id);
            return Response.ok(contacts).status(Response.Status.OK).build();
        } else {
            return Response.noContent().status(Response.Status.NO_CONTENT).build();
        }
    }

    @PUT
    @Path("{id: [0-9]+}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response updateContacts(@PathParam("id") int id, Contacts contacts){
        if (repository.checkUserById(id)){
                repository.update(id, contacts);
                return Response.ok().status(Response.Status.CREATED).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("{id: [0-9]+}")
    public Response deleteContact(@PathParam("id") int id){
        if (repository.checkUserById(id)){
            repository.delete(id);
            return Response.ok().status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }






}
