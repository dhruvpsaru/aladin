package ginie.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by dhruvr on 25/7/16.
 */
@Path("/")
@Singleton
public class TestAPI {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestAPI.class);

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hi(){
        return "\"_\"";
    }


    @POST
    @Path("/echo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String echo(String json){
        LOGGER.info("Json {} ", json);
        return json;    
    }

    @Path("/insert")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertData(String json){

    }

}
