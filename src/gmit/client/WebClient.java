/**
 * @author Andy
 *
 */
// https://learnonline.gmit.ie/course/view.php?id=2346 -- Material on Moodle used to help with project
package gmit.client;

import java.io.*; //We need the Java IO library to read from the socket's input stream and write to its output stream
import java.net.*; //Sockets are packaged in the java.net library


public class WebClient { //The class WebClient must be declared in a file called WebClient.java
	
	//Main method to get the ball rolling
	public  void client() throws Throwable
	{
		final String request = "GET /characters.txt HTTP/1.1\n\n"; //The message to send to the server
		
		//Loop 10 times to simulate 10 concurrent connections to the server. Examine the output and increase to 10000 
		for (int i = 0; i < 10; i++){
			
			//Create a new Thread using the constructor Thread(Runnable r, String threadName).
			
			new Thread(new Runnable(){
				// Every thread / runnable needs a run() method. 
				public void run() { 
					try { //Attempt the following. If something goes wrong, the flow jumps down to catch()
						Socket s = new Socket("localhost", 7777); //Connect to the server
						
						//Serialise / marshal a request to the server
						ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
						out.writeObject(request); //Serialise
						out.flush(); //Ensure all data sent by flushing buffers
						
						Thread.yield(); //Pause the current thread for a short time (not used much)
						
						//Deserialise / unmarshal response from server 
						ObjectInputStream in = new ObjectInputStream(s.getInputStream());
						String response = (String) in.readObject(); //Deserialise
						
						//Get the name of the thread (worker) doing this runnable (job)
						String threadName = Thread.currentThread().getName(); 
 	 					System.out.println(response + "-->" + threadName + " bye bye...");
						
 	 					//Pause this thread for 10 seconds
 	 					Thread.sleep(10000);
						
						System.out.println(threadName + " the socket is now closing");
						
						s.close(); //Tidy up
						
					} catch (Exception e) { //Deal with the error here. A try/catch stops a programme crashing on error  
						System.out.println("Error: " + e.getMessage());
					}//End of try /catch
				}//End of run(). The thread will now die)
				
			}, "Client-" + i).start(); //Start the thread
		}//End of for loop
		
		System.out.println("Main Method...");
		
	}//End of main method
}//End of class