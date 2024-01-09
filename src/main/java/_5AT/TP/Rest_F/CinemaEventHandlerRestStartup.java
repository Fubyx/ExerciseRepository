package _5AT.TP.Rest_F;

import com.sun.net.httpserver.HttpServer;
import jakarta.ws.rs.core.UriBuilder;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

public class CinemaEventHandlerRestStartup {
    private final static int port = 9998;
    private final static String host="http://0.0.0.0/";

    public static void main(String[] args) {
        URI baseUri = UriBuilder.fromUri(host).port(port).build();
        ResourceConfig config = ResourceConfig.forApplication(new MyApplication());

        HttpServer server = JdkHttpServerFactory.createHttpServer(baseUri, config);
    }

}