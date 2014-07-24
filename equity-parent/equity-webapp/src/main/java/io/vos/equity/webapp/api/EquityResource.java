package io.vos.equity.webapp.api;

import com.google.inject.servlet.RequestScoped;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/equity")
@RequestScoped
public class EquityResource {

    @GET
    @Produces("text/plain")
    public String get() {
        return "I'm an equity";
    }
}
