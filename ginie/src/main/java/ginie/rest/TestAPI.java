package ginie.rest;

import com.google.inject.Inject;
import ginie.database.Repository.TestRepository;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by dhruvr on 25/7/16.
 */
@Path("/")
@Singleton
public class TestAPI {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestAPI.class);


    @Inject
    private TestRepository testRepository;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hi() {
        return "\"_\"";
    }


    @POST
    @Path("/echo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String echo(String json) {
        LOGGER.info("Json {} ", json);
        return json;
    }

    @Path("/tp")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertData(String json) {
        JSONObject jsonObject = new JSONObject(json);
        String userName = jsonObject.getString("username");
        String password = jsonObject.getString("password");

        LOGGER.info("userName  : {}, password {} ", userName, password);
        List list = testRepository.isPresent(userName, password);

        if (!list.isEmpty()) {
            return Response.ok("{\n" +
                    "\t\n" +
                    "\t\"result\" : \"ok\"\n" +
                    "}").build();
        } else {
            return Response.ok("{\n" +
                    "\t\n" +
                    "\t\"result\" : \"not_ok\"\n" +
                    "}").build();
        }
    }

}
