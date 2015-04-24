/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testthreading;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.time;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Holly-Marie
 */
public class Reader implements Runnable {

    Socket newSocket = null;
    BufferedReader in = null;
    List<String> lane1in = null;
    List<String> lane1out = new ArrayList<>();
    List<String> lane2in = null;
    List<String> lane2out = new ArrayList<>();
    List<String> lane3in = null;
    List<String> lane3out = new ArrayList<>();
    List<String> lane4in = null;
    List<String> lane4out = new ArrayList<>();
    List<String> ped1 = new ArrayList<>();
    List<String> ped2 = new ArrayList<>();
    List<String> ped3 = new ArrayList<>();
    List<String> ped4 = new ArrayList<>();
    long time = System.currentTimeMillis();

    int selector = 1;

    Reader(Socket s) {
        newSocket = s;
        //System.out.println("In read constructor");
    }

    @Override
    @SuppressWarnings("empty-statement")
    public void run() {
        try {
//is = new DataInputStream(newSocket.getInputStream());
            //System.out.println("Trying to read");
            in = new BufferedReader(new InputStreamReader(newSocket.getInputStream()));
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: hostname - Input");
        }
// to the socket we have opened a connection to on port 5000
        if (newSocket != null && in != null) {
            try {
                //System.out.println("Neither reader null");
                String responseLine;
                while ((responseLine = in.readLine()) != null) {
                    //System.out.println("Server: " + responseLine);

                    if (responseLine.contains(":") && responseLine.contains(";")) {
                        //System.out.println("Detected cars");
                        String[] carStream = responseLine.split(";");
                        for (String carStream1 : carStream) {
//Debug line to show cars being split
                            //System.out.println(carStream1);

                            switch (carStream1.charAt(0)) {
                                case '1':
                                    lane1out.add(carStream1);
                                    //System.out.println(lane1out.get(0) + " sasdad ");
                                    break;
                                case '2':
                                    lane2out.add(carStream1);
                                    //System.out.println(lane1out.get(0) + " sasdad ");
                                    break;
                                case '3':
                                    lane3out.add(carStream1);
                                    //System.out.println(lane1out.get(0) + " sasdad ");
                                    break;
                                case '4':
                                    lane4out.add(carStream1);
                                //System.out.println(lane1out.get(0) + " sasdad ");
                            }
                        }
                    } //Above handles car detection and indexing to relevant arrays
                    else if (responseLine.contains(";")) {
                        //System.out.println("Detected pedestrians");
                        String[] pedStream = responseLine.split(";");
                        for (String pedStream1 : pedStream) {
                            //Debug line to show peds being split
                            //System.out.println(pedStream1);
                            switch (pedStream1.charAt(0)) {
                                case '1':
                                    ped1.add(pedStream1);
                                    break;
                                case '2':
                                    ped2.add(pedStream1);
                                    break;
                                case '3':
                                    ped3.add(pedStream1);
                                    break;
                                case '4':
                                    ped4.add(pedStream1);
                            }
                        }
                    }

                    //Process exec = Runtime.getRuntime().exec("cls");
                    System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");//adams idea `-`
                    System.out.println("Current Event:");
                    switch (selector) {
                        case 1:
                            lane1out.clear();

                            System.out.println("Exit 1 lights are green. Cars can go.");
                            break;
                        case 2:
                            lane2out.clear();

                            System.out.println("Exit 2 lights are green. Cars can go.");
                            break;
                        case 3:
                            lane3out.clear();

                            System.out.println("Exit 3 lights are green. Cars can go.");
                            break;
                        case 4:
                            lane4out.clear();

                            System.out.println("Exit 4 lights are green. Cars can go.");
                            break;
                        case 5:
                            ped1.clear();
                            ped2.clear();
                            ped3.clear();
                            ped4.clear();

                            System.out.println("All exits are red. Pedestrians are crossing.");
                            break;
                    }
                    
                    if ((time + 5000) < System.currentTimeMillis()) {

                        time = System.currentTimeMillis();
                        if ((ped1.size() + ped2.size() + ped3.size() + ped4.size()) > 10) {
                            selector = 5;
                        } else {
                            selector++;

                            if (selector > 4) {
                                selector = 1;

                            }
                        };
                    }

                    System.out.println(" ");
                    System.out.println("Cars at Exit 1: " + lane1out.size());
                    System.out.println("Cars at Exit 2: " + lane2out.size());
                    System.out.println("Cars at Exit 3: " + lane3out.size());
                    System.out.println("Cars at Exit 4: " + lane4out.size());
                    System.out.println("Total Cars Waiting: " +(lane1out.size()+lane2out.size()+lane3out.size()+lane4out.size()));
                    System.out.println(" ");
                    System.out.println("Peds at Exit 1: " + ped1.size());
                    System.out.println("Peds at Exit 2: " + ped2.size());
                    System.out.println("Peds at Exit 3: " + ped3.size());
                    System.out.println("Peds at Exit 4: " + ped4.size());
                    System.out.println("Total Pedestrians Waiting: " + (ped1.size() + ped2.size() + ped3.size() + ped4.size()));
//try {
                    //TimeUnit.SECONDS.sleep(5);

//} catch (InterruptedException e) {
                    //Handle exception
//}
                }
//Above handles peds detection and indexing to relevant arrays

                in.close();
            } catch (UnknownHostException e) {
                System.err.println("Trying to connect to unknown host: " + e);
            } catch (IOException e) {
                System.err.println("IOException: " + e);
            }
        }
    }
}
