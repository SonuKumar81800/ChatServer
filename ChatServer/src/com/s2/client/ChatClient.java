package com.s2.client;

import java.net.*;
import java.io.*;

import com.s2.threads.ReadThread;
import com.s2.threads.WriteThread;
import com.s2.common.ArgsValidation;

/**
 * This is the chat client program.
 * Type 'bye' to terminate the program.
 *
 * @author Sonu Kumar
 */
public class ChatClient {
    private String hostname;
    private int port;
    private String userName;

    public ChatClient(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    public void execute() {
        try {
            Socket socket = new Socket(hostname, port);

            System.out.println("Connected to the chat server");

            new ReadThread(socket, this).start();
            new WriteThread(socket, this).start();

        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O Error: " + ex.getMessage());
        }

    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return this.userName;
    }


    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Syntax: java ChatClient <ip/address> <port-number>");
            System.exit(1);
        }

        if(!ArgsValidation.isValidHostnameArg(args[0])) {
            System.out.println("Err: Invalid hostname");
            System.exit(1);
        }

        String hostname = args[0];

        if(!ArgsValidation.isValidPortArg(args[1])) {
            System.out.println("Err: Non numeric argument not allowed");
            System.exit(1);
        }

        int port = Integer.parseInt(args[1]);

        if(!ArgsValidation.isValidPort(port)) {
            System.out.println("Err: Attempt to use well-known port");
            System.exit(1);
        }

        ChatClient client = new ChatClient(hostname, port);
        client.execute();
    }
}