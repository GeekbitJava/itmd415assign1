/**
 * Deborah Barndt
 * Thomas Boller
 * 1-30-18
 * calculatorServer.java
 * Assignment 1
 * This program will allow the client to send a message to the server. Once the message
 * is received, the server will respond with a message.
 * Written by Deborah Barndt & Thomas Boller. */

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.StringTokenizer;
public class calculatorServer 
{
    private static int portNumber = 5556;
    private static Socket clientSock = null;
    private static BufferedReader clientReader;
    private static PrintWriter clientWriter;
    
    public static void main(String[] args) throws IOException 
    {
		// Creates the server socket, and logs that it was started
	    ServerSocket serverSocket = new ServerSocket(portNumber);	
	    System.out.println("The Server Socket is set. \n");
	    
	    // Infinite loop for searching for connections
	    while (true) 
	    {
	    	try 
	    	{     
	    		// Creates the connection to the client
	    		clientSock = serverSocket.accept();

	    	    // PrintWriter takes our string and sends as a byte stream
	    	    clientWriter =
	    	        new PrintWriter(clientSock.getOutputStream(), true);
	    	    
	    	    // Buffered reader handles the input stream from the client
		        clientReader = new BufferedReader (
		        	new InputStreamReader(clientSock.getInputStream()));  

                // Create a new thread
                Thread clientThread = new ClientHandler(clientSock, clientReader, clientWriter);
 
                // Invoking the start() method
                clientThread.start();   
	    	}	    	    
	    	catch (IOException e) 
	    	{
	    	    System.out.println("Server Error: " + e.getMessage()); 	        
	    	}
	    }
    }
}




