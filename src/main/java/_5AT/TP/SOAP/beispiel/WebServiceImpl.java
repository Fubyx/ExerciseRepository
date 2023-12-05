package _5AT.TP.SOAP.beispiel;


import jakarta.jws.WebService;

@WebService(targetNamespace = "http://localhost:12345/HelloWorld", endpointInterface = "_5AT.TP.SOAP.beispiel.WebServiceInterface", name="HelloWord", serviceName="HelloWorldService")
// attributes of the "definitions" element (the element that contains everything in the WSDL file)
public class WebServiceImpl implements WebServiceInterface {
    @Override
    public String getHelloWorldAsString(String str) {
        return "Hello World of JAX-WS " + str;
    }

}
