/**
 * @author Andy
 *
 */
package gmit.client;

import java.util.Scanner;

public class Runner {
	
	// variables
	 static WebClient w = new WebClient();
	
	
	//main
	public static void main(String[] args)throws Throwable {
		
		Context ctx = new Context();
		ContextParser cp = new ContextParser(ctx);
		
		cp.init();
		System.out.println(ctx);
		
		
		int option;
		Scanner scanner = new Scanner(System.in);
		do
		{	
			// Display Menu 
			System.out.println("=============================");
		    System.out.println("|   Multi-Threaded Menu      |");
		    System.out.println("============================= ");
		    System.out.println("| Options:                   |");
		    System.out.println("|    1. Connect to Server    |");
		    System.out.println("|    2. Print File Listing   |");
		    System.out.println("|    3. Download File        |");
		    System.out.println("|    4. Quit          	     |");
		    System.out.println("============================");
			System.out.println("Enter Your Option: ");
			option = scanner.nextInt();
		
		// Connect to the server
		switch (option)
		{
			case 1:
					{
						
						w.client();
						System.out.println("You Are Connected");
						break;
					}
	}
		}
		while(option!=4);
		scanner.close();
}}