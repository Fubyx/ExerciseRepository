package _5AT.TP.Rest_F;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class RestClientJava {
    public static void main(String[] args) throws IOException {

        String url = "http://0.0.0.0:9998/movieevent";
        HttpURLConnection httpClient =
                (HttpURLConnection) new URL(url).openConnection();
        httpClient.setRequestMethod("POST");
        httpClient.setRequestProperty("Content-Type", "application/json");
        //httpClient.setRequestProperty("Accept", "application/json");
        httpClient.setDoOutput(true);
        String jsonInputString = "Test";
        System.out.println(httpClient.getRequestMethod());
        try(OutputStream os = httpClient.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
        int responseCode = httpClient.getResponseCode();
        System.out.println("Sending GET request to URL " + url);
        System.out.println("Response-Code: " + responseCode);
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(httpClient.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            System.out.println(response);
        }
    }
}