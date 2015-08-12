package com.diogosantos.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;

@Path("/my")
@Produces(MediaType.APPLICATION_JSON)
public class Resource {

    @GET
    @Path("/names")
    public List<String> getNames() {
        return Arrays.asList("Diogo", "Santos");
    }

}
