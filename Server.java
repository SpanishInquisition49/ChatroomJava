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
                System.out.println("Server:: New Connection Accepted | Username: " + foo.getUsername() + " | Clients number: " + connectionsList.size());
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
            //System.out.println("broadcast() - Entering First Loop");
            for(int i = 0; i<connectionsList.size(); i++){
                System.out.println("Server:: Listenning for messages");
                for(int j = 0; j<connectionsList.size(); j++){
                    if(j!=i){
                        if(connectionsList.get(i).getInput().available() > 0){
                            connectionsList.get(j).getOutput().writeUTF("[" + connectionsList.get(i).getUsername() + "] " + connectionsList.get(i).getInput().readUTF());
                            System.out.println("Server:: [" + connectionsList.get(i).getUsername() + "] Message: " + connectionsList.get(i).getInput().readUTF());
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        System.out.println("Server:: Exit from broadcast()");
    }

    public Server(int port){
        try {
            serverSocket = new ServerSocket(port);
            connectionsList = new ArrayList<Connection>();
            start();
            System.out.println("Server:: Started on port: " + port);
            try {
                while(true){
                    broadcast();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("Server:: Loop Endend");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
