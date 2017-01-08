package gmit.client;

import java.util.Scanner;

public class Menu {
	public static void main(String[] args) {
	//Variables
	int option;
	Scanner scan = new Scanner(System.in);
	do
	{	
		System.out.println("=============================");
	    System.out.println("|   Multi-Threaded Menu      |");
	    System.out.println("============================= ");
	    System.out.println("| Options:                   |");
	    System.out.println("|    1. Connect to Server    |");
	    System.out.println("|    2. Print File Listing   |");
	    System.out.println("|    3. Download File        |");
	    System.out.println("|    4. Quit          	     |");
	    System.out.println("============================");
		System.out.println("Type Option [1-4]>");
		option = scan.nextInt();
	
	}
	while(option !=4);
	scan.close();
	
	}
	

}