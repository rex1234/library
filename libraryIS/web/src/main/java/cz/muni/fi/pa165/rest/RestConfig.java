package cz.muni.fi.pa165.rest;

import cz.muni.fi.pa165.library.dtos.ImpressionTO;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

public class RestConfig extends ResourceConfig {

    public RestConfig() {
        register(RequestContextFilter.class);
        register(ImpressionTO.class);
        register(ImpressionRest.class);
    }
}
