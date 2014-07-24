package io.vos.equity.webapp.api;

import com.google.gson.Gson;
import com.google.inject.servlet.RequestScoped;

import io.vos.equity.model.Equity;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/equity")
@RequestScoped
public class EquityResource {

  private final Gson gson;

  @Inject
  EquityResource(Gson gson) {
    this.gson = gson;
  }

  @GET
  @Produces("application/json")
  public String get() {
    return "I'm all the equities";
  }

  @GET
  @Path("{id: [0-9]+}")
  @Produces("application/json")
  public String get(@PathParam("id") String id) {
    Equity e = new Equity(Integer.parseInt(id), "a name", "a value");
    return gson.toJson(e);
  }
}

