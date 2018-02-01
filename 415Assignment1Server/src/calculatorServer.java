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
    static String okMsg = "515OK - From CalculatorServer";


    public static void main(String[] args) throws IOException 
    {
    	// Just faster to put here, for changes
    	int portNumber = 5556;

		// Creates the server socket, and logs that it was started
	    ServerSocket serverSocket = new ServerSocket(portNumber);	
	    System.out.println("The Server Socket is set. \n");
	    
	    // Infinite loop for searching for connections
	    while (true) 
	    {
	    	// ClientSock to receive data
	    	Socket clientSock = null;
	    	
	    	try 
	    	{     
	    		// Creates the connection to the client
	    		clientSock = serverSocket.accept();

	    	    // PrintWriter takes our string and sends as a byte stream
	    	    PrintWriter clientWriter =
	    	        new PrintWriter(clientSock.getOutputStream(), true);
	    	    
	    	    // Buffered reader handles the input stream from the client
		        BufferedReader clientReader = new BufferedReader (
		        	new InputStreamReader(clientSock.getInputStream()));  

                // Create a new thread
                Thread clientThread = new ClientHandler(clientSock, clientReader, clientWriter);
 
                // Invoking the start() method
                clientThread.start();

             //   if (clientReader.equals("Are you there?")) {
                	
            //    	clientWriter.println(okMsg); 
            //    	clientWriter.flush();
           //     }
                	    

	    	}
	    	    
	    	catch (IOException e) 
	    	{
	    	    System.out.println("Server Error: " + e.getMessage());
	    	        
	    	}
	    }
	    
	    /*
	    // Create a socket to listen at port 1234
        DatagramSocket listen = new DatagramSocket();
        
        byte[] buf = null;
        
        DatagramPacket DataReceive = null;
        
        DatagramPacket DataSend = null;
        
        while (true)
        {
        	buf = new byte[65535];
 
            // Create a DatgramPacket to receive the data
            DataReceive = new DatagramPacket(buf, buf.length);
 
            // Receive the data in byte buffer
            listen.receive(DataReceive);
 
            String input = new String(buf, 0, buf.length);
 
            // To remove extra spaces
            input=input.trim();
            System.out.println("Equation Received:- " + input);
 
            // Exit the server if the client sends exit
            if (input.equals("exit"))
            {
                System.out.println("Client sent exit.....EXITING PROGRAM....GOODBYE");
                break;
            }
 
            int result;
 
            // Use StringTokenizer to break the equation into number and operation
            StringTokenizer st = new StringTokenizer(input);
 
            int num1 = Integer.parseInt(st.nextToken());
            String operation = st.nextToken();
            int num2 = Integer.parseInt(st.nextToken());
 
            // Perform the required operation given by user
            if (operation.equals(" + "))
                result = num1 + num2;
 
            else if (operation.equals(" - "))
                result = num1 - num2;
 
            else if (operation.equals(" * "))
                result = num1 * num2;
            
            else if (operation.equals(" % "))
                result = num1 % num2;
 
            else
                result = num1 / num2;
 
            System.out.println("Sending the result...");
            String res = Integer.toString(result);
 
            // Clear the buffer after every message
            buf = res.getBytes();
 
            // Get the port of client
            int port = DataReceive.getPort();
 
            DataSend = new DatagramPacket(buf, buf.length, InetAddress.getLocalHost(), port);
            listen.send(DataSend);
        }
        */
    }
}




