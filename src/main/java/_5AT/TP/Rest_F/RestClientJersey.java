package _5AT.TP.Rest_F;

import jakarta.ws.rs.client.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


public class RestClientJersey {
    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target("http://localhost:9998/movieevent");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.TEXT_PLAIN);
        Response response = invocationBuilder.post(Entity.entity("Test", MediaType.APPLICATION_JSON));
        System.out.println(response.getStatus());
        System.out.println(response.readEntity(String.class));
    }
}