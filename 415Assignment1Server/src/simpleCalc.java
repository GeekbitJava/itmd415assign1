/**
 * Deborah Barndt
 * Thomas Boller
 * 1-30-18
 * simpleCalc.java
 * Assignment 1
 * This program will allow the client to type in a simple equation, send it to the server,
 * and the server will give a result.
 * Written by Deborah Barndt & Thomas Boller. */

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class simpleCalc
{
    public static void main(String args[]) throws IOException
    {
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
 
            // invoke the send call to actually send the data.
            problem.send(DataSend);
 
            // break the loop if user enters exit
            if (input.equals("exit"))
                break;
 
            buf = new byte[65535];
            DatagramPacket DataReceive = new DatagramPacket(buf, buf.length);
            problem.receive(DataReceive);
 
            System.out.println("Answer = " + new String(buf,0,buf.length));
        }
    }
}