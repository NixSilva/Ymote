/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package yahoonewsremotecontrol.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import yahoonewsremotecontrol.Command;

/**
 *
 * @author CCFLAB
 */
public class ServerConnector {
    String ip = "127.0.0.1";
    int port = 12345;
    Socket socket = null;
    
    BufferedReader is;
    public boolean isConnected;
    
    public ServerConnector(){
        
    }
    
    public boolean connect(String ip, int port){
        this.ip = ip;
        this.port = port;
        try{
            socket = new Socket(ip, port);
            is=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Connect to Server " + ip + ":" + port);
        }
        catch(Exception e){
            e.printStackTrace();
            isConnected = false;
            return false;
        }
        isConnected = true;
        return true;
    }
    
    public Command getCommand(){
        System.out.println("Waiting for command..");
        try{
//            String line;
//            while(true){
//                line = is.readLine();
//                System.out.println("Cliend recieve:\t" + line);
//                if(line == "exit"){
//                    break;
//                }
//            }
            String line = is.readLine();
            System.out.println("Cliend recieve:\t" + line);
            return new Command(line);
        }
        catch(Exception e){
            return new Command(Command.Cmd.Exception, e.toString());
        }
    }
}
