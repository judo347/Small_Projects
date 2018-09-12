package dk.old;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    private static final int PORT_NUMBER = 4444;

    public static void main(String[] args) {

        String hostName = "mikkel";
        int portNumber = PORT_NUMBER;

        try (
                Socket thisSocket = new Socket(hostName, portNumber);
                PrintWriter out = new PrintWriter(thisSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(thisSocket.getInputStream()));
        ) {
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String fromServer;
            String fromUser;

            while ((fromServer = in.readLine()) != null) {
                System.out.println("dk.old.Server: " + fromServer);

                if (fromServer.equals("Bye.")) //dk.old.Client closes if this is met
                    break;

                fromUser = stdIn.readLine();
                if (fromUser != null) {
                    System.out.println("dk.old.Client: " + fromUser);
                    out.println(fromUser);
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    hostName);
            System.exit(1);
        }

    }
}
