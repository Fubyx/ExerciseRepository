package _5AT.TP.SOAP.beispiel;

import jakarta.xml.ws.Endpoint;

public class Server {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:12345/HelloWorld", new WebServiceImpl());
    }
}