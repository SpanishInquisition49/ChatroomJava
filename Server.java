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

// Gestione Errori
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;

public class Server {
    ServerSocket serverSocket;
    ArrayList<Socket> socketList;
    ArrayList<DataInputStream> inputList;
    DataInputStream input;

    private Runnable connectionHandler(ServerSocket serverSocket){
        try{
            while(true){
                socketList.add(serverSocket.accept());
                input = new DataInputStream(socket.getInputStream());
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public Server(int port){
        try {
            serverSocket = new ServerSocket(port);
            socketList = new ArrayList<>();
            new Thread(connectionHandler(this.serverSocket)).start();
            try {
                
                while(true){
                    System.out.println(input.readUTF());
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
