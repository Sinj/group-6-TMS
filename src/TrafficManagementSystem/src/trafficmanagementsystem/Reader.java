/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficmanagementsystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Holly-Marie
 */
public class Reader implements Runnable {

    Socket newSocket = null;
    BufferedReader in = null;

    Reader(Socket s) {
        newSocket = s;
        System.out.println("In read constructor");
    }

    @Override
    public void run() {
        try {
            //is = new DataInputStream(newSocket.getInputStream());
            System.out.println("Trying to read");
            in = new BufferedReader(new InputStreamReader(newSocket.getInputStream()));
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: hostname - Input");
        }

        // to the socket we have opened a connection to on port 5000
        if (newSocket != null && in != null) {
            try {
                System.out.println("Neither reader null");
                String responseLine;
                while ((responseLine = in.readLine()) != null) {
                    System.out.println("Server: " + responseLine);
                }
                in.close();


            } catch (UnknownHostException e) {
                System.err.println("Trying to connect to unknown host: " + e);
            } catch (IOException e) {
                System.err.println("IOException:  " + e);
            }
        }
    }
}
