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

import javax.swing.JOptionPane;

public class calculatorClient 
{
	// Declarations of class variables
    private static Socket clientSock; 
    private static int port = 5556;
    private static String server = "localhost";
    private static String pingmsg = "Are you there?";
    private static BufferedReader clientReader;
    private static PrintWriter clientWriter;
    private static Scanner keyScan = null;
    private static String response = null;
    private static String equation = null;
    private static String result = null;
 
	public static void main(String[] args) throws IOException
	{
	   
	    // Try and catch block to open communication via the Socket sock1
	    try 
	    {
	    		// Creates instance of clientSock at localhost port 5555
	           	clientSock = new Socket(server, port);
	           
	           	// BufferedReader will take an InputStreamReader which takes our sockets input stream
	           	// This will be how we will take data in from the socket
	           	clientReader = new BufferedReader ( new InputStreamReader(clientSock.getInputStream()));

	           	// PrintWriter takes the data from client and sends to server as byte stream
	           	clientWriter = new PrintWriter(clientSock.getOutputStream(), true);
	       
	           	// Scanner KeyScan will take the users input from the keyboard
	           	keyScan = new Scanner(System.in);
	           
	            // This will display a proper connection is made with the server
	            clientWriter.println(pingmsg);
	            response = clientReader.readLine();
	            System.out.println(response);

 	           	// Prompt the user to enter input and receive result
	            System.out.println("Welcome to the 415/515 Calculator.");
	            System.out.println("You may enter \"count\" to show the number of connections,");
	            System.out.println("or \"exit\" to disconnect.");
	            /*JOptionPane.showMessageDialog(null, "Welcome to the 415/515 Calculator. \n You may enter \"count\" to show the number of connections, "
        		+ "or \"exit\" to disconnect.", "415/515 Calculator", JOptionPane.INFORMATION_MESSAGE);*/
         
	            // Do while loop keeps the prompt going until the user enters exit
	            do
	            {
	            	System.out.println("Enter a simple equation in the format: operand1 operator operand2 \n");
	            	//JOptionPane.showInputDialog("Enter a simple equation in the format: operand1 operator operand2 \n");
	            	equation = keyScan.nextLine();
		           
	            	// Send the user's equation to the server
	            	clientWriter.println(equation);
		           
	            	// If the input is not exit, then it will show what was received from the server
	            	if(!equation.equalsIgnoreCase("exit"))
	            	{
	            		result = clientReader.readLine();
	            		System.out.println(result);
	            		//JOptionPane.showMessageDialog(null, "The answer is: " + result, "Result of Equation", JOptionPane.INFORMATION_MESSAGE);
	            		keyScan.close();
	            	}        
	            }	           
	           // If exit is entered by user it will exit this loop
	           while(!equation.equalsIgnoreCase("exit"));         
	    }	    
	    catch (IOException e) 
	    {
	        System.out.println("Client side error: " + e.getMessage());
	        keyScan.close();
	    }
	}
}
