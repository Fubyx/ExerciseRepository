package _5AT.TP.SOAP.WhackAMoleServer;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

@WebService(name = "WhackAMole", targetNamespace = "http://localhost:12345/WhackAMole") // defines "porttype" element in the WSDL file
@SOAPBinding(style = SOAPBinding.Style.RPC) // remote procedure call (call a method that gets executed on remote)
public interface WebServiceInterface {
    @WebMethod(operationName = "getScores")
    @WebResult(name = "result")
    public String getScores();
    @WebMethod(operationName = "addScore")
    @WebResult(name = "result")
    public String addScore(@WebParam(name = "str") String str);
}

