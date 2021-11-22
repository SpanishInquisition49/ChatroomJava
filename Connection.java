import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Connection {
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;
    private String username;

    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        input = new DataInputStream(socket.getInputStream());
        output = new DataOutputStream(socket.getOutputStream());
        this.username = input.readUTF();
    }

    public Socket getSocket() {
        return socket;
    }

    public DataInputStream getInput() {
        return input;
    }

    public DataOutputStream getOutput() {
        return output;
    }

    public String getUsername() {
        return username;
    }
}

