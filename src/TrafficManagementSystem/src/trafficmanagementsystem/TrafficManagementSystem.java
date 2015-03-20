/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficmanagementsystem;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Holly-Marie
 */
public class TrafficManagementSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Socket socket = null;

        try {

            socket = new Socket("192.168.0.80", 5000);

            Writer write = new Writer(socket);
            Reader read = new Reader(socket);


            Thread tWrite = new Thread(write);
            Thread tRead = new Thread(read);

            System.out.println("Starting write thread");
            tWrite.start();
            System.out.println("Starting read thread");
            tRead.start();


            //socket.close();

        } catch (UnknownHostException e) {
            System.out.println("Unknown Host");
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("IO Exception");
            System.err.println(e.getMessage());
        }

    }
}
