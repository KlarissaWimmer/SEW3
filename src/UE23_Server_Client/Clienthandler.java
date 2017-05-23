package UE23_Server_Client;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;

import static UE23_Server_Client.TrivialEchoServer.ECHOS;

/**
 * UE23_Server_Client
 * Created by Klarissa Wimmer
 * am Montag den 08.Mai.2017
 */
public class Clienthandler extends Thread{
    public Server server;
    public Socket socket;

    public Clienthandler(Server se, Socket so) {
        server = se;
        socket = so;
    }

    @Override
    public void run(){
        super.run();

        Writer w = null;
        try {
            w = new OutputStreamWriter(socket.getOutputStream(), Charset.forName("ISO-8859-1"));
            BufferedWriter bw = new BufferedWriter(w);

            Reader r = new InputStreamReader(socket.getInputStream());
            BufferedReader br = new BufferedReader(r);

                System.out.println("Verbindung angenommen von " + socket.getRemoteSocketAddress());

                while (true) {
                    // Eingabeaufforderung senden und Zeile einlesen
                    bw.write("Bitte rufen (Lerzeile zum Beenden):\r\n");
                    bw.flush();

                    String zeile = br.readLine();
                    if (zeile == null || zeile.trim().isEmpty()) {
                        break;
                    }

                    // Zeile dreimal zurücksenden
                    zeile += "\r\n";
                    bw.write(zeile);
                    bw.flush();

                    for (int i = 0; i < ECHOS-1; i++) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {}

                        bw.write(zeile);
                        bw.flush();
                    }
                }

                System.out.println("Verbindung beendet mit " + socket.getRemoteSocketAddress());
    } catch (IOException e) {
            e.printStackTrace();
        }
    }
}