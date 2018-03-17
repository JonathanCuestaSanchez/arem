/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverhttp;

import java.net.*;
import java.io.*;

/**
 *
 * @author user
 */
public class httpserver {
     public static void main(String[] args) throws IOException {
       
            ServerSocket serverSocket = null;
            Socket clientSocket = null;
            try {
                serverSocket = new ServerSocket(new Integer(System.getenv("PORT")));
            } catch (IOException e) {
                System.err.println("Could not listen on port");
                System.exit(1);
            }
         while(true){
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }            
        }
    }
}
