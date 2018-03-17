/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverhttp;

import java.net.*;
import java.io.*;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class multisolicitud extends Thread {

    private Socket cliente;

    public multisolicitud(Socket cliente) {
        this.cliente = cliente;
    }

    @Override
    public void run() {
        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(cliente.getOutputStream()));
            BufferedReader in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            String inputLine, outputLine, format, data;            
            inputLine = in.readLine();
            if (inputLine != null) {
                inputLine = inputLine.split(" ")[1];
                if (inputLine.endsWith(".html")) {
                    File pagina = new File("./" + inputLine);
                    String resultado = "";
                    try {
                        FileReader fReader = new FileReader(pagina);
                        BufferedReader bReader = new BufferedReader(fReader);
                        String line;
                        while ((line = bReader.readLine()) != null) {
                            resultado += line + "\n";
                        }
                        bReader.close();
                    } catch (FileNotFoundException ex) {
                        System.err.println("El recurso solicitado " + "./" + inputLine + " no existe");
                        ex.printStackTrace();

                    } catch (IOException ex) {
                        System.err.println("Error en la lectura del Buffer");
                        ex.printStackTrace();
                    }
                    outputLine = "HTTP/1.1 200 OK \r\n"
                            + "Content-Type: text/html\r\n"
                            + "\r\n"
                            + resultado
                            + inputLine;
                    out.write(outputLine);

                } else if (inputLine.endsWith(".jpg")) {
                    byte[] bytes;
                    bytes = Files.readAllBytes(new File("./" + inputLine).toPath());
                    data = "" + bytes.length;
                    format = "image/html";
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(multisolicitud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
