/**
 * @author Andy
 *
 */
package gmit.server;
//https://learnonline.gmit.ie/course/view.php?id=2346 -- Material on Moodle used to help with project
// This class provides a very simple implementation of a web server. 

import java.io.*; //Contains classes for all kinds of I/O activity
import java.net.*; //Contains basic networking classes


public class WebServer {
	private ServerSocket ss; //A server socket listens on a port number for incoming requests
	
	// Port 7777  
	private static final int SERVER_PORT = 7777;  
	
	//The boolean value keepRunning is used to control the while loop in the inner class called Listener
	private volatile boolean keepRunning = true;
	
	
	//A null constructor for the WebServer class
	private WebServer(){
		try { 
			
			ss = new ServerSocket(SERVER_PORT); //Start the server socket listening on port 7777
		
			Thread server = new Thread(new Listener(), "Web Server Listener"); //We can also name threads
			server.setPriority(Thread.MAX_PRIORITY); //Ask the Thread Scheduler to run this thread as a priority
			server.start(); 
			System.out.println("The Server has Started And Connected on Port " + SERVER_PORT);
			
		} catch (IOException e) { // Error Handler
			System.out.println("Something Went Wrong!" + e.getMessage());
		}
	}
	
	//main method
	public static void main(String[] args) {
		new WebServer(); //Create an instance of a WebServer
	}
	
	
	
	//The inner class Listener is a Runnable, i.e. a job that can be given to a Thread. 
	 
	private class Listener implements Runnable{ //A Listener IS-A Runnable
		
		//The interface Runnable declare the method "public void run();" that must be implemented
		public void run() {
			int counter = 0; //A counter to track the number of requests
			while (keepRunning){ //Loop will keepRunning is true.  keepRunning is "volatile"
				try { 
					
					Socket s = ss.accept(); //This is a blocking method, causing this thread to stop and wait here for an incoming request
					
					new Thread(new HTTPRequest(s), "T-" + counter).start(); //Give the new job to the new worker and tell it to start work
					counter++; //Increment counter
				} catch (IOException e) { //Error Handler
					System.out.println("Error handling incoming request..." + e.getMessage());
				}
			}
		}
	}//End of inner class Listener
	
	
	// The inner class HTTPRequest is a Runnable, i.e. a job that can be given to a Thread. 
	 
	private class HTTPRequest implements Runnable{
		private Socket sock; //A specific socket connection between some computer on a network and this programme
		
		private HTTPRequest(Socket request) { //Taking the client socket as a constructor enables the Listener class above to farm out the request quickly
			this.sock = request; //Assign to the instance variable sock the value passed to the constructor. 
		}

		//The interface Runnable declare the method "public void run();" that must be implemented
        public void run() {
            try{ //Try the following. If anything goes wrong, the error will be passed to the catch block
            	
            	//Read in the request from the remote computer to this programme
            	ObjectInputStream in = new ObjectInputStream(sock.getInputStream());
                Object command = in.readObject(); //Deserialise 
                System.out.println(command);
                
                //Write out a response back to the client
                String message = "<h1>Welcome to the Server</h1>";
            	ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
                out.writeObject(message);
                out.flush();
                out.close(); //Tidy up 
                
            } catch (Exception e) { //catch 
            	System.out.println("Error processing request from " + sock.getRemoteSocketAddress());
            	e.printStackTrace();
            }
        }
	}//End of inner class HTTPRequest
}//End of class WebServer
