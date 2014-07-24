package io.vos.equity.webapp.api;

import com.google.gson.Gson;
import com.google.inject.servlet.RequestScoped;

import java.util.Date;

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

  private class Equity {
    private final int id;
    private final String name;
    private final String value;
    private final Date now;

    Equity(int id, String name, String value) {
      this.id = id;
      this.name = name;
      this.value = value;
      this.now = new Date();
    }
  }
}

