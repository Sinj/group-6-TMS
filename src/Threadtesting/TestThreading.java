/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testthreading;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

/**
 *
 * @author Sinjun Strydom
 */
public class TestThreading {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        System.out.println("before try/catch in main");
        try {
            //establishing connection
            System.out.println("In try in main");
            Socket test;
            test = new Socket("192.168.0.213", 5000);
            PrimeThread serverin = new PrimeThread("HELO", test);
            SecondaryThread serverout = new SecondaryThread (test);
            
            serverin.start();
            serverout.start();
            //testing it
            //catch exceptions below
            serverin.stop();
            serverout.stop();
            test.close();
            
        } catch (IOException e) {
            System.out.println("in catch");
            System.err.println(e.getMessage());
        }
    }
}

class PrimeThread extends Thread {

    // Put string in the server
    PrimeThread(String in, Socket socket) {

        System.out.println("before try/catch in thread");
        try {
            //
            System.out.println("In try in thread");
            PrintWriter toServer = new PrintWriter(socket.getOutputStream(), true);
            toServer.println(in);

            
        } catch (IOException e) {
            System.out.println("in catch");
            System.err.println(e.getMessage());
        }
    }
    public void SendStringServer(String Text){
    
    
    }
    

        // create method which will kill the socket connection
    // thread should keep waiting for input until above method is called
}

class SecondaryThread extends Thread {

    String outString;

    // Get string from the server

    SecondaryThread(Socket socket) {

        System.out.println("before try/catch in thread");
        try {

            System.out.println("In try in thread-2");
            BufferedReader inServer = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));

            System.out.println(inServer);

        } catch (IOException e) {
            System.out.println("in catch");
            System.err.println(e.getMessage());
        }
    }
    
}
