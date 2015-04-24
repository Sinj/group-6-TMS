/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TMS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.lang.Thread;

/**
 * 
 *
 * @author Sinjun Strydom
 */
public class TMS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

//        StringBuffer instr = new StringBuffer();
//        String TimeStamp;
        //System.out.printIn("initialised");
        System.out.println("before try/catch");
        try {
            PrimeRun p = new PrimeRun("TEST");
            new Thread(p).start();
            
            System.out.println("in try");
            Socket test;
            test = new Socket("192.168.0.213", 5000);
            //print out prints to the server
            PrintWriter out
                    = new PrintWriter(test.getOutputStream(), true);
            BufferedReader in, stdIn;
            in = new BufferedReader(new InputStreamReader(
                    test.getInputStream()));
            
            stdIn = new BufferedReader(new InputStreamReader(System.in));

            out.println("HELO");
            /*
            need wait, till muti thread
            */
            String t = in.readLine();
            System.out.println(t);
            /*
             * 
             * do magic here
             *
             */
            
            test.close();

        } catch (IOException e) {
            System.out.println("in catch");
            System.err.println(e.getMessage());
        }

//        BufferedInputStream bis = new BufferedInputStream(test.getInputStream());
//
//        InputStreamReader isr
//                = new InputStreamReader(bis, "US-ASCII");
//
//        int c;
//        while ((c = isr.read()) != 13) {
//            instr.append((char) c);
//        }
//
//        test.close();
//        System.out.printIn(instr);
    }
}
