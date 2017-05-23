package UE23_Server_Client;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * UE23_Server_Client
 * Created by Klarissa Wimmer
 * am Montag den 08.Mai.2017
 */
public class Server {

    public static void main(String[] args) {
        new Server().init();
    }

    public void init(){
        try (ServerSocket server = new ServerSocket(10023)){
            System.out.println("Echo-Server bereit");

            while(true) {
                    Socket verbindung = server.accept();
                    Clienthandler Client = new Clienthandler(this, verbindung);
                    Client.start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
