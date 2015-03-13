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
import java.util.stream.Stream;

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
        try 
        {
            //establishing connection
            System.out.println("In try in main");
            Socket test;
            test = new Socket("192.168.0.142", 5000);
            WriteThread serverin = new WriteThread(test);
            ReadThread serverout = new ReadThread(test);
            serverin.SendMessage("HELO");
            serverin.run();
            serverout.run();
            serverin.SendMessage("HELP");
            serverin.run();
            serverout.run();
            test.close();
        } 
        catch (IOException e) 
        {
            System.out.println("in catch");
            System.err.println(e.getMessage());
        }
    }
}

class WriteThread implements Runnable {

    private Socket socketG;
    private String message;

    WriteThread(Socket socket)
    {
        System.out.println("Write constructor");
        socketG = socket;
    }
    
    @Override
    public void run()
    {
        System.out.println("Write run");
            try 
            {
                System.out.println("In try");
                PrintWriter toServer;
                toServer = new PrintWriter(socketG.getOutputStream(), true);
                toServer.println(message);
            } 
            catch (IOException e) 
            {
                System.out.println("in catch");
                System.err.println(e.getMessage());
            }
    }
    
    public void SendMessage(String input)
    {
        message = input;
    }
}

class ReadThread implements Runnable {

    Socket socketG;
    String readLine;
    String output;

    // Get string from the server
    ReadThread(Socket socket) 
    {
        System.out.println("Read constructor");
        socketG = socket;
    }
    
    @Override
    public void run()
    {
        System.out.println("Read run");
        try 
        {
            System.out.println("In try");
            BufferedReader inServer = new BufferedReader(new InputStreamReader(
                    socketG.getInputStream()));
            
            readLine = inServer.readLine();
            System.out.println(readLine);
        } 
        catch (IOException e) 
        {
            System.out.println("in catch");
            System.err.println(e.getMessage());
        }
    }

}
