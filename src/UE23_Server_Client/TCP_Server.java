package UE23_Server_Client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
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
    public static Map<String, Socket> spitzname = new HashMap<>();


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
        String sender = spitzname.entrySet().stream().filter(e -> e.getValue() == s).map(e -> e.getKey()).findFirst().orElse("");
        for(Map.Entry<String, Socket> e : spitzname.entrySet()){
            System.out.println(e.getValue() + " " + (e.getValue() == s));
            if (e.getKey() != sender) TCP_Client.WriteLine( msg, sender, e.getValue());
        }
    }

    public static void abmelden(String n, Socket s) throws IOException {
        s.close();
        spitzname.remove(n,s);
        System.out.println(spitzname);

    }
    public static String userAusgeben(){
        for(Map.Entry e : spitzname.entrySet()) {
            return String.valueOf(e.getKey());
        }
        return "keine User vorhanden";
    }
}