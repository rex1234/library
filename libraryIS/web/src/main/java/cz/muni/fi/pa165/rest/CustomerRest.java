package cz.muni.fi.pa165.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cz.muni.fi.pa165.library.dtos.CustomerTO;
import cz.muni.fi.pa165.library.dtos.ImpressionTO;
import cz.muni.fi.pa165.library.services.CustomerService;
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
import org.joda.time.LocalDate;

/**
 *
 * @author Michal
 */
@Path("rest")
public class CustomerRest {

    @Context
    private UriInfo context;
    @Autowired
    private CustomerService service;

    public CustomerRest() {
    }

    @GET
    @Path("customers/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public CustomerTO getById(@PathParam("id") Long id) {
        CustomerTO i = service.findCustomerById(id);
        return i;
    }

    @GET
    @Path("customers")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAll() {
        List<CustomerTO> i = service.findAllCustomers();
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JodaTimeSerializer()).create();
        return gson.toJson(i);
    }

    @POST
    @Path("customers")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response post(CustomerTO c) {
        c.setId(null);
        service.createCustomer(c);
        return Response.created(URI.create(context.getAbsolutePath() + "/" + c.getId())).build();
    }

    @PUT
    @Path("customers")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putJson(CustomerTO c) {
        URI uri = context.getAbsolutePath();
        service.updateCustomer(c);
        Response response = Response.created(uri).build();
        return response;
    }

    @DELETE
    @Path("customers/{id}")
    public void delete(@PathParam("id") Long id) {
        CustomerTO i = service.findCustomerById(id);
        service.deleteCustomer(i);
    }
}
