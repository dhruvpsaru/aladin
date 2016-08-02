package pal.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pal.auth.AuthDetails;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by dhruvr on 31/7/16.
 */
@Path("/")
public class Token {

    private static Logger LOGGER = LoggerFactory.getLogger(Token.class);


    @POST
    @Path("/token")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response token(AuthDetails authDetails){

    }

}
