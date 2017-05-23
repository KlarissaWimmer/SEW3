package UE23_Server_Client;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * PACKAGE_NAME
 * Created by Klarissa Wimmer
 * am Montag den 08.Mai.2017
 */
public class HelloWorldServer {

    public static void main(String[] args){

        //Server auf Port 10023 horchen lassen
        try (ServerSocket server = new ServerSocket(10023)){
            System.out.println("Server bereit");

            //Auf ankommende Verbindungen warten, accept() blockiert so lange
            Socket verbindung = server.accept();
            System.out.println("Verbindung aufgenommen");

            //Zum senden eines Texts wird ein Writer benötigt --
            //diesen über den OutputStream stülpen
            Writer w = new OutputStreamWriter(verbindung.getOutputStream());

            //Nachricht senden
            w.append("Hallo Welt!\n");

            //Verbindung beenden
            w.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
