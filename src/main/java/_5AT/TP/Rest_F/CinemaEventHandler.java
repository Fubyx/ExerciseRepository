package _5AT.TP.Rest_F;

import jakarta.ws.rs.*;

@Path("movieevent")
public class CinemaEventHandler {

    @POST
    @Consumes("application/json")
    @Produces("text/plain")
    public String postMovieEvent(String event) {

        System.out.println("received event: " + event);
        return "event " + event + " received";

    }
    @GET
    @Produces("text/plain")
    public String getMovieEvent() {

        return "nothing to report from getMovieEvent";

    }

}