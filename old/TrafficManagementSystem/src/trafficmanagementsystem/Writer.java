/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficmanagementsystem;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Holly-Marie
 */
public class Writer implements Runnable{
    
    Socket newSocket = null;
    PrintWriter os = null;
    
    Writer (Socket s)
    {
        newSocket = s;
        System.out.println("In write constructor");
    }
    
    public void run()
    {
        try {
            System.out.println("trying to write");
            os = new PrintWriter(newSocket.getOutputStream(), true);
            if (newSocket != null && os != null) {
                System.out.println("Neither writer null");
                os.println("HELO");
                os.println("HELP");
                os.println("REGI");
                os.println("BEGI");
            }

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: hostname");
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: hostname - Output");
        }
    }
}
