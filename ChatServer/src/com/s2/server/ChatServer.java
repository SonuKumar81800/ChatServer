package com.s2.server;

import java.io.*;
import java.net.*;
import java.util.*;

import com.s2.threads.UserThread;
import com.s2.common.ArgsValidation;

/**
 * This is the chat server program.
 * Press Ctrl + C to terminate the program.
 *
 * @author Sonu Kumar
 */
public class ChatServer {
    private int port;
    private Set<String> userNames = new HashSet<>();
    private Set<UserThread> userThreads = new HashSet<>();

    public ChatServer(int port) {
        this.port = port;
    }

    public void execute() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {

            System.out.println("Chat Server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New user connected");

                UserThread newUser = new UserThread(socket, this);
                userThreads.add(newUser);
                newUser.start();

            }

        } catch (IOException ex) {
            System.out.println("Error in the server: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Syntax: java ChatServer <port-number>");
            System.exit(1);
        }

        if(!ArgsValidation.isValidPortArg(args[0])) {
            System.out.println("Err: Non numeric argument not allowed");
            System.exit(1);
        }

        int port = Integer.parseInt(args[0]);

        if(!ArgsValidation.isValidPort(port)) {
            System.out.println("Err: Attempt to use well-known port");
            System.exit(1);
        }

        ChatServer server = new ChatServer(port);
        server.execute();
    }

    /**
     * Delivers a message from one user to others (broadcasting)
     */
    public void broadcast(String message, UserThread excludeUser) {
        for (UserThread aUser : userThreads) {
            if (aUser != excludeUser) {
                aUser.sendMessage(message);
            }
        }
    }

    /**
     * Stores username of the newly connected client.
     */
    public void addUserName(String userName) {
        userNames.add(userName);
    }

    /**
     * When a client is disconneted, removes the associated username and UserThread
     */
    public void removeUser(String userName, UserThread aUser) {
        boolean removed = userNames.remove(userName);
        if (removed) {
            userThreads.remove(aUser);
            System.out.println(userName + " has left the chat");
        }
    }

    public Set<String> getUserNames() {
        return this.userNames;
    }

    /**
     * Returns true if there are other users connected (not count the currently connected user)
     */
    public boolean hasUsers() {
        return !this.userNames.isEmpty();
    }
}