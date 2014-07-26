package io.vos.equity.webapp.api;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import com.google.inject.Singleton;

import io.vos.equity.model.Equity;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/equity")
@Produces(APPLICATION_JSON)
@Singleton
public class EquityResource {

  @GET
  public String get() {
    return "I'm all the equities";
  }

  @GET
  @Path("{id: [0-9]+}")
  public Equity get(@PathParam("id") String id) {
    return new Equity(Integer.parseInt(id), "a name", "a value");
  }
}
