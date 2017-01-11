/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vlad
 */
public class Server {

    static ServerSocket ss;
    static ArrayList<ServerThread> clienti = new ArrayList<ServerThread>();
    
    public static void main(String[] args) throws IOException {
       
        try {
            ss = new ServerSocket(4321);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        while(true){
            
            ServerThread st = new ServerThread(ss.accept());
            clienti.add(st);
            st.start();
        }   
        
    }
    
}
