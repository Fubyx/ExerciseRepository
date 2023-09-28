package _5AT.TP.Executor.Client_Server;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        Socket socket;
        try {
            socket = new Socket("127.0.0.1", 50000);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ObjectOutputStream out;
        ObjectInputStream in;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(socket.getInputStream());
            System.out.println(in.readObject());
            String temp = br.readLine();
            out.writeObject(temp);
            out.flush();
            temp = (String) in.readObject();
            System.out.println(temp);
            if (!temp.equals("Password:")){
                out.close();
                in.close();
                System.exit(0);
            }
            temp = br.readLine();
            out.writeObject(temp);
            out.flush();

            System.out.println(in.readObject());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (true){
            try {

                System.out.println(in.readObject());
                String request = br.readLine();

                if (request.equals("end"))break;

                out.writeObject(request);
                out.flush();
                String response = "";
                while (!response.equals("EOF") && !response.equals("You do not have the privileges for this action.")){
                    response = (String) in.readObject();
                    if(response.equals("EOF") || response.equals("You do not have the privileges for this action."))break;
                    System.out.println(response);
                    request = br.readLine();
                    out.writeObject(request);
                    out.flush();
                }
                System.out.println("--------------");
            } catch (IOException | ClassNotFoundException ignored) {
                break;
            }
        }
        try {
            br.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
