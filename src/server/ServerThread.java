/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vlad
 */
public class ServerThread extends Thread{
    
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    
    public ServerThread(Socket socket){
        this.socket = socket;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    public void run(){
        try{
           while(true){
               String line = in.readLine();
               for (ServerThread client : Server.clienti){
                   client.sendMessage(line);
               }
           } 
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void sendMessage(String message){
        out.println(message);
    }
}

