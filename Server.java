// Creazione Socket, Trasferimento/Ricezione del File
import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.io.DataInputStream;
import java.io.DataOutputStream;

// Strumenti per Input e controllo 
import java.util.Scanner;
import java.util.StringTokenizer;
// Data Structure
import java.util.ArrayList;
import java.lang.Thread;

// Gestione Errori
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;

public class Server extends Thread{
    ServerSocket serverSocket;
    ArrayList<Connection> connectionsList;

    public void run(){
        try{
            while(true){
                //connectionsList.add(new Connection(serverSocket.accept()));
                Connection foo = new Connection(serverSocket.accept());
                connectionsList.add(foo);
                //socketList.add(serverSocket.accept());
                //input = new DataInputStream(socket.getInputStream());
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void broadcast(){
        try {
            System.out.println("broadcast() - Entering First Loop");
            for(int i = 0; i<connectionsList.size(); i++){
                System.out.println("broadcast() - Entering Second Loop");
                for(int j = 0; j<connectionsList.size(); j++){
                    if(j!=i){
                        System.out.println("broadcast() - Reading Connection");
                        if(connectionsList.get(i).getInput().available() > 0)
                            connectionsList.get(j).getOutput().writeUTF(connectionsList.get(i).getInput().readUTF());
                    }
                }
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    public Server(int port){
        try {
            serverSocket = new ServerSocket(port);
            connectionsList = new ArrayList<Connection>();
            start();
            try {
                
                while(true){
                    broadcast();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
