package _5AT.TP.Executor.Client_Server;

import _5AT.TP.Executor.PrimeChecker.PrimeCheckerThreadController;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

public class RequestHandler implements Runnable{
    private final Socket connection;

    public RequestHandler (Socket connection){
        this.connection = connection;
    }

    @Override
    public void run() {

        ObjectInputStream in;
        ObjectOutputStream out;
        try {
            out = new ObjectOutputStream(connection.getOutputStream());
            out.flush();
            in = new ObjectInputStream(connection.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while(!connection.isClosed()) {
            String request;
            String args = "";
            try {
                System.out.println("Waiting for request");
                String requestString = (String)(in.readObject());
                String[] text = requestString.split(" ", 2);
                request = text[0];
                try {
                    args = text[1];
                }catch (ArrayIndexOutOfBoundsException ignored){}
            } catch (IOException | ClassNotFoundException e) {

                throw new RuntimeException(e);
            }
            System.out.println(request);
            switch (request) {
                case "close" -> {
                    Server.active = false;
                    try {
                        connection.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "help" -> {
                    try {
                        out.writeObject(
                                "isPrime [number(s)]: Returns a boolean array for each number stating whether it is a prime number or not.\n"
                        );
                        out.flush();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "isPrime" -> {
                    String[] numberStrings = args.split(" ");
                    long[] numbers = new long[numberStrings.length];
                    for (int i = 0; i < numberStrings.length; ++i) {
                        numbers[i] = Long.parseLong(numberStrings[i]);
                    }
                    PrimeCheckerThreadController controller = new PrimeCheckerThreadController(numbers);
                    try {
                        boolean[] result = controller.checkNumbers();
                        try {
                            out.writeObject(Arrays.toString(result));
                            //System.out.println(Arrays.toString(result));
                            out.flush();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } catch (ExecutionException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        }
        try {
            out.close();
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
