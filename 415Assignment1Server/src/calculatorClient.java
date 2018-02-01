/**
 * Deborah Barndt
 * Thomas Boller
 * 1-30-18
 * calculatorClient.java
 * Assignment 1
 * This program will send messages to the server.
 * Written by Deborah Barndt & Thomas Boller. */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class calculatorClient 
{
	
	public static void main(String[] args) throws IOException
	{
		// Declarations of local variables
	    Socket clientSock; 
	    int port = 5556;
	    String server = "localhost";
	   
	    // Try and catch block to open communication via the Socket sock1
	    try 
	    {
	    		// Creates instance of clientSock at localhost port 5555
	           	clientSock = new Socket(server, port);
	           
	           	// BufferedReader will take an InputStreamReader which takes our sockets input stream
	           	// This will be how we will take data in from the socket
	           	BufferedReader clientReader = new BufferedReader ( new InputStreamReader(clientSock.getInputStream()));

	           	// PrintWriter takes the data from client and sends to server as byte stream
	           	PrintWriter clientWriter = new PrintWriter(clientSock.getOutputStream(), true);
	       
	           	// Scanner KeyScan will take the users input from the keyboard
	           	Scanner keyScan = new Scanner(System.in);
	           
	           	// Variables to hold user input and response from server
	            String 	equation, 	// simple math problem given
	            		result;		// answer to the equation
	           
	            // This will display a proper connection is made with the server
	            String response = clientReader.readLine();
	            System.out.println(response);

 	           	// Prompt the user to enter input and receive result
	            System.out.println("Welcome to the 415/515 Calculator.");
	            System.out.println("You may enter \"count\" to show the number of connections,");
	            System.out.println("or \"exit\" to disconnect.");
	           
	            // Do while loop keeps the prompt going until the user enters exit
	            do
	            {
	            	System.out.println("Enter a simple equation in the format: operand1 operator operand2 \n");
	            	equation = keyScan.nextLine();
		           
	            	// Send the user's equation to the server
	            	clientWriter.println(equation);
		           
	            	// If the input is not exit, then it will show what was received from the server
	            	if(!equation.equals("exit"))
	            	{
	            		result = clientReader.readLine();
	            		System.out.println(result);
	            	}           
	            }
	           
	           // If exit is entered by user it will exit this loop
	           while(!equation.equals("exit"));         
	    }
	    
	    catch (IOException e) 
	    {
	        System.out.println("Client side error: " + e.getMessage());
	    }
	    
	    /*
	    // Creates a scanner object
	    Scanner compute = new Scanner(System.in);
	    
	    // Create the socket object for carrying the data
        DatagramSocket problem = new DatagramSocket();
        
        InetAddress ip = InetAddress.getLocalHost();
        byte buf[] = null;
        
        // Loop until the user enters exit
        while (true)
        {
            System.out.print("Enter the equation in the format: operand1 operator operand2\n");
            String input = compute.nextLine();
            buf = new byte[65535];
 
            // Convert the string input into a byte array
            buf = input.getBytes();
 
            // Create a datagramPacket for sending the data
            DatagramPacket DataSend = new DatagramPacket(buf, buf.length, ip, 1234);
 
            // Invoke the send call to actually send the data.
            problem.send(DataSend);
 
            // Break the loop if user enters exit
            if (input.equals("exit"))
                break;
 
            buf = new byte[65535];
            DatagramPacket DataReceive = new DatagramPacket(buf, buf.length);
            problem.receive(DataReceive);
 
            System.out.println("Answer = " + new String(buf,0,buf.length));
        }
*/
	}
}
