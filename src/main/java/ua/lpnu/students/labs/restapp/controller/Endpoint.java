package ua.lpnu.students.labs.restapp.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.springframework.beans.factory.annotation.Autowired;
import ua.lpnu.students.labs.restapp.logic.DecorationService;
import ua.lpnu.students.labs.restapp.model.ElectricDecoration;

/**
 * {Summary} Rest controller for the app.
 */
@Path("/")
public class Endpoint {
    @Autowired
    DecorationService decorationService;

    @GET
    @Path("id/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.ok(decorationService.getAll()).build();
    }

    /**
     * Get all decorations.
     *
     * @param id in the url
     * @return Response(ok and Json-decoration or Bad_request and error text)
     */
    @GET
    @Path("id/{id: \\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") long id) {
        var decoration = decorationService.getById(id);
        if (decoration.isEmpty()) {
            return Response.status(Status.BAD_REQUEST).entity("The id isn't exist").build();
        }
        return Response.ok(decoration.get()).build();
    }

    /**
     * Create decoration. Send post method with Json of the decoration.
     *
     * @param decoration from json
     * @return response created and message or bad_request and message
     */
    @POST
    @Path("id/{var: .*}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addDecoration(ElectricDecoration decoration) {
        decorationService.addDecoration(decoration);
        return Response.status(Status.CREATED).entity("Created").build();
    }

    /**
     * Update decoration. Send put method with id and json body in request.
     *
     * @param id         in url
     * @param decoration from json from body
     * @return response with status and inform message
     */
    @PUT
    @Path("id/{id: \\d*}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateDecoration(@PathParam("id") long id, ElectricDecoration decoration) {
        var isChanged = decorationService.updateDecoration(id, decoration);
        if (isChanged) {
            return Response.status(Status.OK).entity("Updated successfully").build();
        }
        return Response.status(Status.BAD_REQUEST).entity("Not updated at all").build();
    }

    /**
     * Deletes the decoration using delete request and is in url.
     *
     * @param id from url
     * @return responce with message
     */
    @DELETE
    @Path("id/{id: \\d*}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteDecoration(@PathParam("id") long id) {
        decorationService.deleteDecoration(id);
        return Response.ok("Multiplied by zero successfully").build();
    }
}
