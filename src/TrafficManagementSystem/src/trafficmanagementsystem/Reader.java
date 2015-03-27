/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficmanagementsystem;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;

/**
 *
 * @author Holly-Marie
 */
public class Reader implements Runnable {

    Socket newSocket = null;
    BufferedReader in = null;
    List<String> lane1in = null;
    List<String> lane1out = null;
    List<String> lane2in = null;
    List<String> lane2out = null;
    List<String> lane3in = null;
    List<String> lane3out = null;
    List<String> lane4in = null;
    List<String> lane4out = null;
    List<String> ped1 = null;
    List<String> ped2 = null;
    List<String> ped3 = null;
    List<String> ped4 = null;

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

                    lane1out = new ArrayList<>();
                    lane2out = new ArrayList<>();
                    lane3out = new ArrayList<>();
                    lane4out = new ArrayList<>();
                    ped1 = new ArrayList<>();
                    ped2 = new ArrayList<>();
                    ped3 = new ArrayList<>();
                    ped4 = new ArrayList<>();

                    if (responseLine.contains(":") && responseLine.contains(";")) {
                        System.out.println("Detected cars");
                        String[] carStream = responseLine.split(";");
                        for (String carStream1 : carStream) {
                            System.out.println(carStream1);
                            switch (carStream1.charAt(0)) {
                                case 1:
                                    lane1out.add(carStream1);
                                    break;
                                case 2:
                                    lane2out.add(carStream1);
                                    break;
                                case 3:
                                    lane3out.add(carStream1);
                                    break;
                                case 4:
                                    lane4out.add(carStream1);
                            }
                        }
                    }
                    else if(responseLine.contains(";"))
                    {
                        System.out.println("Detected pedestrians");
                        String[] pedStream = responseLine.split(";");
                        for (String pedStream1 : pedStream){
                            System.out.println(pedStream1);
                            switch (pedStream1.charAt(0)) {
                                case 1:
                                    ped1.add(pedStream1);
                                    break;
                                case 2:
                                    ped2.add(pedStream1);
                                    break;
                                case 3:
                                    ped3.add(pedStream1);
                                    break;
                                case 4:
                                    ped4.add(pedStream1);
                            }
                        }
                    }
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
