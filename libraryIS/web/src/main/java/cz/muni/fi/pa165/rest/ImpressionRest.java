/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.rest;

import cz.muni.fi.pa165.library.dtos.ImpressionTO;
import cz.muni.fi.pa165.library.services.ImpressionService;
import java.net.URI;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import org.springframework.beans.factory.annotation.Autowired;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * Jersey2 Spring integration example. Demonstrate how to inject a Spring bean
 * into a Jersey managed JAX-RS resource class.
 *
 * @author Marko Asplund (marko.asplund at gmail.com)
 */
@Path("rest")
public class ImpressionRest {

    @Context
    private UriInfo context;
    @Autowired
    private ImpressionService service;

    public ImpressionRest() {
    }

    @GET
    @Path("impressions/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public ImpressionTO getById(@PathParam("id") Long id) {
        ImpressionTO i = service.findImpressionById(id);
        return i;
    }

    @GET
    @Path("impressions")
    @Produces({MediaType.APPLICATION_JSON})
    public List<ImpressionTO> getAll() {
        List<ImpressionTO> i = service.findAllImpressions();
        return i;
    }

    @POST
    @Path("impressions")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response post(ImpressionTO impression) {
        impression.setId(null);
        service.createImpression(impression);
        return Response.created(URI.create(context.getAbsolutePath() + "/" + impression.getId())).build();
    }

    @PUT
    @Path("impressions")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putJson(ImpressionTO impression) {
        URI uri = context.getAbsolutePath();      
        service.updateImpression(impression);
        Response response = Response.created(uri).build();
        return response;
    }

    @DELETE
    @Path("impressions/{id}")
    public void delete(@PathParam("id") Long id) {        
        ImpressionTO i = service.findImpressionById(id);
        service.deleteImpression(i);
    }
}
