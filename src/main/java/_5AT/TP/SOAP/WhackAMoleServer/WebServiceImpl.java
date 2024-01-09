package _5AT.TP.SOAP.WhackAMoleServer;


import jakarta.jws.WebService;

import java.util.ArrayList;

@WebService(targetNamespace = "http://localhost:12345/WhackAMole", endpointInterface = "_5AT.TP.SOAP.WhackAMoleServer.WebServiceInterface", name = "WhackAMole", serviceName = "WhackAMoleService")
// attributes of the "definitions" element (the element that contains everything in the WSDL file)
public class WebServiceImpl implements WebServiceInterface {
    ArrayList<String> scores = new ArrayList<>();

    @Override
    public String getScores() {
        if (scores.size() == 0) {
            return "";
        }
        StringBuilder s = new StringBuilder("");
        for (String p : scores) {
            s.append(p);
            s.append(";");
        }
        s.deleteCharAt(s.length() - 1);
        return s.toString();
    }

    @Override
    public String addScore(String str) {
        scores.add(str);
        return "worked";
    }
}
