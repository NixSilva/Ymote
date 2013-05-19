/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package yahoonewsremotecontrol.net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author CCFLAB
 */
public class FalseServer extends Thread{
    static ServerSocket server;
    static int port;
    
    public static void  main(String[] args){
        port = 12345;
        try {
            server = new ServerSocket(port);
            System.out.println("waiting for client..");
            while (true) {
                Socket socket = server.accept();
                System.out.println("Client " + socket + " connected..");

                BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                String line;

                while ((line = sin.readLine()) != "exit") {
                    line = line + "\n";
                    out.write(line);
                    System.out.print("Server send:\t" + line);
                    out.flush();
                }
            }           
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
