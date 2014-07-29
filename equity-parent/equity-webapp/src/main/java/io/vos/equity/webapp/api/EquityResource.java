package io.vos.equity.webapp.api;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import io.vos.equity.controller.EquityController;
import io.vos.equity.model.Equity;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/equity")
@Produces(APPLICATION_JSON)
@Singleton
public class EquityResource implements ApiResource {

  @Inject
  private Provider<EquityController> controllerProvider;

  @GET
  @RolesAllowed("user")
  public String get() {
    return "I'm all the equities";
  }

  @GET
  @Path("{id: [0-9]+}")
  public Equity get(@PathParam("id") String id) {
    return controllerProvider.get().find(Integer.parseInt(id));
  }

  @GET
  @Path("/error")
  public Response error() {
    return Response.status(404)
        .entity(new com.sun.jersey.api.NotFoundException("the error path"))
        .build();
  }

  @POST
  public Equity post() {
    Equity e = new Equity("erickj", "awesome");
    controllerProvider.get().save(e);
    return e;
  }
}
