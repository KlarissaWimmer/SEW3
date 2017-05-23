package UE23_Server_Client;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.TreeMap;

/**
 * UE23_Server_Client
 * Created by Klarissa Wimmer
 * am Montag den 15.Mai.2017
 */
public class TCP_Server {

    public static void main(String[] args) {
        new TCP_Server().init();
    }
    public static Map<String, Socket> spitzname = new TreeMap<>();

    public void init() {
        try (ServerSocket server = new ServerSocket(50505)) {
            System.out.println("Echo-Server ist bereit!");

            while (true) {
                Socket verbindung = server.accept();
                TCP_Client Client = new TCP_Client(this, verbindung);
                Client.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean anmelden(String Client, Socket verbindung) throws IOException {
        if (spitzname.containsKey(Client)) {
            return false;
        } else {
            spitzname.put(Client, verbindung);
            return true;
        }
    }
    public static void NachrichtVerteilen(String msg, Socket s) throws IOException {
        String r = "";
        for(Map.Entry e : spitzname.entrySet()){
            if(e.getValue() == s) r = String.valueOf(e.getKey());
        }
        int i = 0;
        while(!r.equals(spitzname.get(i))){
            i++;
            TCP_Client.WriteLine(msg, r);
            }
        }
    }
}