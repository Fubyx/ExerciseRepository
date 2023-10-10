package _5AT.TP.Damian.TCP_UDP_Exercise;

import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Dieses Programm wird die schwierige Aufgabe, bei der TCP als auch UDP verwendet werdnen.
 */

public class Client {

    // A utility method to convert the byte array
    // data into a string representation.
    public static StringBuilder data(byte[] a)
    {
        if (a == null)
            return null;
        StringBuilder ret = new StringBuilder();
        int i = 0;
        while (a[i] != 0)
        {
            ret.append((char) a[i]);
            i++;
        }
        return ret;
    }

    //Writer und Scanner
    public static Scanner in;
    public static PrintWriter out;

    //UDPPort
    public static int UDPPort;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket( "10.171.154.42", 20000 );


        in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream(), true);

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        ExecutorService executorService = Executors.newCachedThreadPool();

        //Mainserver informieren, dass es sich um einen Client handelt
        out.println("Client");
        out.flush();

        //nach einem Port abfragen, der für UDP gebracuht wird
        System.out.println("Gib einen Port an, ueber den du erreichbar sein moechtest:\n");
        UDPPort = Integer.parseInt(bf.readLine());
        out.println(UDPPort);
        executorService.submit(reader);

        //Befehle vom Server auslesen
        System.out.println("Folgende Befehle sind bereit:");

        int commandAmmount = Integer.parseInt(in.nextLine());
        System.out.println(commandAmmount);
        for (int i = 0; i < commandAmmount; i++) {
            System.out.println(in.nextLine());
        }


        String input = "null";
        String msgFromServer = "";
        //Scanner scanner = new Scanner(System.in);

        while (!(input.equals("/quit"))) {
            //System.out.println("Test");
            input = bf.readLine();
            String[] cache = input.split(" ");
            //System.out.println("Test1");

            if("/msg".equals(cache[0])){
                out.println(input);
                openUDPChat(input);
            }else if (!("/quit".equals(cache[0]))) {
                //alle restlichen Befehle
                //den Befehle, der eingegeben wurde an Server schicken, damit dieser den Befehl bearbeitet
                out.println(input);
                int amountLines = Integer.parseInt(in.nextLine());
                for (int i = 0; i < amountLines; i++) {
                    System.out.println(in.nextLine());
                }
                //immer vom Server die anzahl an Zeilen schicken und dann so lange vom Client auslesen

            }
        }
        in.close();
        out.close();
        socket.close();
    }

    //ist hier um alles einzulesen und auszugeben
    public static Runnable reader = new Runnable() {
        @Override
        public void run() {
            // Step 1 : Create a socket to listen at port 1234
            DatagramSocket ds;
            try {
                ds = new DatagramSocket(UDPPort);
            } catch (SocketException e) {
                throw new RuntimeException(e);
            }
            byte[] receive = new byte[65535];

            DatagramPacket DpReceive = null;
            do {
                Arrays.fill(receive, (byte) 0);

                // Step 2 : create a DatgramPacket to receive the data.
                DpReceive = new DatagramPacket(receive, 65535);

                // Step 3 : revieve the data in byte buffer.
                try {
                    ds.receive(DpReceive);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(data(receive));
            } while (true);

        }
    };

    /**
     * Used to open a UDPChat to a IPAdress
     * @param command
     * @throws UnknownHostException
     */
    private static void openUDPChat(String command) throws UnknownHostException {
        String[] cache = command.split(" ");
        //dem Server mitteilen, dass man schicken moechte, der gibt dann den Port zurueck
        int port = Integer.parseInt(in.nextLine());
        System.out.println(port);
        DatagramSocket ds = null;
        try {
            ds = new DatagramSocket();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
        InetAddress ip = null;
        try {
            ip = InetAddress.getByName(cache[1]);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        byte buf[] = null;

        Scanner scanner = new Scanner(System.in);
        StringBuilder inputBuf;
        String stringCache;
        do {
            inputBuf = new StringBuilder("");

            //immer die eigene Adresse und den Port mitschicken
            inputBuf.append(InetAddress.getLocalHost().toString()).append(" ").append(UDPPort).append(": ");
            System.out.print(">>");
            stringCache = scanner.nextLine();
            inputBuf.append(cache);
            if (!(inputBuf.toString().equals("/exit"))) {
                buf = inputBuf.toString().getBytes();

                DatagramPacket DpSend = new DatagramPacket(buf, buf.length, ip, port);
                try {
                    ds.send(DpSend);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } while (!(stringCache.toString().equals("/exit")));
    }
}

/*
            //Anweisung einlesen.
            System.out.println(
                    "1: Primzahl pruefen \n" +
                            "2: Fakultät berechnen\n" +
                            "0: Trenne die Verbindung\n");
            input = scanner.nextLine();


            System.out.println("client switch");
            switch (input){
                case "0": break;
                case "1":
                    //Anweisung schicken
                    out.println(1);
                    out.flush();

                    //Zu Ueberpruefende Zahl einlesen
                    System.out.println("Welche Zahl moechten sie ueberpruefen?");
                    long cache = scanner.nextLong();
                    out.println(cache);
                    out.flush();

                    //Ergebnis auslesen
                    int result = Integer.parseInt(in.nextLine());
                    if (result == 0){
                        System.out.println("Die Zahl " + cache + " ist keine Primzahl.");
                    }else {
                        System.out.println("Die Zahl " + cache + " ist eine Primzahl.");
                    }
                break;
                case "2":
                    //Anweisung schicken
                    out.println(2);

                    //Zu Ueberpruefende Zahl einlesen
                    System.out.println("Von welcher Zahl moechten Sie die Fakultaet berechnen?");
                    cache = Long.parseLong(scanner.nextLine());
                    out.println(cache);
                    out.flush();

                    //Ergebnis auslesen
                    System.out.println("Die Fakultaet von "+cache+" ist: "+in.nextLine());
                    break;

            }
 */