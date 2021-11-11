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


// Gestione Errori
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;

public class Server {
    ServerSocket serverSocket;
    Socket socket;
    DataInputStream input;

    public Server(int port){
        try {
            serverSocket = new ServerSocket(port);
            socket = serverSocket.accept();
            input = new DataInputStream(socket.getInputStream());
            while(true){
                System.out.println(input.readUTF());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
