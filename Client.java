// Creazione Socket, Trasferimento/Ricezione del File
import java.net.Socket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
//import java.net.ServerSocket;
import java.io.DataInputStream;
import java.io.DataOutputStream;

// Strumenti per Input e controllo 
import java.util.Scanner;
import java.util.StringTokenizer;


// Gestione Errori
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;

public final class Client {
    Scanner keyboard;
    Socket socket;
    DataInputStream input;
    DataOutputStream output;

    public Client(String IP, int port){
        try {
            socket = new Socket(InetAddress.getByName(IP), port);
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        keyboard = new Scanner(System.in);
        String msg;
        while(true){
            msg = keyboard.nextLine();
            try {
                output.writeUTF(msg);
                System.out.println(input.readUTF());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}