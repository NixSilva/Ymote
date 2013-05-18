
import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.io.PrintWriter;  
import java.net.ServerSocket;  
import java.net.Socket;  

import org.json.JSONException;

public class Myserver{

	 public static void main(String[] args) throws IOException, JSONException {
		 	
	        
	        ServerSocket server1 = new ServerSocket(10001);  
	       
	        while (true) {   Socket socket1 = server1.accept();
	            
	            invoke(socket1);  
	        }  
	    }  
	      
	    private static void invoke(final Socket client1) throws IOException, JSONException {  
	    	final ServerSocket server = new ServerSocket(10000);  
	    	final YahooNews yn = new YahooNews();
	    	String res = null;
	    	new Thread(new Runnable() {  
	            public void run() {   
	                PrintWriter out = null;  
	                BufferedReader in = null;;
	                Socket socket = null;
	                try {  
	                    out = new PrintWriter(client1.getOutputStream());  
	                    
	                    while (true) {  
	                    	socket = server.accept();
	                    	in = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
	                    	
	                        String msg = in.readLine();
	                        if(msg==null){
	                        	System.out.println("null!!");
	                        	//continue;
	                        }else{
	                        	System.out.println(msg);
		                        if(msg.equals("left")){
		                        	out.println(yn.getNext());
		                        }else if(msg.equals("right")){
		                        	out.println(yn.getPrevise());
		                        }else if(msg.equals("up")){
		                        	out.println(yn.getNext());
		                        }else if(msg.equals("down")){
		                        	out.println(yn.getPrevise());
		                        }else if(msg.equals("l")){
		                        	out.println(yn.getNext());
		                        }else if(msg.equals("r")){
		                        	out.println(yn.getPrevise());
		                        }else if(msg.equals("u")){
		                        	out.println(yn.getNext());
		                        }else if(msg.equals("d")){
		                        	out.println(yn.getPrevise());
		                        }else if(msg.equals("bye")){
		                        	out.println("bye");
		                        //	break;
		                        }
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
	                        socket.close();  
	                    } catch (Exception e) {}  
	                }  
	            }  
	        }).start();  
	    }  

}
