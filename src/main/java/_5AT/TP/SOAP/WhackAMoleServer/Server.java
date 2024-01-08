package _5AT.TP.SOAP.WhackAMoleServer;

import jakarta.xml.ws.Endpoint;

public class Server {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:12345/WhackAMole", new WebServiceImpl());
        System.out.println("Server started");

    }
}
