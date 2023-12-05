package _5AT.TP.SOAP.beispiel;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

@WebService(name = "HelloWorld", targetNamespace = "http://localhost:12345/HelloWorld") // defines "porttype" element in the WSDL file
@SOAPBinding(style = SOAPBinding.Style.RPC) // remote procedure call (call a method that gets executed on remote)
public interface WebServiceInterface {
    @WebMethod(operationName = "really") // element-name for method call (the element that contains the parameter elements) in the get-request
    @WebResult(name = "what") // changes the name of the element that carries the return value in the XML
    public String getHelloWorldAsString(@WebParam(name = "str") String str); // webparam: name of element that contains the param in xml
}

