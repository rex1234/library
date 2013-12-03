package cz.muni.fi.pa165.rest;
 
import cz.muni.fi.pa165.library.dtos.ImpressionTO;
import cz.muni.fi.pa165.library.services.ImpressionService;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
 
@Path("/impressions")
public class ImpressionsResource {
 
    @Context
    private UriInfo context;
    
    @Autowired
    ImpressionService imService;
 
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getPlain() {
        StringBuilder returnString = new StringBuilder(); 
        for (ImpressionTO impression: imService.findAllImpressions()) {
            returnString.append(impression);
            returnString.append(" ");
        } 
        return returnString.toString();
    }
 
    @Path("{id}")
    public ImpressionTO getImpressionById(@PathParam("id") Long id) {
        return imService.findImpressionById(id);
    }
 
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCount() {
        return String.valueOf(imService.findAllImpressions().size());
    }
}