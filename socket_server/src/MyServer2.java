
import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.io.PrintWriter;  
import java.net.ServerSocket;  
import java.net.Socket;  

import org.json.JSONException;

public class MyServer2 {

	 public static void main(String[] args) throws IOException, JSONException {
		 	
	        ServerSocket server = new ServerSocket(10000);  
	        ServerSocket server1 = new ServerSocket(10001);  
	        while (true) {  
	            Socket socket = server.accept();
	            Socket socket1 = server1.accept();
	            invoke(socket,socket1);  
	        }  
	    }  
	      
	    private static void invoke(final Socket client,final Socket client1) throws IOException, JSONException {  
	    	final YahooNews yn = new YahooNews();
	    	String res = null;
	    	new Thread(new Runnable() {  
	            public void run() {  
	                BufferedReader in = null;  
	                PrintWriter out = null;  
	                try {  
	                    in = new BufferedReader(new InputStreamReader(client.getInputStream()));  
	                    out = new PrintWriter(client1.getOutputStream());  
	                    
	                    while (true) {  
	                    	
	                        String msg = in.readLine();
	                        if(msg==null){
	                        	System.out.println("null!!");
	                        	continue;
	                        }
	                        System.out.println(msg);
	                        if(msg.equals("left")){
	                        	out.println(yn.getNext());
	                        }else if(msg.equals("right")){
	                        	out.println(yn.getPrevise());
	                        }else if(msg.equals("up")){
	                        	out.println(yn.getNext());
	                        }else if(msg.equals("down")){
	                        	out.println(yn.getPrevise());
	                        }else if(msg.equals("bye")){
	                        	out.println("bye");
	                        	break;
	                        }
	                        
	                          
	                        out.flush();  
	                        /*if (msg.equals("bye")) {  
	                            break;  
	                        } */ 
	                    }  
	                } catch(IOException ex) {  
	                    ex.printStackTrace();  
	                } catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {  
	                    try {  
	                        in.close();  
	                    } catch (Exception e) {}  
	                    try {  
	                        out.close();  
	                    } catch (Exception e) {}  
	                    try {  
	                        client.close();  
	                    } catch (Exception e) {}  
	                }  
	            }  
	        }).start();  
	    }  

}
