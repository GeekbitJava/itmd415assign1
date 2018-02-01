/**
 * Deborah Barndt
 * Thomas Boller
 * 1-30-18
 * ClientHandler.java
 * Assignment 1
 * This program will allow the user to enter in a simple math equation to the server 
 * for a result.
 * Written by Deborah Barndt & Thomas Boller. */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

// ClientHandler class
class ClientHandler extends Thread 
{
    final BufferedReader clientReader;
    final PrintWriter clientWriter;
    final Socket s;
    static String okMsg = "515OK - From CalculatorServer";
    
    // Constructor
    public ClientHandler(Socket s, BufferedReader clientReader, PrintWriter clientWriter) 
    {
        this.s = s;
        this.clientReader = clientReader;
        this.clientWriter = clientWriter;
    }
 
    @Override
    public void run() 
    //public void run(Thread[] clientThread)
    {
        String equation;
        String solution = "You did not enter the equation in the format: \n operand1 operator operand2";
        int intresult;
        double doubresult;
        boolean active = true;
        String pingmsg = null;
        
        try
        {
            // Receive the equation from the client
            pingmsg = clientReader.readLine();
            
            if (pingmsg.equals("Are you there?")) 
            {
            	clientWriter.println(okMsg);
            }
        	
        }
        catch (IOException e){
        	
        }
        
        // Logs the new thread in the console
        System.out.println("Client " + this.s + " established");
        
        //While loop continually checks for input and responds.
        while (active) 
        {
        	try 
            {                           
                // Receive the equation from the client
                equation = clientReader.readLine();
                 
                // If statement determines if the user entered exit, count, or an equation
                if (equation.equals("exit"))
                	if (equation.equals("Exit"))
                		if (equation.equals("EXIT"))
                		{ 
                			//Sets the loop to end and breaks to the end
                			active = false;
                			// break;             
                		}
                
                else if (equation.equals("count"))
                {            	
                	// Echoes back the sent information
                	clientWriter.println(equation + " received...");
                	
                	int num = Thread.activeCount();
                	clientWriter.println(num);
                	
                	Thread th[] = new Thread[num];
                	
                	// Returns the number of threads that are put into the array
                	Thread.enumerate(th);
                	                	
                	// Prints the active threads when count is inputed 
                	for (int count = 0; count < num; count++)
                	{
                		clientWriter.println(equation + ": " + th[count]);
                	}          	
                }
                
                else
                {         	
                   	// Echoes back the sent information
                	//clientWriter.println(equation + "Received");

                	// Use StringTokenizer to break the equation into operand and operation
                    StringTokenizer st = new StringTokenizer(equation, " ");
         
                    // Parse string for operand1 operator and operand2
                    String num1 = st.nextToken();
                    String operation = st.nextToken();
                    String num2 = st.nextToken();
                    
                    // Set the operands to double and int to allow for int division
                    int integ2 = Integer.parseInt(num2);
                    int integ1 = Integer.parseInt(num1);
                    double doub1 = Double.parseDouble(num1);
                    double doub2 = Double.parseDouble(num2);

                    // Perform the required operation given by user
                    switch (operation) 
                    {
                    	case "+":
                    		doubresult = doub1 + doub2;
                    		solution = Double.toString(doubresult);                    	
                    		break;
                    	
                    	case "-":
                    		doubresult = doub1 - doub2;
                    		solution = Double.toString(doubresult);
                    		break;
                    	
                    	case "/":
                    		doubresult = doub1 / doub2;
                    		solution = Double.toString(doubresult);
                    		break;
                    	
                    	case "//":
                    		intresult = integ1 / integ2;
                    		solution = Integer.toString(intresult);
                    		break;
                    	
                    	case "%":
                    		doubresult = doub1 % doub2;
                    		solution = Double.toString(doubresult);
                    		break;
                    	
                    	case "*":
                    		doubresult = doub1 * doub2;
                    		solution = Double.toString(doubresult);
                    		break;
                    	
                    	default: System.out.println("ERROR!");
                    	break;
                    }
         
                    System.out.println("Sending the result...");
                    clientWriter.println(solution);
                    clientWriter.flush();
                }
                
            } 
            
            catch (IOException e) 
            {
                e.printStackTrace();
            }
            
        }//end of the while block
         
        try
        {        	
        	// Server says goodbye
        	clientWriter.println("Goodbye");
        	clientWriter.flush();
            
        	System.out.println("Client " + this.s + " Disconnected");
        	
            // Closing resources
            this.clientReader.close();
            this.clientWriter.close();
            this.s.close();
        }
        
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}