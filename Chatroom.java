import java.util.StringTokenizer;

public class Chatroom {
    Client client;
    Server server;

    public Chatroom(String... args){
        String IP = null;
        int PORT = 0;
        if(args.length > 0){
            switch(args[0])
            {
                //case "-c":
                case "--client":
                    if(args.length > 2)
                    {
                        //StringTokenizer st = new StringTokenizer(args[1], ":");
                        IP = args[1];
                        PORT = Integer.valueOf(args[2]);
                        //new Client
                        try
                        {
                            new Client(IP, PORT);
                        }
                        catch(Exception uhe)
                        {
                            System.out.println(IP + ":" + PORT + " host not found...");
                            System.exit(1);
                        }
                    }
                    else
                    {
                        System.out.println("OOF");
                    }
                break;
                //case "-s":
                case "--server":
                    if(args.length > 1)
                    {
                        try
                        {
                            PORT = Integer.valueOf(args[1]);
                            new Server(PORT);
                        }catch(Exception e){e.printStackTrace();}
                    }
                    else
                    {
                        System.out.println("Something is missing, try -help");
                    }
                break;
            }
        }
    }

    public static void main(String... args){
        new Chatroom(args);
    }
}
